package example.employeemanagement.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import example.employeemanagement.demo.dto.EmployeeRequest;
import example.employeemanagement.demo.dto.EmployeeResponse;
import example.employeemanagement.demo.entity.Department;
import example.employeemanagement.demo.entity.Employee;
import example.employeemanagement.demo.repository.DepartmentRepository;
import example.employeemanagement.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
	}
	

	public EmployeeResponse createEmployee(EmployeeRequest request) {
		
		Department department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department not found"));
		Employee employee = new Employee();
		employee.setName(request.getName());
		employee.setEmail(request.getEmail());
		employee.setDepartment(department);
		
		Employee saved = employeeRepository.save(employee);
		
		EmployeeResponse response = new EmployeeResponse();
		response.setId(saved.getId());
		response.setName(saved.getName());
		response.setEmail(saved.getEmail());
		response.setDepartmentName(department.getName());

		return response;
	}
	
	public EmployeeResponse getEmployee(Long id) {
		EmployeeResponse response = new EmployeeResponse();
		
		//Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
		
		response.setId(employeeRepository.getReferenceById(id).getId());
		response.setName(employeeRepository.getReferenceById(id).getName());
		response.setEmail(employeeRepository.getReferenceById(id).getEmail());
		response.setDepartmentName(employeeRepository.getReferenceById(id).getDepartment().getName());
		return response;
	}
	
	public List<EmployeeResponse> getAllEmployees() {
		List<EmployeeResponse> empList = new ArrayList<>();
		List<Employee> employees = new ArrayList<>();
		employees=employeeRepository.findAll();
		int x=0;
		for(Employee e: employees) {
			EmployeeResponse response = new EmployeeResponse();
			response.setId(e.getId());
			response.setEmail(e.getEmail());
			response.setDepartmentName(e.getDepartment().getName());
			response.setName(e.getName());
			
			empList.add(response);
			
		}
		return empList;
		
		
	}
	private EmployeeResponse mapToResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setDepartmentName(employee.getDepartment().getName());

        return response;
    }
	
	public EmployeeResponse delete(Long id) {
		
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found!"));
		
		
		employeeRepository.delete(employee);
		return mapToResponse(employee);
	}
	public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
		
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found!"));
		
		Department department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new RuntimeException("Employee not found!"));
		
		
		employee.setName(request.getName());
		employee.setEmail(request.getEmail());
		employee.setDepartment(department);
		
		Employee updated = employeeRepository.save(employee);
		
		return mapToResponse(updated);
		
	}
	
}

