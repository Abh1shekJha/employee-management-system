package example.employeemanagement.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.employeemanagement.demo.dto.EmployeeRequest;
import example.employeemanagement.demo.dto.EmployeeResponse;
import example.employeemanagement.demo.entity.Employee;
import example.employeemanagement.demo.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		this.service =service;
	}
	
	@PostMapping
	public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createEmployee(request));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
		
		return ResponseEntity.status(HttpStatus.FOUND).body(service.getEmployee(id));
	}
	
	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
		
		return ResponseEntity.status(HttpStatus.FOUND).body(service.getAllEmployees());
	}
	
	//Get All Using Pagination
	@GetMapping
	public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(Pageable pageable) {

	    Page<EmployeeResponse> employees = service.getAllEmployees(pageable);

	    return ResponseEntity.ok(employees);
	}
	
	//Optional Default Values
	/*
	 * @GetMapping public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(
	 * 
	 * @RequestParam(defaultValue = "0") int page,
	 * 
	 * @RequestParam(defaultValue = "5") int size) {
	 * 
	 * Pageable pageable = PageRequest.of(page, size);
	 * 
	 * Page<EmployeeResponse> employees = service.getAllEmployees(pageable);
	 * 
	 * return ResponseEntity.ok(employees); }
	 */
	
	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request){
		
		EmployeeResponse response = service.updateEmployee(id, request);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable Long id){
		
		EmployeeResponse response = service.delete(id);
		
		return ResponseEntity.ok(response);
	}
}
