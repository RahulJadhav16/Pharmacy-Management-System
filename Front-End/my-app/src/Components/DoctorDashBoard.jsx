import React, { useEffect,useState } from 'react'
import './CSS/doctorDashboard.css';
import SideBarDoctor from './SideBarDoctor';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Footer from './Footer';
import { Link } from 'react-router-dom';
export default function DoctorDashBoard({onDataReceived}) { 
  
  const navigate = useNavigate();
  useEffect(() => {
    //Runs only on the first render
    if(localStorage.getItem('UserEmailId') ===null || localStorage.getItem('UserEmailId') ==='' || localStorage.getItem('UserEmailId') ===undefined)
    {
      navigate('/login');
    }
  }, []);

  
 const handleDataReceived=() => {
  const obj={
    path:'/login',
    name:"Login"
  }
  onDataReceived({obj});
 }

 const handelViewDrugs=() => {
  navigate('/viewDrugs');
 }

 const handelViewOrders=() => {
  navigate('/viewOrders');
 }

 const handelPickOrders=() => {
  navigate('/pickupOrder');
 }

  
    
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
