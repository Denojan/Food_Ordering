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
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>All Staff</title>
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
<br><br><br>
<center><h1>Details of All Staff</h1></center>
<br><br>


<%String email = (String)session.getAttribute("email"); %>

<center>
<table border="1" cellpadding="12" style="width:50%; border:2px solid black;border-radius: 10px;border-style: inset; text-align: center; font-size:20px;">
  <tr>
  <th>Name </th>
  <th>Email </th>
  <th>Mobile </th>
  <th>Address </th>
  <th>Action </th>
</tr>
<%



try{
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/food?useSSL=false","root","sutha");
statement=connection.prepareStatement("select * from staff");

resultSet = statement.executeQuery();
while(resultSet.next()){

%>

<tr>
   <td><%=resultSet.getString("name") %><br><br></td>
   <td><%=resultSet.getString("email") %><br><br></td>
   <td><%=resultSet.getString("mobile") %><br><br></td>
   <td><%=resultSet.getString("address") %><br><br></td>
	<td><form method="post" action="deleteOther_S"><input type="hidden" name="email" value="<%=resultSet.getString("email") %>"/>
				 <input type="submit" name="delete" id="delete" value= "Delete" class="form-submit bg-info" /> </form><br></td>
</tr>


<%
}
%>

</table>
</center>
<% 
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

</body>
</html>