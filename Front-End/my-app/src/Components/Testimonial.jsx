import React from 'react'
import Footer from './Footer'

export default function Testimonial() {
  return (
    <div>
        <>
  {/* Carousel wrapper */}
  <div
    id="carouselMultiItemExample"
    className="carousel slide carousel-dark text-center"
    data-mdb-ride="carousel"
    style={{marginTop:"120px"}}
  >
   
    {/* Inner */}
    <div className="carousel-inner py-4">
      {/* Single item */}
      <div className="carousel-item active">
        <div className="container">
          <div className="row">
            <div className="col-lg-4">
              <img
                className="rounded-circle shadow-1-strong mb-4"
                src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(1).webp"
                alt="avatar"
                style={{ width: 150 }}
              />
              <h5 className="mb-3">Dr. Emily Watson</h5>
              <p>Doctor</p>
              <p className="text-muted">
                <i className="fas fa-quote-left pe-2" />
               "As a doctor, I greatly appreciate the convenience and efficiency of the pharmacy management system. It allows me to easily browse through the available drugs, place orders, and track their status. The system saves me valuable time and ensures accurate medication dispensing. Highly recommended!" 
              </p>
              <ul className="list-unstyled d-flex justify-content-center text-warning mb-0">
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
              </ul>
            </div>
            <div className="col-lg-4 d-none d-lg-block">
              <img
                className="rounded-circle shadow-1-strong mb-4"
                src={require('../Assets/doctor2.png')}
                alt="avatar"
                style={{ width: 150 }}
              />
              <h5 className="mb-3">Dr. Sarah Thompson</h5>
              <p>Doctor</p>
              <p className="text-muted">
                <i className="fas fa-quote-left pe-2" />
                "I have been using the MedWise for several months now, and it has made a notable difference in my practice. Placing orders for my patients is a breeze, and I can easily track the progress of each order. The system's comprehensive drug information and easy-to-use interface make it an indispensable tool for any healthcare professional."
              </p>
              <ul className="list-unstyled d-flex justify-content-center text-warning mb-0">
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
                <li>
                  <i className="fas fa-star-half-alt fa-sm" />
                </li>
              </ul>
            </div>
            <div className="col-lg-4 d-none d-lg-block">
              <img
                className="rounded-circle shadow-1-strong mb-4"
                src={require('../Assets/doctor1.jpg')}
                alt="avatar"
                style={{ width: 150 }}
              />
              <h5 className="mb-3">Mr. John Anderson</h5>
              <p>Manager</p>
              <p className="text-muted">
                <i className="fas fa-quote-left pe-2" />
                "Being an admin user, the pharmacy management system has revolutionized the way I handle operations. From managing drug inventory to verifying orders and generating sales reports, everything is seamlessly integrated. The system is user-friendly, robust, and has significantly improved our pharmacy's efficiency."
              </p>
              <ul className="list-unstyled d-flex justify-content-center text-warning mb-0">
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
                <li>
                  <i className="fas fa-star fa-sm" />
                </li>
                <li>
                  <i className="far fa-star fa-sm" />
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
     
    </div>
    
  </div>
 
</>
<Footer/>

 




      
    </div>
  )
}
