/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuesAndFees.Controller;

import DuesAndFees.DAO.MonthlyDuesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "EditDuesController", urlPatterns = {"/DuesAndFees/EditDuesController"})
public class EditDuesController extends HttpServlet {

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
        int mduesID = Integer.parseInt(request.getParameter("mdueid_selected"));
        
        if (request.getParameter("remove_plan") != null) {
            
            MonthlyDuesDAO.removeDue(mduesID);
            
        }
        
        else if (request.getParameter("change_amount") != null && request.getParameter("new_amount") != null) {
            
            double amount = Double.parseDouble(request.getParameter("new_amount"));
            MonthlyDuesDAO.editAmount(mduesID, amount);
            
        }
        
        out.print("<h3><font color='red'>Success!</font></h3>");
        request.getRequestDispatcher("RemoveDuesController").include(request, response);
        
    }

}
