import React from 'react'

import './login.css';
import Footer from './Footer';
import { Link } from 'react-router-dom';
import { useEffect,useState } from 'react'

import { useNavigate } from 'react-router-dom';
export default function Login() {
  const navigate = useNavigate();
  

  useEffect(() =>{
    if(localStorage?.getItem('JwtToken'))
    {
      navigate('/doctorDashboard')
    }


  },[])


  return (
    <div>
    <div className='back'>
        <div className='role d-flex justify-content-center'>
        <h1>Choose your role</h1>
        </div>
       
        <div className='d-flex justify-content-around my-5 mx-5 '>
      <div className="card admin" style={{width: "18rem", height:"350px"}}>
  <img className="card-img-top" src={require("../Assets/admin.jpg")} />
  <div className="card-body d-flex justify-content-center">
    <h5 className="card-title ">Admin</h5>
  </div>
</div>

<div className="card doctor" style={{width: "18rem", height:"350px"}}>
    <Link to="/doctor">
  <img className="card-img-top" src={require("../Assets/doctor.jpg")} />
  </Link>
  <div className="card-body d-flex justify-content-center">
    <h5 className="card-title ">Doctor</h5>
  </div>
</div>
</div>

    </div>
    </div>
  )
}
