
import SideAdminRest from './SideAdminRest'
import axios from 'axios';
import { useEffect,useState } from 'react';
import LoadingBar from 'react-top-loading-bar'
export default function AdminMoney() {
  const [progress, setProgress] = useState(0)
  const[data,setData]=useState([]);
  const token = localStorage.getItem("JwtToken");
  
  //Seting the token
  const axiosInstance = axios.create({
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  useEffect(()=>{
    setProgress(10)
    axiosInstance
          .get("http://localhost:9091/adminOprations/getAllPaymentDetails")
 
          .then(function (response) {
            console.log(response.data)
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
        <SideAdminRest/>
        {data.length?data.map((iteam)=>{
          return(
            <div className="card mx-3" style={{height:"200px",width:"405px"}}>
       <h5 className="card-header">Payment Id:{iteam.paymentId}</h5>
       <div className="card-body" style={{backgroundColor:"#D1F2EB"}}>
       <h6 className="card-title">Amount Paid:₹{iteam.amountPaid}</h6>
       <h6 className="card-title">Doctor Mail:{iteam.doctorMail}</h6>
       <h6 className="card-title">Order Id:{iteam.orderId}</h6>
       <h6 className="card-title">Payment Date:{iteam.paymentDate}</h6>
        </div>
          </div>
          )

        }):<div style={{ marginLeft: "450px" }}>
        <img
          src={require("./Assets/empty-box.png")}
          alt="no data found"
          height="200px"
          width="200px"
        />
        <h5 style={{ marginLeft: "30px" }}> No data found!</h5>
      </div>}
      
        

        </div>
    </div>
  )
}
