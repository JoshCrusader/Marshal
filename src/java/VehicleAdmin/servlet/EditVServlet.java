/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.servlet;

import VehicleAdmin.dao.StickerDAO;
import VehicleAdmin.dao.UserDAO;
import VehicleAdmin.dao.UserVehicleDAO;
import VehicleAdmin.dao.VehicleDAO;
import VehicleAdmin.model.Vehicle;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "EditVServlet", urlPatterns = {"/EditVServlet"})
public class EditVServlet extends HttpServlet {

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
        String submit=request.getParameter("submit");
        if(submit.equals("editv1")){
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
            Vehicle userv=null;
            if(uIsExist&&pnIsExist){
                try {
                    userv=UserVehicleDAO.getUserVehicle(platenum, userid);
                } catch (SQLException ex) { 
                    Logger.getLogger(ReqVehSticServ.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("userv", userv);
                request.getRequestDispatcher("EditVehicle2.jsp").forward(request, response);

            }
            else{
                request.getRequestDispatcher("ReqStickerOutErr.jsp").forward(request, response);

            }




            }
        else if(submit.equals("editv2")){
            String platenum = request.getParameter("platenum");
            String model=request.getParameter("model");
            String make=request.getParameter("make");
            String year=request.getParameter("year");
            
            Date date=new Date();  
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(year);
            } catch (ParseException ex) { 
                Logger.getLogger(EditVServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            java.sql.Date d= new java.sql.Date(date.getTime());
            
            Vehicle vehicle=new Vehicle();
            vehicle.setPlatenum(platenum);
            vehicle.setModel(model);
            vehicle.setMake(make);
            vehicle.setYear(d);
            boolean isUpdate = false;
            try {
                isUpdate=VehicleDAO.updateVehicle(vehicle);
            } catch (SQLException ex) {
                Logger.getLogger(EditVServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(isUpdate){
                request.getRequestDispatcher("EditVOutput.jsp").forward(request, response);
            
            }
            else{
                request.setAttribute("vehicle", vehicle);
                request.getRequestDispatcher("EditVOutputErr.jsp").forward(request, response);
            
            }
            
        }
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    

}
