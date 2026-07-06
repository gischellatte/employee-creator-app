package employee.api.demo.employmenthistory.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import employee.api.demo.employmenthistory.entity.EmploymentHistory;

public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, Integer> {
    //findAll() is a Spring Data JPA method, findByXxx() must be based on the entity
    List <EmploymentHistory> findAll();
}
