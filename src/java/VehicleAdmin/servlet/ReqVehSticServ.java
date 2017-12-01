package VehicleAdmin.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import VehicleAdmin.dao.StickerDAO;
import VehicleAdmin.dao.UserDAO;
import VehicleAdmin.dao.UserVehicleDAO;
import VehicleAdmin.model.Sticker;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**Servlet of the Register Vehicle Sticker functionality
 *
 * @author Fred Purisima
 */
@WebServlet(urlPatterns = {"/ReqVehSticServ"})
public class ReqVehSticServ extends HttpServlet {
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       

    }
    
    
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
        Sticker sticker=(Sticker) session.getAttribute("sticker");
        if(sticker==null){
        
            sticker=new Sticker();
        
        }
       
         
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
                StickerDAO.setStickerReq(platenum, userid,sticker.getPrice());
            } catch (SQLException ex) { 
                Logger.getLogger(ReqVehSticServ.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("ReqStickerOutput.jsp").forward(request, response);
                    
        }
        else{
            request.getRequestDispatcher("ReqStickerOutErr.jsp").forward(request, response);
                        
        }

    }
}
