package com.sashastory.infosystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.sashastory.infosystem.util.DButil;
import com.sashastory.infosystem.model.Employee;
public class EmployeeDAOImpl implements EmployeeDAO {

    private static Connection connection;

    public EmployeeDAOImpl() {
        connection = DButil.getConnection();
    }

    @Override
    public void addEmployee(Employee employee) {
        try {
            String query = "insert into employee (FULL_NAME,PHONE_NUMBER,SALARY,DEPARTMENT_ID) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getFullName());
            preparedStatement.setString(2,employee.getPhoneNumber());
            preparedStatement.setString(3,employee.getSalary());
            if (employee.getDepartmentId() == 0) {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setInt(4,employee.getDepartmentId());
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        try {
            String query = "delete from employee where EMPLOYEE_ID=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, employeeId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try {
            String query = "update employee set FULL_NAME=?,PHONE_NUMBER=?,SALARY=?,DEPARTMENT_ID=? where EMPLOYEE_ID=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,employee.getFullName());
            ps.setString(2,employee.getPhoneNumber());
            ps.setString(3,employee.getSalary());
            ps.setInt(4,employee.getDepartmentId());
            ps.setInt(5,employee.getEmployeeId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        employees.forEach(emp -> System.out.println(emp.getFullName()));
        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        Employee employee = new Employee();
        try {
            String query = "select * from EMPLOYEE where EMPLOYEE_ID=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, employeeId);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                employee.setEmployeeId(resultSet.getInt("EMPLOYEE_ID"));
                employee.setFullName(resultSet.getString("FULL_NAME"));
                employee.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                employee.setSalary(resultSet.getString("SALARY"));
                employee.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));
            }
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT EMPLOYEE_ID,FULL_NAME,PHONE_NUMBER,SALARY,DEPARTMENT_ID "
                    +"FROM EMPLOYEE "
                    +"WHERE REGEXP_LIKE (FULL_NAME,'"+name+"','i')");
            while(resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("EMPLOYEE_ID"));
                employee.setFullName(resultSet.getString("FULL_NAME"));
                employee.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                employee.setSalary(resultSet.getString("SALARY"));
                employee.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));
                employees.add(employee);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        employees.forEach(emp -> System.out.println(emp.getFullName()));
        return employees;
    }

}
