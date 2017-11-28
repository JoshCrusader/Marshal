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
@WebServlet("/andrew/mappointApproval")
public class mappointApproval extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public mappointApproval() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("communityMap_Officer.jsp").forward(request, response);
        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            //(double xAxis, double yAxis, String title, String desc, String userID, int mapCategoryID)
            //Integer.parseInt(request.getParameter("idToJoin"));
            String action = request.getParameter("action");
            String msg = "";
            Users currUser = (Users) session.getAttribute("loginUser");
            
            
            if (action.equals("approve")){
                int reqID = Integer.parseInt(request.getParameter("reqID"));
                int blocknum = Integer.parseInt(request.getParameter("blocknum"));
                int lotnum = Integer.parseInt(request.getParameter("lotnum"));
                int endlotnum = Integer.parseInt(request.getParameter("endlotnum"));
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String street = request.getParameter("street");
                int category = Integer.parseInt(request.getParameter("category"));

                double xAxis = Double.parseDouble(request.getParameter("xlong"));
                double yAxis = Double.parseDouble(request.getParameter("ylat"));
                //approveMapPoint(int requestID, double xAxis, double yAxis, String title, String desc, String userID, int mapCategoryID, int blocknum, int lotnum, int endlotnum, String street){
                boolean results = BoardMemberDAO.approveMapPoint(reqID, xAxis, yAxis, title, description, currUser.getuserID(), category, blocknum, lotnum, endlotnum, street);
                if(results == true){
                    msg = "Request ticket #" + reqID + " approved. Map point added to the database";
                }
                else{
                    msg = "Something went wrong with adding your request to the system. Please contact the administrators about this.";
                }
            }
            
            else{
                int dreqID = Integer.parseInt(request.getParameter("dreqID"));
                BoardMemberDAO.disApproveMapPoint(dreqID);
                
            }
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("communityMap_OfficerNew_requests.jsp").forward(request, response);
        }
}