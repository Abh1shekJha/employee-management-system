package example.employeemanagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.employeemanagement.demo.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
