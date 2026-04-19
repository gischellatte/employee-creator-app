import { useEffect, useRef, useState } from "react";
import {useNavigate} from "react-router";
import styling from '../styles/App.module.scss';

const Details =()=> {
    const renderRefCalc = useRef(0);
    const formRef = useRef(null);
    const navigate = useNavigate();

    useEffect(() => {
        renderRefCalc.current++;
    });


    const [windowOpen, setWindowOpen] = useState(true);
    const handleClose = () =>{
      setWindowOpen(!windowOpen);
    }
    

    const handleSubmit = async (e) =>{
        e.preventDefault();
        const employeeForm = formRef.current; 
        const employeeFormData = new FormData(employeeForm);
        const employeeFormVal = Object.fromEntries(employeeFormData);

        
        await fetch("http://localhost:8080/api/employees", {
          method:"POST",
          headers: {
            "Content-Type": "application/json"
          }, 
          body: JSON.stringify(employeeFormVal)
        });
        employeeForm.reset();
        navigate("/allEmployees");
    }
    return(
      <>
     <h1>Employee details</h1>
     
     <form ref={formRef}  onSubmit={handleSubmit} >
      <div>
        <label htmlFor = "empFirstName">First name*</label><br/>
        <input id = "empFirstName" type="text" name="firstName"/>  
      </div>
      <div>
        <label htmlFor = "empMidName">Middle name (if applicable)</label><br/>
        <input id = "empMidName" type="text" name="midName"/>
      </div>
      <div>
        <label htmlFor = "empLastName">
           Last name
        </label><br/>
        <input id = "empLastName"  type="text" name="lastName"/>
      </div>

      <h1> Contact Details</h1>
        <div>
        <label htmlFor = "empEmail">
            Email address
        </label><br/>
        <input id = "empEmail" type="email" name="email"/>
      </div>
      <div className={styling.phoneNum}>
        <label className={styling.phoneNum__label} htmlFor = "empPhone">
          Mobile number
        </label><br/>
        <p className={styling.phoneNum__caption}>Must be an Australian Number</p>
        <input id = "empPhone"  type="tel" name="phone"/>
      </div>
      <div className={styling.address}>
        <label className={styling.address__label} htmlFor = "empAddress">
          Residential address </label>
        <p className={styling.address__caption} >Start typing to search</p>
        <input className={styling.address__input} id = "empAddress"  type="text" name="address"/>
      </div>

      <h1> Employee Status</h1>
        <div>
          <p> What is contract type?</p>
           <input id = "empPerm" type="radio" name="employmentType" value="permanent"/>
           <label htmlFor = "empPerm">
             Permanent
           </label>

           <input id = "empContractType" type="radio" name="employmentType" value="contract"/>
           <label htmlFor = "empContractType">Contract</label>
        </div>
        <div className={styling.contractStart}>
           <p>Start date</p>
              <div  className={styling.contractStart__datePicker}>
                <label htmlFor = "empContrStartDay">Day / Month / Year</label>
                <input id = "empContrStartDay" type="date" name="startDate"/>
              </div>
        </div>

        <div className={styling.contractFinish}>
           <p>Finish date</p>
              <div className={styling.contrFinish__datePicker}>
                <label htmlFor = "empContrFinishDay">Day / Month / Year</label>
                <input id = "empContrFinishDay" type="date" name="finishDate"/>
              </div>

              <input id = "empContrGoing" type="checkbox" name="onGoing"/>
              <label  htmlFor="empContrGoing">On going</label>
              <div>
                <p>
                 Is this on a full-time or part-time basis? 
                </p>
                <input id = "empContrFT" type="radio" name="workType" value="fullTime"/>
                <label htmlFor = "empContrFT" >Full-time</label>
                <input id = "empContrPT" type="radio" name="workType" value="partTime"/>
                <label htmlFor="empContrPT">Part-time</label>
              </div>
            <div>
              <label htmlFor = "empHours">
           Hours per week
              </label><br/>
              <input id = "empHours"  type="number" name="hoursPerWeek" max="50" min="0"/>
            </div>
        </div>
        <input className={styling["button--save"]} type="submit" value="Save"/>
        <button  className={styling["button--close"]}  type="button" onClick ={handleClose}value="Cancel">Cancel</button>
      </form>
      </>  
    )
}
export default Details;
