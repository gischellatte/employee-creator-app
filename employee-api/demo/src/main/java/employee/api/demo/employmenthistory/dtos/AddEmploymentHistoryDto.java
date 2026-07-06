package employee.api.demo.employmenthistory.dtos;

import jakarta.validation.constraints.NotBlank;

public class AddEmploymentHistoryDto {

    @NotBlank
    private String role;

    @NotBlank
    private String department;

    @NotBlank
    private String division;

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
