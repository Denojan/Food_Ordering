<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Product</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>

<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">

	

	<div class="updatemain">
	<section class="update">
			<div class="container">
				<div class="update-content">
					<div class="update-form">
				
						
					<center>
							<div class="form-group">
								<a href="viewAllProduct.jsp" class="form-submit" style="text-decoration: none;">View All Product</a><br><br>

							</div>
							<div class="form-group">
								<a href="addProduct.jsp" class="form-submit" style="text-decoration: none;">Add New Product</a><br><br>
							</div>
							
		
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

</html>>