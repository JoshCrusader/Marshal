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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import VehicleAdmin.model.RegisterVehicle;

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
        
        Connection conn=Database.getDBConnection();
        String platenum=request.getParameter("platenum");
        String model=request.getParameter("model");
        String make=request.getParameter("make");
        String year=request.getParameter("year");
        int result = Integer.parseInt(year);
        String userid=request.getParameter("userid");
        boolean isExist = false;
        
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
                isExist=false;
        
            }
            else{
                isExist=true;
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegVecServ.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(isExist){
            RegisterVehicle regv=new RegisterVehicle();
            regv.setPlatenum(platenum);
            regv.setModel(model);
            regv.setMake(make);
            regv.setYear(result);
            regv.setBanned(false);
            regv.setUserid(userid);

            try {
                regv.insertVehicle();
            } catch (SQLException ex) {
                Logger.getLogger(RegVecServ.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                regv.insertUserVehicle();
            } catch (SQLException ex) {
                Logger.getLogger(RegVecServ.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("regv", regv);
            request.getRequestDispatcher("RegVehOutput.jsp").forward(request, response);
            
        }
        
        else{
            RegisterVehicle regv=new RegisterVehicle();
            request.setAttribute("regv", regv);
            request.getServletContext().getRequestDispatcher("RegVehOutputErr.jsp").forward(request, response);
            
        }
  
    }

}
