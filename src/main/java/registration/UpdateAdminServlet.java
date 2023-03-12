package registration;

import java.sql.*;
import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Denojan
 * Servlet implementation class UpdateAdminServlet
 */
@WebServlet("/updateadminservlet")
public class UpdateAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		
		
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String mobile = request.getParameter("contact");	
		String address = request.getParameter("address");
		
		RequestDispatcher dispatcher = null;
		Connection con = null;

		
		if(name == null || name.equals("")) {
			request.setAttribute("status", "InvalidName");
			dispatcher = request.getRequestDispatcher("updateAdminForm.jsp");
			dispatcher.forward(request, response);
		}
		
		
		if(password == null || password.equals("")) {
			request.setAttribute("status", "InvalidPassword");
			dispatcher = request.getRequestDispatcher("updateAdminForm.jsp");
			dispatcher.forward(request, response);
		}
		
		if(mobile == null || mobile.equals("")) {
			request.setAttribute("status", "InvalidMobile");
			dispatcher = request.getRequestDispatcher("updateAdminForm.jsp");
			dispatcher.forward(request, response);
		}else if(mobile.length() > 12) {
			request.setAttribute("status", "InvalidMobileLength");
			dispatcher = request.getRequestDispatcher("updateAdminForm.jsp");
			dispatcher.forward(request, response);
		}
		
		if(address == null || address.equals("")) {
			request.setAttribute("status", "InvalidAddress");
			dispatcher = request.getRequestDispatcher("updateAdminForm.jsp");
			dispatcher.forward(request, response);
		}
		
		
try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food?useSSL=false","root","sutha");
	
	Person person = new Admin(name,password,mobile,address);
	person.setemail((String)session.getAttribute("email"));
	
	PreparedStatement pst1 = con.prepareStatement("select password from admin where email=?");
	pst1.setString(1, person.getemail());

	ResultSet rs = pst1.executeQuery();
	
	String Password = null;
	while(rs.next()) {
		Password = rs.getString("password");
		
	}
	
	MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	messageDigest.update(password.getBytes());
	byte[] result = messageDigest.digest();
	StringBuilder sb = new StringBuilder();
	for(byte b : result) {
		sb.append(String.format("%02x", b));
	}
	String encrpytedPassword = sb.toString();	
	
	person.setpassword(encrpytedPassword);
		if(encrpytedPassword.equals(Password)) {
		
			PreparedStatement pst = con.prepareStatement("update admin set name = ? ,mobile=?, address=? where email =?");
			pst.setString(1, person.getname());
			pst.setString(2, person.getmobile());
			pst.setString(3, person.getaddress());
			pst.setString(4, person.getemail());

			
			int rowCount = pst.executeUpdate();
			
			
			
			if(rowCount != 0) {
				session.setAttribute("name", person.getname());
				request.setAttribute("status", "success");
				response.sendRedirect("AdminDetail.jsp");
				
			}else {
				request.setAttribute("status", "fail");
				dispatcher = request.getRequestDispatcher("updateAdminForm.jsp");
				dispatcher.forward(request, response);
			}	
			
		}
		
		request.setAttribute("status", "failed");
		dispatcher = request.getRequestDispatcher("updateAdminForm.jsp");
		dispatcher.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

