<%-- 
    Document   : transactionform
    Created on : Nov 22, 2017, 8:29:31 PM
    Author     : Johannes
--%>

<!-- BOARD MEMBER -->
<%@page import="java.util.*"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="VehicleAdmin.servlet.*"%>
<%@page import="VehicleAdmin.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ban Vehicle</title>
    </head>
    <body>
        <form action="banvehicleoutput.jsp" method="POST">
            <select name="banselect">
                <%
                    ArrayList<String> model = new ArrayList<String>();
                    ArrayList<String> platenum = new ArrayList<String>();
                    
                    
                    Connection conn = Database.getDBConnection();

                        String sql = "SELECT * FROM vehicles JOIN user_vehicles ON vehicles.platenum = user_vehicles.plateNum WHERE banned = 0;";

                        PreparedStatement pStmt = conn.prepareStatement(sql);

                        ResultSet rs = pStmt.executeQuery();
                        while(rs.next()){
                            model.add(rs.getString(2));
                            platenum.add(rs.getString(1));
                        }
                        
                        for(int x = 0; x < platenum.size(); x++){
                            out.println("<option value='" + platenum.get(x) +"'>" + model.get(x) + " - " + platenum.get(x) + "</option>");
                        }
                        
                        
                    
                %>
            </select>
            <input type="submit" value="submit">
        </form>
    </body>
</html>