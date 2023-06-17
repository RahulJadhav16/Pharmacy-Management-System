import React from "react";
import "./CSS/doctorlogin.css";
import { Link } from "react-router-dom";
export default function DoctorLogin() {
  return (
    <div className="back d-flex justify-content-center">
      
      <img src={require("../Assets/login.jpg")} width="600px" height="530px" />
      <div className="card my-4 logincard" style={{ width: "20rem" }}>
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
          <div className="form-group">
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

            <div className="form-group form-check">
              <input
                type="checkbox"
                className="form-check-input"
                id="exampleCheck1"
              />
              <label className="form-check-label" for="exampleCheck1">
                Show Password
              </label>
            </div>
          </div>
          <button type="submit" className="btn btn-primary my-2">
            Submit
          </button>
          <Link to="/signup" className="my-4">
            Dont't have an account, Create one
          </Link>
        </div>
      </div>
    </div>
  );
}
