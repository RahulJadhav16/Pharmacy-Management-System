import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { NotificationManager} from 'react-notifications';
import { NotificationContainer} from 'react-notifications';
import 'react-notifications/lib/notifications.css';
import axios from 'axios';
import { isEmail } from 'validator';
import "./CSS/doctorlogin.css";
import RingLoader from "react-spinners/RingLoader";

export default function DocotorForgetPassword() {
    const navigate = useNavigate();
    const[loader,setLoader]=useState(false);

    const [doctorEmail, setEmail] = useState("");
   
   
  
 
  
    const handleEmailChange = (e) => {
      setEmail(e.target.value);
      
    };
  
 
  
    const handleSubmit = (e) => {
      e.preventDefault();
  
    //  
  
      if (!isEmail(doctorEmail)) {
        NotificationManager.warning("Please enter a valid email address!", 'warning');
        return;
      }
  
    
    setLoader(true)
    axios.post('http://localhost:9091/doctor/forgetPassword/'+doctorEmail)
    .then((response)=>{
        console.log(response.data);
        NotificationManager.success('Password reset done!', 'Success');
        NotificationManager.warning("Don't refresh while we redirect you to the login page !", 'warning');

        setTimeout(() => {
            navigate('/doctor');
        }, 6000);

        setLoader(false)


    })
    .catch((error)=>{
        setLoader(false)
        console.log(error);
    })


    }


    return (
      <div className="back d-flex justify-content-center">
       <NotificationContainer/>
        {/* <img src={require("../Assets/signupDoctor.png")} width="600px" height="530px" alt=''/> */}
       
        <iframe src="https://lottie.host/embed/80cc4207-2216-4a9b-947d-bfa970d33f6b/OIHfTBjECK.json" width="600px" height="530px"></iframe>

        <div className="card  logincard" style={{ width: "20rem",height:"300px",marginTop:"150px" }} >
          
          <div className="card-body d-flex flex-column align-items-center my-4">

          {loader? <div className="my-3" ><RingLoader
        color={"green"}
        loading={loader}
    
        size={150}
        aria-label="Loading Spinner"
        data-testid="loader" /><h5>resetting password...</h5> </div>:<div>
            <h4>Forgot Password</h4>

           
<div className="form-group my-4">

  

 
  <label for="exampleInputEmail1" my-4>Email address</label>
  <input
    type="email"
    className="form-control"
    
    aria-describedby="emailHelp"
    placeholder="Enter email"
    onChange={handleEmailChange}
    required
  />



</div>
<button type="submit" className="btn btn-primary my-2" onClick={handleSubmit} >
  Submit Request
</button>
            
            
            </div>}
            
            
          </div>
        </div>
      </div>
    
  )
}
