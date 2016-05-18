package com.sashastory.infosystem.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sashastory.infosystem.model.Employee;

/**
 * Created by Alex on 18.05.2016.
 */
public class QueryUtil {

    private static Connection connection = DButil.getConnection();

    public QueryUtil() {}

    public static void addEmployeeQuery(Employee employee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtil.insertQuery);
            preparedStatement.setString(1,employee.getFullName());
            preparedStatement.setString(2,employee.getPhoneNumber());
            preparedStatement.setString(3,employee.getSalary());
            if (employee.getDepartmentId().equals("")) {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setString(4,employee.getDepartmentId());
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEmployeeQuery(int employeeId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtil.deleteQuery);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateEmployeeQuery(Employee employee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtil.updateQuery);
            preparedStatement.setString(1,employee.getFullName());
            preparedStatement.setString(2,employee.getPhoneNumber());
            preparedStatement.setString(3,employee.getSalary());
            preparedStatement.setString(4,employee.getDepartmentId());
            preparedStatement.setInt(5,employee.getEmployeeId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> getAllEmployeesQuery() {
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
                String department = resultSet.getString("DEPARTMENT_ID");
                if (resultSet.wasNull()) {
                    employee.setDepartmentId("No department");
                } else if (department.equals("10")) {
                    employee.setDepartmentId(StringUtil.adminDepartment);
                } else if(department.equals("20")) {
                    employee.setDepartmentId(StringUtil.marketDepartment);
                } else if(department.equals("30")) {
                    employee.setDepartmentId(StringUtil.hrDepartment);
                } else if(department.equals("40")) {
                    employee.setDepartmentId(StringUtil.itDepartment);
                }
                employees.add(employee);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static Employee getEmployeeQuery(int employeeId) {
        Employee employee = new Employee();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtil.getByIdQuery);
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                employee.setEmployeeId(resultSet.getInt("EMPLOYEE_ID"));
                employee.setFullName(resultSet.getString("FULL_NAME"));
                employee.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                employee.setSalary(resultSet.getString("SALARY"));
                String department = resultSet.getString("DEPARTMENT_ID");
                if (resultSet.wasNull()) {
                    employee.setDepartmentId("No department");
                } else if (department.equals("10")) {
                    employee.setDepartmentId(StringUtil.adminDepartment);
                } else if(department.equals("20")) {
                    employee.setDepartmentId(StringUtil.marketDepartment);
                } else if(department.equals("30")) {
                    employee.setDepartmentId(StringUtil.hrDepartment);
                } else if(department.equals("40")) {
                    employee.setDepartmentId(StringUtil.itDepartment);
                }
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static List<Employee> searchEmployeeQuery(String name) {
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
                String department = resultSet.getString("DEPARTMENT_ID");
                if (resultSet.wasNull()) {
                    employee.setDepartmentId("No department");
                } else if (department.equals("10")) {
                    employee.setDepartmentId(StringUtil.adminDepartment);
                } else if(department.equals("20")) {
                    employee.setDepartmentId(StringUtil.marketDepartment);
                } else if(department.equals("30")) {
                    employee.setDepartmentId(StringUtil.hrDepartment);
                } else if(department.equals("40")) {
                    employee.setDepartmentId(StringUtil.itDepartment);
                }
                employees.add(employee);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}

