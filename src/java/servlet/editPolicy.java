/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.BoardMemberDAO;
import dao.PolicyDAO;
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
@WebServlet("/andrew/editPolicy")
public class editPolicy extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public editPolicy() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("policyManagement.jsp").forward(request, response);
        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            String msg = "";
            Users currUser = (Users) session.getAttribute("loginUser");
            String policydesc = request.getParameter("description");
            int penaltyID = Integer.parseInt(request.getParameter("penalty"));
            int supportDocID = Integer.parseInt(request.getParameter("suppDoc"));
            String enactDate = request.getParameter("enactDate");
            String stopDate = request.getParameter("stopDate");
            String enablingBoard= request.getParameter("enablingBoard");
            int policyID = Integer.parseInt(request.getParameter("policyID"));
            
            boolean results = PolicyDAO.editPolicy(policydesc, penaltyID, supportDocID, enactDate, stopDate, enablingBoard,policyID);
            if(results == true){
                msg = "Changes to the policy with description " + policydesc + " saved.";
            }
            else{
                msg = "Something went wrong with adding your request to the system. Please contact the administrators about this.";
            }

            request.setAttribute("msg", msg);
            request.getRequestDispatcher("policyManagement.jsp").forward(request, response);
        }
}