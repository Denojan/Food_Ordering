<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>


<%

String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "food";
String userid = "root";
String password = "sutha";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
PreparedStatement statement = null;
ResultSet resultSet = null;
%>
<html>
<head>
<title>Admin Password Update Form</title>
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">
	<link rel="stylesheet" href="css/style.css">
	
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
<%String email = (String)session.getAttribute("email"); %>

<%

try{
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.prepareStatement("select * from admin where email=?");
statement.setString(1,email);
resultSet = statement.executeQuery();
while(resultSet.next()){
%>
	<br>
	<div class="updatemain">
	<section class="update">
			<div class="container">
				<div class="update-content">
					<div class="update-form">
						
						<form method="post" action="updateadminpassword" class="update-form"
							id="update-form">
							
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="pass" id="pass"  placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain more than 8 characters including atleast 1 uppercase letter,1 lowercase letter and number " required="required"/>
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="new_pass" id="new_pass"  placeholder="New Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain more than 8 characters including atleast 1 uppercase letter,1 lowercase letter and number " required="required"/>
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="con_new_pass" id="con_new_pass"  placeholder="Confirm New Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain more than 8 characters including atleast 1 uppercase letter,1 lowercase letter and number " required="required"/>
							</div>
							
							<div class="form-group form-button">
								<input type="submit" name="updatepassword" id="updatepassword"
									class="form-submit" value="Edit Password" />
							</div>
							
						</form>
					
						</div>
											
							
					</div>
					
				</div>
			
		</section>
	</div>
	<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	
	<script type="text/javascript">
	var status = document.getElementById("status").value;
	if(status == "success"){
		swal("Congrats","Updated Successfully","success");
	}
	if(status == "InvalidNew"){
		swal("Oops","Please Enter New Password","error");
	}
	if(status == "InvalidNewConfirm"){
		swal("Oops","Please Enter New Confirm Password","error");
	}
	if(status == "InvalidPassword"){
		swal("Oops","Please Enter Password","error");
	}
	if(status == "InvalidConfirmPassword"){
		swal("Oops","New, Confirm Password do not match","error");
	}
	if(status == "failed"){
		swal("Oops","Please Enter Correct Password","error");
	}if(status == "fail"){
		swal("Oops","Something went wrong. Try again","error");
	}
</script>
</body>
</html>
