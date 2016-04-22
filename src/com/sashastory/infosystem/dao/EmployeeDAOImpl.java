package com.sashastory.infosystem.dao;

import com.sashastory.infosystem.model.Employee;
import com.sashastory.infosystem.util.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
	 
	private static Connection connection;
	
	public EmployeeDAOImpl() {
		connection = DButil.getConnection();
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		try {
            String query = "insert into employee (FULL_NAME,PHONE_NUMBER,SALARY,DEPARTMENT_ID) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getFullName());
            preparedStatement.setString(2,employee.getPhoneNumber());
            preparedStatement.setString(3,employee.getSalary());
            preparedStatement.setInt(4,employee.getDepartmentId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");
            while(resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("EMPLOYEE_ID"));
                employee.setFullName(resultSet.getString("FULL_NAME"));
                employee.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                employee.setSalary(resultSet.getString("SALARY"));
                employee.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));
                employees.add(employee);
            }
            System.out.println("Employees length=" + employees.size());
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        employees.forEach(emp -> System.out.println(emp));
        return employees;
	}
	
}
