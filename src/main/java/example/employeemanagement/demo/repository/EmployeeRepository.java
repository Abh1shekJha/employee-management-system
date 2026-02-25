package example.employeemanagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.employeemanagement.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
