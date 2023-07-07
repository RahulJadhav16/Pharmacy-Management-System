import React from 'react'
import Footer from './Footer'

export default function Services() {
  return (
    <div>
        <div className="area" style={{height:"109vh"}}>
        <ul className="circles">
          <li></li>
          <li></li>
          <li></li>
        </ul>

        <div className="d-flex justify-content-center">
          <h1 className="slogen2 my-5">
          Welcome to MedWise
          </h1>
        </div>
       
        <div className="d-flex flex-wrap">

        <div className="d-flex flex-wrap justify-content-around">
       
        <img src={require('../Assets/services.jpg')} width="500px" height="600px"/>
        <div className='mx-5 my-5 servicesText'>
            <h4>Drug Management:</h4>
            <p style={{width:"400px"}}><li>Admin users can easily browse and view the complete list of drugs available in our pharmacy. They can access detailed information about each drug, including its name, dosage, and availability.</li>
            <li>Add/Edit Drugs: Admin users have the authority to add new drugs to our inventory or make necessary edits to existing drug information. This ensures accurate and up-to-date records of all medications.</li>
            <li>Delete Drugs: When a drug is no longer stocked or becomes obsolete, the admin user can remove it from our system to maintain an organized drug inventory.</li>
            </p>
        </div>

        <div className='mx-5 my-5 servicesText'>
            <h4>Order Management:</h4>
            <p style={{width:"400px"}}><li>View Orders: Admin users can monitor all orders placed by doctor users. They can view the details of each order, including the drugs requested, quantities, and timestamps. This helps streamline the order fulfillment process.</li>
            <li>Order Verification: Admin users are responsible for verifying the validity of each order placed by doctors. They can review the requested drugs, quantities, and patient information to ensure accurate dispensing.</li>
            <li>Picked Up Orders: Once an order has been verified and picked up by the patient or doctor, the admin user can move it to the "Picked Up Orders" section for better order tracking and management.</li>
            </p>
        </div>

      

      </div>
      </div>
      </div>
      <Footer/>
    </div>
  )
}
