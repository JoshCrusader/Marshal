/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuesAndFees.Controller;

import DuesAndFees.DAO.MonthlyDuesDAO;
import DuesAndFees.Model.PastDues;
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
@WebServlet(name = "ViewPastDuesController", urlPatterns = {"/DuesAndFees/ViewPastDuesController"})
public class ViewPastDuesController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        
        PastDues pd = new PastDues();
        
        pd = MonthlyDuesDAO.getPastDues();
        
        request.setAttribute("PastDues", pd);
        RequestDispatcher rd = request.getRequestDispatcher("view_pastdues.jsp");
        rd.forward(request, response);
        
    }

}
