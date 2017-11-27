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
 * Servlet implementation class UserProfile
 */
@WebServlet("/UserProfile")
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("uId");
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://localhost:3306/mydb";
		ResultSet rs = null; /* User Details */
		ResultSet ho = null; /* Homeowners of User */
		ResultSet hm = null; /* Homeowners of Users */
		ResultSet kb = null; /* Kasambahay of Users */
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(jdbcUrl,"root", "");
			
			String sql = "SELECT U.userID,U.lname,U.fname,U.mame,U.bDate, RO.occupation, U.movingIn,RT.usertype,D.documentLocation,U.email,U.phoneno,U.telno from (SELECT * FROM USERS WHERE userID = '"+userId+"') U\n" + 
					"			  LEFT JOIN REF_USERTYPE RT ON RT.usertypeid = U.usertypeid\n" + 
					"			  LEFT JOIN DOCUMENTS D ON D.documentID = U.photoID\n" + 
					"              LEFT JOIN REF_OCCUPATION RO ON RO.occupationID = U.occupationID;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			rs.next();
			User myUser = new User(rs.getString(1));
			myUser.setlName(rs.getString(2));
			myUser.setfName(rs.getString(3));
			myUser.setmName(rs.getString(4));
			myUser.setDate(rs.getString(5));
			myUser.setOccupation(rs.getString(6));
			myUser.setmoveInDate(rs.getString(7));
			myUser.settypeDesc(rs.getString(8));
			myUser.setphotolocation(rs.getString(9));
			myUser.setEmail(rs.getString(10));
			myUser.setmNum(rs.getString(10));
			myUser.settNum(rs.getString(11));
			/*
			while(rs.next()) {
				System.out.println("Title: "+rs.getString(2));
			}
			*/
			sql = "SELECT CONCAT_WS('',HO.LOTNUM,HM.LOTNUM,K.LOTNUM) as 'lotnum', CONCAT_WS('',HO.BLOCKNUM,HM.BLOCKNUM,K.BLOCKNUM) as 'blocknum' FROM (SELECT USERID FROM USERS WHERE USERID = '"+userId+"') U\n" + 
					"		 LEFT JOIN HOMEOWNER HO ON HO.USERID = U.USERID\n" + 
					"         LEFT JOIN HOMEMEMBER HM ON HM.USERID = U.USERID\n" + 
					"         LEFt JOIN KASAMBAHAY K ON K.USERID = U.USERID;";
			
			pStmt = conn.prepareStatement(sql);
			ResultSet lotblock = pStmt.executeQuery();
			lotblock.next();
			
			int lot = lotblock.getInt(1);
			int block = lotblock.getInt(2);
			
			sql = "SELECT U.userid,fname,mame,lname,D.documentLocation,RO.occupation FROM (SELECT USERID FROM HOMEOWNER WHERE LOTNUM = "+lot+" AND BLOCKNUM = "+block+") K\n" + 
					"								LEFT JOIN USERS U ON U.USERID = K.USERID\n" + 
					"                                LEFT JOIN DOCUMENTS D ON D.documentID = U.photoID\n" + 
					"                                LEFT JOIN REF_OCCUPATION RO ON RO.occupationID = U.occupationID;";
			pStmt = conn.prepareStatement(sql);
			ho = pStmt.executeQuery();
			ArrayList <User> homeowners = new ArrayList<>();
			while(ho.next()) {
				User homeowner = new User(ho.getString(1));
				homeowner.setfName(ho.getString(2));
				homeowner.setmName(ho.getString(3));
				homeowner.setlName(ho.getString(4));
				homeowner.setphotolocation(ho.getString(5));
				homeowner.setOccupation(ho.getString(6));
				homeowners.add(homeowner);
			}
			sql = "SELECT U.userid,fname,mame,lname,D.documentLocation,RO.occupation FROM (SELECT USERID FROM HOMEMEMBER WHERE LOTNUM = "+lot+" AND BLOCKNUM = "+block+") K\n" + 
					"								LEFT JOIN USERS U ON U.USERID = K.USERID\n" + 
					"                                LEFT JOIN DOCUMENTS D ON D.documentID = U.photoID\n" + 
					"                                LEFT JOIN REF_OCCUPATION RO ON RO.occupationID = U.occupationID;";
			pStmt = conn.prepareStatement(sql);
			hm = pStmt.executeQuery();
			ArrayList <User> homemembers = new ArrayList<>();
			while(hm.next()) {
				User homemember = new User(hm.getString(1));
				homemember.setfName(hm.getString(2));
				homemember.setmName(hm.getString(3));
				homemember.setlName(hm.getString(4));
				homemember.setphotolocation(hm.getString(5));
				homemember.setOccupation(hm.getString(6));
				homemembers.add(homemember);
			}
			
			sql = "SELECT U.userid,fname,mame,lname,D.documentLocation,RO.occupation FROM (SELECT USERID FROM KASAMBAHAY WHERE LOTNUM = "+lot+" AND BLOCKNUM = "+block+") K\n" + 
					"								LEFT JOIN USERS U ON U.USERID = K.USERID\n" + 
					"                                LEFT JOIN DOCUMENTS D ON D.documentID = U.photoID\n" + 
					"                                LEFT JOIN REF_OCCUPATION RO ON RO.occupationID = U.occupationID;";
			pStmt = conn.prepareStatement(sql);
			kb = pStmt.executeQuery();
			ArrayList <User> kasambahays = new ArrayList<>();
			while(kb.next()) {
				User kasambahay = new User(kb.getString(1));
				kasambahay.setfName(kb.getString(2));
				kasambahay.setmName(kb.getString(3));
				kasambahay.setlName(kb.getString(4));
				kasambahay.setphotolocation(kb.getString(5));
				kasambahay.setOccupation(kb.getString(6));
				kasambahays.add(kasambahay);
			}
			
			sql = "SELECT xAxis, yAxis from (SELECT mappointID from REF_PROPERTIES WHERE lotnum = "+lot+" and blocknum = "+block+") RP\n" + 
					"					LEFT JOIN MAPPOINT MP on RP.mappointID = MP.mappointID;";
			pStmt = conn.prepareStatement(sql);
			ResultSet blockset = pStmt.executeQuery();
			
			blockset.next();
			Double lat = blockset.getDouble(1);
			Double alt = blockset.getDouble(2);
			
			request.setAttribute("myUser",myUser);
			request.setAttribute("ho",homeowners);
			request.setAttribute("hm",homemembers);
			request.setAttribute("kb",kasambahays);
			request.setAttribute("lot", lat);
			request.setAttribute("block", alt);
			request.setAttribute("lotnum", lot);
			request.setAttribute("blocknum", block);
			request.getRequestDispatcher("/Directory/Profile.jsp").forward(request, response);		
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
