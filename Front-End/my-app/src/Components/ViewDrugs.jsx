import React, { useEffect, useState } from "react";
import SideBarDoctor from "./SideBarDoctor";
import { Link } from "react-router-dom";
import axios from "axios";
import "./CSS/viewDrugs.css";
import { NotificationManager} from 'react-notifications';
import { NotificationContainer} from 'react-notifications';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import "./CSS/doctorlogin.css";
export default function ViewDrugs() {
  const storedData = localStorage.getItem("userData");
  const parsedData = JSON.parse(storedData);

  const [data, setData] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [quantityIp, setquantity] = useState('');

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    
  };

  const handelQuantityChange=(e)=>{
    setquantity(e.target.value);
    
  }
  
  /////////////////// Placing the Order calling order microservice
  const handelOrderClick= (item) => () =>{
    //console.log(item);
    if(quantityIp==='' || quantityIp==null || quantityIp<=0)
    {
      NotificationManager.error('', 'Enter Valid Quantity!', 3000);
      return;
    }

    const token = localStorage.getItem("JwtToken");
    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${token}`, 
      },
    });

    axiosInstance
    .post("http://localhost:9091/doctor/addOrder", {
      doctorId: localStorage.getItem("Doctorid"),
      drugName:item.name,
      quantity:quantityIp
      
    })
    .then(function (response) {
      console.log(response.data);
      setquantity(0);
      NotificationManager.success(`${response.data.drugName} with quantity ${response.data.quantity}`, 'Order placed successfully', 3000);

    })
    .catch(function (error) {
      console.log(error);

    })

  }

  useEffect(() => {
    // Set the token value
    const token = localStorage.getItem("JwtToken");

    // Create an instance of Axios with default headers
    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${token}`, // Adding the token to the 'Authorization' header
      },
    });

    axiosInstance
      .get("http://localhost:9091/doctor/viewAllDrugs")
      .then(function (response) {
        console.log(response.data);
        setData(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  const handleBackspace = (e) => {
    if (e.key === 'Backspace') {
      setFilteredPosts(data);
    }
  };

  const handleSearchSubmit = (e) => {
    e.preventDefault();
    

    const token = localStorage.getItem("JwtToken");

    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${token}`, // Adding the token to the 'Authorization' header
      },
    });
    axiosInstance
      .get("http://localhost:9091/doctor/drugByName/" + searchTerm)
      .then(function (response) {
        console.log(response.data);
        setFilteredPosts(response.data);
        
      })
      .catch(function (error) {
        console.log(error);
        if (error.response && error.response.status === 404) {
          NotificationManager.warning('', 'Drug not found !', 3000);
        }
        if (error.response && error.response.status === 401) {
          NotificationManager.error('', 'Enter Valid Name!', 3000);
        }
        
      });
    
  };

  return (
    <div>
      <NotificationContainer/>
      {/* SearchBar */}
      <div className="row height d-flex justify-content-center align-items-center">
        <div className="col-md-8">
          <div className="search">
            <i className="fa fa-search"></i>
            <input
  type="text"
  className="form-control"
  placeholder="Search Drugs.."
  onChange={handleSearchChange}
  onKeyDown={handleBackspace}
/>
            <button className="btn btn-primary" onClick={handleSearchSubmit}>
              Search
            </button>
          </div>
        </div>
      </div>
      <div className="my-5 mx-4 d-flex">
        {/* SideBar */}
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
        <div className="d-flex flex-wrap justify-content-around">
          {filteredPosts.length
            ? filteredPosts.map((item) => {
                
                return (
                  <div
                    className="card mx-5 my-2 mycard "
                    style={{ background: "#6b64f3", borderRadius: "25px" }}
                  >
                    <div className="card-border-top"></div>
                    <div className="img d-flex justify-content-center">
                      <h3>{item.name}</h3>
                    </div>
                    <span>Price: ₹{item.price}</span>
                    <span>Type: {item.type}</span>
                    <p className="job">Category: {item.category}</p>
      
                    <Popup trigger=
                    {<button>📦 Buy</button>}
                  position="right center">
                    <div className="d-flex justify-content-center">
                   <input type="number" class="form-control"  placeholder="Quantity" onChange={handelQuantityChange} ></input>
                   <button type="button" class="btn btn-warning" onClick={handelOrderClick(item)}>Place Order</button>
                   </div>
                 </Popup>
                  </div>
                );
              })
            : data.map((item) => {
              
                return (
                  <div
                    className="card mx-5 my-2 mycard"
                    style={{ background: "#6b64f3", borderRadius: "25px" }}
                  >
                    <div className="card-border-top"></div>
                    <div className="img d-flex justify-content-center">
                      <h3>{item.name}</h3>
                    </div>
                    <span>Price: ₹{item.price}</span>
                    <span>Type: {item.type}</span>
                    <p className="job">Category: {item.category}</p>
                    <Popup trigger=
                    {<button>📦 Buy</button>}
                  position="right center">
                    <div className="d-flex justify-content-center">
                   <input type="number" class="form-control"  placeholder="Quantity" onChange={handelQuantityChange} ></input>
                   <button type="button" class="btn btn-warning" onClick={handelOrderClick(item)}>Place Order</button>
                   </div>
                 </Popup>
                  </div>
                );
              })}



                
        </div>
      </div>
    </div>
  );
}
