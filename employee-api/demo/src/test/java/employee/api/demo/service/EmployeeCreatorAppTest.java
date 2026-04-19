package employee.api.demo.service;

import org.mockito.Mock;
import org.mockito.InjectMocks;

import employee.api.demo.dtos.AddEmployeeDto;
import employee.api.demo.dtos.UpdateEmployeeDto;
import employee.api.demo.entity.*;
import employee.api.demo.repository.EmployeeRepository;
import employee.api.demo.service.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times; 
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class) 
public class EmployeeCreatorAppTest {
    @Mock
    private EmployeeRepository employeeRepository;
    
    @InjectMocks
    private EmployeeService employeeService;

     @Test
     public void testingGetAllEmployees(){
        when (employeeRepository.findAll()).thenReturn(List.of(new Employee()));
        List <Employee> allEmployees = employeeService.getAllEmployees();
        assertNotNull(allEmployees);
        verify(employeeRepository, times(1)).findAll();
     }

     @Test
     public void testingCreateEmployee(){

      AddEmployeeDto addEmployeeDto1 = new AddEmployeeDto();
      addEmployeeDto1.setFirstName("ABC");
      addEmployeeDto1.setMidName("");
      addEmployeeDto1.setLastName("CBA");
      addEmployeeDto1.setEmail("employee@employee.com");
      addEmployeeDto1.setPhone("16783131");
      addEmployeeDto1.setAddress("P Sherman Wallaby Syd");
      addEmployeeDto1.setEmploymentType("Permanent");
      addEmployeeDto1.setStartDate(formatToLocalDate("01 May 2024"));
      addEmployeeDto1.setFinishDate(formatToLocalDate("01 May 2027"));
      addEmployeeDto1.setWorkType("fullTime");
      addEmployeeDto1.setHoursPerWeek(20);

  
    Employee mockEmployee = new Employee();
    mockEmployee.setFirstName(addEmployeeDto1.getFirstName());
    mockEmployee.setMidName(addEmployeeDto1.getMidName());
    mockEmployee.setLastName(addEmployeeDto1.getLastName());
    mockEmployee.setEmail(addEmployeeDto1.getEmail());
    mockEmployee.setPhone(addEmployeeDto1.getPhone());
    mockEmployee.setAddress(addEmployeeDto1.getAddress());
    mockEmployee.setEmploymentType(addEmployeeDto1.getEmploymentType());
    mockEmployee.setStartDate(addEmployeeDto1.getStartDate());
    mockEmployee.setFinishDate(addEmployeeDto1.getFinishDate());
    mockEmployee.setWorkType(addEmployeeDto1.getWorkType());
    mockEmployee.setHoursPerWeek(addEmployeeDto1.getHoursPerWeek());
  
    when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee);

   //2. act 
      Employee result = employeeService.createEmployee(addEmployeeDto1);

   //3. asserts
    assertNotNull(result);

    assertEquals("ABC", result.getFirstName());

    //4. verifies (check interactions)
    verify(employeeRepository, times(1)).save(any(Employee.class));
      
    }

    LocalDate formatToLocalDate (String dateStr) {
      DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
      return LocalDate.parse(dateStr, timeFormatter);
    }

     @Test
     public void testingDeleteEmployeeIfExist(){

      //1. arrange 
      when(this.employeeRepository.existsById(1)).thenReturn(true); 

      //2. act 
      boolean successfullyDeleted = employeeService.deleteEmployee(1);

      //3. assert 
      assertTrue(successfullyDeleted);

      //4. verify
      verify(employeeRepository).existsById(1);
      verify(employeeRepository).deleteById(1);
     }


     @Test
     public void testingUpdateEmployee(){

      //1. Arrange
      UpdateEmployeeDto updateEmployeeDto1 = new UpdateEmployeeDto();
      updateEmployeeDto1.setFirstName("IHG"); //user wants to change his first name to IHG

      Employee mockEmployee2 = new Employee();
      mockEmployee2.setId(1);
      mockEmployee2.setFirstName("GHI");

      when(employeeRepository.findById(1)).thenReturn(Optional.of(mockEmployee2));
      when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee2);

      //2. Act
      Employee updatedResult = employeeService.editEmployeeDetails(1, updateEmployeeDto1);

      //3. Assert
      assertEquals("IHG", updatedResult.getFirstName());

      //4. Verify
      verify(employeeRepository).findById(1);
      verify(employeeRepository).save(any(Employee.class));
     }

}
