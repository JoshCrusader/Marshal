/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MonthlyDuesDAO;
import Model.ToBeDues;
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
@WebServlet(name = "ViewToBeDuesController", urlPatterns = {"/ViewToBeDuesController"})
public class ViewToBeDuesController extends HttpServlet {

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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ToBeDues tb = new ToBeDues();
        
        tb = MonthlyDuesDAO.getToBeDues();
        
        request.setAttribute("ToBeDues", tb);
        RequestDispatcher rd = request.getRequestDispatcher("view_tobedues.jsp");
        rd.forward(request, response);
        
    }

}
