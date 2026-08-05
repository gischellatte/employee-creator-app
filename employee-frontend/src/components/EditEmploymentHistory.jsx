import React, {useEffect, useState} from "react";
import design from '../styles/App.module.scss';
import { useParams, useNavigate } from "react-router";


const EditEmploymentHistory =()=>{
    const {id} = useParams();
    const [error, setError] = useState(null);
    const [employmentHistories, setEmploymentHistories] = useState(null);
    const [loading, setLoading] = useState(true);
    const [employmentHistoriesForm, setEmploymentHistoriesForm] = useState({
        phone:""
    });
    const navigate = useNavigate();
    
    
    useEffect(() => {
        fetch(`http://localhost:8080/api/employment/employee/${id}`)
        .then((response) => response.json())
        .then((employmentHistories)=> {
        console.log(employmentHistories);
        setEmploymentHistories(employmentHistories);
        setLoading(false);
    })
    .catch((error) => {
        setError(error);
        setLoading(false);
    });
    }, [id])

    const handleReturn = async () => {
        navigate(`/allEmployees`);
    }

    return(
        <>
            <ul>
               {employmentHistories && employmentHistories?.map((history)=>
                <li key = {history.id}>
                    
                    <h3>{history.employee.firstName} {history.employee.midName} {history.employee.lastName}</h3>     
                    <h4>Employee ID </h4>
                    <div>{history.employee.id}</div>

                    <hr />

                    <h4>{history.role}  </h4>   
                    <div>Department of {history.department}</div>  
                    <div>{history.division} Division</div>

                    <hr />
                    
                    <h4>Contact Information </h4>
                    <div>📞 {history.employee.phone}</div>
                    <div>✉ {history.employee.email}</div>
                    <div>🏠 {history.employee.address}</div>

                    <hr />

                    <h4>Work Information</h4>
                    <h5> Weekly Hours  </h5>
                    <div>⏰ {history.employee.hoursPerWeek} hours/week</div>
                    
                    <div className={design["employmentHistory__Type"]}>
                      <h5>Employment Type</h5>
                      <div>📄{history.employee.employmentType} ({history.employee.workType})</div>
                    </div>
                    
                    <h5>Employment period</h5>
                    <div>📜{history.employee.startDate} — {history.employee.finishDate}</div>
                </li>  
                )}
            </ul>
            <div>
                <button onClick={handleReturn} className={design["button--back"]}>Back to the Employee List</button>
            </div>
           
        </>  
    )
}

export default EditEmploymentHistory;