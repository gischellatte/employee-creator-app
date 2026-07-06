package employee.api.demo.employmenthistory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employee.api.demo.entity.Employee;
import employee.api.demo.service.EmployeeService;
import employee.api.demo.employmenthistory.dtos.AddEmploymentHistoryDto;
import employee.api.demo.employmenthistory.entity.EmploymentHistory;
import employee.api.demo.employmenthistory.service.EmploymentHistoryService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/employment")

public class EmploymentHistoryController {
    private final EmploymentHistoryService employmentHistoryService;
    private final EmployeeService employeeService;

    public EmploymentHistoryController(EmploymentHistoryService employmentHistoryService, EmployeeService employeeService) {
        this.employmentHistoryService = employmentHistoryService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmploymentHistory>> findAll(){
        return ResponseEntity.ok(employmentHistoryService.getAllEmploymentHistory());
    }
    
    @PostMapping("/{employeeId}")
    public ResponseEntity <EmploymentHistory> addEmploymentHistory(@PathVariable Integer employeeId, @Valid @RequestBody AddEmploymentHistoryDto addEmploymentHistoryDto) {

        EmploymentHistory createdEmploymentHistory = employmentHistoryService.createEmploymentHistory(employeeId, addEmploymentHistoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmploymentHistory);
    }
    

}
