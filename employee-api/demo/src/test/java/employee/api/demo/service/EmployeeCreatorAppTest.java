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
import static org.mockito.Mockito.times; //double check
import static org.mockito.Mockito.verify;//double check
import static org.mockito.Mockito.when;//double check

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

 //command to test the function in Gitbash: check the pom.xml, in the same directory as pom.xml & run mvn test
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
      //1. make a dto input
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


    // Make a mock result & mock entity returns, this simulates of database results after you save
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

   //2. act - execute what you want to test 
      Employee result = employeeService.createEmployee(addEmployeeDto1);

   //3. asserts - check the result, ensures service does not return a null
    assertNotNull(result);

    //ensures service returns what is expected
    //must test the real object, not the mock
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
      /*logic flow: 
      if the data exists
      1. existsById → true 
      2. return true (the data exists)
      3. the service continues to deleteById(id)
      4. return true to caller
      */

      //1. arrange 
      //the method in the service is existsById, Mockito must mirrors the method in the service
      when(this.employeeRepository.existsById(1)).thenReturn(true); 

      //2. act 
      //employeeService.deleteEmployee() is an act that we want to test
      boolean successfullyDeleted = employeeService.deleteEmployee(1);

      //3. assert 
      //checks the results
      assertTrue(successfullyDeleted);

      //4. verify
      verify(employeeRepository).existsById(1);
      verify(employeeRepository).deleteById(1);
     }

       /*logic flow: 
      if the data does not exist
      1. existsById → false 
      2. return false
      3. no deletion
      4. return false to caller
      */

     @Test
     public void testingUpdateEmployee(){

      //1. Arrange
      UpdateEmployeeDto updateEmployeeDto1 = new UpdateEmployeeDto();
      updateEmployeeDto1.setFirstName("IHG"); //user wants to change his first name to IHG

      Employee mockEmployee2 = new Employee();
      mockEmployee2.setId(1);
      // in the database we have an employee with GHI as the firstName
      mockEmployee2.setFirstName("GHI");

      // when service looks for ID 1, pass the data of GHI 
      when(employeeRepository.findById(1)).thenReturn(Optional.of(mockEmployee2));

      when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee2);

      //2. Act
      //GHI gets updated to IHG
      Employee updatedResult = employeeService.editEmployeeDetails(1, updateEmployeeDto1);

      //3. Assert
      //making sure the firstName update was successful, it has been changed to IHG
      assertEquals("IHG", updatedResult.getFirstName());

      //4. Verify
      //most of the time verify() is applied in the service layer, so it is connected to the calls made from service to the repo (like a boss watching his staff)
      //making sure service takes the old data. 
      //We call the employeeService, but actually we are testing  employeeService
      verify(employeeRepository).findById(1);
      //making sure the update is saved
      verify(employeeRepository).save(any(Employee.class));
     }

}
