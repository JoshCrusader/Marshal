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
import model.Policy;
import model.Users;

/**
 *
 * @author Andrew Santiago
 */
@WebServlet("/andrew/FilterPolicies")
public class FilterPolicies extends HttpServlet {

    private static final long serialVersionUID = 1L;

	public FilterPolicies() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
                String action = request.getParameter("action");
                String sql;
                Users currUser = (Users) session.getAttribute("loginUser");
                ArrayList<Policy> policies = new ArrayList<>();
                if(action.equals("ALL")){
                    policies = PolicyDAO.getAllPolicies();
                }
                else if(action.equals("activeOnly")){
                    sql = "SELECT * FROM policies WHERE stopimplementDate > DATE(NOW());";
                    policies = PolicyDAO.getPoliciesFiltered(action, sql);
                }
                else if(action.equals("inactiveOnly")){
                    sql = "SELECT * FROM policies WHERE stopimplementDate < DATE(NOW());";
                    policies = PolicyDAO.getPoliciesFiltered(action, sql);
                }
                else if(action.equals("search")){
                    String searchkeyword =  request.getParameter("searchkeyword");
                    sql = "SELECT * FROM policies WHERE policydesc LIKE '%" + searchkeyword + "%';";
                    policies = PolicyDAO.getPoliciesFiltered(action, sql);
                }
                request.setAttribute("filteredPolicies", policies);
                String msg = "";

                request.setAttribute("msg", msg);
                request.getRequestDispatcher("policyManagementFiltered.jsp").forward(request, response);
        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
                String action = request.getParameter("action");
                String sql;
                Users currUser = (Users) session.getAttribute("loginUser");
                ArrayList<Policy> policies = new ArrayList<>();
                if(action.equals("ALL")){
                    policies = PolicyDAO.getAllPolicies();
                }
                else if(action.equals("activeOnly")){
                    sql = "SELECT * FROM policies WHERE stopimplementDate >= DATE(NOW());";
                    policies = PolicyDAO.getPoliciesFiltered(action, sql);
                }
                else if(action.equals("inactiveOnly")){
                    sql = "SELECT * FROM policies WHERE stopimplementDate < DATE(NOW());";
                    policies = PolicyDAO.getPoliciesFiltered(action, sql);
                }
                else if(action.equals("search")){
                    String searchkeyword =  request.getParameter("searchkeyword");
                    sql = "SELECT * FROM policies WHERE policydesc LIKE '%" + searchkeyword + "%';";
                    policies = PolicyDAO.getPoliciesFiltered(action, sql);
                }
                request.setAttribute("filteredPolicies", policies);
                String msg = "";

                request.setAttribute("msg", msg);
                request.getRequestDispatcher("policyManagementFiltered.jsp").forward(request, response);
        }
}