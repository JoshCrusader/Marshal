package VehicleAdmin.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import VehicleAdmin.dao.ViewVehicleList;

/**It is the Servlet for the Vehicle List functionality
 *
 * @author Fred Purisima
 */
@WebServlet(urlPatterns = {"/VehicleListServ"})
public class VehicleListServ extends HttpServlet {
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
        String filt=request.getParameter("filter");
        ViewVehicleList vvl=new ViewVehicleList();
        vvl.setFilter(filt);
        try {
            vvl.insertUserV();
        } catch (SQLException ex) {
            Logger.getLogger(VehicleListServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("vvl", vvl);
        request.getRequestDispatcher("VehicleListOutput.jsp").forward(request, response);
    }

}
