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
						<a class="dropdown-item" href="/kabx/CarsList">Add Car Details</a>
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
				<h1>Welcome to Cab Management System - Admin Home Page</h1>
			</div>
		</div>
	</div>
</body>
</html>
