import React, { useEffect,useState } from 'react'
import SideAdminRest from './SideAdminRest'
import axios from 'axios';
export default function ExpiredDrugs() {
    const [data, setdata] = useState([]);
    const token = localStorage.getItem("JwtToken");
  //Seting the token
  const axiosInstance = axios.create({
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  useEffect(()=>{
    axiosInstance
          .get("http://localhost:9091/adminOprations/getAllStock")
 
          .then(function (response) {
             const drugs=[];
            response.data.map((e,index)=>{
                if(response.data[index].status==="Expired")
                {
                    drugs.push(e);
                }

            })
            
            console.log(drugs)
            setdata(drugs);
            
            


          })
          .catch(function (error) {
            console.log(error);
          });
  },[])

  return (
    <div>
      <div className="my-5 mx-4 d-flex">
        <SideAdminRest />
        {data.map((iteam)=>{
            return(
                <div
                key={iteam.id}
                className="card my-2 mx-5"
                style={{ width: "1000px", height: "150px",backgroundColor:`${iteam.status==="Expired"?"#F46D6D":"white"}`}}
              >
                <div className="card-header">ID:{iteam.id}</div>
                <div className="card-body">
                  <table className="table">
                    <thead>
                      <tr>
                        <th>Supplier Email</th>
                        <th>Drug Name</th>
                        <th>Quantity</th>
                        <th>Batch ID</th>
                        <th>Price</th>
                        <th>Expire Date</th>
                        <th>Status</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>{iteam.supplierEmailId}</td>
                        <td>{iteam.drugName}</td>
                        <td>{iteam.quantity}</td>
                        <td>{iteam.batchId}</td>
                        <td>{iteam.price}</td>
                        <td>{iteam.expireDate}</td>
                        <td>{iteam.status}</td>
                      </tr>
                      <div
                        className="btn-group"
                        role="group"
                        aria-label="Basic example"
                      >
                       

                       
                      </div>
                    </tbody>
                  </table>
                </div>
              </div>
            )
        })}
        
        </div>
    </div>
  )
}
