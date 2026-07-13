package employee.api.demo.employmenthistory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Min;import jakarta.validation.constraints.NotBlank;


import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import employee.api.demo.entity.Employee;

@Entity
@Table(name="employment_history") 
public class EmploymentHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employment_history_id")
    private Integer id;


    @ManyToOne(optional= false, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "EmployeeID", nullable = false)
    private Employee employee; 

    @NotBlank
    private String role;
 
    @NotBlank
    private String department;

    @NotBlank
    private String division;


    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

}
