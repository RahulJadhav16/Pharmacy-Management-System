
import React, { useState,useEffect ,useRef} from 'react'
import { Link } from 'react-router-dom';
import axios  from 'axios';
import { useNavigate } from 'react-router-dom';
import EmojiPicker from 'emoji-picker-react';

export default function ContactAdmin() {
  const [data,setData]=useState([]);
  const bottomRef = useRef(null);
  const [msg,setMsg]=useState(" ");

  const handleEmojiClick = (emojiObject) => {
  
   setMsg((prevMsg) => prevMsg + emojiObject.emoji);
    // console.log(emojiObject.emoji);
  };
  



  const token = localStorage.getItem("JwtToken");
  const storedData = localStorage.getItem("userData");
  const parsedData = JSON.parse(storedData);
  


  //Seting the token
  const axiosInstance = axios.create({
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  const handelRefresh=()=>{
    axiosInstance.get("http://localhost:9091/doctor/getAllMsg")
    .then((response)=>{
        const updatedData = response.data.map(item => {
            const date = new Date(item.date);
            const formattedDate = date.toISOString().split('T')[0];
            return { ...item, date: formattedDate };
          });

        console.log(updatedData);
        setData(updatedData);
        
        


    })
    .catch(function (error) {
        console.log(error);
        
      });

}

 // Function to scroll to the bottom
 const scrollToBottom = () => {
  bottomRef.current.scrollIntoView({ behavior: 'smooth', block: 'end' });
};


const handelMsgType=(e)=>{
  console.log(e.target.value);
  setMsg(e.target.value);


}


const handelSendmsg=()=>{
   
  
  if(msg===" " || msg==="")
  {
      var userdataString = localStorage.getItem("userData");
      var userData = JSON.parse(userdataString);

     console.log("Empty msg");
     console.log(userData.name)
  }
  else{
      console.log("Sent");
  
const today = new Date();
const year = today.getFullYear();
const month = String(today.getMonth() + 1).padStart(2, '0');
const day = String(today.getDate()).padStart(2, '0');
const formattedDate = `${year}-${month}-${day}`;

// Get current time
const hours = today.getHours();
const minutes = String(today.getMinutes()).padStart(2, '0');
const seconds = String(today.getSeconds()).padStart(2, '0');

const formattedTime = `${hours}:${minutes}:${seconds}`;

const userdataString = localStorage.getItem("userData");
const userData = JSON.parse(userdataString);



      axiosInstance.post("http://localhost:9091/doctor/crateMsg",{
          date:formattedDate,
          time:formattedTime,
          personID:userData.id,
          role:"DOCTOR",
          name:userData.name,
          message:msg

      })
  .then((response)=>{
      console.log(response)
      
      handelRefresh();
      scrollToBottom();

     



  })
  .catch(function (error) {
      console.log(error);
    });

    setMsg("");


  }

 
  
}


  useEffect(()=>{
    
    axiosInstance.get("http://localhost:9091/doctor/getAllMsg")
    .then((response)=>{

        const updatedData = response.data.map(item => {
            const date = new Date(item.date);
            const formattedDate = date.toISOString().split('T')[0];
            return { ...item, date: formattedDate };
          });

        console.log(updatedData);
        setData(updatedData);
       



    })
    .catch(function (error) {
        console.log(error);
       
      });

      const interval = setInterval(() => {
        handelRefresh();
      }, 1000);
  
      return () => clearInterval(interval);


},[])

  return (
    <div>
       <div className="my-5 mx-4 d-flex">
       <div>
          <div
            className="d-flex flex-column flex-shrink-0 p-3 bg-light"
            style={{ width: "280px" }}
          >
            <Link
              to={"/doctorDashboard"}
              className="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none"
            >
              <img
                className="bi me-2"
                width="40"
                height="32"
                src={require("../Assets/menu.png")}
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
        </div>
        <div >
  <EmojiPicker onEmojiClick={handleEmojiClick} />
</div>

       

        <div className="justify-content-around mx-3" >
        {data.length
  ? data.map((item, index) => (
      <div>
        <div className={`alert ${item.personID===parsedData.id ? 'alert-success' : 'alert-primary'}`} role="alert" ref={bottomRef}>

  <h4 class="alert-heading">{item.name} ({item.role}) {item.role==="ADMIN"?"✅":""}</h4>

  
  
  
  <p class="mb-0">{item.message}</p>
  <hr />
  <p>🕒{item.time} :<span>📅{item.date}</span></p> 
</div>
      </div>
    ))
  : <div style={{ marginLeft: "450px" }}>
      <img
        src={require("../Assets/empty-box.png")}
        alt="no data found"
        height="200px"
        width="200px"
      />
      <h5 style={{ marginLeft: "30px" }}> No data found!</h5>
    </div>
}
<div className="sticky-bottom bg-light p-3" >


<div class="input-group mb-3">
  <input
              onChange={handelMsgType}
              type="text"
              value={msg}
              className="form-control"
              placeholder="Your message goes here...."
              aria-label="Recipient's username"
              aria-describedby="button-addon2"
            />
  <div class="input-group-append">
    <button class="btn btn-outline-secondary" type="button" id="button-addon2" onClick={handelSendmsg}>➤</button>
  </div>
</div>


</div>



          
          



        </div>

    </div>


    </div>
  )
}
