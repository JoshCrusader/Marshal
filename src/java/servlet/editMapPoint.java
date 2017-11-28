/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.BoardMemberDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;
import model.mappoint;

/**
 *
 * @author Andrew Santiago
 */
@WebServlet("/andrew/editMapPoint")
public class editMapPoint extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public editMapPoint() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("communityMap_OfficerNew.jsp").forward(request, response);
        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            String msg = "";
            String action = request.getParameter("action");
            Users currUser = (Users) session.getAttribute("loginUser");
            int mapID = Integer.parseInt(request.getParameter("mapID"));
                    if(action.equals(("propertyYes"))){
                    String title = request.getParameter("mapTitle");
                    String desc = request.getParameter("mapDesc");
                    int blocknum = Integer.parseInt(request.getParameter("blockNum"));
                    int lotnum = Integer.parseInt(request.getParameter("lotNum"));
                    int endlotnum = Integer.parseInt(request.getParameter("endLotnum"));
                    String street = request.getParameter("stReet");
                    
                    String userID = currUser.getuserID();
                    int categoryID = Integer.parseInt(request.getParameter("mapcategory"));
                    boolean result = BoardMemberDAO.editMapPoint(action, title, desc, userID, categoryID, blocknum, lotnum, endlotnum, street, mapID);
                    if(result == true){
                        msg = "Changes for Map point ID#" + mapID + " saved.";
                        }
                    }
                    else if(action.equals("propertyNo")){
                    String title = request.getParameter("mapTitle");
                    String desc = request.getParameter("mapDesc");
                    int blocknum = 0;
                    int lotnum = 0;
                    int endlotnum = 0;
                    String street = "";
                    
                    String userID = currUser.getuserID();
                    int categoryID = Integer.parseInt(request.getParameter("mapcategory"));
                    boolean result = BoardMemberDAO.editMapPoint(action, title, desc, userID, categoryID, blocknum, lotnum, endlotnum, street, mapID);
                    if(result == true){
                        msg = "Changes for Map point ID#" + mapID + " saved.";
                        }
                    
                    }
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("communityMap_OfficerNew.jsp").forward(request, response);
        }
}