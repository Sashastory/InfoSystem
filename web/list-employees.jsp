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
<title>Info System</title>
</head>
<body>          
        <div class="container">
            <h2>Info System of Employees</h2>
            <!--Search Form -->
            <form action="InfoSystem" method="get" id="seachEmployeeForm" role="form">
                <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
                <div class="form-group col-xs-5">
                    <input type="text" name="employeeName" id="employeeName" class="form-control" required="true" placeholder="Type the Name or Last Name of the employee"/>                    
                </div>
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Search
                </button>
                <br>
                <br>
            </form>

            <!--Employees List-->
            <c:if test="${not empty message}">                
                <div class="alert alert-success">
                    ${message}
                </div>
            </c:if> 
           	<c:if test="${not empty error}">                
                <div class="alert alert-info">
                    ${error}
                </div>
            </c:if> 
            <form action="InfoSystem" method="post" id="employeeForm" role="form" >              
                <input type="hidden" id="employeeId" name="employeeId">
                <input type="hidden" id="action" name="action">
                <c:choose>
                    <c:when test="${not empty employees}">
                        <table  class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <td>Full Name</td>
                                    <td>Phone Number</td>
                                    <td>Salary</td>
                                    <td>Department Id</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </thead>
                            
                            <c:forEach var="employee" items="${employees}">
                                <c:set var="classSucess" value=""/>
                                <c:if test ="${employeeId == employee.employeeId}">                        	
                                    <c:set var="classSucess" value="info"/>
                                </c:if>
                                <tr class="${classSucess}">                                 
                                    <td>${employee.fullName}</td>
                                    <td>${employee.phoneNumber}</td>
                                    <td>${employee.salary}</td>
                                    <td>${employee.departmentId}</td>  
                                    <td><a href="/InfoSystem?employeeId=${employee.employeeId}&searchAction=searchById">
                                    		<span class="glypchicon glyphicon-pencil"/>
                                        </a>
                                    </td>
                                    <td><a href="#" id="remove" 
                                           onclick="document.getElementById('action').value = 'remove';document.getElementById('employeeId').value = '${employee.employeeId}';                                             
                                                    document.getElementById('employeeForm').submit();"> 
                                            <span class="glyphicon glyphicon-trash"/>
                                        </a>                                                
                                   </td>
                                </tr>
                            </c:forEach>               
                        </table>  
                    </c:when>                    
                    <c:otherwise>
                        <br>           
                        <div class="alert alert-info">
                            No people found matching your search criteria
                        </div>
                    </c:otherwise>
                </c:choose>                        
            </form>
            <form action ="employee.jsp">            
                <br>
                <button type="submit" class="btn btn-primary  btn-md">New employee</button> 
            </form>
        </div>
    </body>
</html>