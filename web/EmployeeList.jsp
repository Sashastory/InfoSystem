<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>All employees</title>
</head>
<body>
<div class="container">
  <h2>List of Employees</h2>   
  <p>Length of employees list is : ${fn:length(employees)}</p>        
  <table class="table table-bordered table-striped">
    <thead>
      <tr>
        <th>Full name</th>
        <th>Phone number</th>
        <th>Salary</th>
        <th>Department Id</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td><c:out value="${employee.fullName}" /></td>
            <td><c:out value="${employee.phoneNumber}" /></td>
            <td><c:out value="${employee.salary}" /></td>
            <td><c:out value="${employee.departmentId}" /></td>
        </tr>
    </c:forEach>
</tbody>
  </table>
</div>
</body>
</html>