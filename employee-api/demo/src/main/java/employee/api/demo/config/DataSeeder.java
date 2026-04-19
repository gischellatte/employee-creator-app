package employee.api.demo.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;

import employee.api.demo.repository.EmployeeRepository;
import employee.api.demo.entity.Employee;

import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner{
    private EmployeeRepository employeeRepository; 

    public DataSeeder (EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override 
     //you can save in repo under this method too
     //employeeRepository.save(empGenerator);
    public void run(String... args) throws Exception {
        if(this.employeeRepository.count()==0){
            //Create an employee object 
            //Employee class does not have createANewEmployee() method
            createANewEmployee("Zazu", "", "Mzingo", "mzi@example.com", "08123456789", "Chyulu Hills", "Full-time", "contract", 40, LocalDate.now(), LocalDate.now().plusMonths(12), true);
        }
    }

     private void createANewEmployee(String firstName, String midName, String lastName, String email, String phone, String address, String employmentType, String workType, Integer hoursPerWeek, LocalDate startDate, LocalDate finishDate, boolean onGoing) {
        Employee empGenerator = new Employee ();
        empGenerator.setFirstName(firstName);
        empGenerator.setMidName(midName);
        empGenerator.setLastName(lastName);
        empGenerator.setEmail(email);
        empGenerator.setPhone(phone);
        empGenerator.setAddress(address);
        empGenerator.setEmploymentType(employmentType);
        empGenerator.setWorkType(workType);
        empGenerator.setHoursPerWeek(hoursPerWeek);
        empGenerator.setStartDate(startDate);
        empGenerator.setFinishDate(finishDate);
        empGenerator.setOnGoing(onGoing);

        //you can save in repo under this method too
        employeeRepository.save(empGenerator);
        System.out.println("One employee seeded");
    }

}
