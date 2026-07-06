package employee.api.demo.service;

import employee.api.demo.repository.EmployeeRepository;
import employee.api.demo.dtos.*;
import employee.api.demo.entity.Employee;

import java.util.List;
import java.lang.RuntimeException;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper theModelMap;

    public EmployeeService (EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.theModelMap =  modelMapper;
    }
 
    public Employee findById(Integer id){
        return employeeRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find employee " + id));
    }
    

    public Employee findByEmail(String email){
        return employeeRepository.findEmployeeByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find "+ email));
    }

    public List <Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee createEmployee (AddEmployeeDto addEmployeeDto){
        System.out.println("DTO employmentType = " + addEmployeeDto.getEmploymentType());

        Employee employee1 = new Employee();
        employee1.setFirstName(addEmployeeDto.getFirstName());

        String emp1MidName = addEmployeeDto.getMidName();
        if(emp1MidName!=null && !emp1MidName.isBlank()) {
           employee1.setMidName(emp1MidName);
        }

        employee1.setLastName(addEmployeeDto.getLastName());
        employee1.setEmail(addEmployeeDto.getEmail());
        employee1.setPhone(addEmployeeDto.getPhone());
        employee1.setAddress(addEmployeeDto.getAddress());
        employee1.setEmploymentType(addEmployeeDto.getEmploymentType());
        employee1.setWorkType(addEmployeeDto.getWorkType());
        employee1.setHoursPerWeek(addEmployeeDto.getHoursPerWeek());
        employee1.setStartDate(addEmployeeDto.getStartDate());
        employee1.setFinishDate(addEmployeeDto.getFinishDate());
        
        if(addEmployeeDto.getStartDate().isAfter(addEmployeeDto.getFinishDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Starting date cannot be after the finish date.");
        }
        employee1.setOnGoing(addEmployeeDto.getOnGoing());

        return employeeRepository.save(employee1);
    }
    public boolean deleteEmployee (Integer id){
        if(!employeeRepository.existsById(id)){
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }

    public Employee editEmployeeDetails(Integer id, UpdateEmployeeDto updateEmployeeDto) {

        Employee employee2 = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find the employee."));

        theModelMap.map(updateEmployeeDto, employee2);

        if(employee2.getStartDate().isAfter(employee2.getFinishDate())){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Starting date cannot be after the finish date.");
        }
        
        return employeeRepository.save(employee2);
    }

}
