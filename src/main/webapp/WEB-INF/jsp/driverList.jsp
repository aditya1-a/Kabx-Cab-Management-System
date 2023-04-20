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
								<c:if test="${not empty successMessage}">
									<div class="alert alert-success alert-dismissible fade show" role="alert">
										<strong>Success!</strong> ${successMessage}
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
										<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:if>
                        <form method="get" action="${pageContext.request.contextPath}/driverList">
                            <div class="form-group">
                                <label for="firstName">Search by First Name:</label>
                                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter first name">
                            </div>
                            <button type="submit" class="btn btn-primary">Search</button>
							<button type="submit" class="btn-btn-primary" name = "reset" value = "true">Reset</button>
                        </form>
                        <br>
                        <form method="post" action="${pageContext.request.contextPath}/deleteDriver">
						<table class="table table-bordered">
							<thead>
								<tr>
                                    <th><input type="checkbox" id="selectAll"></th>
									<th>UserID</th>
									<th>FirstName</th>
									<th>LastName</th>
									<th>Email</th>
									<th>Phone No</th>
                                    <th>Gender</th>
                                    <th>Account Holder Type</th>
								</tr>
							</thead>
							<tbody id="userTable">
                                <c:if test="${not empty drivers}">
                                    <c:forEach var="driver" items="${drivers}">
                                        <tr>
                                            <td><input type="checkbox" name="selectedId" value="${driver.userid}"></td>
											<td>
                                                <c:out value="${driver.userid}"></c:out>
                                            </td>
                                            <td>
                                                <c:out value="${driver.firstName}"></c:out>
                                            </td>
                                            <td>
                                                <c:out value="${driver.lastName}"></c:out>
                                            </td>
                                            <td>
                                                <c:out value="${driver.email}"></c:out>
                                            </td>
                                            <td>
                                                <c:out value="${driver.phoneNo}"></c:out>
                                            </td>
                                            <td>
                                                <c:out value="${driver.gender}"></c:out>
                                            </td>
                                            <td>
                                                <c:out value="${driver.accountHolderType}"></c:out>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty drivers}">
                                    <tr>
                                        <td colspan="6">No data available</td>
                                    </tr>
                                </c:if>
                                <!-- <tr>
                                    <td colspan="7">
                                        <form method="post" action="${pageContext.request.contextPath}/deleteSelected">
                                            <c:forEach var="customer" items="${customers}">
                                                <input type="hidden" name="selectedIds" value="${customer.userid}">
                                            </c:forEach>
                                            <input type="submit" value="Delete Selected">
                                        </form>
                                    </td>
                                </tr> -->

							</tbody>
						</table>
                        <input type="submit" value="Delete Selected">
                    </form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
