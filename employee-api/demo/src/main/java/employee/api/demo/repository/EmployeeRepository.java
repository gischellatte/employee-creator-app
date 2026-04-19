package employee.api.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import employee.api.demo.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import employee.api.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    List <Employee> findAll();

    Optional <Employee> findEmployeeByFirstName(String firstName);

    Optional <Employee> findEmployeeByEmail(String email);
}
