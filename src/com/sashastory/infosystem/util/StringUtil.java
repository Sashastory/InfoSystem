package com.sashastory.infosystem.util;

/**
 * Created by Alex on 18.05.2016.
 */
public class StringUtil {

    public static final String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    public static final String user = "hr";
    public static final String pass = "oraclehr";

    public static final String listParam = "/list-employees.jsp";
    public static final String insertOrEditParam = "/employee.jsp";

    public static final String errorMessage = "Full name of the employee has to be longer than 3 characters!";
    public static final String createMessage = "The new employee has been successfully created.";
    public static final String updateMessage = "The employee has been successfully updated.";
    public static final String removeMessage = "The employee has been successfully removed.";

    public static final String insertQuery = "insert into employee "
            + "(FULL_NAME,PHONE_NUMBER,SALARY,DEPARTMENT_ID) values (?,?,?,?)";
    public static final String deleteQuery = "delete from employee where EMPLOYEE_ID=?";
    public static final String updateQuery = "update employee "
            + "set FULL_NAME=?,PHONE_NUMBER=?,SALARY=?,DEPARTMENT_ID=? where EMPLOYEE_ID=?";
    public static final String getByIdQuery = "select * from EMPLOYEE where EMPLOYEE_ID=?";

    public static final String adminDepartment = "Administration";
    public static final String marketDepartment = "Marketing";
    public static final String hrDepartment = "Human Resources";
    public static final String itDepartment = "IT";
}
