<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Add Product</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<center>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
	<div class="main">

		<section class="admin">
			<div class="container">
				<div class="admin-content">
					<div class="admin-form">
						<h2 class="form-title">Add New Product</h2>
						
						
						<form method="post" action="addNewProduct" class="ad-form"
							id="ad-form">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Your Name" title="only alphabet letters with 4 or more letters" pattern="[a-zA-Z]{4,}" required="required"/>
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email" pattern="[a-z0-9,_%+-]+@[a-z0-9,-]+\.[a-z]{2,3}" required="required"/>
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="pass" id="pass" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain more than 8 characters including atleast 1 uppercase letter,1 lowercase letter and number " required="required"/>
							</div>
							<div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="password" name="re_pass" id="re_pass"
									placeholder="Repeat your password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain more than 8 characters including atleast 1 uppercase letter,1 lowercase letter and number " required="required"/>
							</div>
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-phone"></i></label>
								<input type="text" name="contact" id="contact"
									placeholder="Contact no" pattern="[0-9]{10,15}" title="Length Between 10-15 numbers" required="required"/>
							</div>
							<div class="form-group">
								<label for="address"><i class="zmdi zmdi-home"></i></label>
								<input type="text" name="address" id="address"
									placeholder="Address" required="required"/>
							</div>
							<div class="form-group">
								<input type="checkbox" name="agree-term" id="agree-term"
									class="agree-term" /> <label for="agree-term"
									class="label-agree-term"><span><span></span></span>I
									agree all statements in <a href="#" class="term-service">Terms
										of service</a></label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="addAdmin" id="addAdmin"
									class="form-submit" value="Add" />
							</div>
						</form>
					
						
					</div>
				</div>
			</div>
		</section>


	</div>
	</center>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">

<script type="text/javascript">
	var status = document.getElementById("status").value;
	if(status == "success"){
		swal("Congrats","Account created Successfully","success");
	}
	if(status == "InvalidName"){
		swal("Oops","Please Enter Name","error");
	}
	if(status == "InvalidEmail"){
		swal("Oops","Please Enter Email","error");
	}
	if(status == "InvalidPassword"){
		swal("Oops","Please Enter Password","error");
	}
	if(status == "InvalidConfirmPassword"){
		swal("Oops","Password do not match","error");
	}
	if(status == "InvalidMobile"){
		swal("Oops","Please Enter Mobile Number","error");
	}
	if(status == "InvalidMobileLength"){
		swal("Oops","Please Enter Valid Mobile Number below 12 digits","error");
	}
	if(status == "InvalidAddress"){
		swal("Oops","Please Enter Address","error");
	}
	if(status == "failed"){
		swal("Sorry","Email is already used. Login or try with another email","error");
	}if(status == "fail"){
		swal("Oops","Something went wrong. Try again","error");
	}
	if(status == "wrong"){
		swal("Oops","You are not entered the specific word to show that you are admin. Try again","error");
	}
</script>

</body>

</html>