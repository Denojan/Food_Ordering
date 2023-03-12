package registration;

import java.sql.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String Repassword = request.getParameter("re_pass");
		String mobile = request.getParameter("contact");
		String address = request.getParameter("address");
		
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		if(name == null || name.equals("")) {
			request.setAttribute("status", "InvalidName");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
		if(email == null || email.equals("")) {
			request.setAttribute("status", "InvalidEmail");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
		if(password == null || password.equals("")) {
			request.setAttribute("status", "InvalidPassword");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if(!password.equals(Repassword)) {
			request.setAttribute("status", "InvalidConfirmPassword");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
		if(mobile == null || mobile.equals("")) {
			request.setAttribute("status", "InvalidMobile");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}else if(mobile.length() > 12) {
			request.setAttribute("status", "InvalidMobileLength");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
		if(address == null || address.equals("")) {
			request.setAttribute("status", "InvalidAddress");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		
if(password.equals(Repassword)) {
	try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food?useSSL=false","root","sutha");
		
			boolean start_s = email.startsWith("staff");
			boolean start_a = email.startsWith("admin");
			
			if(start_a != false) {
			PreparedStatement pst = con.prepareStatement("select email from admin");
			ResultSet rs = pst.executeQuery();
			int match = 0;
			while(rs.next()) {
				String inEmail = rs.getString("email");
				if(email.equals(inEmail)) {
					match = 1;
					break;
				}
			}
			if(match == 1) {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("registration.jsp");
				dispatcher.forward(request, response);
			}
			else {
				MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.update(password.getBytes());
				byte[] result = messageDigest.digest();
				StringBuilder sb = new StringBuilder();
				for(byte b : result) {
					sb.append(String.format("%02x", b));
				}
				String encrpytedPassword = sb.toString();
			
				Person person = new Admin(name,email,encrpytedPassword,mobile,address);
				
				
				PreparedStatement pst1 = con.prepareStatement("insert into admin(name,password,email,mobile,address) values(?,?,?,?,?)");
				pst1.setString(1, person.getname());
				pst1.setString(2, person.getpassword());
				pst1.setString(3, person.getemail());
				pst1.setString(4, person.getmobile());
				pst1.setString(5, person.getaddress());
			
			
				int row = pst1.executeUpdate();
		   
				if(row != 0) {
					session.setAttribute("email", person.getemail());
					session.setAttribute("name", person.getname());
					request.setAttribute("status", "success");
					response.sendRedirect("AdminIndex.jsp");
				
				
				}else {
				
					request.setAttribute("status", "fail");
					dispatcher = request.getRequestDispatcher("registration.jsp");
					dispatcher.forward(request, response);
				
				}
		   
			}
		
			}
			else if(start_s != false) {
				
				PreparedStatement pst = con.prepareStatement("select email from staff");
				ResultSet rs = pst.executeQuery();
				int match = 0;
				while(rs.next()) {
					String inEmail = rs.getString("email");
					if(email.equals(inEmail)) {
						match = 1;
						break;
					}
				}
				
				if(match == 1) {
					request.setAttribute("status", "failed");
					dispatcher = request.getRequestDispatcher("registration.jsp");
					dispatcher.forward(request, response);
				}
				else {
					MessageDigest messageDigest = MessageDigest.getInstance("MD5");
					messageDigest.update(password.getBytes());
					byte[] result = messageDigest.digest();
					StringBuilder sb = new StringBuilder();
					for(byte b : result) {
						sb.append(String.format("%02x", b));
					}
					String encrpytedPassword = sb.toString();
				
					Person person = new Staff(name,email,encrpytedPassword,mobile,address);
					
					
					PreparedStatement pst1 = con.prepareStatement("insert into staff(name,password,email,mobile,address) values(?,?,?,?,?)");
					pst1.setString(1, person.getname());
					pst1.setString(2, person.getpassword());
					pst1.setString(3, person.getemail());
					pst1.setString(4, person.getmobile());
					pst1.setString(5, person.getaddress());
				
				
					int row = pst1.executeUpdate();
			   
					if(row != 0) {
						session.setAttribute("email", person.getemail());
						session.setAttribute("name", person.getname());
						request.setAttribute("status", "success");
						response.sendRedirect("StaffIndex.jsp");
					
					
					}else {
					
						request.setAttribute("status", "fail");
						dispatcher = request.getRequestDispatcher("registration.jsp");
						dispatcher.forward(request, response);
					
					}
    			}
    		
    			}
			else {
			PreparedStatement pst = con.prepareStatement("select email from user");
			ResultSet rs = pst.executeQuery();
			int match = 0;
			while(rs.next()) {
				String inEmail = rs.getString("email");
				if(email.equals(inEmail)) {
					match = 1;
					break;
				}
			}
			if(match == 1) {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("registration.jsp");
				dispatcher.forward(request, response);
			}
			else {
				MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.update(password.getBytes());
				byte[] result = messageDigest.digest();
				StringBuilder sb = new StringBuilder();
				for(byte b : result) {
					sb.append(String.format("%02x", b));
				}
				String encrpytedPassword = sb.toString();
			
				Person person = new Person(name,email,encrpytedPassword,mobile,address);
				

				
				PreparedStatement pst1 = con.prepareStatement("insert into user(name,password,email,mobile,address) values(?,?,?,?,?)");
				pst1.setString(1, person.getname());
				pst1.setString(2, person.getpassword());
				pst1.setString(3, person.getemail());
				pst1.setString(4, person.getmobile());
				pst1.setString(5, person.getaddress());
			
			
				int row = pst1.executeUpdate();
		   
				if(row != 0) {
					session.setAttribute("email", person.getemail());
					session.setAttribute("name", person.getname());
					request.setAttribute("status", "success");
					response.sendRedirect("index.jsp");
				
				
				}else {
				
					request.setAttribute("status", "fail");
					dispatcher = request.getRequestDispatcher("registration.jsp");
					dispatcher.forward(request, response);
				
				}
		   
			}
			}
			
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
	request.setAttribute("status", "wrong");
	dispatcher = request.getRequestDispatcher("registration.jsp");
	dispatcher.forward(request, response);
}
}

