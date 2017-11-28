/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuesAndFees.Controller;

import DuesAndFees.DAO.MonthlyDuesDAO;
import DuesAndFees.Model.MonthlyDues;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
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
@WebServlet(name = "ViewDueToRemove", urlPatterns = {"/DuesAndFees/ViewDueToRemove"})
public class ViewDueToRemove extends HttpServlet {
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out=response.getWriter();
        MonthlyDues md = new MonthlyDues();
        
        if (request.getParameter("selected") != null) {
        
            int mduesID = Integer.parseInt(request.getParameter("selected"));

            md = MonthlyDuesDAO.getSelectedFutureDue(mduesID);

            request.setAttribute("CurrentFutureDue",  md);
            RequestDispatcher rd = request.getRequestDispatcher("view_selectedfuturedue.jsp");
            rd.forward(request, response);
        
        }
        
        else {
            
            RequestDispatcher rd = request.getRequestDispatcher("RemoveDuesController");
            rd.forward(request, response);
            
        }
        
    }

}
