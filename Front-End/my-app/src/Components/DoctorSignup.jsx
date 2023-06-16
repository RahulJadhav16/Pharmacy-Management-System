import React from 'react'
import "./CSS/doctorlogin.css";
import { Link } from "react-router-dom";
export default function DoctorSignup() {
  return (
    <div className="back d-flex justify-content-center">
      <img src={require("../Assets/login.jpg")} width="600px" height="530px" />
      <div className="card my-4 logincard" style={{ width: "20rem" }}>
        
        <div className="card-body d-flex flex-column align-items-center">
          <h4>Signup</h4>
          <div className="form-group">
          <label for="exampleInputEmail1">Name</label>
            <input
              type="text"
              className="form-control"
              id="exampleInputEmail1"
              aria-describedby="emailHelp"
              placeholder="Your full name"
            />
            <label for="exampleInputEmail1">Mobile No</label>
            <input
              type="number"
              className="form-control"
              id="exampleInputEmail1"
              aria-describedby="emainumber"
              placeholder="9123457689"
            />
           <label for="exampleFormControlTextarea1">Address</label>
             <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder='house no :325...'></textarea>
            <label for="exampleInputEmail1">Email address</label>
            <input
              type="email"
              className="form-control"
              id="exampleInputEmail1"
              aria-describedby="emailHelp"
              placeholder="Enter email"
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
              id="exampleInputPassword1"
              placeholder="Password"
            />

        
          </div>
          <button type="submit" className="btn btn-primary my-2">
            Submit
          </button>
        </div>
      </div>
    </div>
  )
}
