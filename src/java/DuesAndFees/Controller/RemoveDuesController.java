/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MonthlyDuesDAO;
import Model.FutureDues;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "RemoveDuesController", urlPatterns = {"/RemoveDuesController"})
public class RemoveDuesController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        FutureDues fd = new FutureDues();
        PrintWriter out=response.getWriter();
        fd = MonthlyDuesDAO.futureDues();
        
        out.print("<h3><font color='red'>Please enter Valid Details</font></h3>");
        
        request.setAttribute("FutureDues", fd);
        RequestDispatcher rd = request.getRequestDispatcher("view_removedues.jsp");
        rd.forward(request, response);
        
    }
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        FutureDues fd = new FutureDues();
        
        fd = MonthlyDuesDAO.futureDues();
        
        request.setAttribute("FutureDues", fd);
        RequestDispatcher rd = request.getRequestDispatcher("view_removedues.jsp");
        rd.forward(request, response);
        
    }

}
