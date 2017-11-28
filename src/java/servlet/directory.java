package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;

@WebServlet("/andrew/directory")
public class directory extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public directory() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session = request.getSession();
                Users currUser = (Users) session.getAttribute("loginUser");
		String destination = request.getParameter("destination");
		String forward = "";
		if(session.getAttribute("loginUser") == null){
			request.setAttribute("message", "Redirected Back to Login Page");
			request.getRequestDispatcher("Login?action=Login");
		}
		else{
			if(destination.equals("policyManagement")){
                            if(currUser.getuserType() == 4){
				forward = "policyManagement.jsp";
                            }
                            else{
                                forward = "navbar_index.jsp";
                            }
			}
                        if(destination.equals("penaltyManagement")){
                                if(currUser.getuserType() == 4){
                                    forward = "penaltyManagement.jsp";
                                }
                                else{
                                    forward = "navbar_index.jsp";
                                }
                                
			}
                        if(destination.equals("MapManagement")){
                                if(currUser.getuserType() == 4){
                                    forward = "communityMap_OfficerNew.jsp";
                                }
                                else{
                                    forward = "navbar_index.jsp";
                                }
			}
                        if(destination.equals("MapReqsManagement")){
                                if(currUser.getuserType() == 4){
                                    forward = "communityMap_OfficerNew_requests.jsp";
                                }
                                else{
                                    forward = "navbar_index.jsp";
                                }
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
