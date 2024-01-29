import React, { useState, useEffect } from 'react';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';
import { NotificationManager, NotificationContainer } from 'react-notifications';
import 'react-notifications/lib/notifications.css';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './CSS/doctorlogin.css';
import LoadingBar from 'react-top-loading-bar'

export default function ViewOrders() {
  const [progress, setProgress] = useState(0)
  const navigate = useNavigate();
  const storedData = localStorage.getItem("userData");
  const parsedData = JSON.parse(storedData);

  const emailVerification=localStorage.getItem('emailVerification');
 
  const token = localStorage.getItem("JwtToken");

    
    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

  const[data,setData]=useState([]);
  

  const handleOrderDeleteClick = (item) => () => {
    if (item.status) {
      NotificationManager.error('', 'Verified order cannot be deleted!', 4000);
      return;
    }

    confirmAlert({
      title: "Confirm Delete Order",
      message: "Are you sure to do this.",
      buttons: [
        {
          label: "Yes",
          onClick: () =>{
            axiosInstance
            .delete("http://localhost:9091/doctor/deleteOrder/" + item.orderId)
            .then(function (response) {
              NotificationManager.success('Order deleted successfully!');
              console.log(response.data);
              axiosInstance
              .get("http://localhost:9091/doctor/viewAllOrders/"+localStorage.getItem("Doctorid"))
              .then(function (response) {
                console.log(response.data);
                setData(response.data);
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
  
   
  };

  
  

  useEffect(() => {
    setProgress(10)
    setProgress(50)

    axiosInstance
      .get("http://localhost:9091/doctor/viewAllOrders/"+localStorage.getItem("Doctorid"))
      .then(function (response) {
        console.log(response.data);
        setData(response.data);
        setProgress(100)
      })
      .catch(function (error) {
        console.log(error);
        setProgress(100)
      });

  },[])

  const handelSendOtp=()=>{
    console.log("Hii otp will send ");
    axiosInstance
                .post(
                  "http://localhost:9091/doctor/sendOpt/"+parsedData.email
                )
                  
                  .then(function (response) {
                    
                    console.log(response.data);
                    
                  })
                  .catch(function (error) {
                    console.log(error);
                    
                  });




  }

  if(emailVerification=="false" || emailVerification==null)
  {
   return (
     <div>
       
 <div>
      <LoadingBar
        color='#f11946'
        progress={progress}
        onLoaderFinished={() => setProgress(0)}
      />
      <NotificationContainer/>
        {/* Side bar */}
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
              <span className="fs-4">Hii, Dr {parsedData?.name}</span>
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
        <div className="alert alert-warning text-center mx-5" role="alert">
  <h4 className="alert-heading">Email verification required !</h4>

  <hr />
  <h6 className="mb-0 text-center ">
    <br />
  It seems you haven't verified your email address yet. To complete the process

  </h6>
  <iframe
          src="https://lottie.host/embed/7585df2c-b0c0-4aa1-a90b-c5023d24faad/F6ImG2607f.json"
          
        ></iframe>

  <h6 onClick={handelSendOtp}>
  Please click <Link to="/otpVerification">here</Link>. to verify your ID.

  </h6>
</div>
       

    </div>
     
    </div>






 </div>
   )
  
  
  }


  
  return (
    <div>
      <LoadingBar
        color='#f11946'
        progress={progress}
        onLoaderFinished={() => setProgress(0)}
      />
      <NotificationContainer/>
        {/* Side bar */}
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
              <span className="fs-4">Hii, Dr {parsedData?.name}</span>
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
        <div className="d-flex flex-wrap justify-content-around ">
        {data.length
  ? data.map((item, index) => (
      <div
        className="card mx-4 my-2 order-card"
        style={{
          width: "25rem",
          backgroundColor: `${item.status ? "#AAF4B0" : "#F8F8F8"}`,
        }}
        key={index}
      >
        <div className="card-body">
              <h5 className="card-title">🆔 Order Id:{item.orderId}</h5>
              <hr/>
              <h6 className="card-subtitle mb-2 text-muted">💊Order Details: {item.drugName+" with Quantity "+item.quantity}</h6>
              <h6 className="card-subtitle mb-2 text-muted my-1">📅Order Date: {item.orderDate}</h6>
              <h6 className="card-subtitle mb-2 text-muted my-1">✅Verification status:{item.status?" Verified":" Pending..."}</h6>
              

              <h6 className="card-subtitle mb-2 text-muted my-1">🏠Delivery address:</h6>
              
              <p className="card-text">{item.address}</p>
              
              <button type="button" className="btn btn-danger" onClick={handleOrderDeleteClick(item)}>🗑️ Delete</button>
            </div>
      </div>
    )).reverse()
  : <div style={{ marginLeft: "450px" }}>
      <img
        src={require("../Assets/empty-box.png")}
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
