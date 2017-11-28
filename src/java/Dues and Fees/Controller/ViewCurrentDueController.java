/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;
import DAO.*;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "ViewCurrentDueController", urlPatterns = {"/ViewCurrentDueController"})
public class ViewCurrentDueController extends HttpServlet {
    
    /**
     *
     */
    public ViewCurrentDueController() {
        super();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * 
     * This <code>doGet()</code> method gets the object to pass through the method and then
     * passed on to the jsp page to display its gotten data.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        CurrentMonthlyDue cmd = new CurrentMonthlyDue();
        
        cmd = MonthlyDuesDAO.getCurrentDue();
        
        request.setAttribute("CurrentDue", cmd);
        RequestDispatcher rd = request.getRequestDispatcher("view_currentdue.jsp");
        rd.forward(request, response);
        
        
    }

}
