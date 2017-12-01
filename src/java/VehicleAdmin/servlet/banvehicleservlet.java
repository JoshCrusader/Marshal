package VehicleAdmin.servlet;

import VehicleAdmin.dao.Database;
import VehicleAdmin.model.Vehicle;
import VehicleAdmin.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;



/**It is the servlet for Ban a Vehicle functionality
 *
 * @author Johannes
 */

@WebServlet(urlPatterns = {"/banvehicleservlet"})
public class banvehicleservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String platenum = request.getParameter("banselect");
            Vehicle banvehicle = new Vehicle();
            
            ArrayList<String> platenumA = new ArrayList<String>();
            String bandropdown = request.getParameter("banselect");
            Connection conn = Database.getDBConnection();
            String sql = "SELECT * FROM vehicles WHERE banned = 0;";
                
                PreparedStatement pStmt = null;
        try {
            pStmt = conn.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(banvehicleservlet.class.getName()).log(Level.SEVERE, null, ex);
        }

                ResultSet rs = null;
        try {
            rs = pStmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(banvehicleservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                platenumA.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(banvehicleservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                for(int x = 0; x < platenumA.size(); x++){
                    
                    if(bandropdown.equals(platenumA.get(x))){
                        String sql1 = "UPDATE vehicles SET banned = '1' WHERE platenum LIKE '" + bandropdown + "'";
                        
                        PreparedStatement pStmt1 = null;
                        try {
                            pStmt1 = conn.prepareStatement(sql1);
                        } catch (SQLException ex) {
                            Logger.getLogger(banvehicleservlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            int isInserted = pStmt1.executeUpdate();
                        } catch (SQLException ex) {
                            Logger.getLogger(banvehicleservlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            
        
            
            
            
            request.setAttribute("banselect", platenum);
        request.getRequestDispatcher("banvehicleoutput.jsp").forward(request, response);
    }
}
