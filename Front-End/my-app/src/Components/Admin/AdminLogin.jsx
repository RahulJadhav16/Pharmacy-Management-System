
import React, { useState,useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import { isEmail } from 'validator';
import axios from 'axios';
import LoadingBar from 'react-top-loading-bar'
export default function AdminLogin({ onDataReceived }) {
  const [progress, setProgress] = useState(0)
    const navigate = useNavigate();
    let[Adminemail,setEmail]=useState("");
    let[Adminpassword,setPassword]=useState("");
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
    setProgress(10)
    e.preventDefault();
    if(Adminemail.trim() === '' || Adminpassword.trim() === '')
    {
      setMsg('Please enter valid data!')
      
      return;
    }
    if (!isEmail(Adminemail)) {
      setMsg("Please enter a valid email address!");
      
      return;
    }
    axios
        .post('http://localhost:9091/adminOprations/auth/login', {
            email:Adminemail,
            password:Adminpassword
        })
        .then(function (response){
  
          if(response.data.jwtToken==="" || response.data.jwtToken===undefined)
          {
            setMsg("Invalid credentials !");
            return;
          }
          localStorage.setItem('JwtToken',response.data.jwtToken)
          localStorage.setItem('UserEmailId',response.data.username)
          setProgress(100)
          const AdminEmail=localStorage.getItem('UserEmailId');
          const obj={
            path:'/adminDashboard',
            doctorName:AdminEmail
          }
          console.log(AdminEmail);
          onDataReceived({obj});

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
            
           
               
               
               
                navigate('/adminDashboard');
           
           
        })
        
        .catch(function (error) {
          console.log(error);
        });


         
         
          
  
          
  
  
  
        })
        .catch(function (error) {
          console.log(error);
        })
  
    
    
  
  
   }
  
  
  
  
  
  
  
    return (
      <div>
        <LoadingBar
        color='#f11946'
        progress={progress}
        onLoaderFinished={() => setProgress(0)}
      />
      <div className="back d-flex justify-content-center">
        
        <img src={require("./Assets/login.png")} width="500px" height="540px" alt="" className="my-3"/>
        <div className="card my-4 logincard mx-3 border border-primary" style={{ width: "20rem" ,height:"490px",borderRadius:"10%"}}>
        <iframe src="https://embed.lottiefiles.com/animation/107513"></iframe>
          
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
           
          </div>
        </div>
      </div>
      </div>
    );
  }
  