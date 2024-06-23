import React, { useEffect,useState } from 'react'
import './CSS/doctorDashboard.css';
import SideBarDoctor from './SideBarDoctor';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import tapNotification from '../Assets/tapNotification.mp3'
import { Link } from 'react-router-dom';
export default function DoctorDashBoard({onDataReceived}) { 

  //  let emailVerification=true;

  const tapSound=new Audio(tapNotification);

 


  const storedData = localStorage.getItem("userData");
  const parsedData = JSON.parse(storedData);
  const navigate = useNavigate();
  
  const token = localStorage.getItem("JwtToken");

  const axiosInstance = axios.create({
    headers: {
      Authorization: `Bearer ${token}`, // Adding the token to the 'Authorization' header
    },
  });

  useEffect(() => {
    //Runs only on the first render
    if(parsedData?.role==="ADMIN")
    {
      navigate('/adminDashboard');
    }
    if(localStorage.getItem('UserEmailId') ===null || localStorage.getItem('UserEmailId') ==='' || localStorage.getItem('UserEmailId') ===undefined)
    {
      navigate('/login');
    }

    // // setemailVerification(localStorage.getItem('emailVerification'))
    // const obj=localStorage.getItem('emailVerification');
    // emailVerification=obj.emailVerified;

   
  }, []);

  


  // const handelSendOtp=()=>{
  //   console.log("Hii otp will send ");
  //   axiosInstance
  //               .post(
  //                 "http://localhost:9091/doctor/sendOpt/"+parsedData.email
  //               )
                  
  //                 .then(function (response) {
                    
  //                   console.log(response.data);
                    
  //                 })
  //                 .catch(function (error) {
  //                   console.log(error);
                    
  //                 });




  // }

  
 const handleDataReceived=() => {
  const obj={
    path:'/login',
    name:"Login"
  }
  onDataReceived({obj});
 }

 const handelViewDrugs=() => {
  tapSound.play();
  navigate('/viewDrugs');
 }

 const handelViewOrders=() => {
  tapSound.play();
  navigate('/viewOrders');
 }

 const handelPickOrders=() => {
  tapSound.play();
  navigate('/pickupOrder');
 }

//  if(emailVerification===false)
//  {
//   return (
//     <div>
//       <div class="alert alert-warning" role="alert" onClick={handelSendOtp}>
//   It seems you haven't verified your email address yet. To complete the process, please click <Link to="/otpVerification">here</Link>. to verify your ID.
// </div>
     
      
    



//     <div className="d-flex flex-wrap justify-content-around my-5 ">
//     <SideBarDoctor onDataReceived={handleDataReceived}/>

//     <div className="cookieCard cookieCard-1" onClick={handelViewDrugs}>
//       <h1 className="cookieHeading">💊 View Drugs</h1>
//       <h2 className="cookieDescription">Explore and access the complete list of Drugs.</h2>
      
//     </div>
  
//     <div className="cookieCard cookieCard-2" onClick={handelViewOrders}>
      
//       <h1 className="cookieHeading">📦 View Orders </h1>
//       <h2 className="cookieDescription">View all orders you made and check verification status</h2>
      
//     </div>
  
//     <div className="cookieCard cookieCard-3" onClick={handelPickOrders}>
//       <h1 className="cookieHeading">🚚 Pickup</h1>
//       <h2 className="cookieDescription">View orders in pickup section and make payment </h2>
      
//     </div>

//     <div style={{marginLeft:"350px"}}>
//     <iframe src="https://embed.lottiefiles.com/animation/15420"></iframe>
//     </div>

    

    
  
    
//   </div>
  
//   </div>
//   )


//  }



  
    
  return (
    <div>
 
    <div className="d-flex flex-wrap justify-content-around my-5 ">
    <SideBarDoctor onDataReceived={handleDataReceived}/>

    <div className="cookieCard cookieCard-1" onClick={handelViewDrugs}>
      <h1 className="cookieHeading">💊 View Drugs</h1>
      <h2 className="cookieDescription">Explore and access the complete list of Drugs.</h2>
      
    </div>
  
    <div className="cookieCard cookieCard-2" onClick={handelViewOrders}>
      
      <h1 className="cookieHeading">📦 View Orders </h1>
      <h2 className="cookieDescription">View all orders you made and check verification status</h2>
      
    </div>
  
    <div className="cookieCard cookieCard-3" onClick={handelPickOrders}>
      <h1 className="cookieHeading">🚚 Pickup</h1>
      <h2 className="cookieDescription">View orders in pickup section and make payment </h2>
      
    </div>

    <div style={{marginLeft:"350px"}}>
    <iframe src="https://embed.lottiefiles.com/animation/15420"></iframe>
    </div>

    
  
    
  </div>
  
  </div>
  )
}
