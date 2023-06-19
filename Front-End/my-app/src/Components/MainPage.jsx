import React from "react";
import { useState,useEffect } from 'react';
import "./landingpage.css";
import NavBarL from "./NavBarL";
import Footer from "./Footer";
import { BrowserRouter as Main,Route, Routes } from "react-router-dom";
import LandingPageContent from "./LandingPageContent";
import Login from "./Login";
import DoctorLogin from "./DoctorLogin";
import DoctorSignup from "./DoctorSignup";
import DoctorDashBoard from "./DoctorDashBoard";

export default function MainPage() {
    const obj={
        path:'/login',
        name:"Login"
      }
      
      let [childData, setChildData] = useState(obj);

    
     
    //   const handleDataReceived = (data) => {
    //     setChildData(data);
    //     console.log(data)
    //   };

    
        const handleDataReceived = (data) => {
            if(data===undefined)
            {
                setChildData(obj)

            }
            else{
            setChildData(data);
            console.log(data)
            }
          };
        
    

  return (
    <div>
      <Main>
      <NavBarL name={childData} />
      <Routes>
      <Route exact path="/" element={<LandingPageContent/>}/>
      <Route exact path="/login" element={<Login/>}/>
      <Route exact path="/doctor" element={<DoctorLogin onDataReceived={handleDataReceived}/>}/>
      <Route exact path="/signup" element={<DoctorSignup/>}/>
      <Route exact path="/doctorDashboard" element={<DoctorDashBoard/>}/>

      </Routes>
      
      </Main>
     

      
    </div>
  )
}
