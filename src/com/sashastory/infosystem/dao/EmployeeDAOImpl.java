package com.sashastory.infosystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.sashastory.infosystem.util.DButil;
import com.sashastory.infosystem.util.QueryUtil;
import com.sashastory.infosystem.model.Employee;
public class EmployeeDAOImpl implements EmployeeDAO {

    private static Connection connection;

    public EmployeeDAOImpl() {
        connection = DButil.getConnection();
    }

    @Override
    public void addEmployee(Employee employee) {
        QueryUtil.addEmployeeQuery(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        QueryUtil.deleteEmployeeQuery(employeeId);
    }

    @Override
    public void updateEmployee(Employee employee) {
        QueryUtil.updateEmployeeQuery(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees = QueryUtil.getAllEmployeesQuery();
        employees.forEach(emp -> System.out.println(emp.getFullName()));
        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        Employee employee = new Employee();
        employee = QueryUtil.getEmployeeQuery(employeeId);
        return employee;
    }

    @Override
    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> employees = QueryUtil.searchEmployeeQuery(name);
        employees.forEach(emp -> System.out.println(emp.getFullName()));
        return employees;
    }

}
