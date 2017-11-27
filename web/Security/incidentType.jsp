<%-- 
    Document   : homemember
    Created on : 11 25, 17, 6:56:13 AM
    Author     : Mharjorie Sandel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>View Incident</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
<style>
body {margin:0;}
.dropdown {
    float: left;
	font-family: Verdana;
	font-face: url(sansation_light.woff);
   
}

.dropdown .dropbtn {
    font-size: 16px;    
    border: none;
    outline: none;
    color: #03313e;
    padding: 16px 16px;
    background-color: inherit;
	font-family: Verdana;
	font-face: url(sansation_light.woff);
}

.navbar a:hover, .dropdown:hover .dropbtn {
    background-color: #eeeeee;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: white;
    min-width: 180px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    float: none;
    color: #03313e;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {
    background-color: #eeeeee;
}

.dropdown:hover .dropdown-content {
    display: block;
}
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color:white;
    position: fixed;
    top: 0;
    width: 100%;
}

li {
    float: left;
	font-family: Verdana;
	font-face: url(sansation_light.woff);
}
l {
    
	font-family: Verdana;
	font-face: url(sansation_light.woff);
}

li a {
    display: block;
    color: #03313e;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover:not(.active) {
    background-color: 	#eeeeee;
}

.active {
    color: 	#bf5d27;
d {
    width: 320px;
    padding: 10px;
    border: 5px solid gray;
    margin: 0;
}
table, td, th {    
    border: 1px solid #ddd;
    text-align: left;
}

table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    padding: 15px;
}	
	
</style>
</head>

<body>

<ul> 

  <li><img src="LOGO.png" align="middle" width="40" height="40" ></li>

  <li><a href="home.jsp">Home</a></li>
  <li><a href="viewIncident.jsp" class = "active" >View Incidents</a></li>
  
  <li><a href="viewPenalties.jsp">View Penalties</a></li>

  
  
  <li style="float:right"><img src="USER.png" align="middle" width="50" height="50" ></li>


<div style="padding:20px;margin-top:30px;background-color:white;height:1500px;">
<l><h2><center>View Incidents</center></h2></l>

<div class="container-fluid">
  

  <div class="row">
    <div class="col-sm-2" style="background-color:white;"></div>
    
    <div class="col-sm-8" ">
        <table style = "width: 100%" border = "1">
        <tr bgcolor = "white">
          <th  style='text-align:center' >Security Report ID</th>
          <th  style='text-align:center'>Report Date</th>
          <th  style='text-align:center'>Incident Type</th>
          <th  style='text-align:center'>Complaint</th>
          <th  style='text-align:center'>Status</th>
          <th></th>
     
        </tr>
        
        <%@page import="java.sql.DriverManager"%>
        <%@page import="java.sql.ResultSet"%>
        <%@page import="java.sql.PreparedStatement"%>
        <%@page import="java.sql.Connection"%>
        <%
            String type = request.getParameter( "type" );
            session.setAttribute( "incidentType", type );
            int typeID = Integer.parseInt(type);
          
            int kindID = 0;
            
         %>
        
        
         <%
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root",null);
            String sql = "";
            if (typeID == 1){
                String kind = request.getParameter( "kind" );
                session.setAttribute( "incidentType", kind );
                kindID = Integer.parseInt(kind);
                
                if (kindID == 1) {
                    out.println("List of your Complaints:");
                     
                     sql ="SELECT * FROM SECURITY_VIOLATIONS sv join USER2USER uu on sv.securityReportID = uu.securityReportID where incidentTypeID = '"+typeID+"' and uu.complainant_userID = 'olie@gmail.com'";       
                }
                else {
                    out.println("List of your Accusations:");
                     
                    sql ="SELECT * FROM SECURITY_VIOLATIONS sv join USER2USER uu on sv.securityReportID = uu.securityReportID where incidentTypeID = '"+typeID+"' and uu.accused_userID = 'olie@gmail.com'"; 
                }
               }
            else if (typeID == 2){
               sql ="SELECT * FROM SECURITY_VIOLATIONS sv join USER2anyone ua on sv.securityReportID = ua.securityReportID where incidentTypeID = '"+typeID+"' and ua.userID = 'mharj@gmail.com'";        
            }
            else if (typeID == 3){
                String kind = request.getParameter( "kind" );
                session.setAttribute( "incidentType", kind );
                kindID = Integer.parseInt(kind);
                out.println(kindID);
                if (kindID == 1){
                    out.println("List of your Vehicle's Complaints:");
                     
                    sql ="SELECT * FROM SECURITY_VIOLATIONS sv join VEHICLE2VEHICLE vv on sv.securityReportID = vv.securityReportID where incidentTypeID = '"+typeID+"' and vv.complainantplatenum = 'KYA143'";        
                }
                else {
                    out.println("List of your Vehicle's Accusations:");
                     
                     sql ="SELECT * FROM SECURITY_VIOLATIONS sv join VEHICLE2VEHICLE vv on sv.securityReportID = vv.securityReportID where incidentTypeID = '"+typeID+"' and vv.accusedplatenum = 'TEH115'";   
                }
               }
            else {
               sql ="SELECT * FROM SECURITY_VIOLATIONS sv join VEHICLE2User vu on sv.securityReportID = vu.securityReportID where incidentTypeID = '"+typeID+"' and vu.platenum = 'ULO221'";         
            }
            PreparedStatement pStmt= con.prepareStatement(sql);
            ResultSet resultSet = pStmt.executeQuery(sql);
            while(resultSet.next()){
            %>
            <li> 
                
            <tr bgcolor="white">

            <td  style='text-align:center'><%=resultSet.getInt("securityReportID") %></td>
            <td  style='text-align:center'><%=resultSet.getString("reportDate") %></td>
            <td  style='text-align:center'><% if (typeID == 1){
                out.println("User to User");            }
            else if (typeID == 2) {
                out.println("User to Anyone");
            }
            else if (typeID == 3) {
                out.println("Vehicle to Vehicle");
            }
            else {
                out.println("Vehicle to User");
            }%></td>
            <td  style='text-align:center'><%=resultSet.getString("complaint") %></td>
            <td  style='text-align:center'><%=resultSet.getInt("status") %></td>
            <form name ='viewRep' method ='get' action ='report.jsp'>
            <td><center><button type ="submit" name="viewreport" value ="<%=resultSet.getInt("securityReportID") %>" class="btn btn-default" >View</button></center></td>
            </form>
            </tr>
            <% 
                String report = request.getParameter("viewreport");
        }
}       
        catch (Exception e) {
            e.printStackTrace();


        }
        %>
        </table><br>
        <center><button type="button" class="btn btn-default" onclick="location.href='viewIncident.jsp'" >Back</button></center>
        
	
    </div>
  </div>
		
	
	</div>
    <div class="col-sm-2" style="background-color:white;"></div>
       
  </div>
       
</div>
</div>




</body>
</html>

