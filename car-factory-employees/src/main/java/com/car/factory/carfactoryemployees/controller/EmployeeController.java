package com.car.factory.carfactoryemployees.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.factory.carfactoryemployees.model.Department;
import com.car.factory.carfactoryemployees.model.Employee;
import com.car.factory.carfactoryemployees.model.EmployeeDto;
import com.car.factory.carfactoryemployees.service.EmployeeService;

@RestController
@RequestMapping("/application")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/createEmployee")
	public Employee createEngineEmployee(@RequestBody EmployeeDto employeeDto) {
		
		Employee employee = new Employee();
		employee.setActive(true);
		employee.setDepartment(employeeDto.getDepartment());
		employee.setName(employeeDto.getName());
		employee.setSurname(employeeDto.getSurname());
		employee.setSalary(employeeDto.getSalary());
		
		return employeeService.saveEmployee(employee);
		
	}
	
	@GetMapping("/getEmployeeByDepartment")
	public Employee getEmployeeByDepartment(@RequestParam Department department) {
		List<Employee> departmentEmployee = employeeService.getEmployeeByDeparment(department);
		Random rand = new Random();
		return departmentEmployee.get(rand.nextInt(departmentEmployee.size()));
	}
	
	@GetMapping("/getEmployeeById")
	public Employee getEmployeeById(@RequestParam Long id) {
		return employeeService.getEmployeeById(id);
	}
	
	@DeleteMapping("/deleteEmployeeById")
	public void deleteEmployeeById(@RequestParam Long id) {
		employeeService.deleteEmployeeById(id);
	}

}
