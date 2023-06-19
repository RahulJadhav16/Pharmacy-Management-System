import React, { useState } from "react";
import "./CSS/doctorlogin.css";
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import { isEmail } from 'validator';
import axios from 'axios';
import NavBarL from "./NavBarL";
export default function DoctorLogin({ onDataReceived }) {
  const navigate = useNavigate();
  let[Doctoremail,setEmail]=useState("");
  let[Doctorpassword,setPassword]=useState("");
  let[msg,setMsg]=useState("");
  const [show, setShow] = useState("password");


  const handelEmailChange=(e)=>{
    setEmail(e.target.value);
   
  }

  const handelPasswordChange=(e)=>{
    setPassword(e.target.value);
  }

  const handelShowPassword = () => {
    setShow(show === "password" ? "text" : "password");
  };

 const handelSubmit=(e)=>{
  e.preventDefault();
  if(Doctoremail.trim() === '' || Doctorpassword.trim() === '')
  {
    setMsg('Please enter valid data!')
    return;
  }
  if (!isEmail(Doctoremail)) {
    setMsg("Please enter a valid email address!");
    return;
  }
  axios
      .post('http://localhost:9091/doctor/auth/login', {
        email:Doctoremail,
        password:Doctorpassword
      })
      .then(function (response){

        if(response.data.jwtToken==="" || response.data.jwtToken===undefined)
        {
          setMsg("Invalid credentials !");
          return;
        }
        localStorage.setItem('JwtToken',response.data.jwtToken)
        localStorage.setItem('UserEmailId',response.data.username)
        navigate('/doctorDashboard');
        const Doctorname=localStorage.getItem('UserEmailId');
        const obj={
          path:'/doctorDashboard',
          doctorName:Doctorname
        }
          
        onDataReceived({obj});

        



      })
      .catch(function (error) {
        console.log(error);
      })

  
  


 }







  return (
    <div>
    <div className="back d-flex justify-content-center">
      
      <img src={require("../Assets/login.jpg")} width="600px" height="540px" alt=""/>
      <div className="card my-4 logincard" style={{ width: "20rem" ,height:"540px"}}>
        <div className="d-flex justify-content-center my-5">
          <img
            className="doctorlogo"
            src={require("../Assets/doctorlogo.png")}
            width="48px"
            height="48px"
          />
        </div>
        <div className="card-body d-flex flex-column align-items-center">
          <h4>Login</h4>
          <p style={{color:"red"}}>{msg}</p>
          <div className="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input
              type="email"
              className="form-control"
              
              aria-describedby="emailHelp"
              placeholder="Enter email"
              onChange={handelEmailChange}
            />
            <small id="emailHelp" className="form-text text-muted">
              We'll never share your email with anyone else.
            </small>

            <label for="exampleInputPassword1" className="my-2">
              Password
            </label>
            <input
              type={show}
              className="form-control"
              
              placeholder="Password"
              onChange={handelPasswordChange}
              
            />

            <div className="form-group form-check">
              <input
                type="checkbox"
                className="form-check-input"
                id="exampleCheck1"
                onClick={handelShowPassword}
              />
              <label className="form-check-label" for="exampleCheck1">
                Show Password
              </label>
            </div>
          </div>
          <button type="submit" className="btn btn-primary my-2" onClick={handelSubmit}>
            Submit
          </button>
          <Link to="/signup" className="my-4">
            Dont't have an account, Create one
          </Link>
        </div>
      </div>
    </div>
    </div>
  );
}
