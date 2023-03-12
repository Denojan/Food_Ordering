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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/updatepasswordservlet")
public class updatepasswordservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		
		String password = request.getParameter("pass");
		String newpass = request.getParameter("new_pass");
		String newconfirm = request.getParameter("con_new_pass");
		
		RequestDispatcher dispatcher = null;
		Connection con = null;

		
		if(password == null || password.equals("")) {
			request.setAttribute("status", "InvalidPassword");
			dispatcher = request.getRequestDispatcher("EditPassword.jsp");
			dispatcher.forward(request, response);
		}
		
		if(newpass == null || newpass.equals("")) {
			request.setAttribute("status", "InvalidNew");
			dispatcher = request.getRequestDispatcher("EditPassword.jsp");
			dispatcher.forward(request, response);
		}
		
		if(newconfirm == null || newconfirm.equals("")) {
			request.setAttribute("status", "InvalidNewConfirm");
			dispatcher = request.getRequestDispatcher("EditPassword.jsp");
			dispatcher.forward(request, response);
		}
		
		
		if(!newpass.equals(newconfirm)) {
			request.setAttribute("status", "InvalidConfirmPassword");
			dispatcher = request.getRequestDispatcher("EditPassword.jsp");
			dispatcher.forward(request, response);
		}
		
	if(newpass.equals(newconfirm)) {
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food?useSSL=false","root","sutha");	
	
			Person person = new User();
			person.setemail((String)session.getAttribute("email"));
			
			PreparedStatement pst1 = con.prepareStatement("select password from user where email=?");
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
	
			
			
			if(encrpytedPassword.equals(Password)) {
		
	
				MessageDigest messageDigest1 = MessageDigest.getInstance("MD5");
				messageDigest1.update(newpass.getBytes());
				byte[] result1 = messageDigest1.digest();
				StringBuilder sb1 = new StringBuilder();
				for(byte b1 : result1) {
					sb1.append(String.format("%02x", b1));
				}
			String encrpytedNewPassword = sb1.toString();
	
			person.setpassword(encrpytedNewPassword);
			
			PreparedStatement pst = con.prepareStatement("update user set password = ?  where email =?");
			pst.setString(1, person.getpassword());
			pst.setString(2, person.getemail());

			
			
			int rowCount = pst.executeUpdate();
			
			
			if(rowCount != 0) {
				request.setAttribute("status", "success");
				response.sendRedirect("userDetail.jsp");
				
			}else {
				
				request.setAttribute("status", "fail");
				dispatcher = request.getRequestDispatcher("EditPassword.jsp");
				dispatcher.forward(request, response);
				
			}
			
	}
	request.setAttribute("status", "failed");
	dispatcher = request.getRequestDispatcher("EditPassword.jsp");
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
	
}


