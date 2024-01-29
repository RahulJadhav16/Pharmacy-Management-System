import React from 'react'
import "./CSS/contactus.css"
import Footer from './Footer'
import { useState } from 'react'
import LoadingBar from 'react-top-loading-bar'
import axios from 'axios';
import { NotificationManager} from 'react-notifications';
import { NotificationContainer} from 'react-notifications';
import 'react-notifications/lib/notifications.css';



export default function Contactus() {
  const [progress, setProgress] = useState(0)

  let[FirstName,setFirstName]=useState("");
  let[EmailId,setEmailId]=useState("");
  let[MObNO,setMObNO]=useState("");
  let[Message,setMessage]=useState("");


  const handelSubmit=(e)=>
  {
    setProgress(10)
    axios.post('http://localhost:9091/adminOprations/createContactUs',{
      name:FirstName,
      email:EmailId,
      mobNo:MObNO,
      message:Message


    }).then((response)=>{
      console.log(response)
      setProgress(100)
      NotificationManager.success("Your request has been sent!", 'Success');

    })
    .catch(function (error) {
      console.log(error);
    })




  }


  const handelFirstNameChange=(e)=>{
    setFirstName(e.target.value);
    // console.log(e.target.value);
   
  }

  const handelEmailId=(e)=>{
    setEmailId(e.target.value);
    //console.log(e.target.value);
   
  }

  const handelMobNo=(e)=>{
    setMObNO(e.target.value);
    //console.log(e.target.value);
   
  }

  const handelMessage=(e)=>{
    setMessage(e.target.value);
    //console.log(e.target.value);
   
  }

  return (
    <div>
       <NotificationContainer/>
      <LoadingBar
        color='#f11946'
        progress={progress}
        onLoaderFinished={() => setProgress(0)}
      />
        
        <div className="contact_us_6">
  <div className="responsive-container-block container">
    <form className="form-box">
      <div className="container-block form-wrapper">
        <div className="mob-text">
          <p className="text-blk contactus-head">Get in Touch</p>
          <p className="text-blk contactus-subhead">
           
           </p>
        </div>
        <div className="responsive-container-block" id="i2cbk">
          <div
            className="responsive-cell-block wk-tab-12 wk-mobile-12 wk-desk-12 wk-ipadp-12"
            id="i10mt-3"
          >
            <p className="text-blk input-title">FIRST NAME</p>
            <input
              className="input"
              id="ijowk-3"
              name="FirstName"
              placeholder="Please enter first name..."
              onChange={handelFirstNameChange}
              required
            />
          </div>
          <div
            className="responsive-cell-block wk-tab-12 wk-mobile-12 wk-desk-12 wk-ipadp-12"
            id="ip1yp"
          >
            <p className="text-blk input-title">EMAIL</p>
            <input
              className="input"
              id="ipmgh-3"
              name="Email"
              placeholder="Please enter email..."
              onChange={handelEmailId}
              required
            />
          </div>
          <div
            className="responsive-cell-block wk-tab-12 wk-mobile-12 wk-desk-12 wk-ipadp-12"
            id="ih9wi"
          >
            <p className="text-blk input-title">PHONE NUMBER</p>
            <input
              className="input"
              id="imgis-3"
              name="PhoneNumber"
              placeholder="Please enter phone number..."
              onChange={handelMobNo}
              required
            />
          </div>
          <div
            className="responsive-cell-block wk-tab-12 wk-mobile-12 wk-desk-12 wk-ipadp-12"
            id="i634i-3"
          >
            <p className="text-blk input-title">WHAT DO YOU HAVE IN MIND ?</p>
            <textarea
              className="textinput"
              id="i5vyy-3"
              placeholder="Please enter query..."
              defaultValue={""}
              onChange={handelMessage}
              required
            />
          </div>
        </div>
        <button className="submit-btn" id="w-c-s-bgc_p-1-dm-id-2" onClick={handelSubmit}>
          Submit
        </button>
      </div>
    </form>
    <div
      className="responsive-cell-block wk-desk-7 wk-ipadp-12 wk-tab-12 wk-mobile-12"
      id="i772w"
    >
      <div className="map-part">
        <p className="text-blk map-contactus-head" id="w-c-s-fc_p-1-dm-id">
          Reach us at
        </p>
        <p className="text-blk map-contactus-subhead">
        Street:  291 Purbai Building, J V Road, Ghatkopar (west)
       City:   Mumbai

State/province/area:    Maharashtra

Phone number  02225151919

Zip code  400086

Country calling code  +91

Country  India
        
        </p>
        <div className="social-media-links mob">
          <a className="social-icon-link" href="#" id="ix94i-2-2">
            <img
              className="link-img image-block"
              src="https://workik-widget-assets.s3.amazonaws.com/Footer1-83/v1/images/Icon-twitter.png"
            />
          </a>
          <a className="social-icon-link" href="#" id="itixd">
            <img
              className="link-img image-block"
              src="https://workik-widget-assets.s3.amazonaws.com/Footer1-83/v1/images/Icon-facebook.png"
            />
          </a>
          <a className="social-icon-link" href="#" id="izxvt">
            <img
              className="link-img image-block"
              src="https://workik-widget-assets.s3.amazonaws.com/Footer1-83/v1/images/Icon-google.png"
            />
          </a>
          <a className="social-icon-link" href="#" id="izldf-2-2">
            <img
              className="link-img image-block"
              src="https://workik-widget-assets.s3.amazonaws.com/Footer1-83/v1/images/Icon-instagram.png"
            />
          </a>
        </div>
        <div >
        <iframe
  src="https://www.google.com/maps/embed?pb=!1m23!1m12!1m3!1d28306.066664989594!2d72.49016013675691!3d23.074613729914958!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m8!3e6!4m0!4m5!1s0x395e9cb83bf0fa8d%3A0xbd84e18466200012!2sB%2F607%2C%20The%20capital%2C%20Science%20City%20Rd%2C%20opposite%20Hetarth%20Party%20Plot%2C%20Ahmedabad%2C%20380060!3m2!1d23.0753478!2d72.5084005!5e0!3m2!1sen!2sin!4v1688707348298!5m2!1sen!2sin"
  width={600}
  height={450}
  style={{ border: 0 }}
  allowFullScreen=""
  loading="lazy"
  referrerPolicy="no-referrer-when-downgrade"
/>


        </div>
      </div>
    </div>
  </div>
</div>

    <Footer/>
    </div>
  )
}
