import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import axios  from 'axios';
import { useNavigate } from 'react-router-dom';
import { NotificationManager} from 'react-notifications';
import { NotificationContainer} from 'react-notifications';
import 'react-notifications/lib/notifications.css';

const App = () => {
  const [otp, setOtp] = useState('');
  const [timer, setTimer] = useState(60);
  const [isTimerRunning, setIsTimerRunning] = useState(false);
  const navigate = useNavigate();

  const storedData = localStorage.getItem("userData");
  const [path, setPath] = useState(localStorage?.getItem("profilePath"));
  const parsedData = JSON.parse(storedData);
  const token = localStorage.getItem("JwtToken");

  
  const axiosInstance = axios.create({
    headers: {
      Authorization: `Bearer ${token}`, // Adding the token to the 'Authorization' header
    },
  });

 





  const handleResend = () => {
    console.log("Send otp");
    axiosInstance
                .post(
                  "http://localhost:9091/doctor/sendOpt/"+parsedData.email
                )
                  
                  .then(function (response) {
                    
                    console.log(response.data);
                    NotificationManager.success('Otp has been sent to your respective email', 'Success');
                    
                  })
                  .catch(function (error) {
                    console.log(error);
                    
                  });


   
  };

  const handleVerify = () => {
    
    console.log('Verifying OTP:', otp);

    let setOpt=otp;

    const date=new Date();

    axiosInstance
                .post(
                  "http://localhost:9091/doctor/VerifyOtp",{
                    mailId:parsedData.email,
                    otp:setOpt,
                    dateOfVerification:date


                  }
                  
                )
                  
                  .then(function (response) {
                    console.log(response.data);
                    if(response.data==="Otp verification done!!!!!!")
                    {
                      //Updating the email Satus to the backend
                      axiosInstance.post("http://localhost:9091/doctor/setDoctorVetificationStatus",{
                        id:parsedData.id,
                        emailVerified:true
                      })
                      .then((response)=>{
                        console.log(response.data)

                      })
                      .catch((error)=>{
                        console.log(response.data);
                      })








                        NotificationManager.success('otp verified successfully!, Wait while we redirect you to dashboard', 'Success');
                        axiosInstance
      .get(
        "http://localhost:9091/doctor/getDoctorId/" +
          localStorage.getItem("UserEmailId")
      )
      .then(function (response) {
        localStorage.setItem("Doctorid", response.data);
        return axiosInstance.get(
          "http://localhost:9091/doctor/getDetails/" + response.data
        );
      })
      .then(function (response) {
        localStorage.setItem("userData", JSON.stringify(response.data));
        console.log(response.data);
        })
        .catch((error) => {
            console.log(error)
            

        })

                      


                        setTimeout(()=>{
                            navigate('/doctorDashboard');

                        },8000)
                    
                        


            

                    }

                    else{ 
                      NotificationManager.warning('Otp Verification Failed please check the otp you have provided !', 'warning');

                    }
                    
                  })
                  .catch(function (error) {
                    console.log(error);
                  });



  };

  return (
    <div >
        <NotificationContainer/>

      <div class="alert alert-warning" role="alert" onClick={handleResend}>
      We've sent an OTP to your registered email address. Please check your inbox. If you haven't received it, 
      <a href="#" class="alert-link">you can click here to resend.</a>.
</div>

<div className="my-5 mx-4 d-flex">
<div
          className="d-flex flex-column flex-shrink-0 p-3 bg-light"
          style={{ width: "280px", height: "350px" }}
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
            <li className="sidebar">
              <Link
                to={"/pickupOrder"}
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

        <iframe
          src="https://lottie.host/embed/efcfb8b3-f7dc-4c92-a689-c8c9b56e4ef9/fvtZN8IcIY.json"
          className="mx-5"
        ></iframe>

        <div className="container mt-5">
     
     <div className="card" style={{ width: "21rem" }}>
 <div className="card-body">
    
 <iframe src="https://lottie.host/embed/515a190a-3d68-4b83-8561-5d93604d6762/NGxOB4o6Er.json"></iframe>
 
   <h5 className="card-title text-center">OTP Verification</h5>
   <hr />
   <div className="form-group">
               <input
                 type="number"
                 className="form-control"
                 placeholder="Enter OTP"
                 value={otp}
                 onChange={(e) => setOtp(e.target.value)}
               />
             </div>

             <button className="btn btn-primary btn-block" onClick={handleVerify}>
               Verify OTP
             </button>
   
 </div>
</div>



     
   </div>


</div>

  
 
    </div>
  );
};

export default App;
