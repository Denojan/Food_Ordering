<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%


String driver = "com.mysql.jdbc.Driver";

try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
PreparedStatement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html>
<head>

<title>Admin Detail</title>
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
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">
<style>
body {
background-color:LightGray;

}
</style>
</head>
<body>
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="#page-top">Admin</a>
			<button
				class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto" >
					<li class="nav-item mx-0 mx-lg-1 "><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="Admins.jsp" >Admins</a></li>
					<li class="nav-item mx-0 mx-lg-1 "><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="Staffs.jsp">Staffs</a></li>
					<li class="nav-item mx-0 mx-lg-1 "><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="viewAllUser.jsp">Users</a></li>
					<li class="nav-item mx-0 mx-lg-1 "><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="product.jsp">Products</a></li>	
					<li class="nav-item mx-0 mx-lg-1 "><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="logout">Logout</a></li>
					<li class="nav-item mx-0 mx-lg-1 bg-danger"><a
						class="nav-link py-3 px-0 px-lg-3 rounded"><%=session.getAttribute("name") %></a></li>
				</ul>
			</div>
		</div>
	</nav><br><br>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>"><br><br>

			
			
<br><br><br>
<center><h1>Details of Account holder</h1></center>
<br><br>


<%String email = (String)session.getAttribute("email"); %>

<%



try{
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/food?useSSL=false","root","sutha");
statement=connection.prepareStatement("select * from admin where email=?");
statement.setString(1, email);

resultSet = statement.executeQuery();
while(resultSet.next()){
	
%>

<center>
<table style="width:50%; border:2px solid black;border-radius: 10px;border-style: inset; text-align: center; font-size:20px;">
  <tr>
    <td>
    


Name : <%=resultSet.getString("name") %><br><br>


Email : <%=resultSet.getString("email") %><br><br>


Mobile : <%=resultSet.getString("mobile") %><br><br>


Address : <%=resultSet.getString("address") %><br><br>


<a href="updateAdminForm.jsp" class="form-submit bg-info" style="text-decoration: none;"><input type="submit" name="edit" id="edit"
									class="form-submit bg-info" value="Edit Detail" /></a><br><br>
<a href="EditAdminPassword.jsp" class="form-submit bg-info" style="text-decoration: none;"><input type="submit" name="edit" id="edit"
									class="form-submit bg-info" value="Edit Password" /></a><br><br>
<form method="post" action="delete"><input type="submit" name="delete" id="delete"
									class="form-submit bg-info" value="Delete Account" /></form>
									
					<br>				
</td>
  </tr>
</table>
</center>
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
</script>
			
		
		</div>
	</div>
<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>