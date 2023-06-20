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
import SideBarDoctor from "./SideBarDoctor";
import PrivateRoute from "./ProtectedRotes/PrivateRoute";
import ViewOrders from "./ViewOrders";
import ViewDrugs from "./ViewDrugs";

export default function MainPage() {

   const jwtKey=localStorage?.getItem('JwtToken')
    const obj={
        path:'/login',
        name:"Login"
      }
      
      let [childData, setChildData] = useState(obj);

    
     
    
    
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
      <Route element={<PrivateRoute id={jwtKey}/>}>
      
      <Route exact path="/doctorDashboard" element={<DoctorDashBoard onDataReceived={handleDataReceived}/>}/>
      <Route exact path="/ViewOrders" element={<ViewOrders/>}/>
      <Route exact path="/viewDrugs" element={<ViewDrugs/>}/>
      </Route>

      </Routes>
      
      </Main>








     

      
    </div>
  )
}
