package employee.api.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import employee.api.demo.entity.Employee;
import employee.api.demo.service.EmployeeService;
import employee.api.demo.dtos.AddEmployeeDto;
import employee.api.demo.dtos.UpdateEmployeeDto;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    } 


    ///request param needs: api/employees?employee=1
    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}") 
    //uses path variable because we are using an id here
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
      Employee employee4 = employeeService.findById(id);
      return ResponseEntity.ok(employee4);
    }

    //Dont use repo in the taskController. 
    // POST - add an employeee
    @PostMapping 
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody AddEmployeeDto addEmployeeDto){
        Employee createdEmployee = employeeService.createEmployee(addEmployeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PostMapping ("/login")
    //<?> means the return can be anything 
    //Map<String, String> is a key-value pair (like "name":abc)
    public ResponseEntity<?> loginEmployee(@RequestBody Map<String, String> body){
        String empEmail = body.get("email");
        Employee loggedEmployee = employeeService.findByEmail(empEmail);
         return ResponseEntity.ok(loggedEmployee);
       
    }

     // Delete - Remove an employee
    @DeleteMapping ("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id){
        if(!employeeService.deleteEmployee(id)){
        return ResponseEntity.notFound().build();
        }
        else {
        return ResponseEntity.noContent().build();
        }
    }

    // Patch - Update employee's details 
    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @Valid @RequestBody UpdateEmployeeDto updateEmployeeDto){
        Employee updateEmployee = employeeService.editEmployeeDetails(id, updateEmployeeDto);
        return ResponseEntity.ok(updateEmployee);
    }

}
