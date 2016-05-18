package com.sashastory.infosystem.dao;

import com.sashastory.infosystem.model.Employee;

import java.util.List;

public interface EmployeeDAO {
	void addEmployee(Employee employee);
	void deleteEmployee(int employeeId);
	void updateEmployee(Employee employee);
	List<Employee> getAllEmployees();
    Employee getEmployeeById(int employeeId);
	List<Employee> searchEmployeesByName(String name);
}
