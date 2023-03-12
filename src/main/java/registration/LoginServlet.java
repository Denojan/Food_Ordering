package registration;

import java.sql.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Denojan
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Connection con = null;
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		
		if(email == null || email.equals("")) {
			request.setAttribute("status", "InvalidEmail");
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
		if(password == null || password.equals("")) {
			request.setAttribute("status", "InvalidPassword");
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
		
		try {
			
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes());
			byte[] result = messageDigest.digest();
			StringBuilder sb = new StringBuilder();
			for(byte b : result) {
				sb.append(String.format("%02x", b));
			}
			String encrpytedPassword = sb.toString();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/food?useSSL=false","root","sutha");
			boolean start = email.startsWith("admin");
			boolean staff = email.startsWith("staff");
			if(start != false) {
				Person person = new Admin();
				person.setemail(email);
				person.setpassword(encrpytedPassword);
				
				
				PreparedStatement pst = con.prepareStatement("select * from admin where email = ? and password = ?");
				pst.setString(1, person.getemail());
				pst.setString(2, person.getpassword());
				
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					
					session.setAttribute("email", person.getemail());
					session.setAttribute("name", rs.getString("name"));
					response.sendRedirect("AdminIndex.jsp");
					
				}else {
					request.setAttribute("status", "failed");
					dispatcher = request.getRequestDispatcher("login.jsp");
				}
				dispatcher.forward(request, response);
			}
			else if(staff != false) {
				Person person = new Staff();
				person.setemail(email);
				person.setpassword(encrpytedPassword);
				
				
				PreparedStatement pst = con.prepareStatement("select * from staff where email = ? and password = ?");
				pst.setString(1, person.getemail());
				pst.setString(2, person.getpassword());
				
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					
					session.setAttribute("email", person.getemail());
					session.setAttribute("name", rs.getString("name"));
					response.sendRedirect("StaffIndex.jsp");
					
				}else {
					request.setAttribute("status", "failed");
					dispatcher = request.getRequestDispatcher("login.jsp");
				}
				dispatcher.forward(request, response);
			}
			else {
				Person person = new User();
				person.setemail(email);
				person.setpassword(encrpytedPassword);
			
			
				PreparedStatement pst = con.prepareStatement("select * from user where email = ? and password = ?");
				pst.setString(1, person.getemail());
				pst.setString(2, person.getpassword());
			
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
				
					session.setAttribute("email", person.getemail());
					session.setAttribute("name", rs.getString("name"));
					response.sendRedirect("index.jsp");
				
				}else {
					request.setAttribute("status", "failed");
					dispatcher = request.getRequestDispatcher("login.jsp");
				}
				dispatcher.forward(request, response);
			}
			request.setAttribute("status", "unknown");
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
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
