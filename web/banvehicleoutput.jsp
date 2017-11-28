<%-- 
    Document   : banvehicleoutput
    Created on : Nov 25, 2017, 3:40:38 AM
    Author     : Johannes
--%>

<%@page import="java.util.*"%>
<%@page import="VehicleAdmin.model.Vehicle"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Successfully Updated</title>
    </head>
    <h1>Successfully banned.</h1>
    <body>
        <%
            ArrayList<String> platenumA = new ArrayList<String>();
            String bandropdown = request.getParameter("banselect");
            Connection conn = null;
            String driver = "com.mysql.jdbc.Driver";
            String jdbcUrl = "jdbc:mysql://localhost:3306/vehicleadmin";
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(jdbcUrl, "root", "12345");

                String sql = "SELECT * FROM vehicles WHERE banned = 0;";
                
                PreparedStatement pStmt = conn.prepareStatement(sql);

                ResultSet rs = pStmt.executeQuery();
                while(rs.next()){
                    platenumA.add(rs.getString(1));
                }
                
                for(int x = 0; x < platenumA.size(); x++){
                    
                    if(bandropdown.equals(platenumA.get(x))){
                        String sql1 = "UPDATE vehicles SET banned = '1' WHERE platenum LIKE '" + bandropdown + "'";
                        
                        PreparedStatement pStmt1 = conn.prepareStatement(sql1);
                        int isInserted = pStmt1.executeUpdate();
                    }
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            catch(ClassNotFoundException ex){
                ex.printStackTrace();
            }
            finally{
                if(conn != null){
                    try{
                        conn.close();
                    }
                    catch(SQLException e){

                    }
                }
            } 
        %>
        <a href="Vehicleindex.html">Back to homepage</a>
    </body>
</html>
