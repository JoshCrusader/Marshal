<%-- 
    Document   : homemember
    Created on : 11 25, 17, 6:56:13 AM
    Author     : Mharjorie Sandel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="SecurityControllers.Users" %>
<!DOCTYPE html>
<html>
<title>View Penalties</title>
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
.center {
    margin: auto;
    width: 60%;
    border: 3px solid #73AD21;
    padding: 10px;
}	
</style>
</head>

<body>

<ul> 

  <li><img src="LOGO.png" align="middle" width="40" height="40" ></li>

  <li><a href="home.jsp">Home</a></li>
  <li><a href="viewIncident.jsp">View Incidents</a></li>
  
  <li><a href="viewPenalties.jsp" class = "active" >View Penalties</a></li>

  
  
  <li style="float:right"><img src="USER.png" align="middle" width="50" height="50" ></li>


<div style="padding:20px;margin-top:30px;background-color:white;height:1500px;">
<l><h2><center>View All Penalties</center></h2></l>

<div class="container-fluid">
     <% 
         int i = ((Users) session.getAttribute("sessionUser")).getUsertype();
         String j = ((Users) session.getAttribute("sessionUser")).getUsername();
         
        %>
        <%= i + j%>

  <div class="row">
    <div class="col-sm-2" style="background-color:white;">
         <li> <p>Legend:</p></li>
    <table style = "width: 100%" border = "1">
        <tr bgcolor = "white">
          <th  style='text-align:center' >Incident Type</th>
          
       
      
     
        </tr>
    
         <tr bgcolor="white">

            <td  style='text-align:center'>1 = User to User</td>
            
             <tr bgcolor="white">
             <td  style='text-align:center'>2 = User to Anyone</td>
        
             </tr>
            <tr bgcolor="white">
             <td  style='text-align:center'>3 = Vehicle to Vehicle</td>
            
              </tr>
             <tr bgcolor="white">
             <td  style='text-align:center'>4 = Vehicle to User</td>
           
             </tr>
             
            </tr>
           </table><br>
        </div>    
    
    <div class="col-sm-8" style="background-color:lavenderblush;">
        <div class="center">
            <form name ='selectType' method ='get' action ='<%=request.getContextPath()%>/Security/incidentP.jsp'>
            <li><h4>Filter by type:
                    <select id = "type" name = "type" > 
                    <option id = "1" value = "1">User to User</option>
                    <option id = "2" value = "2">User to Anyone</option>
                    <option id = "3" value = "3">Vehicle to Vehicle</option>
                    <option id = "4" value = "4">Vehicle to User</option>
                   
                </select>
                     <button type="submit" name="submit" class="btn btn-default" >Select</button>
                    
            </h4></li>  
            
            
             
             
            </form>
             <table style = "width: 100%" border = "1">
        <tr bgcolor = "white">
          <th style='text-align:center' >Security Report ID</th>
          <th style='text-align:center'>Report Date</th>
          <th style='text-align:center'>Incident Type ID</th>
          <th style='text-align:center'>Complaint</th>
          <th style='text-align:center'>Policy Violated</th>
          
          <th style='text-align:center'>Status</th>
          <th style='text-align:center'>Penalty Fee</th>
     
     
        </tr>
            
            
            <%      
                String kind = request.getParameter("kind");
                String incidentType = request.getParameter("type");
             
            %>

             
	</div>
        <%@page import="java.sql.DriverManager"%>
        <%@page import="java.sql.ResultSet"%>
        <%@page import="java.sql.PreparedStatement"%>
        <%@page import="java.sql.Connection"%>
        <%
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root",null);
           
            String sql1 = "";
            String sql2 = "";
            String sql3 = "";
            String sql4 = "";
	
             sql1 ="SELECT * FROM (SELECT * FROM USER2USER WHERE accused_userID = '"+j+"') UU LEFT JOIN SECURITY_VIOLATIONS SV ON SV.securityReportID = UU.securityReportID  LEFT JOIN POLICIES P ON SV.violatedpolicyID = P.policyID LEFT JOIN PENALTIES PE ON PE.penaltyID = P.penaltyID";
             
             sql2 ="SELECT * FROM (SELECT * FROM USER2ANYONE WHERE userID = '"+j+"') UA LEFT JOIN SECURITY_VIOLATIONS SV ON SV.securityReportID = UA.securityReportID  LEFT JOIN POLICIES P ON SV.violatedpolicyID = P.policyID LEFT JOIN PENALTIES PE ON PE.penaltyID = P.penaltyID";
             
             sql3 ="SELECT * FROM (SELECT * FROM USER_VEHICLES WHERE USERID = '"+j+"') UV JOIN VEHICLE2VEHICLE VV ON UV.plateNum = VV.accusedplatenum LEFT JOIN SECURITY_VIOLATIONS SV ON SV.securityReportID = VV.securityReportID LEFT JOIN POLICIES P ON SV.violatedpolicyID = P.policyID LEFT JOIN PENALTIES PE ON PE.penaltyID = P.penaltyID";
            sql4 = "SELECT * FROM (SELECT * FROM USER_VEHICLES WHERE USERID = '"+j+"') UV JOIN VEHICLE2USER VU ON UV.plateNum = VU.platenum LEFT JOIN SECURITY_VIOLATIONS SV ON SV.securityReportID = VU.securityReportID LEFT JOIN POLICIES P ON SV.violatedpolicyID = P.policyID LEFT JOIN PENALTIES PE ON PE.penaltyID = P.penaltyID";
          
            PreparedStatement pStmt1= con.prepareStatement(sql1);
            ResultSet resultSet1 = pStmt1.executeQuery(sql1);
            float total = 0;
            float fTotal = 0;
            while(resultSet1.next()){
                 total = total + resultSet1.getInt("pe.penaltyfee");
                 fTotal = fTotal + total;
                 
            %>
              
        
            <li> 
           
            <tr bgcolor="white">
            
            <td style='text-align:center' ><%=resultSet1.getInt("securityReportID") %></td>
            <td style='text-align:center' ><%=resultSet1.getString("reportDate") %></td>
            <td style='text-align:center' ><%=resultSet1.getInt("incidentTypeID")%></td>
            <td style='text-align:center' ><%=resultSet1.getString("complaint") %></td>
            <td style='text-align:center' ><%= resultSet1.getString("policydesc")
            
                %></td>
             <td style='text-align:center' ><%=resultSet1.getInt("status") %></td>
             <td style='text-align:right'>  <%=resultSet1.getInt("penaltyfee") %></td></tr>
            <th></th>
							<th></th>
							
            </tbody>
              
        
            <% 
                String report = request.getParameter("view");
        }
