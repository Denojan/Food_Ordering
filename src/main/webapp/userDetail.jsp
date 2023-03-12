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

<title>User Detail</title>
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">
<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">

<ul class="navbar-nav ms-auto">
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="EditPassword.jsp">EditPassword</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="userDetail.jsp">About</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="index.jsp">Contact</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="logout">Logout</a></li>
					<li class="nav-item mx-0 mx-lg-1 bg-danger"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="Logout"><%=session.getAttribute("name") %></a></li>
				</ul>
<br>
<center><h1>Details of Account holder</h1></center>
<br><br>


<%String email = (String)session.getAttribute("email"); %>

<%



try{
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/food?useSSL=false","root","sutha");
statement=connection.prepareStatement("select * from user where email=?");
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

<a href="updateForm.jsp" class="form-submit" style="text-decoration: none;">Edit Detail</a><br><br>
<a href="EditPassword.jsp" class="form-submit" style="text-decoration: none;">Edit Password</a><br><br>
<form method="post" action="delete"><input type="submit" name="delete" id="delete"
									class="form-submit" value="Delete Account" /></form>
									
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

</body>
</html>