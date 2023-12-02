import React, { useEffect,useState } from 'react'
import { Link } from 'react-router-dom'
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
export default function SideBarAdmin({onDataReceived}) {
    const navigate = useNavigate();
    const [adminData, setadminData] = useState('');
    //Setting the profile img for the first time 
    const [profileImg, setprofileImg] = useState("");
    
    useEffect(() => {
      // Set the token value
      
      const token = localStorage.getItem('JwtToken');
      
      // Create an instance of Axios with default headers
      const axiosInstance = axios.create({
        headers: {
          'Authorization': `Bearer ${token}` // Add the token to the 'Authorization' header
        }
      });
      
      
      axiosInstance.get('http://localhost:9091/adminOprations/getAdminDetails/' + localStorage.getItem('UserEmailId'))
        .then(function (response) {
            localStorage.setItem('userData', JSON.stringify(response.data));
            setadminData(response.data);
            console.log(adminData)
            axiosInstance
           .get(
          "http://localhost:9091/adminOprations/getAdminProfileImg/"+localStorage.getItem("UserEmailId"))
         
          .then(function (response) {
            console.log("Profile picture loaded");

            console.log(response.data);
    
            const base64Image = response.data.image;
            const byteCharacters = atob(base64Image);
            const byteNumbers = new Array(byteCharacters.length);
            for (let i = 0; i < byteCharacters.length; i++) {
              byteNumbers[i] = byteCharacters.charCodeAt(i);
            }
            const byteArray = new Uint8Array(byteNumbers);
            const blob = new Blob([byteArray], { type: "image/jpeg" });
            const imageUrl = URL.createObjectURL(blob);
            setprofileImg(imageUrl);
            localStorage.setItem('profilePath', imageUrl);
          })
          .catch(function (error) {
            console.log(error);
          })
        
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
      <div className="d-flex flex-column flex-shrink-0 p-3 bg-light" style={{width: "280px", height:"480px"}}>
    <Link to={"/adminDashboard"} className="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none" >
      <img className="bi me-2" width="40" height="32" src={require('./Assets/menu.png')}></img>
      <span className="fs-4">Hii,  {adminData.name?adminData.name:" "}</span>
    </Link>
    <hr/>
    <ul className="nav nav-pills flex-column mb-auto">

    <li>
  <Link
    to={"/adminDashboard"}
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
    to={"/Adminorder"}
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
    to={"/drugInventory"}
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
    Drug Inventory
  </Link>
</li>

<li className='sidebar'>
  <Link
    to={"/adminPickup"}
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

<li className='sidebar'>
  <Link
    to={"/analytics"}
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
    Analytics
  </Link>
</li>

<li className='sidebar'>
  <Link
    to={"/requests"}
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
    Requests
  </Link>
</li>

<li className='sidebar'>
  <Link
    to={"/chats"}
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
    Chat
  </Link>
</li>

    </ul>
    <hr/>
    <div className="dropdown">
      <Link href="#" className="d-flex align-items-center link-dark text-decoration-none dropdown-toggle" id="dropdownUser2" data-bs-toggle="dropdown" aria-expanded="false">
      <img
              src={profileImg?profileImg:require("./Assets/profile.png")}
              alt="prfile picture"
              height="64px"
              width="64px"
            />
        <strong className='mx-2'>Profile</strong>
      </Link>
      <ul className="dropdown-menu text-small shadow" aria-labelledby="dropdownUser2">
        
        <li><Link className="dropdown-item" to='/updateAdminProfile'>Update Profile</Link></li>
        <li><hr className="dropdown-divider"/></li>
        <li><button className="dropdown-item"  onClick={handelSignout}>Sign out</button></li>
      </ul>
    </div>
  </div>
    </div>
  )
}

