<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <div class="container-fluid mt-4">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
                        <table class="table table-bordered">
                            <tr>
                                <th>FirstName</th>
                                <th>LastName</th>
                                <th>Email</th>
                                <th>Action</th>
                            </tr>
                            <c:if test = "${empty drivers}">
                                <tr>
                                    <td colspan = "4">No drivers pending for approval</td>
                                </tr>
                            </c:if>
                            <c:forEach items = "${drivers}" var = "driverList">
                                <tr>
                                    <td><c:out value = "${driverList.firstName}"/></td>
                                    <td><c:out value = "${driverList.lastName}"/></td>
                                    <td><c:out value = "${driverList.email}"/></td>
                                    <td>
                                        <form method="POST" action="${pageContext.request.contextPath}/approvalList">
                                            <input type="hidden" name="selectedId" value="${driverList.userid}"/>
                                            <input type="submit" value="Approve"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <p><c:out value="${sMessage}"/></p>
                        <p><c:out value="${errorMessage}"/></p>
                    </div>
                </div>
            </div>
        </div>
    </div> 
</body>
</html>