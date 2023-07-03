import React from "react";
import SideAdminRest from "./SideAdminRest";
import { useEffect } from "react";
import { useState } from "react";
import axios from "axios";
import Popup from "reactjs-popup";
import { NotificationManager} from 'react-notifications';
import { NotificationContainer} from 'react-notifications';
import { useNavigate } from 'react-router-dom';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';

import "reactjs-popup/dist/index.css";
export default function DrugInventory() {
    const navigate = useNavigate();
    const token = localStorage.getItem("JwtToken");
  //Seting the token
  const axiosInstance = axios.create({
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  
  const [searchTerm, setSearchTerm] = useState("");
  const [data, setdata] = useState([]);
  const [email,setEmail]=useState(null);
  const [drugName,setDrugname]=useState(null);
  const [quantity,setQuantity]=useState(null);
  const [batchId,Setbatchid]=useState(null);
  const [price,setprice]=useState(null);
  const [date,setdate]=useState(null);
  const [selectedDate, setSelectedDate] = useState('');

  // Get the current date in the format yyyy-mm-dd
  const currentDate = new Date().toISOString().split('T')[0];

  

  const handelEmailChange=(e)=>{
    setEmail(e.target.value)

  }
  const handelNameChange=(e)=>{
    setDrugname(e.target.value)

  }
  const handelQuantityChange=(e)=>{
    setQuantity(e.target.value)

  }
  const handelBatchidChange=(e)=>{
    Setbatchid(e.target.value)

  }
  const handelPriceChange=(e)=>{
    setprice(e.target.value)

  }
  const handelDateChange=(e)=>{
    console.log(e.target.value)
    setdate(e.target.value)

  }

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    
  };

  const handleBackspace = (e) => {
    if (e.key === 'Backspace') {
        axiosInstance
        .get("http://localhost:9091/adminOprations/getAllStock")
        .then(function (response) {
          setdata(response.data);
        })
        .catch(function (error) {
          console.log(error);
        });
    }
  };

  const handelUpdate=(Iteam)=>()=>{
   if(email===null || drugName==null || quantity===null || batchId===null || price===null || date===null)
   {
    console.log("Please select");
    NotificationManager.error('', 'Enter Valid Data!',1000);
   }
   else{
    
    axiosInstance
    .put("http://localhost:9091/adminOprations/updateStock",{
        id: Iteam.id,
        supplierEmailId: email,
        drugName: drugName,
        quantity: quantity,
        batchId: batchId,
        price: price,
        expireDate: date
        
    })
    .then(function (response) {
         console.log(response.data)
         NotificationManager.success('', 'Drug data updated',1000);
         //Making usestate null again
         setEmail(null);
  setDrugname(null);
  setQuantity(null);
  Setbatchid(null);
  setprice(null);
  setdate(null);
         axiosInstance
         .get("http://localhost:9091/adminOprations/getAllStock")

         .then(function (response) {
           setdata(response.data);
         })
         .catch(function (error) {
           console.log(error);
         });
         

         
      })
      .catch(function (error) {
        console.log(error);
      });}
    

  }

  const handleSearchSubmit=(e)=>{
    e.preventDefault();
    axiosInstance
         .get("http://localhost:9091/adminOprations/getByDrugName/"+searchTerm)
         .then(function (response) {
           setdata(response.data);
         })
         .catch(function (error) {
           console.log(error);
           NotificationManager.warning('', 'Drug not found !', 3000);
         });


  }

  const handelDelete=(id)=>()=>{
    confirmAlert({
        title: "Confirm Delete Order",
        message: "Are you sure to do this.",
        buttons: [
          {
            label: "Yes",
            onClick: () =>{
              axiosInstance
              .delete("http://localhost:9091/adminOprations/deleteStock/" + id)
              .then(function (response) {
                NotificationManager.success('Drug deleted successfully!');
                console.log(response.data);
                axiosInstance
         .get("http://localhost:9091/adminOprations/getAllStock")
         .then(function (response) {
           setdata(response.data);
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

  const handelSave=() => {
     console.log(drugName)
     if(email===null || drugName==null || quantity===null || batchId===null || price===null || date===null)
     {
      console.log("Please select");
      NotificationManager.error('', 'Enter Valid Data!',1000);
      return;
     }
     axiosInstance
     .post("http://localhost:9091/adminOprations/createStock",{
         
         supplierEmailId: email,
         drugName: drugName,
         quantity: quantity,
         batchId: batchId,
         price: price,
         expireDate: date
         
     })
     .then(function (response) {
          console.log(response.data)
          NotificationManager.success('', 'Drug Added',1000);
          //Making usestate null again
          setEmail(null);
   setDrugname(null);
   setQuantity(null);
   Setbatchid(null);
   setprice(null);
   setdate(null);
          axiosInstance
          .get("http://localhost:9091/adminOprations/getAllStock")
 
          .then(function (response) {
            setdata(response.data);
          })
          .catch(function (error) {
            console.log(error);
          });
          
 
          
       })
       .catch(function (error) {
         console.log(error);
       });



  }










  

  useEffect(() => {
    axiosInstance
      .get("http://localhost:9091/adminOprations/getAllStock")
      .then(function (response) {
        setdata(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);
  return (
    <div>
         <NotificationContainer/>
           {/* SearchBar */}
           
      <div className="row height d-flex justify-content-center align-items-center" >
        <div className="col-md-8">
          <div className="search">
            <i className="fa fa-search"></i>
            <input
  type="text"
  className="form-control"
  placeholder="Search Drugs.."
  onChange={handleSearchChange}
  onKeyDown={handleBackspace}
/>
            <button className="btn btn-primary" onClick={handleSearchSubmit}>
              Search
            </button>
          </div>
        </div>
      </div>
      <div className="my-5 mx-4 d-flex">
        <SideAdminRest />
        <Popup
        trigger={ <button class="btn btn-primary mx-1" type="submit" style={{height:"40px"}}>✏️Add</button>
                          }
                          position="right center"
                        >
                             <div
                            className="card my-2 container"
                            style={{ width: "1040px", height: "140px" }}
                          >
                            <div className="row my-1">
                              <div className="col">
                                <b>Supplier Email:</b>
                                <input
                                  type="email"
                                  className="form-control"
                                  placeholder=""
                                  onChange={handelEmailChange}
                                />
                                <button onClick={handelSave}>Save</button>
                              </div>
                              <div className="col">
                                <b>Drug Name:</b>
                                <input
                                  type="text"
                                  className="form-control"
                                  placeholder=""
                                  onChange={handelNameChange}
                                />
                              </div>
                              <div className="col">
                                <b>Quantity:</b>
                                <input
                                  type="number"
                                  className="form-control"
                                  placeholder=""
                                  onChange={handelQuantityChange}
                                />
                              </div>
                              <div className="col">
                                <b>Batch ID:</b>
                                <input
                                  type="text"
                                  className="form-control"
                                  placeholder=""
                                  onChange={handelBatchidChange}
                                />
                              </div>
                              <div className="col">
                                <b>Price:</b>
                                <input
                                  type="number"
                                  className="form-control"
                                  placeholder=""
                                  onChange={handelPriceChange}
                                />
                              </div>
                              <div className="col">
                                <b>Expire Date:</b>
                                <input type="date" className="form-control" onChange={handelDateChange} min={currentDate}/>
                                
                              </div>
                            </div>
                          </div>

                        </Popup>
        
        <div className="d-flex flex-wrap justify-content-around">
        
          {data.map((iteam) => {
            return (
              <div
                className="card my-2"
                style={{ width: "1000px", height: "220px",backgroundColor:`${iteam.quantity<=0?"#F9E79F":iteam.status==="Expired"?"#F46D6D":"white"}`}}
              >
                <div className="card-header">ID:{iteam.id}</div>
                <div className="card-body">
                  <table className="table">
                    <thead>
                      <tr>
                        <th>Supplier Email</th>
                        <th>Drug Name</th>
                        <th>Quantity</th>
                        <th>Batch ID</th>
                        <th>Price</th>
                        <th>Expire Date</th>
                        <th>Status</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>{iteam.supplierEmailId}</td>
                        <td>{iteam.drugName}</td>
                        <td>{iteam.quantity}</td>
                        <td>{iteam.batchId}</td>
                        <td>{iteam.price}</td>
                        <td>{iteam.expireDate}</td>
                        <td>{iteam.status}</td>
                      </tr>
                      <div
                        className="btn-group"
                        role="group"
                        aria-label="Basic example"
                      >
                        <Popup
                          trigger={
                            <button type="button" className="btn btn-secondary">
                              Edit
                            </button>
                          }
                          position="right center"
                        >
                          <div
                            className="card my-2 container"
                            style={{ width: "1040px", height: "140px" }}
                          >
                            <div className="row my-1">
                              <div className="col">
                                <b>Supplier Email:</b>
                                <input
                                  type="email"
                                  className="form-control"
                                  placeholder={iteam.supplierEmailId}
                                  onChange={handelEmailChange}
                                />
                                <button onClick={handelUpdate(iteam)}>Save</button>
                              </div>
                              <div className="col">
                                <b>Drug Name:</b>
                                <input
                                  type="text"
                                  className="form-control"
                                  placeholder={iteam.drugName}
                                  onChange={handelNameChange}
                                />
                              </div>
                              <div className="col">
                                <b>Quantity:</b>
                                <input
                                  type="number"
                                  className="form-control"
                                  placeholder={iteam.quantity}
                                  onChange={handelQuantityChange}
                                />
                              </div>
                              <div className="col">
                                <b>Batch ID:</b>
                                <input
                                  type="text"
                                  className="form-control"
                                  placeholder={iteam.batchId}
                                  onChange={handelBatchidChange}
                                />
                              </div>
                              <div className="col">
                                <b>Price:</b>
                                <input
                                  type="number"
                                  className="form-control"
                                  placeholder={iteam.price}
                                  onChange={handelPriceChange}
                                />
                              </div>
                              <div className="col">
                                <b>Expire Date:</b>
                                <input type="date" className="form-control" onChange={handelDateChange} min={currentDate}/>
                                
                              </div>
                            </div>
                          </div>
                        </Popup>

                        <button type="button" className="btn btn-danger" onClick={handelDelete(iteam.id)}>
                          Delete
                        </button>
                      </div>
                    </tbody>
                  </table>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}
