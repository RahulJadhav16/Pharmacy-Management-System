import React from 'react'
import SideAdminRest from './SideAdminRest'
import { useEffect,useState,useRef } from "react";
import axios from "axios";
import LoadingBar from 'react-top-loading-bar'
import EmojiPicker from 'emoji-picker-react';
export default function Requests() {
    const [progress, setProgress] = useState(0)
    const [data,setData]=useState([]);
    const [msg,setMsg]=useState(" ");

    

    const userdataString = localStorage.getItem("userData");
    const userData = JSON.parse(userdataString);

   const id=JSON.stringify(userData.id);
    


    const token = localStorage.getItem("JwtToken");
    const bottomRef = useRef(null);

  // Function to scroll to the bottom
  const scrollToBottom = () => {
    bottomRef.current.scrollIntoView({ behavior: 'smooth', block: 'end' });
  };

  const handleEmojiClick = (emojiObject) => {
  
    setMsg((prevMsg) => prevMsg + emojiObject.emoji);
     // console.log(emojiObject.emoji);
   };
    

    //Seting the token
    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    const handelRefresh=()=>{
        axiosInstance.get("http://localhost:9091/adminOprations/getAllMsg")
        .then((response)=>{
            const updatedData = response.data.map(item => {
                const date = new Date(item.date);
                const formattedDate = date.toISOString().split('T')[0];
                return { ...item, date: formattedDate };
              });

            console.log(updatedData);
            setData(updatedData);
            
            setProgress(100)


        })
        .catch(function (error) {
            console.log(error);
            setProgress(100) 
          });

    }

    const handelSendmsg=()=>{
        setProgress(30) 
        
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


            axiosInstance.post("http://localhost:9091/adminOprations/crateMsg",{
                date:formattedDate,
                time:formattedTime,
                personID:userData.id,
                role:userData.role,
                name:userData.name,
                message:msg

            })
        .then((response)=>{
            console.log(response)
            setProgress(100) 
            handelRefresh();
            scrollToBottom();

           



        })
        .catch(function (error) {
            console.log(error);
            setProgress(100) 
          });


        }
        setMsg("");

       
        
    }

    const handelMsgType=(e)=>{
        console.log(e.target.value);
        setMsg(e.target.value);

    }

    

   

    // setInterval(()=>{
    //   console.log("I am")

    // },900000)

    useEffect(()=>{
        setProgress(10)
        axiosInstance.get("http://localhost:9091/adminOprations/getAllMsg")
        .then((response)=>{

            const updatedData = response.data.map(item => {
                const date = new Date(item.date);
                const formattedDate = date.toISOString().split('T')[0];
                return { ...item, date: formattedDate };
              });

            console.log(updatedData);
            setData(updatedData);
            setProgress(100)



        })
        .catch(function (error) {
            console.log(error);
            setProgress(100) 
          });


          const interval = setInterval(() => {
            handelRefresh();
          }, 1000);
      
          return () => clearInterval(interval);


    },[])


  return (
    <div>
        <LoadingBar
        color='#f11946'
        progress={progress}
        onLoaderFinished={() => setProgress(0)}
      />
         

      <div className="my-5 mx-4 d-flex">
        <SideAdminRest />
        <div>

        <EmojiPicker onEmojiClick={handleEmojiClick} />
        </div>

        <div className="justify-content-around mx-3" >
        {data.length
  ? data.map((item, index) => (
      <div>
        <div className={`alert ${item.personID===id ? 'alert-success' : 'alert-primary'}`} role="alert" ref={bottomRef}>

  <h4 class="alert-heading">{item.name} ({item.role}) {item.role==="ADMIN"?"✅":""}</h4>

  
  
  
  <p class="mb-0">{item.message}</p>
  <hr />
  <p>🕒{item.time} :<span>📅{item.date}</span></p> 
</div>
      </div>
    ))
  : <div style={{ marginLeft: "450px" }}>
      <img
        src={require("./Assets/empty-box.png")}
        alt="no data found"
        height="200px"
        width="200px"
      />
      <h5 style={{ marginLeft: "30px" }}> No data found!</h5>
    </div>
}
<div className="sticky-bottom bg-light p-3" >
<div class="input-group mb-3">
  {/* <input onChange={handelMsgType} type="text" class="form-control" placeholder="Your message goes here...." aria-label="Recipient's username" aria-describedby="button-addon2"/> */}
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
        
    </div >
  )
}
