package com.sashastory.infosystem.dao;

import com.sashastory.infosystem.model.Employee;

import java.util.List;

public interface EmployeeDAO {
	public void addEmployee(Employee employee);
	public void deleteEmployee(int employeeId);
	public void updateEmployee(Employee employee);
	public List<Employee> getAllEmployees();
    public Employee getEmployeeById(int employeeId);
	public List<Employee> searchEmployeesByName(String name);
}
