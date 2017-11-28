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
import java.util.Date;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import org.json.*;

/**
 *
 * @author paul
 */
@WebServlet(name = "PolicyServlet", urlPatterns = {"/PolicyServlet"})
public class PolicyServlet extends HttpServlet {

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
            out.println("<title>Servlet PolicyServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PolicyServlet at " + request.getContextPath() + "</h1>");
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
        
        
        try{
        
            String sql = "UPDATE `mydb`.`security_violations` SET `violatedpolicyID`= ? WHERE `securityReportID`= ?";
            String sql2 = "UPDATE `mydb`.`security_violations` SET `trxID`= ? WHERE `securityReportID`= ?";
            int policy_ID = Integer.parseInt(request.getParameter("policies"));
            int security_report_ID = Integer.parseInt (request.getParameter("violationID"));
            int transaction_ID = Integer.parseInt(request.getParameter("transactions"));
                
            PreparedStatement prep = conn.prepareStatement(sql);
            PreparedStatement prep2 = conn.prepareStatement(sql2);
            prep.setInt(1, policy_ID);
            prep.setInt(2, security_report_ID );
            prep2.setInt(1, transaction_ID);
            prep2.setInt(2, security_report_ID);
            int rs = prep.executeUpdate();
            int rs2 = prep2.executeUpdate();
            request.getRequestDispatcher("testjsp.jsp").forward(request, response);
        }
        
        catch(SQLException e){}
        
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
