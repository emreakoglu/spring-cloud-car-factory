package com.car.factory.carfactoryemployees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.car.factory.carfactoryemployees.model.Department;
import com.car.factory.carfactoryemployees.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	public List<Employee> findByDepartment(Department department);

}
