package employee.api.demo.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;

import employee.api.demo.repository.EmployeeRepository;
import employee.api.demo.employmenthistory.entity.EmploymentHistory;
import employee.api.demo.employmenthistory.repository.EmploymentHistoryRepository;
import employee.api.demo.entity.Employee;

import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner{
    private EmployeeRepository employeeRepository;
    private EmploymentHistoryRepository employmentHistoryRepository;

    public DataSeeder (EmployeeRepository employeeRepository, EmploymentHistoryRepository employmentHistoryRepository){
        this.employeeRepository = employeeRepository;
        this.employmentHistoryRepository = employmentHistoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.employeeRepository.count()==0){
            createANewEmployee("Zazu", "", "Mzingo", "mzi@example.com", "08123456789", "Chyulu Hills", "Full-time", "contract", 40, LocalDate.now(), LocalDate.now().plusMonths(12), true);
        }

        if(this.employmentHistoryRepository.count()==0){
           Employee empId9 = employeeRepository.findById(9).get();
            createANewEmploymentHistory(empId9, "Intermediate DevOps Engineer", "DevOps", "IT");
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


        employeeRepository.save(empGenerator);
        System.out.println("One employee seeded");
    }

    private void createANewEmploymentHistory(Employee emp, String role, String department, String division){
        EmploymentHistory ehGenerator = new EmploymentHistory ();
        ehGenerator.setEmployee(emp);
        ehGenerator.setRole(role);
        ehGenerator.setDepartment(department);
        ehGenerator.setDivision(division);

        employmentHistoryRepository.save(ehGenerator);
        System.out.println("One employment history record seeded");
    }

}
