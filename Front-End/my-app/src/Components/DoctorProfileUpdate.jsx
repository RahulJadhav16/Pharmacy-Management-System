import React from 'react'
import { Link } from 'react-router-dom';
export default function DoctorProfileUpdate() {
    const storedData = localStorage.getItem("userData");
  const parsedData = JSON.parse(storedData);
  return (
    <div>
      <div className="my-5 mx-4 d-flex">
      <div
            className="d-flex flex-column flex-shrink-0 p-3 bg-light"
            style={{ width: "280px" , height:"350px"}}
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
          
          <iframe src="https://embed.lottiefiles.com/animation/90060" className='mx-5'></iframe>
          

          <div className="card mx-1" style={{width: "18rem", backgroundColor:"#D9F7F6"}}>
  <div className="card-body ">
    <h5 className="card-title mx-5">Update Profile</h5>
    <hr />
    <form>
  <div class="form-group">
    <label for="exampleInputEmail1 my-2">Email address</label>
    <input type="email" class="form-control my-2"  aria-describedby="emailHelp" value={localStorage.getItem("UserEmailId")} disabled/>
    <label >Full Name</label>
    <input type="text" class="form-control my-2"  aria-describedby="emailHelp" value={parsedData.name} />
    <label >Password</label>
    <input type="password" class="form-control my-2"  aria-describedby="emailHelp" />
    <label for="exampleFormControlTextarea1">Address</label>
    <textarea className="form-control" id="exampleFormControlTextarea1" rows="3" value={parsedData.address}></textarea>

    <button type="submit" class="btn btn-primary">Submit</button>


    
  </div>
  </form>
    
  </div>
</div>


          




      </div>
    </div>
  )
}
