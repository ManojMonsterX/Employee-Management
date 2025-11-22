package com.example.Employee_Management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee_Management.exception.resourceNotFoundException;
import com.example.Employee_Management.model.employee;
import com.example.Employee_Management.repository.EmployeeRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class employeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	@PostMapping("/employees")
	public employee createEmployee(@RequestBody employee Employee) {
		return employeeRepository.save(Employee);
	}
	@GetMapping("/employees/{id}") 
	public ResponseEntity<employee> getEmployeeById(@PathVariable Long id){
		employee Employee = employeeRepository.findById(id).orElseThrow(()-> new resourceNotFoundException("Employee not exist with id:"+ id));
		return ResponseEntity.ok(Employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<employee> updateEmployee(@PathVariable Long id, @RequestBody employee emplopyeeDetails) {
		employee Employee = employeeRepository.findById(id)
				.orElseThrow(() -> new resourceNotFoundException("Employee not exist with id :" +id));
		Employee.setFirstName(emplopyeeDetails.getFirstName());
		Employee.setLastName(emplopyeeDetails.getLastName());
		Employee.setEmail(emplopyeeDetails.getEmail());
		employee updateEmployee = employeeRepository.save(Employee);
		return ResponseEntity.ok(updateEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		employee Employee = employeeRepository.findById(id)
				.orElseThrow(() -> new resourceNotFoundException("Employee not exist with id" + id));
		employeeRepository.delete(Employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	@GetMapping("/employees")
	public List<employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
}
