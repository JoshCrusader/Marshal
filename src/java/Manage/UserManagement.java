package Manage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 /**
 * Servlet implementation class A4Controller
 */
@WebServlet("/UserManagement")
public class UserManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagement() {
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
		ResultSet roles = null;
		ResultSet modules = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(jdbcUrl,"root", "");
			
			//GET ROLES MODULES
			String sql = "SELECT R.usertypeid,M.moduleID,M.description,M.category FROM \n" + 
					"												MODULE_ACCESS M\n" + 
					"												LEFT JOIN ROLES_MODULE R ON M.MODULEID = R.MODULEID\n" + 
					"												LEFT JOIN REF_USERTYPE RU ON RU.USERTYPEID = R.USERTYPEID\n" + 
					"                                                WHERE R.removed is NULL\n" + 
					"                                                ORDER BY M.CATEGORY,M.MODULEID,R.usertypeid;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			                 System.out.println(sql);
			//GET USERS
			sql = "SELECT usertypeid,usertype FROM REF_USERTYPE;";
			pStmt = conn.prepareStatement(sql);
			roles = pStmt.executeQuery();
			
			//GET MODULES
			sql = "SELECT moduleID,description,category FROM MODULE_ACCESS ORDER BY CATEGORY, MODULEID;";
			pStmt = conn.prepareStatement(sql);
			modules = pStmt.executeQuery();

			/*
			while(rs.next()) {
				System.out.println("Title: "+rs.getString(2));
			}
			*/
			modules.last();
			int mlength = modules.getRow();
			modules.beforeFirst();
			roles.last();
			int rlength = roles.getRow();
			roles.beforeFirst();
			int[][] modulematrix = new int[mlength][rlength];
			while(rs.next()) {
				modulematrix[rs.getInt(2)-1][rs.getInt(1)-1] = 1;
			}
			rs.first();
			request.setAttribute("rs",rs);
			request.setAttribute("roles",roles);
			request.setAttribute("modules",modules);
			request.setAttribute("modulematrix", modulematrix);
			request.getRequestDispatcher("/UserManagement/AMgroup.jsp").forward(request, response);		
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
		// Will check the changes of the modules granted to the roles and update the database accordingly
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://localhost:3306/mydb";
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(jdbcUrl,"root", "");
			
			//GET ROLES MODULES
			String sql = "SELECT R.usertypeid,M.moduleID,M.description,M.category FROM \n" + 
					"												MODULE_ACCESS M\n" + 
					"												LEFT JOIN ROLES_MODULE R ON M.MODULEID = R.MODULEID\n" + 
					"												LEFT JOIN REF_USERTYPE RU ON RU.USERTYPEID = R.USERTYPEID\n" + 
					"                                                WHERE R.removed is NULL\n" + 
					"                                                ORDER BY M.CATEGORY,M.MODULEID,R.usertypeid;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			
			//GET USERS
			sql = "SELECT usertypeid,usertype FROM REF_USERTYPE;";
			pStmt = conn.prepareStatement(sql);
			ResultSet roles = pStmt.executeQuery();
			
			//GET MODULES
			sql = "SELECT moduleID,description,category FROM MODULE_ACCESS ORDER BY CATEGORY, MODULEID;";
			pStmt = conn.prepareStatement(sql);
			ResultSet modules = pStmt.executeQuery();

			/*
			while(rs.next()) {
				System.out.println("Title: "+rs.getString(2));
			}
			*/
			modules.last();
			int mlength = modules.getRow();
			modules.beforeFirst();
			roles.last();
			int rlength = roles.getRow();
			roles.beforeFirst();
			int[][] modulematrix = new int[mlength][rlength];
			int[][] modulematrix2 = new int[mlength][rlength];
			
			while(rs.next()) {
				modulematrix[rs.getInt(2)-1][rs.getInt(1)-1] = 1;
			}
			rs.first();
			System.out.println("-------get some -----------");
			for(int i = 0; i < modulematrix.length; i++) {
				for(int j = 0; j < modulematrix[i].length; j++) {
					System.out.print(modulematrix[i][j]);
				}
				System.out.println();
			}
			System.out.println("---------------------------");
			String[] mods = request.getParameterValues("Mod");
			for(int i = 0; i < mods.length; i++) {
				String[] modspecific = mods[i].split("x");
				int modrow = Integer.parseInt(modspecific[0])-1;
				int rolcol = Integer.parseInt(modspecific[1])-1;
				modulematrix2[modrow][rolcol] = 1;
			}
			System.out.println("-------get some again -----------");
			for(int i = 0; i < modulematrix2.length; i++) {
				for(int j = 0; j < modulematrix2[i].length; j++) {
					System.out.print(modulematrix2[i][j]);
				}
				System.out.println();
			}
			System.out.println("---------------------------");
			for(int i = 0; i < modulematrix.length; i++) {
				modules.next();
				roles.beforeFirst();
				for(int j = 0; j < modulematrix[i].length; j++) {
					roles.next();
					if(modulematrix[i][j] != modulematrix2[i][j]) {
						if(modulematrix2[i][j] == 0) {
							sql = "UPDATE ROLES_MODULE \n" + 
									"SET removed = NOW()\n" + 
									"WHERE removed is NULL AND moduleID = "+modules.getInt(1)+" AND usertypeid = "+roles.getInt(1)+";";
							System.out.println(sql);
							conn.prepareStatement(sql).executeUpdate();
							
						}
						else {
							sql = "INSERT INTO ROLES_MODULE (moduleID,usertypeid,assigned)\n" + 
									"VALUES ("+modules.getInt(1)+","+roles.getInt(1)+",NOW());";
							System.out.println(sql);
							conn.prepareStatement(sql).executeUpdate();
						}
					}
				}
			}
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
		
		doGet(request, response);
	}

}
