import React from 'react'
import { Link } from 'react-router-dom';
export default function SideAdminRest() {
    const storedData = localStorage.getItem("userData");
  const parsedData = JSON.parse(storedData);
    return (
        <div>
          <div className="d-flex flex-column flex-shrink-0 p-3 bg-light" style={{width: "280px"}}>
        <Link to={"/adminDashboard"} className="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none" >
          <img className="bi me-2" width="40" height="32" src={require('./Assets/menu.png')}></img>
          <span className="fs-4">Hii,  {parsedData.name?parsedData.name:" "}</span>
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
        to={"/adminDashboard"}
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
       
      </div>
        </div>
      )
}
