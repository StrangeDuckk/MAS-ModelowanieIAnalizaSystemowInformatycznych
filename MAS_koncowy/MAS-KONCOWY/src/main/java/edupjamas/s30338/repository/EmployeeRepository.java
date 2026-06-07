package edupjamas.s30338.repository;

import edupjamas.s30338.entity.Wielodziedziczenie.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}