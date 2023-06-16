import React from "react";

import "./landingpage.css";
import NavBarL from "./NavBarL";
import Footer from "./Footer";
import { BrowserRouter as Main,Route, Routes } from "react-router-dom";
import LandingPageContent from "./LandingPageContent";
import Login from "./Login";
import DoctorLogin from "./DoctorLogin";
import DoctorSignup from "./DoctorSignup";
export default function landingpage() {
  return (
    <div>
      <Main>
      <NavBarL/>
      <Routes>
      <Route exact path="/" element={<LandingPageContent/>}/>
      <Route exact path="/login" element={<Login/>}/>
      <Route exact path="/doctor" element={<DoctorLogin/>}/>
      <Route exact path="/signup" element={<DoctorSignup/>}/>

      </Routes>
      
      </Main>

      
    </div>

    
  );
}
