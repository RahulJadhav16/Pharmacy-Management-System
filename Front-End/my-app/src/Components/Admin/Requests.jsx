import React from 'react'
import SideAdminRest from './SideAdminRest'
import { useEffect,useState } from "react";
import axios from "axios";
import LoadingBar from 'react-top-loading-bar'
export default function Requests() {
    const [progress, setProgress] = useState(0)
    const [data,setData]=useState([]);


    const token = localStorage.getItem("JwtToken");
    //Seting the token
    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    const handelRefresh=()=>{
        axiosInstance.get("http://localhost:9091/adminOprations/getAllContactus")
        .then((response)=>{
            console.log(response.data);
            setData(response.data);
            setProgress(100)


        })
        .catch(function (error) {
            console.log(error);
            setProgress(100) 
          });

    }

    

    const handleDeleteClick= (item) => () => {
       
        console.log(item);
        setProgress(10)
        axiosInstance.delete("http://localhost:9091/adminOprations/deleteContactus/"+item.id)
        .then((response)=>{
            console.log(response.data)
            handelRefresh();

            setProgress(100) 

        })
        .catch(function (error) {
            console.log(error);
            setProgress(100) 
          });

        

    }

    

    useEffect(()=>{
        setProgress(10)
        axiosInstance.get("http://localhost:9091/adminOprations/getAllContactus")
        .then((response)=>{
            console.log(response.data);
            setData(response.data);
            setProgress(100)


        })
        .catch(function (error) {
            console.log(error);
            setProgress(100) 
          });


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

        <div className="d-flex flex-wrap justify-content-around ">
        {data.length
  ? data.map((item, index) => (
      <div
        className="card mx-4 my-2 order-card"
        style={{
          width: "25rem",
          backgroundColor: `${item.status ? "#AAF4B0" : "#F8F8F8"}`,
        }}
        key={index}
      >
        <div className="card-body">
             
              <h6 className="card-subtitle mb-2 text-muted"> 🧔 Requester Name: {item.name}</h6>
              <h6 className="card-subtitle mb-2 text-muted my-1">📧 Email: {item.email}</h6>
              <h6 className="card-subtitle mb-2 text-muted my-1">📱 Mobile Number:{item.mobNo}</h6>
              

              <h6 className="card-subtitle mb-2 text-muted my-1">🖹 Message :</h6>
              <hr />
              
              <p className="card-text">{item.message}</p>
              
              <button type="button" className="btn btn-danger" onClick={handleDeleteClick(item)}>🗑️ Delete</button>
            </div>
      </div>
    )).reverse()
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

          
          



        </div>





        </div>
    </div>
  )
}
