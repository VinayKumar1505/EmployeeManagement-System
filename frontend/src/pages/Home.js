import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';

export default function Home() {

    const [users, setUsers]= useState([])
    const {id}= useParams();

    useEffect(()=>{
      loadUsers();
    },[])

    const deleteUser= async (id)=>{
      await axios.delete(`http://localhost:8080/user/${id}`)
      loadUsers();
    }

    const loadUsers= async ()=>{
        const result= await axios.get("http://localhost:8080/users")
        // console.log(result.data)
        setUsers(result.data)
    }
  return (
    <div className='container'>
        <div className='py-4'>

        <table className="table">
  <thead className="light">
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Name</th>
      <th scope="col">UserName</th>
      <th scope="col">Email</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
     {
        users.map((user, index)=>(
          <tr>
          <th scope="row" key={index}>{index+ 1}</th>
          <td>{user.name}</td>
          <td>{user.username}</td>
          <td>{user.email}</td>
          <Link className='btn bg-primary btn-primary mx-2 my-1'to={`/viewUser/${user.id}`}>View</Link>
          <Link className='btn btn-outline-primary mx-2' to={`/editUser/${user.id}`}>Edit</Link>
          <button className='btn bg-danger btn-danger mx-2' onClick={()=> deleteUser(user.id)}>Delete</button>
        </tr>
        ))
     }
    
    
    
  </tbody>
</table>

 
        </div>
      
    </div>
  )
}
