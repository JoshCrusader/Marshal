/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.PolicyDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Penalty;
import model.Users;

/**
 *
 * @author Andrew Santiago
 */
@WebServlet("/andrew/FilterPenalties")
public class FilterPenalties extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public FilterPenalties() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
                String action = request.getParameter("action");
                String sql;
                Users currUser = (Users) session.getAttribute("loginUser");
                ArrayList<Penalty> penalties = new ArrayList<>();
                if(action.equals("ALL")){
                    penalties = PolicyDAO.getAllPenalties();
                }
                else if(action.equals("feeHigh")){
                    sql = "SELECT * FROM penalties ORDER BY penaltyfee DESC;";
                    penalties = PolicyDAO.getPenaltiesFiltered(sql);
                }
                else if(action.equals("feeLow")){
                    sql = "SELECT * FROM penalties ORDER BY penaltyfee ASC;";
                    penalties = PolicyDAO.getPenaltiesFiltered(sql);
                }
                else if(action.equals("levelHigh")){
                    sql = "SELECT * FROM penalties ORDER BY penaltyLevel DESC;";
                    penalties = PolicyDAO.getPenaltiesFiltered(sql);
                }
                else if(action.equals("levelLow")){
                    sql = "SELECT * FROM penalties ORDER BY penaltyLevel ASC;";
                    penalties = PolicyDAO.getPenaltiesFiltered(sql);
                }
                
                else if(action.equals("search")){
                    String searchkeyword =  request.getParameter("searchkeyword");
                    sql = "SELECT * FROM penalties WHERE penaltydescription LIKE '%" + searchkeyword + "%';";
                    penalties = PolicyDAO.getPenaltiesFiltered(sql);
                }
                request.setAttribute("filteredPenalties", penalties);
                String msg = "";

                request.setAttribute("msg", msg);
                request.getRequestDispatcher("penaltyManagementFiltered.jsp").forward(request, response);
        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
                String action = request.getParameter("action");
                String sql;
                Users currUser = (Users) session.getAttribute("loginUser");
                ArrayList<Penalty> penalties = new ArrayList<>();
                if(action.equals("search")){
                    String searchkeyword =  request.getParameter("searchkeyword");
                    sql = "SELECT * FROM penalties WHERE penaltydescription LIKE '%" + searchkeyword + "%';";
                    penalties = PolicyDAO.getPenaltiesFiltered(sql);
                }
                request.setAttribute("filteredPenalties", penalties);
                String msg = "";

                request.setAttribute("msg", msg);
                request.getRequestDispatcher("penaltyManagementFiltered.jsp").forward(request, response);
        }
}