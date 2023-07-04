import React, { useEffect } from 'react'
import SideAdminRest from './SideAdminRest'
import { useState,useRef } from 'react';
import axios from 'axios';
import Popup from 'reactjs-popup';
import { NotificationManager} from 'react-notifications';
import { NotificationContainer} from 'react-notifications';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';
import LoadingBar from 'react-top-loading-bar'
export default function AdminDrugs() {
    const [progress, setProgress] = useState(0)
    const fileInputRef = useRef(null);
    let [file, setFile] = useState(null);
    const [path, setPath] = useState("");
    const storedData = localStorage.getItem("userData");
    const[drudid,setDrugId]=useState("");
  const parsedData = JSON.parse(storedData);
  const [data, setData] = useState([]);
  const [imgPath,setImgPath]=useState([]);
  const [name,setName]=useState(null);
  const [price,setPrice]=useState(null);
  const [type,setType]=useState(null);
  const [category,setCatagory]=useState(null);


  const token = localStorage.getItem("JwtToken");
  const axiosInstance = axios.create({
    headers: {
      Authorization: `Bearer ${token}`, 
    },
  });

  const refreshData=()=>{
    axiosInstance
    .get("http://localhost:9091/adminOprations/getalldrugs")
    .then(function (response) {
      console.log(response.data);
      setData(response.data);
    })
    .catch(function (error) {
      console.log(error);
      
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
      
       

    })
    .catch(function (error) {
      console.log(error);
    });

  }

  useEffect(()=>{
    setProgress(0);
    axiosInstance
    .get("http://localhost:9091/adminOprations/getalldrugs")
    .then(function (response) {
      console.log(response.data);
      setData(response.data);
    })
    .catch(function (error) {
      console.log(error);
      setProgress(100);
      
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
      setProgress(100);
       

    })
    .catch(function (error) {
      console.log(error);
      setProgress(100);
    });

  },[])


  const handelNamechange=(e)=>{
    setName(e.target.value);
  }

  const handelPriceChange=(e)=>{
    setPrice(e.target.value);

  }
  const handelTypeChange=(e)=>{
    setType(e.target.value);

  }
  const handelCatagoryChange=(e)=>{
    setCatagory(e.target.value);

  }
  const handelUpdate=(item)=>()=>{
    console.log("Update")
    if (name === null && type === null && category === null && price === null  && drudid==null) {
        NotificationManager.error('', 'Enter Valid Data!', 1000);
        return;
      }

      axiosInstance
      .put("http://localhost:9091/adminOprations/updatedrug",{
          id: item.id,
          name: name?name:item.name,
          price: price?price:item.price,
          type: type?type:item.type,
          category:category?category:item.category
          
          
      })
      .then(function (response) {
        console.log(response.data)
        const formData = new FormData();
        formData.append("id",drudid)
        formData.append("file", file);
        axios
        .post(
            "http://localhost:9091/drugs/addDrugImg",
            formData
          )
            
            .then(function (response) {
              console.log("Img upload-------------");
              console.log(response.data);
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
                
                 
          
              })
              .catch(function (error) {
                console.log(error);
              });
              
            })
            .catch(function (error) {
              console.log(error);
             
            });


        NotificationManager.success('', 'Drug data updated',1000);
        setName(null);
        setPrice(null);
        setType(null);
        setCatagory(null);
        setPath("")
       setDrugId("")
       setFile(null);

        
      
        axiosInstance
        .get("http://localhost:9091/adminOprations/getalldrugs")

        .then(function (response) {
            setData(response.data);
        })
        .catch(function (error) {
          console.log(error);
        });
        

        
     })
     .catch(function (error) {
        console.log(error);
      });



  }

  const  handleImgChange = (item) => ()=>{
    fileInputRef.current.click();
    setDrugId(item.id)
    console.log(item.id)

  };
  const handleFileInputChange = (event) => {
    setFile(event.target.files[0]);
    // Handle the uploaded file here
    console.log("Uploaded file:", event.target.files[0]);
  
    const imageURL = URL.createObjectURL(event.target.files[0]);
   
    setPath(imageURL)
    console.log(imageURL);
  };

  const handelclose=()=>{
    console.log("discard")
    setPath("")
    setDrugId("")
    setFile(null);
    setName(null);
    setPrice(null);
    setType(null);
    setCatagory(null);

  }

  const handelSave=()=>{
    if (name === null && type === null && category === null && price === null  && file===null) {
        NotificationManager.error('', 'Enter Valid Data!', 1000);
        return;
      }

    axiosInstance
      .post("http://localhost:9091/adminOprations/createdrug",{
          name: name,
          price: price,
          type: type,
          category:category
          
          
      })
      .then(function (response) {
        const formData = new FormData();
        formData.append("id",response.data.id);
        formData.append("file", file);
        axios
        .post(
            "http://localhost:9091/drugs/addDrugImg",
            formData
          )
            
            .then(function (response) {
              console.log("Img upload-------------");
              console.log(response.data);
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
                axiosInstance
              .get("http://localhost:9091/adminOprations/getalldrugs")
                .then(function (response) {
              console.log(response.data);
               setData(response.data);
               NotificationManager.success('', 'New Drug added successfuly', 1000);
               setPath("")
    setDrugId("")
    setFile(null);
    setName(null);
    setPrice(null);
    setType(null);
    setCatagory(null);
      
      
      
                })
                 .catch(function (error) {
                 console.log(error);
      
                });


                
                 
          
              })
              .catch(function (error) {
                console.log(error);
              });
              
            })
            .catch(function (error) {
              console.log(error);
             
            });


      })
      .catch(function (error) {
        console.log(error);
      })


  }
  const handleImgUpload=()=>{
    console.log("img upload");
    fileInputRef.current.click();

  }

  const handelFileUpload=(event)=>{
    console.log("file upload");
    setFile(event.target.files[0]);
    const imageURL = URL.createObjectURL(event.target.files[0]);
   
    setPath(imageURL)
  }

  const handelDeleteDrug=(id)=>()=>{
    axiosInstance
    .delete("http://localhost:9091/adminOprations/deletedrug/"+id)
    .then(function (response) {
      console.log(response.data);
      axios
      .delete("http://localhost:9091/drugs/deleteImg/"+id)
      .then(function (response) {
        console.log(response.data);
        refreshData();
        NotificationManager.success('', 'Drug deleted successfuly', 1000);

    })
    .catch(function (error) {
        console.log(error);
        
      });
      
    })
    .catch(function (error) {
      console.log(error);
      NotificationManager.error('', 'an error occured', 1000);
    });



  }







  return (
    <div>
        <LoadingBar
        color='#f11946'
        progress={progress}
        onLoaderFinished={() => setProgress(0)}
      />

         <NotificationContainer/>
         <div className='d-flex justify-content-center my-3'>
         <Popup trigger=
                    { <button type="button" className="btn btn-primary btn-lg btn-block" >➕Add Drug</button>}
                  position="bottom center">
                   {close => (
      <div>
                   <div className="card my-2 mx-1 border border-dark" style={{width: "18rem"}}>
      
      <img className="card-img-top imgHover" src={path?path:require('./Assets/upload.jpg')} alt="Card image cap" style={{height:"250px",cursor:"pointer"}}  onClick={handleImgUpload}
                  title="Click to upload image" /> 
                   <input
                  type="file"
                  ref={fileInputRef}
                  style={{ display: "none" }}
                  onChange={handelFileUpload}
                />
    <div className="card-body">
    <h6>Name:<input type="text" className="form-control" placeholder="name" onChange={handelNamechange}/></h6>
   <b>Price: ₹<input type="number" className="form-control" placeholder="price" onChange={handelPriceChange}/> </b>
   <b>Type: <input type="text" className="form-control" placeholder="type" onChange={handelTypeChange}/></b>
   <b>Category: <input type="text" className="form-control" placeholder="category" onChange={handelCatagoryChange}/></b>

   <div className="btn-group" role="group" aria-label="Basic example">
    <button type="button" className="btn btn-secondary" onClick={handelSave}>Save</button> 
    <button type="button" className="btn btn-secondary close mx-5" onClick={()=>{close();handelclose() }}>Discard</button>
    
    </div>
        
       



                  </div>
                   </div>
                  
        
      </div>
    )}
    </Popup>

         
         </div>
        

         <div className="my-2 mx-4 d-flex">
        <SideAdminRest/>
        <div className="d-flex flex-wrap justify-content-around">
        {data.map((item,index) => {

        return(
              
                
    <div className="card my-2 mx-1 border border-dark" style={{width: "18rem"}} key={item.id}>
      
     <img className="card-img-top" src={imgPath[index]} alt="Card image cap" style={{height:"250px"}}/> 
   <div className="card-body">
  <h5 className="card-title">{item.name}</h5>
  <p><b>Price: ₹</b>{item.price}</p>
  <p><b>Type:</b> {item.type}</p>
  <p className="card-text"><b>Category:</b> {item.category}</p>
          <Popup trigger=
                    {  <div className="btn-group" role="group" aria-label="Basic example">
                    <button type="button" className="btn btn-secondary">✏️Edit</button> </div>}
                  position="right center">
                   {close => (
      <div>
                   <div className="card my-2 mx-1 border border-dark" style={{width: "18rem"}} key={item.id}>
      
      <img className="card-img-top imgHover" src={path?path:imgPath[index]} alt="Card image cap" style={{height:"250px",cursor:"pointer"}}  onClick={handleImgChange(item)}
                  title="Click to upload image" /> 
                   <input
                  type="file"
                  ref={fileInputRef}
                  style={{ display: "none" }}
                  onChange={handleFileInputChange}
                />
    <div className="card-body">
    <h6>Name:<input type="text" className="form-control" placeholder={item.name} onChange={handelNamechange}/></h6>
   <b>Price: ₹<input type="number" className="form-control" placeholder={item.price} onChange={handelPriceChange}/> </b>
   <b>Type: <input type="text" className="form-control" placeholder={item.type} onChange={handelTypeChange}/></b>
   <b>Category: <input type="text" className="form-control" placeholder={item.category} onChange={handelCatagoryChange}/></b>

   <div className="btn-group" role="group" aria-label="Basic example">
    <button type="button" className="btn btn-secondary" onClick={handelUpdate(item)}>Save</button> 
    <button type="button" className="btn btn-secondary close mx-5" onClick={()=>{close();handelclose() }}>Discard</button>
    
    </div>
        
       



                  </div>
                   </div>
                  
        
      </div>
    )}
    </Popup>
      <div className="btn-group" role="group" aria-label="Basic example">
    
             <button type="button" className="btn btn-secondary" style={{marginLeft:"10px"}} onClick={handelDeleteDrug(item.id)} >❌Delete</button>
            </div>
   
    </div>
        </div>)



        })}
        </div>
        </div>
     
    </div>
  )
}
