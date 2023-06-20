import React, { useEffect,useState } from 'react'
import { Link } from 'react-router-dom'
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
export default function SideBarDoctor({ onDataReceived }) {
    const navigate = useNavigate();
    const [doctorData, setDoctorData] = useState('');
    
    useEffect(() => {
      // Set the token value
      
      const token = localStorage.getItem('JwtToken');
      
      // Create an instance of Axios with default headers
      const axiosInstance = axios.create({
        headers: {
          'Authorization': `Bearer ${token}` // Add the token to the 'Authorization' header
        }
      });
      
      
      axiosInstance.get('http://localhost:9091/doctor/getDoctorId/' + localStorage.getItem('UserEmailId'))
        .then(function (response) {
          localStorage.setItem('Doctorid', response.data);
          return axiosInstance.get('http://localhost:9091/doctor/getDetails/' + response.data);
        })
        .then(function (response) {
          localStorage.setItem('userData', JSON.stringify(response.data));
          setDoctorData(response.data);
          console.log("Name of doctor" + response.data.name);
        })
        .catch(function (error) {
          console.log(error);
        });
    
    }, []);
    
    const handelSignout = () => {
      localStorage.clear();
      const obj = {
        path: '/login',
        name: "Login"
      };
      onDataReceived({ obj });
      navigate('/');
    }
    




  return (
    <div>
      <div className="d-flex flex-column flex-shrink-0 p-3 bg-light" style={{width: "280px"}}>
    <Link to={"/doctorDashboard"} className="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none" >
      <img className="bi me-2" width="40" height="32" src={require('../Assets/menu.png')}></img>
      <span className="fs-4">Hii, Dr {doctorData.name?doctorData.name:" "}</span>
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
    <div className="dropdown">
      <Link href="#" className="d-flex align-items-center link-dark text-decoration-none dropdown-toggle" id="dropdownUser2" data-bs-toggle="dropdown" aria-expanded="false">
        <img src={require('../Assets/profile.png')} alt="prfile picture" height="32px" width="32px"/>
        <strong className='mx-2'>Profile</strong>
      </Link>
      <ul className="dropdown-menu text-small shadow" aria-labelledby="dropdownUser2">
        
        <li><Link className="dropdown-item" to='/'>Update Profile</Link></li>
        <li><hr className="dropdown-divider"/></li>
        <li><button className="dropdown-item"  onClick={handelSignout}>Sign out</button></li>
      </ul>
    </div>
  </div>
    </div>
  )
}
