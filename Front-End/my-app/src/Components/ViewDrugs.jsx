import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import "./CSS/viewDrugs.css";
import { NotificationManager} from 'react-notifications';
import { NotificationContainer} from 'react-notifications';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import "./CSS/doctorlogin.css";
import SyncLoader from "react-spinners/SyncLoader";
import LoadingBar from 'react-top-loading-bar'
export default function ViewDrugs() {
  const [progress, setProgress] = useState(0)
  const[loader,setLoader]=useState(false);
  const storedData = localStorage.getItem("userData");
  const parsedData = JSON.parse(storedData);

  const [data, setData] = useState([]);
  const [filterImg, setFilterImg] = useState("");
  const [imgPath,setImgPath]=useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [quantityIp, setquantity] = useState('');


  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    
  };

  const handelQuantityChange=(e)=>{
    setquantity(e.target.value);
    
  }

  const handelBuy=()=>{
    console.log(imgPath);
    console.log(data);
  }
  
  /////////////////// Placing the Order calling order microservice
  const handelOrderClick= (item) => () =>{
    //console.log(item);
    if(quantityIp==='' || quantityIp==null || quantityIp<=0)
    {
      NotificationManager.error('', 'Enter Valid Quantity!', 3000);
      return;
    }

    const token = localStorage.getItem("JwtToken");
    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${token}`, 
      },
    });

    axiosInstance
    .post("http://localhost:9091/doctor/addOrder", {
      doctorId: localStorage.getItem("Doctorid"),
      drugName:item.name,
      quantity:quantityIp
      
    })
    .then(function (response) {
      console.log(response.data);
      setquantity(0);
      NotificationManager.success(`${response.data.drugName} with quantity ${response.data.quantity}`, 'Order placed successfully', 3000);

    })
    .catch(function (error) {
      console.log(error);

    })

  }

  useEffect(() => {
    setLoader(true)
    setProgress(50)
    // Set the token value
    const token = localStorage.getItem("JwtToken");

    // Create an instance of Axios with default headers
    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${token}`, // Adding the token to the 'Authorization' header
      },
    });

    axiosInstance
      .get("http://localhost:9091/doctor/viewAllDrugs")
      .then(function (response) {
        console.log(response.data);
        setData(response.data);
        
        
        
      })
      .catch(function (error) {
        console.log(error);
        setLoader(false)
        setProgress(100)
      });

      axios
      .get("http://localhost:9091/drugs/getAllDrugImg")
      .then(function (response) {
        const imgPathArray=[];
       
       

        response.data.map((item) => {
          const base64Image = item.image;
        const byteCharacters = atob(base64Image);
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
          byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], { type: "image/jpeg" });
        const imageUrl = URL.createObjectURL(blob);
        imgPathArray.push(imageUrl);
       
        
        




        });

        setImgPath(imgPathArray);
        setLoader(false)
        setProgress(100)
         

      })
      .catch(function (error) {
        console.log(error);
        setLoader(false)
        setProgress(100)

      });
  }, []);

  const handleBackspace = (e) => {
    if (e.key === 'Backspace') {
      setFilteredPosts([]);
    }
  };

  const handleSearchSubmit = (e) => {
    e.preventDefault();
    

    const token = localStorage.getItem("JwtToken");

    const axiosInstance = axios.create({
      headers: {
        Authorization: `Bearer ${token}`, // Adding the token to the 'Authorization' header
      },
    });
    axiosInstance
      .get("http://localhost:9091/doctor/drugByName/" + searchTerm)
      .then(function (response) {
        console.log(response.data);
        setFilteredPosts(response.data);
      
        
        axios.get("http://localhost:9091/drugs/getDrugImg/"+response.data[0].id)
        .then(function (response) {
          const base64Image = response.data.image;
          const byteCharacters = atob(base64Image);
          const byteNumbers = new Array(byteCharacters.length);
          for (let i = 0; i < byteCharacters.length; i++) {
            byteNumbers[i] = byteCharacters.charCodeAt(i);
          }
          const byteArray = new Uint8Array(byteNumbers);
          const blob = new Blob([byteArray], { type: "image/jpeg" });
          const imageUrl = URL.createObjectURL(blob);
          setFilterImg(imageUrl);


        })
        .catch(function (error){
          console.log(error);

        })

        
      })
      .catch(function (error) {
        console.log(error);
        if (error.response && error.response.status === 404) {
          NotificationManager.warning('', 'Drug not found !', 3000);
        }
        if (error.response && error.response.status === 401) {
          NotificationManager.error('', 'Enter Valid Name!', 3000);
        }
        
      });
    
  };

  return (
    <div>
      <LoadingBar
        color='#f11946'
        progress={progress}
        onLoaderFinished={() => setProgress(0)}
      />
      <NotificationContainer/>
      {/* SearchBar */}
      <div className="row height d-flex justify-content-center align-items-center">
        <div className="col-md-8">
          <div className="search">
            <i className="fa fa-search"></i>
            <input
  type="text"
  className="form-control"
  placeholder="Search Drugs.."
  onChange={handleSearchChange}
  onKeyDown={handleBackspace}
/>
            <button className="btn btn-primary" onClick={handleSearchSubmit}>
              Search
            </button>
          </div>
        </div>
      </div>
      <div className="my-5 mx-4 d-flex">
        {/* SideBar */}
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
              <span className="fs-4">Hii, Dr {parsedData?.name}</span>
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
        <div className="d-flex flex-wrap justify-content-around">
        {loader?
        <div className="my-3" style={{marginLeft:"450px"}}>
        <SyncLoader
        color={"#36d7b7"}
        loading={loader}
    
        size={20}
        aria-label="Loading Spinner"
        data-testid="loader" /></div>:
          filteredPosts.length
            ? filteredPosts.map((item) => {
              
                
                return (
                  <div className="card my-2 mx-1" style={{width: "18rem"}}>
                 <img className="card-img-top" src={filterImg} alt="Card image cap" style={{height:"250px"}}/> 
                <div className="card-body">
               <h5 className="card-title">{item.name}</h5>
               <p><b>Price: ₹</b>{item.price}</p>
               <p><b>Type:</b> {item.type}</p>
               <p className="card-text"><b>Category:</b> {item.category}</p>
               <Popup trigger=
                    {<button>📦 Buy</button>}
                  position="right center">
                    <div className="d-flex justify-content-center">
                   <input type="number" class="form-control"  placeholder="Quantity" onChange={handelQuantityChange} ></input>
                   <button type="button" class="btn btn-warning" onClick={handelOrderClick(item)}>Place Order</button>
                   </div>
                 </Popup>
                 </div>
                     </div>
                );
              })
            : data.map((item,index) => {
              return(
              
                
                  <div className="card my-2 mx-1 border border-dark" style={{width: "18rem"}}>
                    
                   <img className="card-img-top" src={imgPath[index]} alt="Card image cap" style={{height:"250px"}}/> 
                 <div className="card-body">
                <h5 className="card-title">{item.name}</h5>
                <p><b>Price: ₹</b>{item.price}</p>
                <p><b>Type:</b> {item.type}</p>
                <p className="card-text"><b>Category:</b> {item.category}</p>
                <Popup trigger=
                    {<button>📦 Buy</button>}
                  position="right center">
                    <div className="d-flex justify-content-center">
                   <input type="number" class="form-control"  placeholder="Quantity" onChange={handelQuantityChange} ></input>
                   <button type="button" class="btn btn-warning" onClick={handelOrderClick(item)}>Place Order</button>
                   </div>
                 </Popup>
                  </div>
                      </div>
                
                
               


                
                   
              )})}



                
        </div>
      </div>
    </div>
  );
}
