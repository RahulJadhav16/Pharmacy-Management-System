import React, { useEffect } from "react";
import StripeCheckout from "react-stripe-checkout";
import axios from "axios";
import { NotificationManager, NotificationContainer } from 'react-notifications';
import { useNavigate } from 'react-router-dom';
const StripeButton = ({ price }) => {
  const publishableKey = "pk_test_51NIOVESJkYBRGgMneRMuEzV0UdMpNR7JpeaFw7WVNdec1BbJUtyPtxVniKnNmDLQV7XtNRGVRqnaDbrE2BtYkF8v00rcioYcCk";
  const stripePrice = (price.itemDeatils.totalBill-price.itemDeatils.moneyPaid) * 100;
  const navigate = useNavigate();
 

 
  const onToken = (token) => {
    const jwttoken = localStorage.getItem("JwtToken");

    
    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${jwttoken}`, // Adding the token to the 'Authorization' header
      },
    });

    
    axios
      .post("http://localhost:8086/payment", {
        amount: stripePrice,
        token,
      })
      .then((response) => {
      
        
        
        NotificationManager.success("payment success");
      })
      .catch((error) => {
        console.log(error);
        NotificationManager.error('payment failed');
      });
      NotificationManager.warning("Wait Dont't refersh the page we are updating payment status");
      
     
        //Updateing the payment status
      axiosInstance
      .put("http://localhost:9091/doctor/makePayment",{
        pickupId:price.itemDeatils.pickupId,
        totalBill:price.itemDeatils.totalBill,
        pickupdate:price.itemDeatils.pickupdate,
        paymentStatus:true,
        moneyPaid:price.itemDeatils.totalBill-price.itemDeatils.moneyPaid,
        orders:price.itemDeatils.orders

      })
      .then((response) => {
        console.log(response.data);

        
      })

      .catch((error) => {
        console.log(error);
       
      });
      
      //Adding payment details to the database
      console.log("Adding details --------------------------------")
      axios
      .post("http://localhost:9091/pickupDoctor/addPaymentDetails",{
        orderId:price.itemDeatils.pickupId,
        amountPaid:price.itemDeatils.totalBill-price.itemDeatils.moneyPaid,
        paymentDate:"",
        doctorMail:localStorage.getItem("UserEmailId")

      })
      .then((response) => {
        
        console.log(response.data);
        navigate("/pickupOrder")

        
      })

      .catch((error) => {
        console.log(error);
       
      });

      
      

      






  };

  return (
    <>
    <NotificationContainer/>
    <StripeCheckout
      amount={stripePrice}
      label="Pay Now"
      name="MedWise"
      image=""
      description={`Your total is ${price.itemDeatils.totalBill-price.itemDeatils.moneyPaid }`}
      panelLabel="Pay Now"
      token={onToken}
      stripeKey={publishableKey}
      currency="INR"
    />
    </>
  );
};

export default StripeButton;