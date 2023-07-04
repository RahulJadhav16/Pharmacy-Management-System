import React, { useEffect,useState } from 'react'
import SideAdminRest from './SideAdminRest'
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';
import { NotificationManager, NotificationContainer } from 'react-notifications';
import 'react-notifications/lib/notifications.css';
import axios from 'axios';
import LoadingBar from 'react-top-loading-bar'
export default function Pickup() {
  const [progress, setProgress] = useState(0)
    let date = new Date().toJSON();
    const expire="bg-danger text-light";
    const token = localStorage.getItem("JwtToken");
    const[data,setData]=useState([]);
    const[info,setInfo]=useState([]);
    const axiosInstance = axios.create({
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      useEffect(()=>{
        setProgress(10)
        axiosInstance
        .get("http://localhost:9091/adminOprations/getAllPickups")

        .then(function (response) {
           setData(response.data);
        console.log(response.data);
        setProgress(100)
        })
        .catch(function (error) {
          console.log(error);
          setProgress(100)
        });
    
      },[])

      const handelMoreInfo = (item) => {
        axiosInstance
          .get("http://localhost:9091/adminOprations/getPaymentDetailsByOrderid/" + item.pickupId)
          .then(function (response) {
            console.log(response.data);
            setInfo(response.data);
            
          })
          .catch(function (error) {
            console.log(error);
          });
      };

    const handelDelete=(item)=>()=>{
        
        confirmAlert({
            title: "Confirm Delete Order",
            message: "Are you sure to do this.",
            buttons: [
              {
                label: "Yes",
                onClick: () =>{
                  axiosInstance
                  .delete("http://localhost:9091/pickupAdmin/deletePickup/" + item.pickupId)
                  .then(function (response) {
                    NotificationManager.success('Order deleted successfully!');
                    axiosInstance
                    .get("http://localhost:9091/adminOprations/getAllPickups")
            
                    .then(function (response) {
                       setData(response.data);
                    console.log(response.data);
                    })
                    .catch(function (error) {
                      console.log(error);
                    });
                    
                    
                  })
                  .catch(function (error) {
                    
                    console.log(error);
                    
                  });
      
                }
              },
              {
                label: "No"
                
              }
            ]
          });


    }
      
  return (
    <div>
       <LoadingBar
        color='#f11946'
        progress={progress}
        onLoaderFinished={() => setProgress(0)}
      />
        <NotificationContainer/>
       <div className="my-5 mx-4 d-flex">
        <SideAdminRest />
        
        {data.length?data.map((item)=>{
            return(
                <div className={`card mx-4 my-2 order-card ${item.pickupdate<date?expire:""}`} style={{width: "25rem" }}>
                <div className="card-body">
                  <h5 className="card-title">🆔 Order Id:{item.pickupId}</h5>
                  <hr/>
                  <h6 className="card-subtitle mb-2 text-muted my-1">💰Total Bill: {item.totalBill}</h6>
                  <h6 className="card-subtitle mb-2 text-muted my-1">💰Bill Paid: {item.moneyPaid}</h6>
                  <h6 className="card-subtitle mb-2 text-muted my-1">📅Last Date for payment: {item.pickupdate}</h6>
                  <h6 className="card-subtitle mb-2 text-muted my-1">📅Order verification date: {item.orders[0].orderDate}</h6>
                  <h6 className="card-subtitle mb-2 text-muted my-1">✅Payment Status:{item.paymentStatus?" Paid":" Pending..."}</h6>
                  <h6 className="card-subtitle mb-2 text-muted my-1">💊 Order Details: </h6>
                  <div className='my-1 mx-2'>
                  {item.orders.map((e)=>{
                    return (
                      <li className="card-subtitle mb-2 text-muted">{e.drugName + " with Quantity " + e.quantity}</li>
                    );
                    
                     
                   })}
                   </div>
    
                  <h6 className="card-subtitle mb-2 text-muted my-1">🏠Delivery address:</h6>
                   <p className="card-text">{item.orders[0].address}</p>
                   <hr />
                   <div className="btn-group" role="group" aria-label="Basic example">
                   {/* <button type="button" className="btn btn-secondary" onClick={() => handelMoreInfo(item)}>ℹ️ More Info</button> */}
                   <Popup trigger=
                    {<button type="button" className="btn btn-secondary">ℹ️ More Info</button>}
                    onOpen={() => handelMoreInfo(item)} 
                  position="top left">
                     {close => (
                    <div className="">
                        {info.length?info.map((e)=>{
                            return(
                                <div className="card my-1" style={{width: "400px",backgroundColor:"#D1F2EB"}}>
                                <div className="card-body">
                                  <h5 className="card-title">PaymentId:{e.paymentId}</h5>
                                  <h6 className="card-subtitle mb-2 text-muted">Doctor Mail:{e.doctorMail}</h6>
                                  <p className="card-text">order Id:{e.orderId}</p>
                                  <p className="card-text">Payment Date:{e.paymentDate}</p>
                                  <h6 className="card-subtitle mb-2 ">Amount Paid:₹{e.amountPaid}</h6>
                                  
                                </div>
                              </div>
                            
                            )
                        }):<p style={{color:"red"}}>no data found!</p>}
            
                   <a className="close" onClick={close}>
          &times;
        </a>
                   </div>)}
                  
      
                 </Popup>
            
  
                   
                   
                   
                    <button type="button" className="btn btn-secondary mx-1" onClick={handelDelete(item)} disabled={item.paymentStatus}>❌Delete</button> 
                   


                   </div>
                   

                  
    
    
    
    
        
                  
                  
                </div>
              </div>
            )
        }):<div style={{ marginLeft: "450px" }}>
        <img
          src={require("./Assets/empty-box.png")}
          alt="no data found"
          height="200px"
          width="200px"
        />
        <h5 style={{ marginLeft: "30px" }}> No data found!</h5>
      </div>}

        </div>
    </div>
  )
}
