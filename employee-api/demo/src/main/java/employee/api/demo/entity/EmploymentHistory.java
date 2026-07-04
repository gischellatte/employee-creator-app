package employee.api.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

//Must update seeder with Employment History
@Entity
public class EmploymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    private Integer id;

    private String role;
    private LocalDate StartDate;

    @NotNull
    //null finish date means the person is still listed as an employee
    private LocalDate finishDate;

    @Min(0)
    private Integer hoursPerWeek;

    @NotBlank
    private String department;

    @NotBlank
    private String division;

}
