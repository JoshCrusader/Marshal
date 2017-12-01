/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.servlet;

import VehicleAdmin.model.Sticker;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**It is the servlet of Change Sticker Price functionality
 *
 * @author Fred Purisima
 */
@WebServlet(name = "CPriceServ", urlPatterns = {"/CPriceServ"})
public class CPriceServ extends HttpServlet {

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
       HttpSession session =request.getSession();
       String price=request.getParameter("price");
       double result=Double.parseDouble(price);
       Sticker sticker=new Sticker();
       sticker.setPrice(result);
       session.setAttribute("sticker",sticker);
       request.getRequestDispatcher("ChangePOutput.jsp").forward(request, response);
        
    }

}
