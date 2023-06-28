import React from 'react'
import SideBarAdmin from './SideBarAdmin'
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
export default function AdminDashboard({onDataReceived}) {
    const navigate = useNavigate();
    useEffect(() => {
        const storedData = localStorage.getItem("userData");
        const parsedData = JSON.parse(storedData);
        if(parsedData?.role==="ADMIN")
        {
          navigate('/adminDashboard');
        }
        else{
            navigate('/doctorDashboard');
        }



    })
    const handleDataReceived=() => {
        const obj={
          path:'/login',
          name:"Login"
        }
        onDataReceived({obj});
       }
  return (
    <div>
        <SideBarAdmin onDataReceived={handleDataReceived}/>
      this is admin
    </div>
  )
}
