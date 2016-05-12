<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Add New Employee</title>
</head>
    <body>
        <div class="container">
            <form action="InfoSystem" method="post"  role="form" data-toggle="validator" >
                <c:if test ="${empty action}">                        	
                    <c:set var="action" value="add"/>
                </c:if>
                <input type="hidden" id="action" name="action" value="${action}">
                <input type="hidden" id="employeeId" name="employeeId" value="${employee.employeeId}">
                <h2>Employee</h2>
                <div class="form-group col-xs-4">
                    <label for="fullName" class="control-label col-xs-4">Full Name:</label>
                    <input type="text" name="fullName" id="fullName" class="form-control" value="${employee.fullName}" required/>                                   

                    <label for="phoneNumber" class="control-label col-xs-4">Phone:</label>                   
                    <input type="text" name="phoneNumber" id="phoneNumber" class="form-control" value="${employee.phoneNumber}" maxlength="10" required/> 

                    <label for="salary" class="control-label col-xs-4">Salary:</label>                 
                    <input type="text" name="salary" id="salary" class="form-control" value="${employee.salary}" min="500" maxlength = "5" required/>

                    <label for="departmentId" class="control-label col-xs-4">Department:</label>                    
                    <input type="text" name="departmentId" id="departmentId" class="form-control" value="${employee.departmentId}"/> 

                    <br>
                    <button type="submit" class="btn btn-primary  btn-md">Accept</button> 
                </div>                                                      
            </form>
        </div>
    </body>
</html>