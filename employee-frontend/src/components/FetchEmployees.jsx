import React, {useEffect, useState} from "react";
import design from '../styles/App.module.scss';
import { useNavigate } from "react-router";

const FetchEmployees =()=>{

    const [employees, setEmployees] = useState(null);
    const [employmentHistories, settEmploymentHistories] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:8080/api/employees")
        .then((response) => response.json())
        .then((employees)=> {
        setEmployees(employees);
        setLoading(false);
    })
    .catch((error) => {
        setError(error);
        setLoading(false);
    });
    }, [])


    useEffect(() => {
        fetch("http://localhost:8080/api/employment")
        .then((response) => response.json())
        .then((employmentHistories)=> {
        settEmploymentHistories(employmentHistories);
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
            setEmployees(prev=>prev.filter(emp => emp.id !=id));
        })
  
        .catch((error) => console.log("Can't remove the employee." + error))
      }
    }

    const handleUpdate = async (id) => {
      navigate(`/employees/${id}`);
    }

    const handleHistoryPreview = async (id) =>{
      navigate(`/employmenthistory/employee/${id}`);
    }

  return (
    <div>
      <h1>Employee List</h1>
      <ul>
        {employees && employees.map((employee) => (
          
          <li key={employee.id}>
            <div>
              <h3>{employee.firstName} {employee.lastName}</h3>
              <div>
                {employmentHistories?.filter((history) =>
                
                 history.employee.id === employee.id).map (history => 
                <div key = {history.id}>
                 <h4>{history.role}</h4>
                 <span>Department of {history.department}</span>
                 <p>{history.division} division</p>
                </div>  
                 
                 ).slice(-1)}
              </div>
              <div>
              {employee.email}
              
              </div>
            </div>
            
            
            <button className={design["button--edit"]} onClick={() => handleUpdate(employee.id)} >Edit</button>

            <button className={design["button--delete"]} onClick={() => handleDelete(employee.id)} >delete</button>
            <button className={design["button--fullProfile"]} onClick={()=> handleHistoryPreview(employee.id)}>View Full Profile</button>
          </li>
        ))}
        
        </ul>
    </div>
  );
}

export default FetchEmployees;
