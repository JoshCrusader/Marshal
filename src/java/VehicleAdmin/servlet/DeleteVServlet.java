/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.servlet;

import VehicleAdmin.dao.StickerDAO;
import VehicleAdmin.dao.UserDAO;
import VehicleAdmin.dao.UserVehicleDAO;
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

/**
 *
 * @author user
 */
@WebServlet(name = "DeleteVServlet", urlPatterns = {"/DeleteVServlet"})
public class DeleteVServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
        String platenum = request.getParameter("platenum");
        String userid = request.getParameter("userid");
        boolean uIsExist = false;
        boolean pnIsExist = false;
                    
        try {
            uIsExist = UserDAO.isUserExist(userid);
        } catch (SQLException ex) {
            Logger.getLogger(ReqVehSticServ.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        try {
            pnIsExist=UserVehicleDAO.isUserVehicle(platenum, userid);
        } catch (SQLException ex) {
            Logger.getLogger(ReqVehSticServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(uIsExist&&pnIsExist){
            try {
                UserVehicleDAO.deleteUserVehicle(platenum, userid);
            } catch (SQLException ex) { 
                Logger.getLogger(ReqVehSticServ.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("DeleteVOutput.jsp").forward(request, response);
                    
        }
        else{
            request.getRequestDispatcher("DeleteVOutputErr.jsp").forward(request, response);
                        
        }
    }

    

}
