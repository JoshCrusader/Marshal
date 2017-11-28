/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityControllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author paul
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = Database.getDBConnection();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String path = "wrong.jsp";
        Users user = new Users (username, password);
       
        
        try{
        
            
            int isCorrect = 0;
            
            String sql = "SELECT * FROM USERS WHERE userID = '"+ user.getUsername() +"' AND passwd ='" + user.getPassword() + "'";
            
            
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
         
            while (rs.next()){
                int i = rs.getInt("usertypeid");
                String j = rs.getString("fname");
                user.setUsertype(i);
                user.setfName(j);
                if (i == 3) {
                    
                    path = "testjsp.jsp";
                
                    HttpSession session = request.getSession();
                    session.setAttribute("sessionUser", user);
                }
                else if (i == 2) {
                    path = "Security/home.jsp";
                
                    HttpSession session = request.getSession();
                    session.setAttribute("sessionUser", user);
                }
                else if (i == 1){
                  
                }
                else if (i == 4) {
                    
                    path = "Security/home1.jsp";
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("sessionUser", user);
                    session.setAttribute("sessionz", 0);
                }
              
                
            }
            
        }
        
        catch(SQLException e){
            e.printStackTrace();
        }
        
        request.getRequestDispatcher(path).forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
