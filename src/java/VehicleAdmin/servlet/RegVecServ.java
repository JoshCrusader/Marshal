package VehicleAdmin.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import VehicleAdmin.dao.UserDAO;
import VehicleAdmin.dao.UserVehicleDAO;
import VehicleAdmin.dao.VehicleDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import VehicleAdmin.model.Vehicle;

/**Servlet of the Register Vehicle functionality
 *
 * @author Fred Purisima
 */
@WebServlet(name = "RegVecServ", urlPatterns = {"/RegVecServ"})
public class RegVecServ extends HttpServlet {
    
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
        
        String platenum=request.getParameter("platenum");
        String model=request.getParameter("model");
        String make=request.getParameter("make");
        String year=request.getParameter("year");
        int result = Integer.parseInt(year);
        String userid=request.getParameter("userid");
        boolean isExist = false;
                    
        Vehicle vehicle=new Vehicle();
        vehicle.setPlatenum(platenum);
        vehicle.setModel(model);
        vehicle.setMake(make);
        vehicle.setYear(result);
        vehicle.setBanned(false);
       
        try {
            isExist = UserDAO.isUserExist(userid);
        } catch (SQLException ex) {
            Logger.getLogger(RegVecServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(isExist){
            try {
                VehicleDAO.insertVehicle(vehicle);
            } catch (SQLException ex) {
                Logger.getLogger(RegVecServ.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                UserVehicleDAO.insertUserVehicle(vehicle, userid);
            } catch (SQLException ex) {
                Logger.getLogger(RegVecServ.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("RegVehOutput.jsp").forward(request, response);

        }
        else{      
            request.getRequestDispatcher("RegVehOutputErr.jsp").forward(request, response);

        }
  
    }

}
