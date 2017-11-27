package JoshController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Objects.User;
/**
 * Servlet implementation class SearchDirectory
 */
@WebServlet("/SearchDirectory")
public class SearchDirectory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDirectory() {
        super();	
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://localhost:3306/mydb";
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(jdbcUrl,"root", "");
			
			String sql = "select fname,lname,D.documentLocation,U.userid,RO.occupation,mame from users U\n" + 
					"						LEFT JOIN DOCUMENTS D ON D.documentID = U.photoID\n" + 
					"						LEFT JOIN REF_OCCUPATION RO ON RO.occupationID = U.occupationID\n"+
					"                        order by lname, fname;";
			String searchrequest = request.getParameter("directoryinput");
			if(searchrequest != null) {

				String filter = "";
				if(request.getParameter("searchtype").equals("Services")) {
					filter = "RO.occupation";
				}
				else {
					filter = "CONCAT(fname,' ',mame,' ',lname)";
					
				}
				sql = "select fname,lname,D.documentLocation,U.userid, RO.occupation,mame from users U\n" + 
						"						LEFT JOIN DOCUMENTS D ON D.documentID = U.photoID\n" + 
						"						LEFT JOIN REF_OCCUPATION RO ON RO.occupationID = U.occupationID\n"+
						"						WHERE "+filter+" LIKE '%"+searchrequest+"%'\n" + 
						"                        order by lname, fname;";
			}
			PreparedStatement pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			
			ArrayList<User> searchresult = new ArrayList<User>();
			while(rs.next()) {
				User user = new User(rs.getString(4));
				user.setfName(rs.getString(1));
				user.setmName(rs.getString(6));
				user.setlName(rs.getString(2));
				user.setphotolocation(rs.getString(3));
				user.setOccupation(rs.getString(5));
				searchresult.add(user);
			}
			/*
			while(rs.next()) {
				System.out.println("Title: "+rs.getString(2));
			}
			*/
			request.setAttribute("rs",searchresult);
			request.getRequestDispatcher("/Directory/DirectoryLibrary.jsp").forward(request, response);		
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
