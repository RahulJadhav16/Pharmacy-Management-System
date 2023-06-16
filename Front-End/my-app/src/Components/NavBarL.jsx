import React from 'react'
import { Link } from 'react-router-dom';
export default function NavBarL() {
  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-light bg-light sticky-top ">
        <Link className="navbar-brand mx-5" to="/">
          <img
            src={require("../Assets/logo.jpg")}
            width="260"
            height="100"
            className="d-inline-block align-top"
            alt=""
          />
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            <li className="nav-item mx-5 navbar-brand ">
              <a className="nav-link txt" href="#">
                Services
              </a>
            </li>
            <li className="nav-item mx-4 navbar-brand ">
              <a className="nav-link txt" href="#">
                Gallery
              </a>
            </li>
            <li className="nav-item mx-4 navbar-brand ">
              <a className="nav-link txt" href="#">
                Testimonials
              </a>
            </li>
            <li className="nav-item mx-4 navbar-brand ">
              <a className="nav-link txt" href="#">
                Contact us
              </a>
            </li>
          </ul>
          <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
            <li className="nav-item mx-5 navbar-brand ">
                <Link className="nav-link txt" to="/login"><span className="sr-only">👤</span>Login</Link>
             
            </li>
          </ul>
        </div>
      </nav>
    </div>
  )
}
