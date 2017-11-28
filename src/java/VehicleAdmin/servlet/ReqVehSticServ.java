package VehicleAdmin.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import VehicleAdmin.model.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import VehicleAdmin.model.RequestSticker;

/**Servlet of the Register Vehicle Sticker functionality
 *
 * @author Fred Purisima
 */
@WebServlet(urlPatterns = {"/ReqVehSticServ"})
public class ReqVehSticServ extends HttpServlet {

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
        Connection conn=Database.getDBConnection();
        String platenum=request.getParameter("platenum");
        String userid=request.getParameter("userid");
        
        boolean uIsExist = false;
        boolean pnIsExist = false;
        
        
        String sql1="SELECT userID FROM users where userID='"+userid+"';";
        PreparedStatement pStmt1 = null;
        try {
            pStmt1 = conn.prepareStatement(sql1);
        } catch (SQLException ex) {
            Logger.getLogger(RegVecServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs1 = null;
        try {
            rs1 = pStmt1.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(RegVecServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs1.next();
            if(rs1.getString(1)==null){
                uIsExist=false;
        
            }
            else{
                uIsExist=true;
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegVecServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        String sql2="SELECT plateNum FROM user_vehicles where plateNum='"+platenum+"' and userID='"+userid+"';";
        PreparedStatement pStmt2 = null;
        try {
            pStmt2 = conn.prepareStatement(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(RegVecServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs2 = null;
        try {
            rs2 = pStmt2.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(RegVecServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs2.next();
            if(rs2.getString(1)==null){
                pnIsExist=false;
        
            }
            else{
                pnIsExist=true;
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegVecServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if(uIsExist&&pnIsExist){
            RequestSticker reqs=new RequestSticker();
            reqs.setPlatenum(platenum);
            reqs.setUserid(userid);
            try {
                reqs.setStickerReq();
            } catch (SQLException ex) {
                Logger.getLogger(ReqVehSticServ.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            request.setAttribute("reqs", reqs);
            request.getRequestDispatcher("ReqStickerOutput.jsp").forward(request, response);

        }
        else{
            RequestSticker reqs=new RequestSticker();
            request.setAttribute("reqs", reqs);
            request.getRequestDispatcher("ReqStickerOutErr.jsp").forward(request, response);
        }
        
        
        
    }

}
