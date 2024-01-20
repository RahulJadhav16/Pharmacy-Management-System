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
import PickupOrders from "./PickupOrders";
import NotFound from "./Errors/NotFound";
import Checkout from "./Stripe/Checkout";
import DoctorProfileUpdate from "./DoctorProfileUpdate";
import Receipt from "./Stripe/Receipt";
import AdminLogin from "./Admin/AdminLogin";
import AdminDashboard from "./Admin/AdminDashboard";
import AdminRoute from "./ProtectedRotes/AdminRoute";
import AdminProfileUpdate from "./Admin/AdminProfileUpdate";
import DrugInventory from "./Admin/DrugInventory";
import ExpiredDrugs from "./Admin/ExpiredDrugs";
import Pickup from "./Admin/Pickup";
import AdminMoney from "./Admin/AdminMoney";
import AdminViewOrders from "./Admin/AdminViewOrders";
import DrugOutOffStock from "./Admin/DrugOutOffStock";
import AdminDrugs from "./Admin/AdminDrugs";
import Analytics from "./Admin/Analytics";
import Services from "./Services";
import Contactus from "./Contactus";
import Testimonial from "./Testimonial";
import Gallary from "./Gallary";
import Requests from "./Admin/Requests";

export default function MainPage() {

   const jwtKey=localStorage?.getItem('JwtToken')
   const userData=localStorage?.getItem('UserEmailId')
   
   
    const obj={
        path:'/login',
        name:"Login"
      }
      
      let [childData, setChildData] = useState(obj);
      const[item, setItem]=useState('');

    
     
    
    
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

          const handelPaymentDetails=(item)=>{
            
            setItem(item)
            console.log("I am on the main page ")
            console.log(item);


          }
        
    

  return (
    <div>
      <Main>
      <NavBarL name={childData} />
      <Routes>
      <Route exact path="/" element={<LandingPageContent/>}/>
      <Route exact path="/login" element={<Login/>}/>
      <Route exact path="/doctor" element={<DoctorLogin onDataReceived={handleDataReceived}/>}/>
      <Route exact path="/signup" element={<DoctorSignup/>}/>
      <Route exact path="/contactus" element={<Contactus/>}/>
      <Route exact path="/services" element={<Services/>}/>
      <Route exact path="/testimonial" element={<Testimonial/>}/>
      <Route exact path="/gallary" element={<Gallary/>}/>
      <Route exact path="/admin" element={<AdminLogin onDataReceived={handleDataReceived}/>}/>



      <Route element={<PrivateRoute id={jwtKey}/>}>
      
      <Route exact path="/doctorDashboard" element={<DoctorDashBoard onDataReceived={handleDataReceived}/>}/>
      <Route exact path="/viewOrders" element={<ViewOrders/>}/>
      <Route exact path="/viewDrugs" element={<ViewDrugs/>}/>
      <Route exact path="/pickupOrder" element={<PickupOrders onDataReceived={handelPaymentDetails}/>}/>
      <Route exact path="/checkout" element={<Checkout itemDeatils={item}/>}/>
      <Route exact path="/updateProfile" element={<DoctorProfileUpdate/>}/>
      <Route exact path="/receipt" element={<Receipt itemDeatils={item}/>}/>
      
      </Route>

      <Route element={<AdminRoute id={userData}/>}>

      <Route exact path="/adminDashboard" element={<AdminDashboard onDataReceived={handleDataReceived} />}/>
      <Route exact path="/updateAdminProfile" element={<AdminProfileUpdate />}/>
      <Route exact path="/drugInventory" element={<DrugInventory />}/>
      <Route exact path="/expiredDrugs" element={<ExpiredDrugs />}/>
      <Route exact path="/adminPickup" element={<Pickup />}/>
      <Route exact path="/adminMoney" element={<AdminMoney />}/>
      <Route exact path="/Adminorder" element={<AdminViewOrders />}/>
      <Route exact path="/drugOutofStock" element={<DrugOutOffStock />}/>
      <Route exact path="/adminDrug" element={<AdminDrugs />}/>
      <Route exact path="/analytics" element={<Analytics />}/>
      <Route exact path="/requests" element={<Requests />}/>
      
      </Route>
      

      <Route path="*" element={<NotFound />} />

      </Routes>
      
      </Main>








     

      
    </div>
  )
}
