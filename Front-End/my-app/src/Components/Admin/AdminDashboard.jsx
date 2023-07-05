import React from 'react'
import SideBarAdmin from './SideBarAdmin'
import { useEffect } from 'react';

import "./CSS/AdminDashboard.css"
import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import LoadingBar from 'react-top-loading-bar'
export default function AdminDashboard({onDataReceived}) {
  const [progress, setProgress] = useState(0)
    const navigate = useNavigate();
    const token = localStorage.getItem("JwtToken");
    

    //setting the usestates
    const [drugsquantity,setDrugsquantity]=useState(0);
    const [orderQuantity,setOrderQuantity]=useState(0);
    const [pickupQuantity,setPickupQuantity]=useState(0);
    const [expiredquantity,setExpiredquantity]=useState(0);
    const [outofstockQuantity,setOutofstockQuantity]=useState(0);
    const[money,setmoney]=useState(0);

    //Seting the token
    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${token}`, 
      },
    });

    useEffect(() => {
      setProgress(25)
        const storedData = localStorage.getItem("userData");
        const parsedData = JSON.parse(storedData);
        if(parsedData?.role==="ADMIN")
        {
          navigate('/adminDashboard');
        }
        else{
            navigate('/doctorDashboard');
        }

        //From here i am calling all microservices to get info
        axiosInstance
        .get("http://localhost:9091/adminOprations/getAllStock")
        .then(function (response) {
          const date = new Date();
          let expire=0;
          let outofstock=0;
          
          response.data.map((e)=>{
            if(e.status==="Expired")
            {
              expire+=1;
            }
            if(e.quantity<=0)
            {
              outofstock+=1;
            }
            
          })
          setExpiredquantity(expire);
          setOutofstockQuantity(outofstock);



        })
        .catch(function (error) {
          console.log(error);
        })

        axiosInstance
        .get("http://localhost:9091/adminOprations/allOrders")
        .then(function (response) {
          setOrderQuantity(response.data.length)
          setProgress(100)
        })
        .catch(function (error) {
          console.log(error);
          setProgress(100)
        })

        axiosInstance
        .get("http://localhost:9091/adminOprations/getalldrugs")
        .then(function (response) {
          setDrugsquantity(response.data.length)
         
        })
        .catch(function (error) {
          console.log(error);
          
        })

        axiosInstance
        .get("http://localhost:9091/adminOprations/getAllPickups")
        .then(function (response) {
          setPickupQuantity(response.data.length)
          setProgress(100)
        })
        .catch(function (error) {
          console.log(error);
          setProgress(100)
        })

        axiosInstance
        .get("http://localhost:9091/adminOprations/getAllPaymentDetails")
        .then(function (response) {
          let totalmoney=0;
          response.data.map((e)=>{
            totalmoney+=e.amountPaid;
          })

          setmoney(totalmoney)
        })
        .catch(function (error) {
          console.log(error);
        })

    },[])

    const handelDrugInventory=()=>{
      navigate('/drugInventory')

    }
    const handelExpiredDrugsclicked=()=>{
      navigate('/expiredDrugs')
    }
    const handelPickupClick=()=>{
      navigate('/adminPickup')
    }
    const handelMoneyClick=()=>{
      navigate('/adminMoney')
    }
    const handelOrderClick=()=>{
      navigate('/Adminorder')
    }
    const handelDrugOutofStock=()=>{
      navigate('/drugOutofStock')
    }
    const handelDrug=()=>{
      navigate('/adminDrug')

    }
    const handelanalyticsClick=()=>{
      navigate("/analytics")
    }




    const handleDataReceived=() => {
        const obj={
          path:'/login',
          name:"Login"
        }
        onDataReceived({obj});
       } 
    
       

    


  return (
    <div className="d-flex mx-1 my-1">
       <LoadingBar
        color='#f11946'
        progress={progress}
        onLoaderFinished={() => setProgress(0)}
      />
      <SideBarAdmin onDataReceived={handleDataReceived} />
    <div className="d-flex justify-content-between my-4 flex-wrap">
      
      <div className="cookieCard cookieCard-1 View-Drugs" style={{cursor: "pointer"}} onClick={handelDrug}>
        <h1 className="cookieHeading">💊 View Drugs [{drugsquantity}]</h1>
        <h2 className="cookieDescription">Explore and access the complete list of Drugs, Add drugs and edit.</h2>
      </div>
  
      <div className="cookieCard cookieCard-2 View-Orders" style={{cursor: "pointer"}}onClick={handelOrderClick}>
        <h1 className="cookieHeading">📦 View Orders [{orderQuantity}]</h1>
        <h2 className="cookieDescription">View all orders and verify the order status</h2>
      </div>
  
      <div className="cookieCard cookieCard-3 Pickup" style={{cursor: "pointer"}} onClick={handelPickupClick}>
        <h1 className="cookieHeading">🚚 Pickup [{pickupQuantity}]</h1>
        <h2 className="cookieDescription">View orders in pickup section and check payment status</h2>
      </div>
  
      <div className="cookieCard cookieCard-3 Pickup" style={{cursor: "pointer"}} onClick={handelExpiredDrugsclicked}>
        <h1 className="cookieHeading">📅 Expired Drugs [{expiredquantity}]</h1>
        
      </div>
  
      <div className="cookieCard cookieCard-3 Pickup my-3" style={{cursor: "pointer"}} onClick={handelDrugOutofStock}>
        <h1 className="cookieHeading">Drugs Out of Stock [{outofstockQuantity}]</h1>
        
      </div>
      
      <div className="cookieCard cookieCard-3 Pickup my-3" onClick={handelDrugInventory} style={{cursor: "pointer"}}>
        <h1 className="cookieHeading">🏢Drug Inventory</h1>
        <h1 className="cookieDescription">Manage your drug inventory add drugs, add suppliers</h1>
      </div>
  
      <div className="cookieCard cookieCard-3 Pickup my-3"style={{cursor: "pointer"}} onClick={handelMoneyClick}>
        <h1 className="cookieHeading">₹{money}</h1>
        <h2 className="cookieHeading">Total money received</h2>
      </div>
  
      <div className="cookieCard cookieCard-3 Pickup my-3" onClick={handelanalyticsClick} style={{cursor: "pointer"}}>
        <h1 className="cookieHeading">📈 Analytics</h1>
        <h2 className="cookieDescription">View orders in pickup section and check payment status</h2>
      </div>
    </div>
  </div>
  
      
    
  )
}
