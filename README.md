# employee-creator-app

**Demo & Snippets**
https://github.com/gischellatte/employee-creator-app/tree/main

<img width="269" height="379" alt="image" src="https://github.com/user-attachments/assets/43282352-ab20-4271-b10e-aef38fa3ce89" />

**Requirements / Purpose**

MVP
- Add a new employee. 
- View details of a specific employee
- Update information
- Delete employees

Project Purpose
This app designed to help our clients to manage and assess the status of employees within their companies. It provides a digital interface for checking and managing employee records. 

**Tech Stack** 

Frontend 
- HTML - To build the frontend skeleton of the application
- SCSS - To style the application and to make it more responsive
- React JS - To build a dynamic User interface that can be connected to Springboot
- React Testing Library - To ensure the component fulfills the expectation


Backend
- Java - To build the backend of the application
- Springboot - To connect with MySQL and build a RESTful API 
- MySQL - To store users' data

** Build Steps** 
Prerequisites
Before running the application, make sure you have successfully installed: 
- Node.js (v16+ or higher)
- npm or yarn
- Java JDK 17+
- Maven or Gradle 

**How to Run the Project**
Frontend 
1. Navigate to the employee-frontend folder using your terminal.
cd employee-frontend

2. (After installing dependencies) Execute npm run dev in the employee-frontend folder.
npm run dev

NB: The frontend can **only** run on http://localhost:5173

Backend
1. Navigate to the demo folder using your terminal.
2. (After installing dependencies) Run mvn spring-boot:run in the demo folder
mvn spring-boot:run

**Design Goals / Approach**
Goals
- To build an interactive employee management system.
- To build a user-friendly employee management app.
- To keep the code readable and reusable.
- To ensure clear separation between the backend and frontend parts.

Approach
-The frontend part handles user input, output and responsive display.
- The backend part handles business, services and data processing. Users can also update the employee details via backend (e.g. MySql and Postman app)
- The data communication happens via HTTP requests.
  
**Features** 
- Employee login using email
- Add a new employee
- View the complete list of employees
- Update employee information
- Remove employees
- Interpage navigation

**Known Issues** 
- A few error messages are shown in the Browser console, but not displayed in the UI.
- Limited error checking and validation in some input fields.
- Some fields accept null input.

**Future Goals**
- Introduce more TypeScript coverage
- Add a blocker to prevent users from selecting past dates for contract end date.
- Improve form validation to prevent users from entering invalid or incomplete input.
