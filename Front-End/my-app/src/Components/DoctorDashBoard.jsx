import React from 'react'
import './CSS/doctorDashboard.css';

export default function DoctorDashBoard() {
    
  return (
    <div className="d-flex justify-content-around my-4 ">
    <div className="cookieCard cookieCard-1">
      <p className="cookieHeading">💊 View Drugs</p>
      <p className="cookieDescription">explore and access the complete list of Drugs.</p>
      
    </div>
  
    <div className="cookieCard cookieCard-2">
      <p className="cookieHeading">Cookies.</p>
      <p className="cookieDescription">By using this website you automatically accept that we use cookies. <a href="#">What for?</a></p>
      
    </div>
  
    <div className="cookieCard cookieCard-3">
      <p className="cookieHeading">Cookies.</p>
      <p className="cookieDescription">By using this website you automatically accept that we use cookies. <a href="#">What for?</a></p>
      
    </div>
  
    <div className="cookieCard cookieCard-4">
      <p className="cookieHeading">Cookies.</p>
      <p className="cookieDescription">By using this website you automatically accept that we use cookies. <a href="#">What for?</a></p>
      
    </div>
  </div>
  
  )
}
