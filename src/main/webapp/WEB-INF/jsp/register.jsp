<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>User Registration</title>
	<style>
		body {
			background-color: #f2f2f2;
			font-family: Arial, sans-serif;
		}
		
		.container {
			margin: auto;
			max-width: 600px;
			padding: 20px;
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
	<div class="container">
	<h1>User Registration</h1>
	<c:if test="${not empty message}">
			<c:choose>
				<c:when test="${message.startsWith('Invalid')}">
					<p class="error-message">${message}</p>
				</c:when>
				<c:otherwise>
					<p class="success-message">${sMessage}</p>
				</c:otherwise>
			</c:choose>
		</c:if>
	<form:form method="POST" modelAttribute= "user" action="/kabx/saveregister">
		<table>
			<tr>
				<td><form:label path="firstName">First Name:</form:label></td>
				<td><form:input path="firstName" required="true"/></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Last Name:</form:label></td>
				<td><form:input path="lastName"/></td>
			</tr>
			<tr>
				<td><form:label path="email">Email:</form:label></td>
				<td><form:input path="email"  required="true"/></td>
			</tr>
			<tr>
				<td><form:label path="password">Password:</form:label></td>
				<td><form:password path="password" required="true"/></td>
			</tr>
			<tr>
				<td><form:label path="phoneNo">Phone Number:</form:label></td>
				<td><form:input path="phoneNo" required="true"/></td>
			</tr>
			<tr>
				<td><form:label path="gender">Gender:</form:label></td>
				<td>
					<form:radiobutton path="gender" value="Male"/>Male
					<form:radiobutton path="gender" value="Female"/>Female
				</td>
			</tr>
			<tr>
				<td><form:label path="accountHolderType">Account Type:</form:label></td>
				<td>
					<form:radiobutton path="accountHolderType" value="ADMIN"/>Admin
					<form:radiobutton path="accountHolderType" value="DRIVER"/>Driver
					<form:radiobutton path="accountHolderType" value="CUSTOMER"/>Customer
				</td>
			</tr>
			<tr>
				<td><form:label path="address.apartment">Apartment:</form:label></td>
				<td><form:input path="address.apartment"/></td>
			</tr>
			<tr>
				<td><form:label path="address.street">Street:</form:label></td>
				<td><form:input path="address.street"/></td>
			</tr>
			<tr>
				<td><form:label path="address.city">City:</form:label></td>
				<td><form:input path="address.city"/></td>
			</tr>
			<tr>
				<td><form:label path="address.country">Country:</form:label></td>
				<td><form:input path="address.country"/></td>
			</tr>
			<tr>
				<td><form:label path="address.pin">PIN:</form:label></td>
				<td><form:input path="address.pin"/></td>
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
				
