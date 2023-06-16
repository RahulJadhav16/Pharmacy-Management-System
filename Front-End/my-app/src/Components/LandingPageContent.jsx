import React from 'react'
import Footer from './Footer'
export default function LandingPageContent() {
  return (
    <div>
        <div className="area">
        <ul className="circles">
          <li></li>
          <li></li>
          <li></li>
        </ul>

        <div className="d-flex justify-content-center">
          <h1 className="slogen my-5">
            Empowering doctors with seamless medication procurement
          </h1>
        </div>
        <div className="main">
          <div className="my-4 image">
            <img
              src={require("../Assets/landing.jpg")}
              width="900px"
              height="500px"
              alt=""
            />
          </div>
          <div className="content my-5">
            <h1>
              Why choose us <span style={{ color: "orange" }}>?</span>
            </h1>
            <div
              className="d-flex justify-content-between"
              style={{ height: "200px" }}
            >
              <div
                className="card mx-1 cards-container first-card"
                style={{ width: "100%", height: "100%" }}
              >
                <div className="card-body">
                  <h5 className="card-title">1</h5>
                  <h6 className="card-subtitle mb-2 text-muted">
                  Extensive Medication Catalog
                  </h6>
                  <p className="card-text">We offer an extensive catalog of medications, ensuring doctors have access to a wide range of pharmaceutical products to meet your patients' needs. </p>
                </div>
              </div>
              <div
                className="card mx-2 first-card"
                style={{ width: "100%", height: "100%" }}
              >
                <div className="card-body">
                  <h5 className="card-title">2</h5>
                  <h6 className="card-subtitle mb-2 text-muted">
                  Seamless Ordering Process
                  </h6>
                  <p className="card-text">
                  MedWise provides a streamlined and user-friendly ordering process. Doctors can easily browse medications, place orders, and track their status.
                  </p>
                </div>
              </div>
            </div>
            <div
              className="d-flex justify-content-between my-2"
              style={{ height: "200px" }}
            >
              <div
                className="card mx-1 cards-container first-card"
                style={{ width: "100%", height: "100%" }}
              >
                <div className="card-body">
                  <h5 className="card-title">3</h5>
                  <h6 className="card-subtitle mb-2 text-muted">
                  Competitive Pricing
                  </h6>
                  <p className="card-text">We offer competitive pricing on medications, allowing doctors to maximize their budget without compromising on quality.</p>
                </div>
              </div>
              <div
                className="card mx-2 first-card"
                style={{ width: "100%", height: "100%" }}
              >
                <div className="card-body">
                  <h5 className="card-title">4</h5>
                  <h6 className="card-subtitle mb-2 text-muted">
                  Reliable Delivery Services
                  </h6>
                  <p className="card-text">
                  We prioritize timely and reliable medication deliveries. Doctors can rely on our efficient delivery network to receive their orders promptly.
                  </p>
                </div>
              </div>
            </div>
            <div
              className="d-flex justify-content-between"
              style={{ height: "200px" }}
            >
              <div
                className="card mx-1 cards-container first-card"
                style={{ width: "100%", height: "100%" }}
              >
                <div className="card-body">
                  <h5 className="card-title">5</h5>
                  <h6 className="card-subtitle mb-2 text-muted">
                  Exceptional Customer Support:
                  </h6>
                  <p className="card-text">We provide dedicated customer support to assist doctors throughout their medication procurement journey.</p>
                </div>
              </div>
              <div
                className="card mx-2 first-card"
                style={{ width: "100%", height: "100%" }}
              >
                <div className="card-body">
                  <h5 className="card-title">6</h5>
                  <h6 className="card-subtitle mb-2 text-muted">
                  Compliance and Safety
                  </h6>
                  <p className="card-text">
                  We prioritize patient safety and adhere to strict quality control standards.
                  </p>
                </div>
              </div>
            </div>

            
          </div>
        </div>
      </div>
      <Footer/>
    </div>
  )
}
