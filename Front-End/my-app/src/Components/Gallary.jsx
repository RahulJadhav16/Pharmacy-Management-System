import React from 'react'
import "./CSS/gallary.css"
import Footer from './Footer'
export default function Gallary() {
  return (
       <>
        <div className="area">
        <ul className="circles">
          <li></li>
          <li></li>
          <li></li>
        </ul>
        <div className='container'>
        <div class="d-flex justify-content-center flex-wrap gallary">
        <h1>We have wide range of drugs</h1>
        </div>
        <div class="d-flex justify-content-around flex-wrap">
    <div className="card my-4" style={{width: "18rem"}}>
  <img className="card-img-top" src={require('../Assets/Aspirin.jpg')} alt="Card image cap"/>
  <div className="card-body">
  </div>
</div>

<div className="card my-4" style={{width: "18rem"}}>
  <img className="card-img-top" src={require('../Assets/Amoxicillin.jpg')} alt="Card image cap"/>
  <div className="card-body">
  </div>
</div>

<div className="card my-4" style={{width: "18rem"}}>
  <img className="card-img-top" src={require('../Assets/Cetirizine.jpg')} alt="Card image cap"/>
  <div className="card-body">
  </div>
</div>

<div className="card my-4" style={{width: "18rem"}}>
  <img className="card-img-top" src={require('../Assets/Ibuprofen.jpg')} alt="Card image cap"/>
  <div className="card-body">
  </div>
</div>

<div className="card" style={{width: "18rem"}}>
  <img className="card-img-top" src={require('../Assets/Lisinopril.jpg')} alt="Card image cap"/>
  <div className="card-body">
  </div>
</div>
<div className="card" style={{width: "18rem"}}>
  <img className="card-img-top" src={require('../Assets/Metformin.jpg')} alt="Card image cap"/>
  <div className="card-body">
  </div>
</div>

<div className="card" style={{width: "18rem"}}>
  <img className="card-img-top" src={require('../Assets/paracetamol.jpg')} alt="Card image cap"/>
  <div className="card-body">
  </div>
</div>

<div className="card" style={{width: "18rem"}}>
  <img className="card-img-top" src={require('../Assets/atorvastatin.jpg')} alt="Card image cap"/>
  <div className="card-body">
  </div>
</div>
</div>



    </div>
    </div>
    </>
  )
}
