/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.BoardMemberDAO;
import dao.HomeownerDAO;
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
@WebServlet("/andrew/makeMapRequest")
public class makeMapRequest extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public makeMapRequest() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("communityMap.jsp").forward(request, response);
        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            String msg = "";
            Users currUser = (Users) session.getAttribute("loginUser");
            String title = request.getParameter("maptitle");
            String description = request.getParameter("mapdesc");
            int blocknum = Integer.parseInt(request.getParameter("blocknum"));
            int lotnum = Integer.parseInt(request.getParameter("lotnum"));
            int endlotnum = Integer.parseInt(request.getParameter("endlotnum"));
            String street = request.getParameter("street");
            int category = Integer.parseInt(request.getParameter("mapcategory"));
            boolean results = HomeownerDAO.requestMapPoint(blocknum, lotnum, endlotnum, street, title, description, currUser.getuserID(), category);
            if(results == true){
                msg = "Request has been sent to the officers. Please wait for them to approve your request.";
            }
            else{
                msg = "Something went wrong with adding your request to the system. Please contact the administrators about this.";
            }
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("communityMap.jsp").forward(request, response);
        }
}