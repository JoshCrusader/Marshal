package VehicleAdmin.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import VehicleAdmin.model.ConfirmSticker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**Servlet of the Confirm Sticker Request functionality
 *
 * @author Fred Purisima
 */
@WebServlet(urlPatterns = {"/VehicleAdmin/ConfStickServ"})
public class ConfStickServ extends HttpServlet {

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
        String[] userv = request.getParameterValues("check_list");
        
        ConfirmSticker cs = new ConfirmSticker();
        cs.setUserdat(userv);
        try {
            cs.setApprovedReq();
        } catch (SQLException ex) {
            Logger.getLogger(ConfStickServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("cs", cs);
        request.getRequestDispatcher("ConfStickOutput.jsp").forward(request, response);
           
    }

}
