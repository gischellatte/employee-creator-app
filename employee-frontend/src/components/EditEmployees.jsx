import React, {useEffect, useState} from "react";
import { useParams } from "react-router";

//patch request (front-end)
const EditEmployees =()=>{
 const {id} = useParams();
   
const [form, setForm] = useState({
  firstName:"",
  midName:"",
  lastName:"",
  email:"",
  phone:"",
  address:"",
  employmentType:"", //FT vs PT
  workType:"", //Contract vs Permanent
  hoursPerWeek:"",
  startDate:"",
  finishDate:"",
  onGoing:"",
});


const [error, setError] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/api/employees/${id}`)
    .then((response) => response.json())
    .then((data)=> {
      const fixedStart = data.startDate?.split("T")[0] || "";
      const fixedFinish = data.finishDate?.split("T")[0] || "";
         
      setForm({...data, startDate:fixedStart, finishDate: fixedFinish});
    })
    .catch((error) => {
      console.log("Failed to update"+ error);
        });
  }, [id]);


const handleEdit=(e)=>{
  const { name, value } = e.target;
  
  setForm({...form, [name]:value})
}


const handleSubmittedChange = async (e)=>{
  e.preventDefault();

  const payload ={
  id:form.id,
  firstName: form.firstName,
  midName:form.midName || null,
  lastName:form.lastName,
  email:form.email,
  phone:form.phone,
  address:form.address,
  employmentType:form.employmentType,
  workType:form.workType,
  startDate:form.startDate,
  finishDate:form.finishDate,
  onGoing:form.onGoing,
  hoursPerWeek: Number(form.hoursPerWeek || 0)
  }

  
  try {
    const feedbc = await fetch (`http://localhost:8080/api/employees/${id}`, {
      method: "PATCH",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(payload)
    });

    if(!feedbc.ok){
      throw new Error("Failed to apply changes");
    }
    alert("Successfully updated employee number " + id);
  }

  catch(anError) {
      console.error(anError);
      alert("Error in applying changes to employee "+ id);
  }
}
   return(
    <>
    <h1>Edit details</h1>
      <form onSubmit={handleSubmittedChange}>
      <div>
        <label>First name</label><br/>
        <input type="text" name="firstName" value={form.firstName} onChange={handleEdit}
        />  
      </div>
      <div>
        <label>Middle name (if applicable)</label><br/>
        <input type="text" name="midName" value={form.midName} onChange={handleEdit}/>
      </div>
      <div>
        <label>
           Last name
        </label><br/>
        <input type="text" name="lastName" value={form.lastName} onChange={handleEdit}/>
      </div>

      <h1> Contact Details</h1>
        <div>
        <label>
            Email address
        </label><br/>
        <input type="email" name="email" value={form.email} onChange={handleEdit}/>
      </div>
      <div>
        <label>
          Mobile number
        </label><br/>
        <p>Must be an Australian Number</p>
        <input type="tel" name="phone" value={form.phone} onChange={handleEdit}/>
      </div>
      <div>
        <label>
          Residential address </label>
        <p>Start typing to search</p>
        <input type="text" name="address" value={form.address} onChange={handleEdit}/>
      </div>

      <h1> Employee Status</h1>
        <div>
          <p> What is contract type?</p>
          <input id = "empContrFT" type="radio" name="contractType" value="fullTime" checked={form.workType==="contract"} onChange={handleEdit}/>
                <label>Contract</label>
             
                <input id = "empContrPT" type="radio" name="workType" value="permanent" checked={form.workType==="permanent"} onChange={handleEdit}/>
                <label>permanent</label>
        </div>
        <div>
           <p>Start date</p>
            <div>
              <div>
               <label>Day</label>
                <input type="date" name="startDate" value={form.startDate} onChange={handleEdit}/>
              </div>
              
             
            </div>
        </div>

        <div>
           <p>Finish date</p>
            <div>
              <div>
               <label htmlFor = "empContrFinishDay">Day</label>
                <input id = "empContrFinishDay" name="finishDate" type="date" 
                value={form.finishDate} onChange={handleEdit}/>
              </div>
          
              <input id = "empContrGoing" type="checkbox" name="onGoing" checked={form.onGoing} onChange={(e) => setForm({...form, onGoing: e.target.checked})}/>
              <label htmlFor="empContrGoing">On going</label>
              <div>
                <p>
                 Is this on a full-time or part-time basis? 
                </p>
                
                <label>Full-Time</label>
                <input type="radio" name="employmentType" value="Full-Time" checked={form.employmentType==="Full-Time"} onChange={handleEdit}/>

                <label>Part-Time</label>
                <input type="radio" name="employmentType" value="Part-Time" checked={form.employmentType==="Part-Time"} onChange={handleEdit}/>
              </div>
            </div>
            <div>
              <label htmlFor = "empHours">
           Hours per week
              </label><br/>
              <input id = "empHours"  type="number" name="hoursPerWeek" max="50" min="0" value={form.hoursPerWeek} onChange={handleEdit}/>
            </div>
        </div>
        <input type="submit" value="Save"/>
        <button type="button" value="Cancel">Cancel</button>
      </form>
    </>

   )     
}

export default EditEmployees;
