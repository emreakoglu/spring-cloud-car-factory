package com.car.factory.carfactoryemployees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.factory.carfactoryemployees.model.Department;
import com.car.factory.carfactoryemployees.model.Employee;
import com.car.factory.carfactoryemployees.repository.EmployeeRepository;
import java.util.List;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.saveAndFlush(employee);
	}
	
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).get();
	}
	
	public void deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
	}
	
	public List<Employee> getEmployeeByDeparment(Department department) {
		return employeeRepository.findByDepartment(department);
	}
	
}
