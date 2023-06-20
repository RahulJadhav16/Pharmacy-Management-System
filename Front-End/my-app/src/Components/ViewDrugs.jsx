import React, { useEffect, useState } from 'react'
import SideBarDoctor from './SideBarDoctor'
import { Link } from 'react-router-dom';
import axios from 'axios';
import './CSS/viewDrugs.css';
export default function ViewDrugs() {
    const storedData = localStorage.getItem('userData');
    const parsedData = JSON.parse(storedData);

    const[data,setData]=useState([]);

    useEffect(() => {
         // Set the token value
         const token = localStorage.getItem('JwtToken');
      
         // Create an instance of Axios with default headers
         const axiosInstance = axios.create({
           headers: {
             'Authorization': `Bearer ${token}` // Adding the token to the 'Authorization' header
           }
         });

         axiosInstance.get('http://localhost:9091/doctor/viewAllDrugs')
         .then(function (response) {
           
           console.log(response.data);
           setData(response.data)
         })
         .catch(function (error) {
           console.log(error);
         });
   
         
       



      },[]);




  return (
    <div>
        {/* SearchBar */}
    <div className="row height d-flex justify-content-center align-items-center">

<div className="col-md-8">

  <div className="search">
    <i className="fa fa-search"></i>
    <input type="text" className="form-control" placeholder="Search Drugs.."/>
    <button className="btn btn-primary">Search</button>
  </div>
  
</div>

</div>
    <div className='my-5 mx-4 d-flex'>
        {/* SideBar */}
        <div>
      <div className="d-flex flex-column flex-shrink-0 p-3 bg-light" style={{width: "280px"}}>
    <Link to={"/doctorDashboard"} className="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none" >
      <img className="bi me-2" width="40" height="32" src={require('../Assets/menu.png')}></img>
      <span className="fs-4">Hii, Dr {parsedData.name}</span>
    </Link>
    <hr/>
    <ul className="nav nav-pills flex-column mb-auto">

    <li>
  <Link
    to={"/doctorDashboard"}
    className="nav-link link-dark"
    style={{
      backgroundColor: 'initial',
      transition: 'background-color 0.3s',
    }}
    onMouseOver={(e) => {
      e.target.style.backgroundColor = 'violet';
    }}
    onMouseLeave={(e) => {
      e.target.style.backgroundColor = 'initial';
    }}
  >
    Dashboard
  </Link>
</li>

<li className='sidebar'>
  <Link
    to={"/doctorDashboard"}
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
    Orders
  </Link>
</li>

<li className='sidebar'>
  <Link
    to={"/doctorDashboard"}
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
    Drugs
  </Link>
</li>

<li className='sidebar'>
  <Link
    to={"/doctorDashboard"}
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
    Contact Admin
  </Link>
</li>

    </ul>
    <hr/>

  
  </div>

 

     

  
      
    
    </div>
    <div className='d-flex flex-wrap justify-content-around'>
    {data.map((item) => (
        <div className="card mx-5 my-2 mycard" style={{background:"#6b64f3",borderRadius:"25px"}}>
        <div className="card-border-top">
        </div>
        <div className="img d-flex justify-content-center">
            <h3>{item.name}</h3>
        </div>
        <span>Type: {item.type}</span>
        <p className="job">Category: {item.category}</p>
        <button>📦 Buy
        </button>
      </div>
    
      ))}
      </div>

    
       
    
    </div>
</div>
  )
}
