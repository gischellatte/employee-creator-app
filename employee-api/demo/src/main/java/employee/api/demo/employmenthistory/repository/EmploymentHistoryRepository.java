package employee.api.demo.employmenthistory.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import employee.api.demo.employmenthistory.entity.EmploymentHistory;

public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, Integer> {
    
    
    List <EmploymentHistory> findAll();
    
    
    List <EmploymentHistory> findByEmployeeId(Integer employeeId);
}
