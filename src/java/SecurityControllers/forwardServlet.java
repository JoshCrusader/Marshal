/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityControllers;

import SecurityControllers.Users;
import SecurityControllers.Database;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.ResultSet;
/**
 *
 * @author paul
 */
@WebServlet(name = "forwardServlet", urlPatterns = {"/forwardServlet"})


public class forwardServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet forwardServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet forwardServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String mapLat = request.getParameter("mapLat");
        String mapLong = request.getParameter("mapLong");
        int map_point = 0;
        Users user = (Users) session.getAttribute("sessionUser");
        Violation v1 = new Violation(request.getParameter("offender"), request.getParameter("offended"), request.getParameter("desc"), Integer.parseInt(request.getParameter("report_type")), user );    
        Connection conn = Database.getDBConnection();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            String policyselect = "<select name = 'policies'>"; 
            String transactionselect = "<select name = 'transactions'>";
            String policysql = "SELECT policyID, policydesc FROM POLICIES"; /*Selects all policies*/
            String maxviolationsql = "SELECT MAX(securityReportID) FROM security_violations"; /*Gets the max value of security report ID's(the most recent in this case)*/
            String violationtypesql = "";
            String max_id = "";
            String mappointsql = "SELECT MAX(MAPPOINTID) FROM MAPPOINT";/*Gets the max value of map point ID (the most recent in this case)*/
            String mapsql = "INSERT INTO `hoa_db`.`mappoint` (`xAxis`, `yAxis`, `title`, `description`, `userID`, `createDate`, `removed`, `mappointcategoryID`) VALUES (?, ?, 'Incident Location', 'Incident Happened Here', ?, DATE(NOW()), '0', '1');";
            /*mapsql query creates a new mappoint*/
            String sql = "INSERT INTO `hoa_db`.`SECURITY_VIOLATIONS` (`incidenttypeID`,`complaint`, `securityID`, `mappointID`, `reportDate`) VALUES (?, ?, ?, ?, DATE(NOW()));";
            /*sql query inserts into security violations*/
            
            String transactionsql = "SELECT trxID, description FROM trxReferences";
            
            /*This line of if's determine which table based on incident type the security violation information will be placed*/
            if (Integer.parseInt(request.getParameter("report_type")) == 1){
            
                violationtypesql = "INSERT INTO `hoa_db`.`user2user` (`securityReportID`, `complainant_userID`, `accused_userID`) VALUES ((SELECT MAX(security_violations.securityReportID) FROM security_violations), ?, ?);";
                
            }
            
            if (Integer.parseInt(request.getParameter("report_type")) == 2){
            
                violationtypesql = "INSERT INTO `hoa_db`.`user2anyone` (`securityReportID`, `otherparty`, `userID`) VALUES ((SELECT MAX(security_violations.securityReportID) FROM security_violations), ?, ?);";
                
            }
            
            if (Integer.parseInt(request.getParameter("report_type")) == 3){
            
                violationtypesql = "INSERT INTO `hoa_db`.`vehicle2vehicle` (`securityReportID`, `complainantplatenum`, `accusedplatenum`) VALUES ((SELECT MAX(security_violations.securityReportID) FROM security_violations), ?, ?);";
                
            }
            
            PreparedStatement prep = conn.prepareStatement(sql);
            PreparedStatement prep2 = conn.prepareStatement(mapsql);
            PreparedStatement prep3 = conn.prepareStatement(mappointsql);
            PreparedStatement prep4 = conn.prepareStatement(violationtypesql);
            PreparedStatement prep5 = conn.prepareStatement(policysql);
            PreparedStatement prep6 = conn.prepareStatement(maxviolationsql);
            PreparedStatement prep7 = conn.prepareStatement(transactionsql);
            
            ResultSet rs20 = prep7.executeQuery();
            while (rs20.next()){
            
                transactionselect += "<option value = '"+rs20.getInt(1)+"'>"+rs20.getString(2)+"</option>";
                
            }
            
            transactionselect += "</select>";
            
            ResultSet rs10 = prep5.executeQuery();
            
            while (rs10.next()){
            
                policyselect += "<option value ='"+rs10.getInt(1)+"'>"+rs10.getString(2)+"</option>";
                
            }
            
            
            
            policyselect += "</select>";
            
            request.setAttribute("Select_Transactions", transactionselect);
            request.setAttribute("Select_Policy", policyselect);
            
            
            prep2.setString(1, mapLat);
            prep2.setString(2, mapLong);
            prep2.setString(3, user.getUsername());
            
            int rs2 = prep2.executeUpdate();
            
            ResultSet rs3 = prep3.executeQuery();
            while(rs3.next()){
            
                map_point = rs3.getInt(1);
                
            }
                     
            
            prep.setInt(1, v1.getReportType());
            prep.setString(2, v1.getDescription());
            prep.setString(3, user.getUsername());
            prep.setInt(4, map_point);
            
            
            prep4.setString(1, v1.getOffended());
            prep4.setString(2, v1.getOffender());
            
            request.setAttribute("violationSample", v1);
            
            
            
            int rs = prep.executeUpdate();
            
            if (rs!=0){
            
                System.out.println("Update Successful!");
                
            }
            
            ResultSet rs11 = prep6.executeQuery();
            
            while(rs11.next()){
            
                max_id = ""+rs11.getInt(1);
            
            }
            
            request.setAttribute("Violation_ID", max_id);
            
            int rs4 = prep4.executeUpdate();
            
            request.getRequestDispatcher("set_policy.jsp").forward(request, response);
            
        }catch(SQLException e){
                
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                
        }catch(ClassNotFoundException ex){
                
                        ex.printStackTrace();
                
        }finally{
                
                if (conn != null){
                
                    try{
                
                        conn.close();
                
                    }catch(SQLException e){
                
                
                    }
                
                    }
                    
                }
   
        
    }// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
