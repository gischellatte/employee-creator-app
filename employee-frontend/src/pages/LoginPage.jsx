import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router";

const LoginPage =()=>{

const navigate = useNavigate();
    const handleLogin = async (e)=>{
        e.preventDefault();

        const email = e.target.empProfileEmail.value;
        const response = await fetch("http://localhost:8080/api/employees/login", {
            
          method:"POST",
          headers: {
            "Content-Type": "application/json"
          }, 
          body: JSON.stringify({email})
        });
        
          if(!response.ok){
           window.alert("Wrong email, please insert the right credentials.");
           return
          }
        
        let data= await response.json();
        navigate(`/employees/${data.id}`);
    }
return (
    <>
      <form onSubmit={handleLogin} >
       
            <section>
                <label htmlFor="loginEmail">Email*</label><br/>
                <input id="loginEmail" type="text" name="empProfileEmail" required></input>
            </section>
    
        <input type="submit" value="login"/>
      </form>
    </>
    )
}
export default LoginPage;
