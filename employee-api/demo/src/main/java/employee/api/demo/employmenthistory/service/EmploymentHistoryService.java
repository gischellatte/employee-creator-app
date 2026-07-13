package employee.api.demo.employmenthistory.service;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import employee.api.demo.employmenthistory.entity.EmploymentHistory;
import employee.api.demo.employmenthistory.repository.EmploymentHistoryRepository;
import employee.api.demo.entity.Employee;
import employee.api.demo.repository.EmployeeRepository;
import employee.api.demo.employmenthistory.dtos.AddEmploymentHistoryDto;
import employee.api.demo.employmenthistory.dtos.UpdateEmploymentHistoryDto;

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
    public List <EmploymentHistory> findByEmployeeId(Integer employeeId) {
        
        if(employeeId == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee "+ employeeId+ " has no available record.");
        }
        return employmentHistoryRepository.findByEmployeeId(employeeId);

    }

    public EmploymentHistory getEmploymentHistory(Integer employmentHistoryId) {
        return employmentHistoryRepository.findById(employmentHistoryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find employee " + employmentHistoryId + ". Failed to add a new record."));
    }

    public EmploymentHistory createEmploymentHistory(Integer employeeId, AddEmploymentHistoryDto addEmploymentHistoryDto){

        Employee foundEmployee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find employee " + employeeId + ". Failed to add a new record."));
    

        EmploymentHistory eh1 = new EmploymentHistory();

        eh1.setEmployee(foundEmployee);
        eh1.setDepartment(addEmploymentHistoryDto.getDepartment());
        eh1.setDivision(addEmploymentHistoryDto.getDivision());
        eh1.setRole(addEmploymentHistoryDto.getRole());

        return employmentHistoryRepository.save(eh1);
    }

    public EmploymentHistory editEmploymentHistory(Integer employmentHistoryId, UpdateEmploymentHistoryDto updateEmploymentHistoryDto) {

        EmploymentHistory editedRecord= employmentHistoryRepository.findById(employmentHistoryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,  "Cannot find " + employmentHistoryId + "in the repository. Failed to update record."));


        modelledMap.map(updateEmploymentHistoryDto, editedRecord);

        return employmentHistoryRepository.save(editedRecord);
    }

    public boolean deleteEmploymentHistory(Integer employmentHistoryId) {
        if(!employmentHistoryRepository.existsById(employmentHistoryId)) {
            return false;
        }

        employmentHistoryRepository.deleteById(employmentHistoryId);
        return true;

    }
}
