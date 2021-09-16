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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/application")
@Api(value = "Araba Fabrikası API Dokumantasyonu")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping(value="/createEmployee",produces = "application/json")
	@ApiOperation(value = "Create Employee Operasyonu")
	public Employee createEmployee(@RequestBody @ApiParam("Create Employee Web Service Parameter") EmployeeDto employeeDto) {
		
		Employee employee = new Employee();
		employee.setActive(true);
		employee.setDepartment(employeeDto.getDepartment());
		employee.setName(employeeDto.getName());
		employee.setSurname(employeeDto.getSurname());
		employee.setSalary(employeeDto.getSalary());
		
		return employeeService.saveEmployee(employee);
		
	}
	
	@GetMapping(value = "getEmployeeByDepartment",produces = "application/json")
	@ApiOperation(value = "Get Employee by Department Operasyonu")
	public Employee getEmployeeByDepartment(@RequestParam @ApiParam("Get Employee by Department Parameter") Department department) {
		List<Employee> departmentEmployee = employeeService.getEmployeeByDeparment(department);
		Random rand = new Random();
		return departmentEmployee.get(rand.nextInt(departmentEmployee.size()));
	}
	
	@GetMapping(value="/getEmployeeById",produces = "application/json")
	@ApiOperation(value = "Get Employee by Id Operasyonu")
	public Employee getEmployeeById(@RequestParam @ApiParam("Get Employee by Id Parameter") Long id) {
		return employeeService.getEmployeeById(id);
	}
	
	@DeleteMapping(value="/deleteEmployeeById",produces = "application/json")
	@ApiOperation(value = "Delete Employee by Id Operasyonu")
	public void deleteEmployeeById(@RequestParam @ApiParam("Delete Employee by Id Parameter") Long id) {
		employeeService.deleteEmployeeById(id);
	}

}
