import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { NotificationManager } from "react-notifications";
import { NotificationContainer } from "react-notifications";
import { confirmAlert } from "react-confirm-alert";
//import "./CSS/Adminlogin.css";
import { useRef } from "react";
export default function AdminProfileUpdate() {
    
    const fileInputRef = useRef(null);
    const storedData = localStorage.getItem("userData");
    const [path, setPath] = useState(localStorage?.getItem("profilePath"));
    const parsedData = JSON.parse(storedData);
    const token = localStorage.getItem("JwtToken");
  
    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${token}`, // Adding the token to the 'Authorization' header
      },
    });
    const [AdminId,setAdminId]=useState(parsedData.id);
    const [AdminName, setName] = useState(parsedData.name);
    
    const [Adminpassword, setPassword] = useState("test");
    let [file, setFile] = useState(null);
  
    const handelNameChange = (e) => {
      console.log(e.target.value);
      setName(e.target.value);
    };
  
    const handelPasswordChange = (e) => {
      console.log(e.target.value);
      setPassword(e.target.value);
    };
  
   
  
    const handleImgChange = () => {
      fileInputRef.current.click();
    };
  
    const handleFileInputChange = (event) => {
    setFile(event.target.files[0]);
    // Handle the uploaded file here
    console.log("Uploaded file:", event.target.files[0]);
  
    const imageURL = URL.createObjectURL(event.target.files[0]);
    setPath(imageURL);
  
    console.log(imageURL);
  };
  
    const handelProfileUpdate = (e) => {
      e.preventDefault();
      confirmAlert({
        title: "Confirm Profile Update",
        message: "Are you sure to do this.",
        buttons: [
          {
            label: "Yes",
            onClick: () => {
              axiosInstance
                .put("http://localhost:9091/adminOprations/updateAdmin", {
                    id:AdminId,
                    email:localStorage.getItem("UserEmailId"),
                    password:Adminpassword,
                    name:AdminName

                 
                })
                .then(function (response) {
                  console.log(response.data);
                  const formData = new FormData();
                  formData.append("id",localStorage.getItem("UserEmailId"))
                  formData.append("file", file);
                  axiosInstance
                  .post(
                    "http://localhost:9091/adminOprations/addAdminProfileImg",
                    formData
                  )
                    
                    .then(function (response) {
                      console.log("Img upload-------------");
                      console.log(response.data);
                      
                    })
                    .catch(function (error) {
                      console.log(error);
                     
                    });
  
                  NotificationManager.success("Profile updated successfully");
                })
                .catch(function (error) {
                  console.log(error);
                  NotificationManager.error("Profile update failed!");
                });
            },
          },
          {
            label: "No",
          },
        ],
      });
    };
  
    return (
      <div>
        <NotificationContainer />
        <div className="my-5 mx-4 d-flex">
          <div
            className="d-flex flex-column flex-shrink-0 p-3 bg-light"
            style={{ width: "280px", height: "350px" }}
          >
            <Link
              to={"/AdminDashboard"}
              className="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none"
            >
              <img
                className="bi me-2"
                width="40"
                height="32"
                src={require("./Assets/menu.png")}
              ></img>
  
              <span className="fs-4">Hii,{parsedData.name}</span>
            </Link>
            <hr />
            <ul className="nav nav-pills flex-column mb-auto">
              <li>
                <Link
                  to={"/AdminDashboard"}
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
                  to={"/AdminDashboard"}
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
            src="https://embed.lottiefiles.com/animation/90060"
            className="mx-5"
          ></iframe>
  
          <div
            className="card mx-1"
            style={{
              width: "20rem",
              backgroundColor: "#D9F7F6",
              height: "520px",
            }}
          >
            <div className="card-body profile-img ">
              <div className="d-flex justify-content-center">
                <h5 className="card-title mx-5">Update Profile</h5>
              </div>
              <div className="d-flex justify-content-center">
                <img
                  src={path ? path : require("./Assets/profile.png")}
                  alt="profile picture"
                  height="128px"
                  width="128px"
                  onClick={handleImgChange}
                  title="Click to upload image"
                />
                <input
                  type="file"
                  ref={fileInputRef}
                  style={{ display: "none" }}
                  onChange={handleFileInputChange}
                />
              </div>
  
              <hr />
              <form>
                <div className="form-group">
                  <label for="exampleInputEmail1 my-2">Email address</label>
                  <input
                    type="email"
                    className="form-control my-2"
                    aria-describedby="emailHelp"
                    value={localStorage.getItem("UserEmailId")}
                    disabled
                  />
                  
                  <label>Full Name</label>
                  <input
                    type="text"
                    className="form-control my-2"
                    aria-describedby="emailHelp"
                    onChange={handelNameChange}
                    placeholder={parsedData.name}
                  ></input>
                  <label>Password</label>
                  <input
                    type="password"
                    className="form-control my-2"
                    aria-describedby="emailHelp"
                    onChange={handelPasswordChange}
                  />
                  
  
                  <button
                    type="submit"
                    className="btn btn-primary"
                    onClick={handelProfileUpdate}
                  >
                    Submit
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
  
