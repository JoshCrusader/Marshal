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
@WebServlet("/andrew/removeMappoint")
public class removeMappoint extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public removeMappoint() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("communityMap_OfficerNew.jsp").forward(request, response);
        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            String msg = "";
            int mapID = Integer.parseInt(request.getParameter("idToReomve"));
            Users currUser = (Users) session.getAttribute("loginUser");
            
            boolean result = BoardMemberDAO.removeMapPoint(mapID);
            if(result == true){
                msg = "Map point removed.";
            }
            else{
                msg = "Something went wrong with removing the point.";
            }
           
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("communityMap_OfficerNew.jsp").forward(request, response);
        }
}