%>
<%
                                                            
                                                            PreparedStatement pStmt2= con.prepareStatement(sql2);
            ResultSet resultSet2 = pStmt2.executeQuery(sql2);
            total = 0;
            while(resultSet2.next()){
                 total = total + resultSet2.getInt("pe.penaltyfee");
                 fTotal = fTotal + total;
             
            %>
              
        
            <li> 
           
            <tr bgcolor="white">
            
            <td  style='text-align:center'><%=resultSet2.getInt("securityReportID") %></td>
            <td  style='text-align:center'><%=resultSet2.getString("reportDate") %></td>
            <td  style='text-align:center'><%=resultSet2.getInt("incidentTypeID")%></td>
            <td  style='text-align:center'><%=resultSet2.getString("complaint") %></td>
            <td  style='text-align:center'><%= resultSet2.getString("policydesc")
            
                %></td>
             <td  style='text-align:center'><%=resultSet2.getInt("status") %></td>
             <td style='text-align:right'>  <%=resultSet2.getInt("penaltyfee") %></td></tr>
            <th></th>
							<th></th>
							
            </tbody>
              
        
            <% 
                String report = request.getParameter("view");
        }
%>
<%
            PreparedStatement pStmt3= con.prepareStatement(sql3);
            ResultSet resultSet3 = pStmt3.executeQuery(sql3);
            total = 0;
            while(resultSet3.next()){
                 total = total + resultSet3.getInt("penaltyfee");
                 fTotal = fTotal + total;
                
            %>
              
        
            <li> 
           
            <tr bgcolor="white">
            
            <td  style='text-align:center'><%=resultSet3.getInt("securityReportID") %></td>
            <td  style='text-align:center'><%=resultSet3.getString("reportDate") %></td>
            <td  style='text-align:center'><%=resultSet3.getInt("incidentTypeID")%></td>
            <td  style='text-align:center'><%=resultSet3.getString("complaint") %></td>
            <td  style='text-align:center'><%= resultSet3.getString("policydesc")
            
                %></td>
             <td  style='text-align:center'><%=resultSet3.getInt("status") %></td>
             <td style='text-align:right'>  <%=resultSet3.getInt("penaltyfee") %></td></tr>
            <th></th>
							<th></th>
							
            </tbody>
              
        
            <% 
                String report = request.getParameter("view");
        }
%>
<%
            PreparedStatement pStmt4= con.prepareStatement(sql4);
            ResultSet resultSet4 = pStmt4.executeQuery(sql4);
            total = 0;
            while(resultSet4.next()){
                 total = total + resultSet4.getInt("penaltyfee");
                 fTotal = fTotal + total;
               
            %>
              
        
            <li> 
           
            <tr bgcolor="white">
            
            <td  style='text-align:center'><%=resultSet4.getInt("securityReportID") %></td>
            <td  style='text-align:center'><%=resultSet4.getString("reportDate") %></td>
            <td  style='text-align:center'><%=resultSet4.getInt("incidentTypeID")%></td>
            <td  style='text-align:center'><%=resultSet4.getString("complaint") %></td>
            <td  style='text-align:center'><%= resultSet4.getString("policydesc")
            
                %></td>
             <td style='text-align:center'><%=resultSet4.getInt("status") %></td>
             <td style='text-align:right'>  <%=resultSet4.getInt("penaltyfee") %></td></tr>
            
							
            </tbody>
              
        
            <% 
                String report = request.getParameter("view");
        }
%>
<th bgcolor ="white"></th>
<th bgcolor ="white"></th> 
<th bgcolor ="white"></th>
<th bgcolor ="white"></th>
<th bgcolor ="white"></th>
<th style='text-align:right' bgcolor ="white" >Total: </th>
<th style='text-align:right' bgcolor ="white">P<% out.print(fTotal);%></th>

                                                        <%
            
                                                            
                                                                                     
}       
        catch (Exception e) {
            e.printStackTrace();


        }
        %>

   </table><br>
       
    </div>
  </div>
		
	
	</div>
		
	
	
    <div class="col-sm-2" style="background-color:white;"></div>
  </div>
</div>
</div>




</body>

<script>
    function changeSelect(val1){
        if(val1 == "1" || val1 == "3"){
            $('#change').attr("hidden", false);
        }else{
            $('#change').attr("hidden", true);
        }
    }
</script>
</html>

