<%-- 
    Document   : testjsp
    Created on : Nov 23, 2017, 10:54:22 PM
    Author     : Rafael Pangan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.json.*;"%>
<!DOCTYPE html>
<% 
 String policyselect = (String) request.getAttribute("Select_Policy");
 String violationid = (String) request.getAttribute("Violation_ID");
 String selecttransaction = (String) request.getAttribute("Select_Transactions");
%>
<html>
    
    <head>
        <link rel="stylesheet" type="text/css" href="css_standard.css">
	 <link rel="stylesheet" type="text/css" href="test.css">
	 <script src="jquery.js"></script>
	 <script src="css_scripts.js"></script>

	 <!-- remove this afterwards gg -->
	 <meta charset="utf-8">
	 <meta name="viewport" content="width=device-width, initial-scale=1">
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
	 <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	 <!--                           -->
         
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>JSP Page</title>
        <style>
       #map {
        height: 400px;
        width: 100%;
        
       }
       
       
       input[type="text"]{
           
           width:80%;
           height: 30%;
           padding: 12px 20px;
           border-radius: 4px;
           box-sizing: border-box;
       }
       
       textarea{ 
           border-radius: 4px;
           box-sizing: border-box; 
       }
       
       select{
           
           width: 100%;
           padding: 16px, 20px;
           border: none;
           border-radius: 4px;
           background-color: #f1f1f1;
           
       }
    </style>
    </head>
    <body>
        <div class = "css-navbar"> 
		<div class = "css-burgerbun"><div class = "css-hamburger"><div  class = "css-burgerinside"></div></div></div>
	</div>
        <div id = "sidebartarget" class = "css-sidebarbody">
		<div class = "css-darkness"></div>
		<div class = "css-sidebar">
			<div class = "css-profilediv">

			</div>
			<ul class = "css-sidelist">
				<li>
					<a href = "#"> User Management</a>
				</li>
				<li>
					<a href = "#"> Security</a>
				</li>
				<li>
					<a href = "#"> Records Management</a>
				</li>
				<li>
					<a href = "#"> Module Configuration</a>
				</li>
				<li>
					<a href = "#"> SMS Management</a>
				</li>
			</ul>
		</div>
	</div>
        
        <div id = "css-id-body" style = "top:70px;Position:absolute;width:100%;">
		<div class="container">
		  <h2>File a Security Complaint</h2>
			  <div class="panel panel-default col-md-3 pull-left" style = "height:95%; width: 80%;">
			    <div class="panel-body">
			    	
        
        <form action ="PolicyServlet" method ="post">
            
            Violation ID: <%=violationid%> <br>
            Type of transaction: <%= selecttransaction %>
            Select Policy Violated:   <%=policyselect %> <!-- Shows the policies, retrieved from forwardServlet -->
            
            <input type ="hidden" name ="violationID" value = "<%=violationid%>"> <!-- Stores current violationID passed from forwardServlet -->
            <input type ="submit" name ="submit" value ="Submit Complaint!">
            
              </form>
            
            
			    </div>
			  </div>
                </div>
        </div>
        
        
        
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBG-MD22zr0bO5f1kSH37MeIE3vevvVH4M&callback=initMap">
    </script>
        <script>
                
                    
                
                
            </script>
    </body>
</html>
