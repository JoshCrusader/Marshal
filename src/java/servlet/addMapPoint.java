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
@WebServlet("/andrew/addMapPoint")
public class addMapPoint extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public addMapPoint() {
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
                    if(action.equals(("propertyYes"))){
                    double xAxis =  Double.parseDouble(request.getParameter("xlong"));
                    double yAxis =  Double.parseDouble(request.getParameter("ylat"));
                    String title = request.getParameter("maptitle");
                    String desc = request.getParameter("mapdesc");
                    int blocknum = Integer.parseInt(request.getParameter("blocknum"));
                    int lotnum = Integer.parseInt(request.getParameter("lotnum"));
                    int endlotnum = Integer.parseInt(request.getParameter("endlotnum"));
                    String street = request.getParameter("street");
                    
                    String userID = currUser.getuserID();
                    int categoryID = Integer.parseInt(request.getParameter("mapcategory"));
                    boolean result = BoardMemberDAO.addMapPoint(action, xAxis, yAxis, title, desc, userID, categoryID, blocknum, lotnum, endlotnum, street);
                    if(result == true){
                        msg = "Map point for " + title + " added.";
                        }
                    }
                    else if(action.equals("propertyNo")){
                    double xAxis =  Double.parseDouble(request.getParameter("xlong"));
                    double yAxis =  Double.parseDouble(request.getParameter("ylat"));
                    String title = request.getParameter("maptitle");
                    String desc = request.getParameter("mapdesc");
                    
                    String userID = currUser.getuserID();
                    int categoryID = Integer.parseInt(request.getParameter("mapcategory"));
                    boolean result = BoardMemberDAO.addMapPoint(action, xAxis, yAxis, title, desc, userID, categoryID, 0, 0, 0, "blank");
                    if(result == true){
                        msg = "Map point for " + title + " added.";
                        }
                    
                    }
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("communityMap_OfficerNew.jsp").forward(request, response);
        }
}