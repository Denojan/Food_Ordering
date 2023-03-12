package registration;

import java.io.IOException;
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
/**
 * Denojan
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				HttpSession session = request.getSession();
				
				RequestDispatcher dispatcher = null;
				Connection con = null;
				

				try {
					
					String email = (String)session.getAttribute("email");
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food?useSSL=false","root","sutha");
					
					
					boolean start_s = email.startsWith("staff");
					boolean start_a = email.startsWith("admin");
					
					if(start_a != false) {
						Person person = new Admin();
						person.setemail(email);
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food?useSSL=false","root","sutha");
						PreparedStatement pst = con.prepareStatement("delete from admin where email=?");
						pst.setString(1, person.getemail());
						
						int rowCount = pst.executeUpdate();
						
						dispatcher = request.getRequestDispatcher("AdminDetail.jsp");
						  
						if(rowCount > 0) {
							request.setAttribute("status","success");
							response.sendRedirect("registration.jsp");
							
						}else {
							request.setAttribute("status", "failed");
						}
						dispatcher.forward(request,response);
						
					}
					else if(start_s != false) {
						Person person = new Staff();
						person.setemail(email);
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food?useSSL=false","root","sutha");
						PreparedStatement pst = con.prepareStatement("delete from staff where email=?");
						pst.setString(1, person.getemail());
						
						int rowCount = pst.executeUpdate();
						
						dispatcher = request.getRequestDispatcher("StaffDetail.jsp");
						  
						if(rowCount > 0) {
							request.setAttribute("status","success");
							response.sendRedirect("registration.jsp");
							
						}else {
							request.setAttribute("status", "failed");
						}
						dispatcher.forward(request,response);
					}
					else {
					Person person = new User();
					person.setemail(email);
					PreparedStatement pst = con.prepareStatement("delete from user where email=?");
					pst.setString(1, person.getemail());
					
					int rowCount = pst.executeUpdate();
					
					dispatcher = request.getRequestDispatcher("userDetail.jsp");
					  
					if(rowCount > 0) {
						request.setAttribute("status","success");
						response.sendRedirect("registration.jsp");
						
					}else {
						request.setAttribute("status", "failed");
					}
					dispatcher.forward(request,response);
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
	


