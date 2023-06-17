import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { NotificationManager} from 'react-notifications';
import { NotificationContainer} from 'react-notifications';
import 'react-notifications/lib/notifications.css';
import axios from 'axios';
import { isEmail } from 'validator';
import "./CSS/doctorlogin.css";

export default function DoctorSignup() {
  const navigate = useNavigate();

  const [doctorName, setName] = useState("");
  const [doctorContact, setContact] = useState("");
  const [doctorEmail, setEmail] = useState("");
  const [DoctorPassword, setPassword] = useState("");
  const [doctorAddress, setAddress] = useState("");
  const [msg, setMessage] = useState("");
 

  const handleNameChange = (e) => {
    setName(e.target.value);
  };

  const handleContactChange = (e) => {
    setContact(e.target.value);
  };

  const handleAddressChange = (e) => {
    setAddress(e.target.value);
  };

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (
      doctorName.trim() === "" ||
      doctorContact.trim() === "" ||
      doctorEmail.trim() === "" ||
      DoctorPassword.trim() === "" ||
      doctorAddress.trim() === ""
    ) {
      setMessage("Please enter valid data!");
      return;
    }

    if (!isEmail(doctorEmail)) {
      setMessage("Please enter a valid email address!");
      return;
    }

    axios
      .post('http://localhost:8081/doctor/create', {
        name: doctorName,
        contact: doctorContact,
        email: doctorEmail,
        password: DoctorPassword,
        doctorAddress: doctorAddress,
      })
      .then(function (response) {
        // Perform any desired action after successful form submission
       
          NotificationManager.success('Sign up successful', 'Success');
         
        setTimeout(() => {
          navigate('/doctor');
        }, 2000);
       
        
      })
      .catch(function (error) {
        console.log(error);
        if (error.response && error.response.status === 409) {
          setMessage("Email! Id already registered");
        }
      });
  }
  return (
    <div className="back d-flex justify-content-center">
     <NotificationContainer/>
      <img src={require("../Assets/login.jpg")} width="600px" height="530px" alt=''/>
      <div className="card my-4 logincard" style={{ width: "20rem" }}>
        
        <div className="card-body d-flex flex-column align-items-center">
          <h4>Signup</h4>
          <p style={{color:"red"}}>{msg}</p>
          <div className="form-group">
          <label for="exampleInputEmail1">Name</label>
            <input
              type="text"
              className="form-control"
            
              aria-describedby="emailHelp"
              placeholder="Your full name"
              onChange={handleNameChange}
              required
            />
            <label for="exampleInputEmail1">Mobile No</label>
            <input
              type="number"
              className="form-control"
             
              aria-describedby="emainumber"
              placeholder=""
              onChange={handleContactChange}
              required
            />
           <label for="exampleFormControlTextarea1">Address</label>
             <textarea className="form-control" id="exampleFormControlTextarea1" rows="3" placeholder='house no :325...' onChange={handleAddressChange} required></textarea>
            <label for="exampleInputEmail1">Email address</label>
            <input
              type="email"
              className="form-control"
              
              aria-describedby="emailHelp"
              placeholder="Enter email"
              onChange={handleEmailChange}
              required
            />
            <small id="emailHelp" className="form-text text-muted">
              We'll never share your email with anyone else.
            </small>

            <label for="exampleInputPassword1" className="my-2">
              Password
            </label>
            <input
              type="password"
              className="form-control"
              
              placeholder="Password"
              onChange={handlePasswordChange}
              required
            />

        
          </div>
          <button type="submit" className="btn btn-primary my-2" onClick={handleSubmit}>
            Submit
          </button>
          
        </div>
      </div>
    </div>
  )
}
