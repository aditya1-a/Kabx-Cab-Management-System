<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<anyxmlelement xmlns:c="jakarta.tags.core" /> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cab Management System</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <style>
		body {
			background-color: #f2f2f2;
			font-family: Arial, sans-serif;
		}
		
		.container {
			margin: center;
			max-width: 600px;
			padding: 50px;
			background-color: #fff;
			box-shadow: 0 0 10px #ccc;
			border-radius: 5px;
		}
		
		h1 {
			text-align: center;
			color: #333;
		}
		
		form {
			margin-top: 20px;
		}
		
		label {
			display: block;
			margin-bottom: 10px;
			font-weight: bold;
		}
		
		input[type="text"], select {
			padding: 10px;
			margin-bottom: 20px;
			border-radius: 5px;
			border: none;
			background-color: #f2f2f2;
			width: 100%;
			box-sizing: border-box;
		}
		
		input[type="submit"] {
			padding: 10px 20px;
			background-color: #333;
			color: #fff;
			border: none;
			border-radius: 5px;
			cursor: pointer;
		}
		
		input[type="submit"]:hover {
			background-color: #555;
		}
		
		.error-message {
			color: red;
			font-weight: bold;
			margin-bottom: 20px;
		}
		
		.success-message {
			color: green;
			font-weight: bold;
			margin-bottom: 20px;
		}
	</style>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Cab Management System - Admin</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="#">Home</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="userList" href="/kabx/userList">User List</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="driverList" href="/kabx/driverList">Driver List</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="driverList" href="/kabx/approvalList">Approvals</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown">
						Car Brand
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/kabx/registerCarBrand">Add a CarBrand</a>
						<a class="dropdown-item" href="#">CarBrand List</a>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown">
						Car Details
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">Add Car Details</a>
						<a class="dropdown-item" href="#">Car List</a>
					</div>
				</li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown">
						Admin
					</a>
					<div class="dropdown-menu dropdown-menu-right">
						<a class="dropdown-item" href="/kabx/login">Logout</a>
					</div>
				</li>
			</ul>
		</div>
	</nav>
    <div class="container">
        <h1>Car Brand Registration</h1>
        <c:if test="${not empty sMessage}">
         <div class="success-message">${sMessage}</div>
      </c:if>
      <c:if test="${not empty errorMessage}">
         <div class="error-message">${errorMessage}</div>
      </c:if>
            <form:form method="POST" modelAttribute= "CarBrand" action="/kabx/registerCarBrand">
		<table>
			<tr>
				<td><form:label path="carBrandName">Car Brand:</form:label></td>
				<td><form:input path="carBrandName" type = "text" required="true"/></td>
			</tr>
            <tr>
				<td colspan="2">
					<input type="submit" value="Register"/>
                </td>
            </tr>
        </table>
    </form:form>
	</div>
</body>
</html>