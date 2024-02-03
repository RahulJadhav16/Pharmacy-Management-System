import React, { useEffect,useState } from 'react'
import SideAdminRest from './SideAdminRest'
import axios from 'axios';
import RingLoader from "react-spinners/RingLoader";
import { NotificationManager} from 'react-notifications';
import { NotificationContainer} from 'react-notifications';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';
import LoadingBar from 'react-top-loading-bar'

export default function AdminViewOrders() {
  const [progress, setProgress] = useState(0)
    const[loader,setLoader]=useState(false);
    const [data, setdata] = useState([]);
    const token = localStorage.getItem("JwtToken");
  //Seting the token
  const axiosInstance = axios.create({
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

    useEffect(()=>{
      setProgress(10)
        axiosInstance
        .get("http://localhost:9091/adminOprations/allOrders")
        .then(function (response) {
          console.log(response.data)
         
         setdata(response.data);
         setProgress(100)
          
        })
        .catch(function (error) {
          console.log(error);
          setProgress(100)
        });
       


    },[])


    const handelverifyOrder=(iteam)=>{
      setProgress(25)
        console.log("Before verification")
        console.log(iteam)
        setLoader(true)
        axiosInstance
        .put("http://localhost:9091/adminOprations/verifyOrder",{
            orderId: iteam.orderId,
            doctorId: iteam.doctorId,
            doctorName: iteam.doctorName,
            email: iteam.email,
            address: iteam.address,
            drugName: iteam.drugName,
            quantity: iteam.quantity,
            status: false,
            orderDate: iteam.orderDate
        })
        .then(function (response) {
          
          console.log(response.data)
          axiosInstance
        .get("http://localhost:9091/adminOprations/allOrders")
        .then(function (response) {
          setdata(response.data);
          console.log(response.data)
          setLoader(false)
          NotificationManager.success('', 'Order has been verified!',4000);
          NotificationManager.success('', `Verification Mail Sent to ${iteam.email}`,4000);
          setProgress(100)
        })
        .catch(function (error) {
          console.log(error);
          setLoader(false)
          setProgress(100)
        });

        })
        .catch(function (error) {
          console.log(error);
          setLoader(false)
          NotificationManager.error('', `Verification Failed!`,4000);
          NotificationManager.warning('', `Either quantity not available for drug stock is expired!`,4000);
          setProgress(100)
        });

    }

    const handelDeleteOrder=(id)=>{
      setProgress(10)
      confirmAlert({
        title: "Confirm Delete Order",
        message: "Are you sure to do this.",
        buttons: [
          {
            label: "Yes",
            onClick: () =>{
      axiosInstance
      .delete("http://localhost:9091/adminOprations/deleteOrder/"+id)
      .then(function (response) {
        console.log(response.data)
        NotificationManager.success('', 'Order has been deleted!',1000);
        axiosInstance
        .get("http://localhost:9091/adminOprations/allOrders")
        .then(function (response) {
          console.log(response.data)
         
         setdata(response.data);
         setProgress(100)
          
        })
        .catch(function (error) {
          console.log(error);
          setProgress(100)
        });
        
        


      })
      .catch(function (error) {
        console.log(error);
        setProgress(100)
      });
    }
  },
  {
    label: "No",
    onClick: ()=>{setProgress(100)}
    
    
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
        <div className="d-flex flex-wrap justify-content-around ">
        {loader?
        <div className="my-3" style={{marginLeft:"450px"}}>
        <RingLoader
        color={"green"}
        loading={loader}
    
        size={150}
        aria-label="Loading Spinner"
        data-testid="loader" /><h5>Verifying Order...</h5></div>:
        
    
        data.length
         ? data.map((item, index) => (
      <div
        className="card mx-4 my-2 order-card"
        style={{
          width: "30rem",
          backgroundColor: `${item.status ? "#AAF4B0" : "#F8F8F8"}`,
        }}
        key={index}
      >
        <div className="card-body flex-wrap">
              <h5 className="card-title">🆔 Order Id:{item.orderId}</h5>
              <hr/>
              <h6 className="card-subtitle mb-2 text-muted">👨‍⚕️Name: Dr.{item.doctorName}</h6>
              <h6 className="card-subtitle mb-2 text-muted">📧EmailId:{item.email}</h6>
              <hr />
              <h6 className="card-subtitle mb-2 text-muted">💊Order Details: {item.drugName+" with Quantity "+item.quantity}</h6>
              <h6 className="card-subtitle mb-2 text-muted my-1">📅Order Date: {item.orderDate}</h6>
              <h6 className="card-subtitle mb-2 text-muted my-1">✅Verification status:{item.status?" Verified":" Pending..."}</h6>
              

              <h6 className="card-subtitle mb-2 text-muted my-1">🏠Delivery address:</h6>
              
              <p className="card-text">{item.address}</p>
              <div className="btn-group" role="group" aria-label="Basic example">
             <button type="button" className="btn btn-secondary" onClick={()=>handelverifyOrder(item)}disabled={item.status}> ✅Verify Order </button>
  
             <button type="button" className="btn btn-secondary" style={{marginLeft:"160px"}} disabled={item.status} onClick={()=>handelDeleteOrder(item.orderId)}>❌Delete</button>
            </div>
              
            </div>
      </div>
    )).reverse()
  : <div style={{ marginLeft: "450px" }}>
      <img
        src={require("./Assets/empty-box.png")}
        alt="no data found"
        height="200px"
        width="200px"
      />
      <h5 style={{ marginLeft: "30px" }}> No data found!</h5>
    </div>

}
          
          



        </div>
      
      </div>
    </div>
  )
}
