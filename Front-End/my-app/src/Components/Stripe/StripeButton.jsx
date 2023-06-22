import React from "react";
import StripeCheckout from "react-stripe-checkout";
import axios from "axios";

const StripeButton = ({ price }) => {
  const publishableKey = "pk_test_51NIOVESJkYBRGgMneRMuEzV0UdMpNR7JpeaFw7WVNdec1BbJUtyPtxVniKnNmDLQV7XtNRGVRqnaDbrE2BtYkF8v00rcioYcCk";
  const stripePrice = price * 100;

  const onToken = (token) => {
    console.log(token);
    axios
      .post("http://localhost:8086/payment", {
        amount: stripePrice,
        token,
      })
      .then((response) => {
        console.log(response.data);
        console.log(stripePrice/100)
        alert("payment success");
      })
      .catch((error) => {
        console.log(error);
        alert("Payment failed");
      });
  };

  return (
    <StripeCheckout
      amount={stripePrice}
      label="Pay Now"
      name="MedWise"
      image=""
      description={`Your total is ${price}`}
      panelLabel="Pay Now"
      token={onToken}
      stripeKey={publishableKey}
      currency="INR"
    />
  );
};

export default StripeButton;