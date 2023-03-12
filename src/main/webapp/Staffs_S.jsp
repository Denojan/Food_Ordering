<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Staff</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/index-styles.css" rel="stylesheet" />
</head>
<body id="page-top">
	<!-- Navigation-->
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="#page-top">Staff</a>
			<button
				class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
					
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="Staffs_S.jsp">Staffs</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="StaffDetail_S.jsp">My Detail</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="viewAllProduct_S.jsp">Products</a></li>	
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="logout">Logout</a></li>
					<li class="nav-item mx-0 mx-lg-1 bg-danger"><a
						class="nav-link py-3 px-0 px-lg-3 rounded"><%=session.getAttribute("name") %></a></li>
				</ul>
			</div>
		</div>
	</nav><br><br>

<input type="hidden" id="status" value="<%= request.getAttribute("status") %>"><br><br>

	

	<div class="updatemain">
	<section class="update">
			<div class="container">
				<div class="update-content">
					<div class="update-form">
				<br><br>
						
						<center><br><br>
					<table style="width:50%; border:2px solid black;border-radius: 10px;border-style: inset; text-align: center; font-size:20px;">
					<tr>
					<td>
					<br>
							<div class="form-group">
								<a href="viewAllStaff_S.jsp" class="form-submit" style="text-decoration: none;"><input type="submit" name="edit" id="edit"
									class="form-submit bg-info" value="View All Staff" /></a><br><br>

							</div>
							<div class="form-group">
								<a href="addStaff_S.jsp" class="form-submit" style="text-decoration: none;"><input type="submit" name="edit" id="edit"
									class="form-submit bg-info" value="Add New Staff" /></a><br><br>
							</div>
							
		
						</td>
							</tr>
					</table>
						</center>
					
				</div>
		</div>
		</div>
	</section>
	</div>

	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">

<script type="text/javascript">
	var status = document.getElementById("status").value;
	if(status == "failed"){
		swal("Sorry","Wrong Username or Password","error");
	}
	if(status == "InvalidEmail"){
		swal("Oops","Please Enter Your Email","error");
	}
	if(status == "InvalidPassword"){
		swal("Oops","Please Enter Your Password","error");
	}
	if(status == "InvalidUser"){
		swal("Oops","Please Enter which User are you","error");
	}
</script>
</body>

</html>