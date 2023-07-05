import React from 'react'
import axios from 'axios';
import { useEffect,useState } from 'react';
import SideAdminRest from './SideAdminRest'
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'react-chartjs-2';
ChartJS.register(ArcElement, Tooltip, Legend);

export default function Analytics() {

    const[verifiedOrders,setVerifiedOrders]=useState(0);
    const[pendingOrders,setPendingOrders]=useState(0);
    const[avilableDrugs,setavilableDrugs]=useState(0);
    const[outofstockdrugs,setOutofstockdrugs]=useState(0);
    const[expiredDrugs,setExpiredDrugs]=useState(0);
    const[completedPayment,setCompletedPayment]=useState(0);
    const[pendingPayment,setPendingPayment]=useState(0);
    const[expiredPickup,setExpiredPickup]=useState(0);
    const[totalMoney,setTotalMoney]=useState(0);
    const[moneyrecived,setMoneyRecived]=useState(0);
    const[pendingMoney,setPendingMoney]=useState(0);


    
    const token = localStorage.getItem("JwtToken");
  const axiosInstance = axios.create({
    headers: {
      Authorization: `Bearer ${token}`, 
    },
  });



    useEffect(()=>{
    axiosInstance
    .get("http://localhost:9091/adminOprations/allOrders")
    .then(function (response) {
      console.log(response.data);
      let vorder=0;
      let porder=0;
     response.data.map((e)=>{
        if(e.status)
        {
            vorder+=1;
        }
        else{
            porder+=1;
        }
      })
      setVerifiedOrders(vorder);
      setPendingOrders(porder);
    })
    .catch(function (error) {
      console.log(error);
      
    });

    axiosInstance
    .get("http://localhost:9091/adminOprations/getAllStock")
    .then(function (response) {
      console.log(response.data);
      let outOfStockQuantity=0;
      let avilableQuantiry=0
      let expiredQuantity=0
      
     response.data.map((e)=>{
        if(e.quantity<=0)
        {
            outOfStockQuantity+=1;
        }
        if(e.quantity>0 && e.status==="Not expired")
        {
            avilableQuantiry +=1;
        }
        if(e.status==="Expired")
        {
            expiredQuantity+=1;
        }
        
      })
      setavilableDrugs(avilableQuantiry)
      setOutofstockdrugs(outOfStockQuantity)
      setExpiredDrugs(expiredQuantity)    
    })
    .catch(function (error) {
      console.log(error);
      
    });

    axiosInstance
    .get("http://localhost:9091/adminOprations/getAllPickups")
    .then(function (response) {
      console.log(response.data);
      let totalMoney=0;
      let moneyGet=0;
      let moneypending=0;
      let completed=0;
      let pending=0;
      let expiredpickup=0;
      const date = new Date();
     response.data.map((e)=>{
        totalMoney+=e.totalBill;
        moneyGet+=e.moneyPaid;
        if(e.paymentStatus)
        {
            completed+=1;
        }
        if(e.paymentStatus==false){
            pending+=1;
        }
        if(e.pickupdate<date)
        {
            expiredpickup+=1;
        }

        
      })
      moneypending=totalMoney-moneyGet;
      setCompletedPayment(completed);
      setPendingPayment(pending);
      setExpiredPickup(expiredpickup)
      setTotalMoney(totalMoney)
      setMoneyRecived(moneyGet)
      setPendingMoney(moneypending)

    })
    .catch(function (error) {
      console.log(error);
      
    });





    },[])


    //  Data sets 
    const orderData = {
        labels: ['Verified orders', 'Pending orders'],
        datasets: [
          {
            label: 'Orders',
            data: [verifiedOrders, pendingOrders],
            backgroundColor: [
              
              'rgba(75, 192, 192, 0.2)',
              
              'rgba(255, 159, 64, 0.2)',
            ],
            borderColor: [
              
                'rgba(75, 192, 192, 1)',
             
              'rgba(255, 159, 64, 1)',
            ],
            borderWidth: 1.5,
          },
        ],
      };

      const DrugData = {
        labels: ['Available drugs', 'Out of stock drugs','Expired drugs'],
        datasets: [
          {
            label: 'Drugs',
            data: [avilableDrugs, outofstockdrugs,expiredDrugs ],
            backgroundColor: [
              
              'rgba(75, 192, 192, 0.2)',
              'rgba(255, 159, 64, 0.2)',
              'rgba(255, 99, 132, 0.2)',
            ],
            borderColor: [
              
                'rgba(75, 192, 192, 1)',
             
              'rgba(255, 159, 64, 1)',
              'rgba(255, 99, 132, 1)',
            ],
            borderWidth: 1.5,
          },
        ],
      };

      const PickupData = {
        labels: ['Money Paid Orders', 'Payment Pending Orders','Expired Order'],
        datasets: [
          {
            label: 'Pickup Order',
            data: [completedPayment, pendingPayment,expiredPickup ],
            backgroundColor: [
              
              'rgba(75, 192, 192, 0.2)',
              'rgba(255, 159, 64, 0.2)',
              'rgba(255, 99, 132, 0.2)',
            ],
            borderColor: [
              
                'rgba(75, 192, 192, 1)',
             
              'rgba(255, 159, 64, 1)',
              'rgba(255, 99, 132, 1)',
            ],
            borderWidth: 1.5,
          },
        ],
      };

      const MoneyData = {
        labels: ['Total Money', 'Money Received','Pending Payments'],
        datasets: [
          {
            label: '₹',
            data: [totalMoney, moneyrecived,pendingMoney ],
            backgroundColor: [
              
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 206, 86, 0.2)',
            ],
            borderColor: [
              
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 206, 86, 1)',
            ],
            borderWidth: 1.5,
          },
        ],
      };




  return (
    <div>
      <div className="my-5 mx-4 d-flex flex-wrap">
        <SideAdminRest />
        
        <div style={{height:"350px",width:"350px",marginLeft:"120px"}}>
        <Doughnut data={orderData} />
        <div  style={{marginLeft:"140px"}}>
        <h4>Orders</h4>
        </div>
        </div>

        <div style={{height:"350px",width:"350px",marginLeft:"130px"}}>
        <Doughnut data={DrugData} />
        <div  style={{marginLeft:"140px"}}>
        <h4>Drugs</h4>
        </div>
        </div>
        

        <div className='my-5' style={{height:"350px",width:"350px",marginLeft:"390px"}}>
        <Doughnut data={PickupData} />
        <div  style={{marginLeft:"140px"}}>
        <h4>Pickup</h4>
        </div>
        </div>

        <div className='my-5' style={{height:"350px",width:"350px",marginLeft:"150px"}}>
        <Doughnut data={MoneyData} />
        <div  style={{marginLeft:"140px"}}>
        <h4>Payment</h4>
        </div>
        </div>


        
        </div>
    </div>
  )
}
