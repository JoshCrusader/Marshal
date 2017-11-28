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
@WebServlet("/andrew/addPenalty")
public class addPenalty extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public addPenalty() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("penaltyManagement.jsp").forward(request, response);
        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            String msg = "";
            //addPenalty(int penaltyLevel, String penaltyDescription, double penaltyFee, String penaltyAction, int enablingDocID)
            int penaltyLevel = Integer.parseInt(request.getParameter("penaltylevel"));
            String penaltyDescription = request.getParameter("description");
            Double penaltyFee = Double.parseDouble(request.getParameter("penaltyFee"));
            String penaltyAction = request.getParameter("penaltyAction");
            int enablingDocID = Integer.parseInt(request.getParameter("enablingDocID"));
            
            boolean result = PolicyDAO.addPenalty(penaltyLevel, penaltyDescription, penaltyFee, penaltyAction, enablingDocID);
            if(result == true){
                msg = "Penalty " + penaltyDescription + " has been successfully added.";
            }
            else{
                msg = "Something went wrong with adding the penalty.";
            }

            request.setAttribute("msg", msg);
            request.getRequestDispatcher("penaltyManagement.jsp").forward(request, response);
        }
}