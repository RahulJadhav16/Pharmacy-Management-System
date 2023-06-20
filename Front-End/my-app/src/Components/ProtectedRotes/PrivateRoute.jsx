import React from 'react'
import { Outlet,Navigate } from 'react-router-dom'
export default function PrivateRoute(props) {
    const id=props.id;
  return (
   
    id ?<Outlet/>:<Navigate to='/login'/>
  )
}
