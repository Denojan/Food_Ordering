package registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oop.servlet.String;
/**
 * Denojan
 * Servlet implementation class DeleteOtherServlet
 */
@WebServlet("/deleteOther_S")
public class DeleteOtherServlet_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOtherServlet_S() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				HttpSession session = request.getSession();
		
				String email = request.getParameter("email");	
				RequestDispatcher dispatcher = null;
				Connection con = null;
				String myemail = (String)session.getAttribute("email"); 

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food?useSSL=false","root","sutha");
					
					boolean start_s = email.startsWith("staff");
					if(start_s != false) {
						if(myemail.equals(email)) {
							
							PreparedStatement pst = con.prepareStatement("delete from staff where email=?");
							pst.setString(1, email);
							
							int rowCount = pst.executeUpdate();
							
							dispatcher = request.getRequestDispatcher("viewAllStaff_S.jsp");
							  
							if(rowCount > 0) {
								request.setAttribute("status","success");
								response.sendRedirect("registration.jsp");
								
							}else {
								request.setAttribute("status", "failed");
							}
							dispatcher.forward(request,response);
							}else {
								PreparedStatement pst = con.prepareStatement("delete from staff where email=?");
								pst.setString(1, email);
								
								int rowCount = pst.executeUpdate();
								
								dispatcher = request.getRequestDispatcher("viewAllStaff_S.jsp");
								  
								if(rowCount > 0) {
									request.setAttribute("status","success");
									response.sendRedirect("viewAllStaff_S.jsp");
									
								}else {
									request.setAttribute("status", "failed");
								}
								dispatcher.forward(request,response);
							}
							}
				
					
					
				}catch(SQLException e) {
					e.printStackTrace();
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
	


