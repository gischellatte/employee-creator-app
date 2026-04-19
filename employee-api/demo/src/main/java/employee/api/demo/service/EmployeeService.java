package employee.api.demo.service;

import employee.api.demo.repository.EmployeeRepository;
import employee.api.demo.dtos.*;
import employee.api.demo.entity.Employee;

import java.util.List;
import java.lang.RuntimeException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService (EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    //Dont use repo in the taskController. 
    public Employee findById(Integer id){
        return employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot find employee "+ id));
    }

    public Employee findByEmail(String email){
        return employeeRepository.findEmployeeByEmail(email).orElseThrow(()->new RuntimeException("Cannot find "+ email));
    }

    public List <Employee> getAllEmployees(){
    return employeeRepository.findAll();
    }

    public Employee createEmployee (AddEmployeeDto addEmployeeDto){
        System.out.println("DTO employmentType = " + addEmployeeDto.getEmploymentType());

        //A runtime exception: Occurs when accessing a method or field on a null object 
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
        //edited employee
        //findById is available in the repo by default and we dont need to specifically write it in the repo.
        Employee employee2 = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find the employee."));
        employee2.setFirstName(updateEmployeeDto.getFirstName());
        employee2.setLastName(updateEmployeeDto.getLastName());
        employee2.setEmail(updateEmployeeDto.getEmail());
        employee2.setPhone(updateEmployeeDto.getPhone());
        employee2.setAddress(updateEmployeeDto.getAddress());
        employee2.setEmploymentType(updateEmployeeDto.getEmploymentType());
        employee2.setWorkType(updateEmployeeDto.getWorkType());
        employee2.setHoursPerWeek(updateEmployeeDto.getHoursPerWeek());
        employee2.setStartDate(updateEmployeeDto.getStartDate());
        employee2.setFinishDate(updateEmployeeDto.getFinishDate());
        employee2.setOnGoing(updateEmployeeDto.getOnGoing());

        return employeeRepository.save(employee2);
    }

}