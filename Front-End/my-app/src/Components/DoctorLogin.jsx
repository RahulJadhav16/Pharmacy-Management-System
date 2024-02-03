import React, { useState,useEffect } from "react";
import "./CSS/doctorlogin.css";
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import { isEmail } from 'validator';
import axios from 'axios';
import LoadingBar from 'react-top-loading-bar'
import { NotificationManager} from 'react-notifications';
import { NotificationContainer} from 'react-notifications';
import 'react-notifications/lib/notifications.css';



export default function DoctorLogin({ onDataReceived }) {
  const [progress, setProgress] = useState(0)

                        
  const navigate = useNavigate();
  useEffect(() =>{
    if(localStorage?.getItem('JwtToken'))
    {
      navigate('/doctorDashboard')
    }


  },[])






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
    NotificationManager.warning("⚠️Please enter a valid email address!", 'warning');
    return;
  }
  setProgress(10)
  axios
      .post('http://localhost:8081/doctor/auth/login', {
        email:Doctoremail,
        password:Doctorpassword
      })
      .then(function (response){

        if(response.data.jwtToken==="" || response.data.jwtToken===undefined)
        {
          setProgress(100)
          setMsg("Invalid credentials !");
          NotificationManager.error("⚠️Invalid Credentials!", 'warning');
          return;
          
        }
        localStorage.setItem('JwtToken',response.data.jwtToken)
        localStorage.setItem('UserEmailId',response.data.username)
        setProgress(100)
        navigate('/doctorDashboard');
        const Doctorname=localStorage.getItem('UserEmailId');
        const obj={
          path:'/doctorDashboard',
          doctorName:Doctorname
        }
        console.log(Doctorname);
        onDataReceived({obj});

        



      })
      .catch(function (error) {
        setProgress(100)
        NotificationManager.error("⚠️Invalid Credentials!", 'warning');
        console.log(error);
      })

  
  


 }







  return (
    <div>
       <NotificationContainer/>
      <LoadingBar
        color='#f11946'
        progress={progress}
        onLoaderFinished={() => setProgress(0)}
      />
      
      
      
    <div className="back d-flex justify-content-center">

      <img src={require("../Assets/login.jpg")} width="600px" height="540px" alt=""/>
     
      
      <div className="card my-4 logincard" style={{ width: "20rem" ,height:"580px"}}>
        
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
              id="testEmail"
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
              id="testPassword"
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
          <Link to="/forgetpassword" className="my-4">
              Forgot password ?
        
          </Link>
          <button type="submit" className="btn btn-primary my-2" onClick={handelSubmit}>
            Submit
          </button>
          <Link to="/signup" className="mx-4">
            Dont't have an account
          </Link>
        </div>
      </div>
    </div>
    
    
    </div>
  );
}
