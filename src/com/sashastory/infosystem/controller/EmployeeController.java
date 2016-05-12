package com.sashastory.infosystem.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.sashastory.infosystem.dao.*;
import com.sashastory.infosystem.model.Employee;
import com.sun.glass.ui.Window.Level;
/**
 * Servlet implementation class EmployeeController
 */
@WebServlet(urlPatterns = "/InfoSystem")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDAO dao;
	public static final String listParam = "/list-employees.jsp";
	public static final String insertOrEditParam = "/employee.jsp";
	/**
	 * Default constructor.
	 */
	public EmployeeController() {
		// TODO Auto-generated constructor stub
		dao = new EmployeeDAOImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("searchAction");
		if (action != null) {
			switch (action) {
				case "searchById":
					searchEmployeeById(request, response);
					break;
				case "searchByName":
					searchEmployeeByName(request, response);
					break;
			}
		} else {
			List<Employee> employees = dao.getAllEmployees();
			forwardEmployeeList(request, response,employees);
		}
	}

	private void searchEmployeeById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int employeeId = Integer.valueOf(request.getParameter("employeeId"));
		Employee employee = null;
		try {
			employee = dao.getEmployeeById(employeeId);
		} catch (Exception ex) {
			//Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
		}
		request.setAttribute("employee", employee);
		request.setAttribute("action", "edit");
		RequestDispatcher view = request.getRequestDispatcher(insertOrEditParam);
		view.forward(request, response);
	}

	private void searchEmployeeByName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("employeeName");
		List<Employee> employees = dao.searchEmployeesByName(name);
		forwardEmployeeList(request, response,employees);
	}

	private void forwardEmployeeList(HttpServletRequest request, HttpServletResponse response,
									 List<Employee> employees)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher(listParam);
		request.setAttribute("employees", employees);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		switch (action) {
			case "add":
				addEmployeeAction(request, response);
				break;
			case "edit":
				editEmployeeAction(request, response);
				break;
			case "remove":
				removeEmployeeAction(request, response);
				break;
		}
	}

	private void addEmployeeAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee employee = new Employee();
		if (request.getParameter("fullName").length() < 4) {
			String message = "Full name of the employee has to be longer than 3 characters!";
			request.setAttribute("error",message);
			forwardEmployeeList(request,response,dao.getAllEmployees());
			return;
		} else {
			employee.setFullName(request.getParameter("fullName"));
		}
		employee.setPhoneNumber(request.getParameter("phoneNumber"));
		employee.setSalary(request.getParameter("salary"));
		if (request.getParameter("departmentId").equalsIgnoreCase("")) {
			employee.setDepartmentId(0);
		} else {
			employee.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
		}
		dao.addEmployee(employee);
		List<Employee> employees = dao.getAllEmployees();
		String message = "The new employee has been successfully created.";
		request.setAttribute("message", message);
		forwardEmployeeList(request, response, employees);
	}

	private void editEmployeeAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee employee = new Employee();
		employee.setFullName(request.getParameter("fullName"));
		employee.setPhoneNumber(request.getParameter("phoneNumber"));
		employee.setSalary(request.getParameter("salary"));
		employee.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
		int employeeId = Integer.valueOf(request.getParameter("employeeId"));
		employee.setEmployeeId(employeeId);
		dao.updateEmployee(employee);
		String message = "The employee has been successfully updated.";
		request.setAttribute("message", message);
		List<Employee> employees = dao.getAllEmployees();
		forwardEmployeeList(request, response, employees);
	}

	private void removeEmployeeAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int employeeId = Integer.valueOf(request.getParameter("employeeId"));
		dao.deleteEmployee(employeeId);
		String message = "The employee has been successfully removed.";
		request.setAttribute("message", message);
		List<Employee> employees = dao.getAllEmployees();
		forwardEmployeeList(request, response, employees);
	}
}