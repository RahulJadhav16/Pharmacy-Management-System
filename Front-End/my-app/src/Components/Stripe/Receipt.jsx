import React, { useLayoutEffect, useRef, useState } from 'react'
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useReactToPrint } from "react-to-print";
import '../CSS/receipt.css'
export default function Receipt(props) {
   
    const [currentDate, setCurrentDate] = useState('');
    const storedData = localStorage.getItem("userData");
    const parsedData = JSON.parse(storedData);

    const [data,setData]=useState([]);
    const conponentPDF= useRef();

    const handelDownload=useReactToPrint({
       
        content:()=>conponentPDF.current,
        documentTitle:"Order_receipt",
        onAfterPrint:()=>alert("Pdf Downloaded")

    });

    


    useLayoutEffect(()=>{
        const date = new Date();
        setCurrentDate(date.toLocaleDateString());
        console.log("Receipt========");
        console.log(localStorage.getItem('paymentId'));
        console.log(localStorage.getItem('pickupId'));


        axios.get("http://localhost:9091/pickupAdmin/getByPickupId/"+localStorage.getItem('pickupId'))
        .then(function (response) {
            console.log("recipting")
            console.log(response.data);
            setData(response.data);
          })
          .catch((error) => {
            console.log(error);
           
          }); 


         



    },[])
  return (
    <div>
        <div className="my-5 mx-4 d-flex">
          <div
            className="d-flex flex-column flex-shrink-0 p-3 bg-light"
            style={{ width: "280px",height:"320px" }}
          >
            <Link
              to={"/doctorDashboard"}
              className="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none"
            >
              <img
                className="bi me-2"
                width="40"
                height="32"
                src={require("./Asset/menu.png")}
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
          <button type="button" class="btn btn-success mx-4" onClick={handelDownload} style={{height:"34px"}}>Download</button>
          <div ref={conponentPDF} style={{width:'100%',height:'100%'}}>
          <div className="col-md-12 mx-5">   
        <div className="row">
            
		
        <div className="receipt-main col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
            <div className="row">
    			<div className="receipt-header">
					<div className="col-xs-6 col-sm-6 col-md-6">
						<div className="receipt-left">
							
						</div>
					</div>
					<div className="col-xs-6 col-sm-6 col-md-6 text-right">
						<div className="receipt-right">
							<h5>MedWise</h5>
							<p>+91 9809878765 <i className="fa fa-phone"></i></p>
							<p>medwise@gmail.com <i className="fa fa-envelope-o"></i></p>
							<p>India <i className="fa fa-location-arrow"></i></p>
						</div>
					</div>
				</div>
            </div>
			
			<div className="row">
				<div className="receipt-header receipt-header-mid">
					<div className="col-xs-8 col-sm-8 col-md-8 text-left">
						<div className="receipt-right">
							<h5>{parsedData.name}</h5>
							<p><b>Mobile :</b>{parsedData.contact}</p>
							<p><b>Email :</b> {parsedData.email}</p>
							<p><b>Address :</b>{parsedData.address}</p>
						</div>
					</div>
					<div className="col-xs-4 col-sm-4 col-md-4">
						<div className="receipt-left">
							<p>Payment Id:{localStorage.getItem('paymentId')}</p>
						</div>
					</div>
				</div>
            </div>
			
            <div>
                <table className="table table-bordered">
                    <thead>
                        <tr>
                            <th>Description</th>
                            <th>Quantity</th> 
                        </tr>
                    </thead>
                    <tbody>
  {data.orders && data.orders.map((item) => (
    <tr key={item.id}>
      <td className="col-md-9">{item.drugName}</td>
      <td className="col-md-9">{item.quantity}</td>
    </tr>
  ))}
  <tr>
    <td className="text-right">
      <p>
        <strong>Total Amount: </strong>
      </p>
    </td>
    <td>
      <p>
        <strong><i className="fa fa-inr"></i> {data.totalBill}/-</strong>
      </p>
    </td>
  </tr>
  <tr>
    <td className="text-right">
      <h2><strong>Amount Paid: </strong></h2>
    </td>
    <td className="text-left text-danger">
      <h2><strong><i className="fa fa-inr"></i> {data.moneyPaid}/-</strong></h2>
    </td>
  </tr>
</tbody>

                </table>
            </div>
			
			<div className="row">
				<div className="receipt-header receipt-header-mid receipt-footer">
					<div className="col-xs-8 col-sm-8 col-md-8 text-left">
						<div className="receipt-right">
							<p><b>Date :{currentDate}</b></p>
							<h5 style={{color: "rgb(140, 140, 140)"}}>Thanks for shopping.!</h5>
						</div>
					</div>
					<div className="col-xs-4 col-sm-4 col-md-4">
						
					</div>
                    
				</div>
            </div>
			
        </div>    
	</div>
    
</div>
</div>

</div>

   
        </div>
        
      
    
  )
}
