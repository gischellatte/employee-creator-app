import React, {useEffect, useState} from "react";
import { useNavigate } from "react-router";

const FetchEmployees =()=>{

    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:8080/api/employees")
        .then((response) => response.json())
        .then((data)=> {
        setData(data);
        setLoading(false);
    })
    .catch((error) => {
        setError(error);
        setLoading(false);
    });
    }, []) 

     const handleDelete = async (id) =>{
     
     let deleteConfirmation = window.confirm("Remove this employee?");

      if(deleteConfirmation){
        fetch(`http://localhost:8080/api/employees/${id}`, {
          method: "DELETE"
        })
        .then((response)=> {
            if(!response.ok) {
                throw new Error("Failed to remove");
            }
            setData(prev=>prev.filter(emp =>emp.id !=id));
        })
  
        .catch((error) => console.log("Can't remove the employee." + error))
      }
    }

    const handleUpdate = async (id) => {
      navigate(`/employees/${id}`);
    }

  return (
    <div>
      <h1>Employee List</h1>
      <ul>
        {data && data.map((employee) => (
          <li key={employee.id}>
            {employee.firstName} {employee.lastName} - {employee.email} 
            <button onClick={() => handleUpdate(employee.id)} >Edit</button>
            <button onClick={() => handleDelete(employee.id)} >delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default FetchEmployees;
