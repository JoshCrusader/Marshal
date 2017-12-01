    package JoshController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Objects.User;
    
import dao.SearchDirectoryDAO;
import model.Users;
/**
 * Servlet implementation class SearchDirectory
 */
@WebServlet("/SearchDirectory")
public class SearchDirectory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDirectory() {
        super();	
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
                String searchrequest = request.getParameter("directoryinput");
                ArrayList <Users> searchresult;
                if(searchrequest != null){
                    searchresult = SearchDirectoryDAO.getUnfilteredUsers(1, "");
                }
                else{
                    String filtr = request.getParameter("searchtype");
                    if(filtr.equals("Services")){
                        searchresult = SearchDirectoryDAO.getUnfilteredUsers(2, filtr);
                    }
                    else{
                        searchresult = SearchDirectoryDAO.getUnfilteredUsers(3, filtr);
                    }
                }
                
                request.setAttribute("searchresult",searchresult);
                request.getRequestDispatcher("/Directory/DirectoryLibrary.jsp").forward(request, response);		
                response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
