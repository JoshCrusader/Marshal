package VehicleAdmin.servlet;

import VehicleAdmin.model.Vehicle;
import VehicleAdmin.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * This program serves a means to select the specific vehicle that an
 * administrator can ban.
 * 
 */

/**
 *
 * @author Johannes
 */

@WebServlet(urlPatterns = {"/banvehicleservlet"})
public class banvehicleservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String platenum = request.getParameter("banselect");
            Vehicle banvehicle = new Vehicle();
            
            
            request.setAttribute("banselect", platenum);
        request.getRequestDispatcher("banvehicleoutput.jsp").forward(request, response);
    }
}
