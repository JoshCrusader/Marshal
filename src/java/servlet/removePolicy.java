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

/**
 *
 * @author Andrew Santiago
 */
@WebServlet("/andrew/removePolicy")
public class removePolicy extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public removePolicy() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("policyManagement.jsp").forward(request, response);
        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            //addPolicy(String policydesc, int penaltyID, int supportDocID, String enactDate, String stopDate, String enablingBoard){
            //Integer.parseInt(request.getParameter("idToJoin"));
            Users currUser = (Users) session.getAttribute("loginUser");
            int policyID = Integer.parseInt(request.getParameter("idToRemove"));
            
            boolean result = PolicyDAO.dismissPolicy(policyID);
            String msg = "";

            request.setAttribute("msg", msg);
            request.getRequestDispatcher("policyManagement.jsp").forward(request, response);
        }
}