<%-- 
    Document   : homemember
    Created on : 11 25, 17, 6:56:13 AM
    Author     : Mharjorie Sandel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="SecurityControllers.Users" %>
<%@page import="javax.servlet.http.HttpSession;" %>

<!DOCTYPE html>
<html>
<title>Evaluate Reports</title>
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
  <li><a href="evaluateReport.jsp" class = "active" >Evaluate Reports</a></li>
  
  <li><a href="viewPenalties.jsp">View Reports</a></li>

  
  
  <li style="float:right"><img src="USER.png" align="middle" width="50" height="50" ></li>


<div style="padding:20px;margin-top:30px;background-color:white;height:1500px;">
<l><h2><center>Evaluate Report</center></h2></l>

<div class="container-fluid">
   <%
            int i = ((Users) session.getAttribute("sessionUser")).getUsertype();
            String j =((Users) session.getAttribute("sessionUser")).getUsername();
         
        %>
        <%= i + j%>

  <div class="row">
    <div class="col-sm-3" style="background-color:white;">
        <li> <p>Legend:</p></li>
    <table style = "width: 100%" border = "1">
        <tr bgcolor = "white">
          <th  style='text-align:center' >Incident Type</th>
          
       
          <th  style='text-align:center'>Status</th>
     
        </tr>
    
         <tr bgcolor="white">

            <td  style='text-align:center'>1 = User to User</td>
            
            <td  style='text-align:center'>0 = Pending</td></tr>
             <tr bgcolor="white">
             <td  style='text-align:center'>2 = User to Anyone</td>
        
            <td  style='text-align:center'>1 = On Going</td>
             </tr>
            <tr bgcolor="white">
             <td  style='text-align:center'>3 = Vehicle to Vehicle</td>
            
            <td  style='text-align:center'>2 = Resolved</td>
             </tr>
             <tr bgcolor="white">
             <td  style='text-align:center'>4 = Vehicle to User</td>
           
            <td  style='text-align:center'>3 = Not Resolved</td>
             </tr>
             
            </tr>
           </table><br>
        </div>    
    <div class="col-sm-6" >
        
        
        <%@page import="java.sql.DriverManager"%>
        <%@page import="java.sql.ResultSet"%>
        <%@page import="java.sql.PreparedStatement"%>
        <%@page import="java.sql.Connection"%>
        <%
            String rep = request.getParameter( "viewreport" );
            String rep1= request.getParameter( "viewreport" );
            session.setAttribute( "incidentType!", rep );
            session.setAttribute( "incidentType!!", rep1 );
            int repID = Integer.parseInt(rep);
            
          
            
            
         %>
        
        
         <%
        try
        {
           
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root",null);
            String sql = "";
             String sqlinsert = "";
            sqlinsert = "UPDATE security_violations set status = '1', boardmemberID = '"+j+"' where securityReportID = '"+repID+"'";
            PreparedStatement a = con.prepareStatement(sqlinsert);
                a.executeUpdate();
            sql = "SELECT * FROM SECURITY_VIOLATIONS WHERE SECURITYREPORTID = '"+repID+"'";
            PreparedStatement pStmt= con.prepareStatement(sql);
            ResultSet resultSet = pStmt.executeQuery(sql);
            while(resultSet.next()){
            %>
            <div class ='container'>
                <div class ="row">
                    <div class="col-sm-4" style="background-color:white;">
                         <li> 
                <p></p>
                 <form name ='selectType' id='test' method ='get' action ='<%=request.getContextPath()%>/Security/evaluateReport.jsp'>
               
                <p><b>Security Report ID: </b> <input type = 'text' name = 'pls' value = '<%=resultSet.getInt("securityReportID") %>' disabled = 'true'></p>
                
                
                <p><b>Incident Type ID: </b><%=resultSet.getInt("incidentTypeID") %></p>
                <p><b>Map Point ID: </b> <%=resultSet.getInt("mappointID") %></p>
                <p><b>Violated Policy ID: </b> <%=resultSet.getInt("violatedpolicyID") %></p>
                <p><b>Complaint:</b></p>
                <p><%=resultSet.getString("complaint") %></p>
                  <p><b>Resolution:</b></p>
                <p><textarea form ='test' rows="4" cols="50" name="comment" ></textarea></p>
                    
                    
                    </div>
        
                    <div class="col-sm-4" style="background-color:white;">
                         <li> 
                <p></p>
                <p align = 'right'><b>Report Date: </b> <%=resultSet.getString("reportDate") %></p>
                <p align = 'right'><b>Security ID: </b> <%=resultSet.getString("securityID") %></p>
                 <p align = 'right'><b>Board Member: </b> <%=resultSet.getString("boardmemberID") %></p>
                <p align = 'right'><b>Status: </b> <%=resultSet.getInt("status") %></p>
                
                
                    <label> Update:</label>
                    <select id = "status" name = "status"> 
                   
                    <option id = "2" value = "2">Resolved</option>
                    <option id = "3" value = "3">Not Resolved</option>		
                </select>
         
                    </div>
                <div class="col-sm-4" style="background-color:white;"></div>
              
                    
                    
                </div>
                
                
                
            </div>
           

               
           
            <% 
              out.print(rep1);
              out.print("hi");
                    session.setAttribute("sessionz", repID);
              String id = request.getParameter("pls");
                String stat = request.getParameter("status");
                String resol = request.getParameter("comment");
                  out.print(id);
                   out.print(resol);
        }
}       
        catch (Exception e) {
            e.printStackTrace();


        }
        %>
    
        <center><button type="button" class="btn btn-default" ><a href = "<%=request.getContextPath()%>/Security/evaluateReport.jsp">Back</a></button>
        <button type="submit" name = "submit" class="btn btn-default" onclick="location.href='eReport.jsp'" >Save</button></center>
  </form>
	
    </div>
  </div>
		
	
	</div>
    <div class="col-sm-3" style="background-color:white;"></div>
       
  </div>
       
</div>
</div>




</body>
</html>

