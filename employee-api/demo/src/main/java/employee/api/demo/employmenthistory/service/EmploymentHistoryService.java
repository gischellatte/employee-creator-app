package employee.api.demo.employmenthistory.service;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import employee.api.demo.employmenthistory.entity.EmploymentHistory;
import employee.api.demo.employmenthistory.repository.EmploymentHistoryRepository;
import employee.api.demo.entity.Employee;
import employee.api.demo.repository.EmployeeRepository;
import employee.api.demo.employmenthistory.dtos.*;

import java.util.List;


@Service
public class EmploymentHistoryService {
    private final EmploymentHistoryRepository employmentHistoryRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelledMap;


    public EmploymentHistoryService(EmploymentHistoryRepository employmentHistoryRepository, EmployeeRepository employeeRepository, ModelMapper modelledMap) {
        this.employmentHistoryRepository = employmentHistoryRepository;
        this.employeeRepository = employeeRepository;
        this.modelledMap = modelledMap;
    }

    public List <EmploymentHistory> getAllEmploymentHistory(){
        return employmentHistoryRepository.findAll();
    }

    public EmploymentHistory createEmploymentHistory(Integer employeeId, AddEmploymentHistoryDto addEmploymentHistoryDto){

        Employee foundEmployee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find employee " + employeeId + ". Failed to update history."));
    

        EmploymentHistory eh1 = new EmploymentHistory();

        eh1.setEmployee(foundEmployee);
        eh1.setDepartment(addEmploymentHistoryDto.getDepartment());
        eh1.setDivision(addEmploymentHistoryDto.getDivision());
        eh1.setRole(addEmploymentHistoryDto.getRole());

        return employmentHistoryRepository.save(eh1);
    }

    //mapper for update EmploymentHistory
}