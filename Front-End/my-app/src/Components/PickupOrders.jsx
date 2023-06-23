import React, { useState,useEffect } from 'react'
import { Link } from 'react-router-dom';
import axios  from 'axios';
import { useNavigate } from 'react-router-dom';
export default function PickupOrders({onDataReceived}) {
   const navigate = useNavigate();
    const token = localStorage.getItem("JwtToken");
    const[data,setData]=useState([]);
    let date = new Date().toJSON();

    
    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    const storedData = localStorage.getItem("userData");
    const parsedData = JSON.parse(storedData);
    const expire="bg-danger text-light";

    useEffect(() => { 

      

      axiosInstance
      .get("http://localhost:9091/doctor/viewAllPickups/"+localStorage.getItem("Doctorid"))
      .then(function (response) {
        console.log(response.data);
        setData(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });



     },[])

     const handelPayNow=(item)=>()=>{
      
      onDataReceived( item );
      
      navigate("/checkout")


     }


  return (
    <div>
       <div className="my-5 mx-4 d-flex">
       <div>
          <div
            className="d-flex flex-column flex-shrink-0 p-3 bg-light"
            style={{ width: "280px" }}
          >
            <Link
              to={"/doctorDashboard"}
              className="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none"
            >
              <img
                className="bi me-2"
                width="40"
                height="32"
                src={require("../Assets/menu.png")}
              ></img>
              <span className="fs-4">Hii, Dr {parsedData.name}</span>
            </Link>
            <hr />
            <ul className="nav nav-pills flex-column mb-auto">
              <li>
                <Link
                  to={"/doctorDashboard"}
                  className="nav-link link-dark"
                  style={{
                    backgroundColor: "initial",
                    transition: "background-color 0.3s",
                  }}
                  onMouseOver={(e) => {
                    e.target.style.backgroundColor = "violet";
                  }}
                  onMouseLeave={(e) => {
                    e.target.style.backgroundColor = "initial";
                  }}
                >
                  Dashboard
                </Link>
              </li>

              <li className="sidebar">
                <Link
                  to={"/viewOrders"}
                  className="nav-link link-dark"
                  style={{
                    backgroundColor: "initial",
                    transition: "background-color 0.3s",
                  }}
                  onMouseOver={(e) => {
                    e.target.style.backgroundColor = "orchid";
                  }}
                  onMouseLeave={(e) => {
                    e.target.style.backgroundColor = "initial";
                  }}
                >
                  Orders
                </Link>
              </li>
              

              <li className="sidebar">
                <Link
                  to={"/viewDrugs"}
                  className="nav-link link-dark"
                  style={{
                    backgroundColor: "initial",
                    transition: "background-color 0.3s",
                  }}
                  onMouseOver={(e) => {
                    e.target.style.backgroundColor = "orchid";
                  }}
                  onMouseLeave={(e) => {
                    e.target.style.backgroundColor = "initial";
                  }}
                >
                  Drugs
                </Link>
              </li>
              <li className='sidebar'>
  <Link
    to={"/pickupOrder"}
    className="nav-link link-dark"
    style={{
      backgroundColor: 'initial',
      transition: 'background-color 0.3s',
    }}
    onMouseOver={(e) => {
      e.target.style.backgroundColor = 'orchid';
    }}
    onMouseLeave={(e) => {
      e.target.style.backgroundColor = 'initial';
    }}
  >
    Pickup
  </Link>
</li>

              <li className="sidebar">
                <Link
                  to={"/doctorDashboard"}
                  className="nav-link link-dark"
                  style={{
                    backgroundColor: "initial",
                    transition: "background-color 0.3s",
                  }}
                  onMouseOver={(e) => {
                    e.target.style.backgroundColor = "orchid";
                  }}
                  onMouseLeave={(e) => {
                    e.target.style.backgroundColor = "initial";
                  }}
                >
                  Contact Admin
                </Link>
              </li>
            </ul>
            <hr />
          </div>
        </div>
        {
           data.length?data.map((item) => (
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
               <button type="button" class="btn btn-success" disabled={(item.pickupdate<date) || (item.totalBill-item.moneyPaid===0)} onClick={handelPayNow(item)}>Pay Now</button>




    
              
              
            </div>
          </div>
          )):
          <div style={{marginLeft:"450px"}}> 
            <img src={require("../Assets/empty-box.png")} alt="no data found" height="200px" width="200px" />
            <h5 style={{marginLeft:"30px"}}> No data found!</h5>
            
          </div>
        }





       </div>
    </div>
  )
}
