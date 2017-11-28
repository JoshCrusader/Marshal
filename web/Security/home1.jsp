<%-- 
    Document   : homemember
    Created on : 11 25, 17, 6:56:13 AM
    Author     : Mharjorie Sandel
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="SecurityControllers.Users" %>
<!DOCTYPE html>

<html>
<title>Home</title>
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
   <div class = "css-navbar"> 
		<div class = "css-burgerbun pull-left"><div class = "css-hamburger"><div  class = "css-burgerinside"></div></div></div>
		<!-- 
		<input class = "pull-right css-search"type="text" id="directoryinput" name="directoryinput" placeholder="Search">
	-->
	<div class = "css_container">
		<a href = "#">
			<div class = "pull-left css-item" style = "width:50px;height:50px;margin-top:-7px;">
				<img src="Security/Marshal.png" style = "height:100%;width:100%;">
			</div>
		</a>
		
		<form id = "directorysearchform" action = "SearchDirectory" method = "GET">
				<div class = "nav-search">
						<input class = "pull-left css-search"type="text" id="directoryinput" name="directoryinput" placeholder="Search" style = "margin-left:15px;">
					
					<select class = "pull-left css-filterd" name = "searchtype">
					  <option value="Users" selected>Users</option>
					  <option value="Services">Services</option>
	
					</select>
					<button class = "pull-left css-searchb" onclick = "submitAform('directorysearchform')"><i class="fa fa-search"></i></button>
				</div>
			</form>
		<div class = "css-navright">

			
		</div>
		</div>
	</div>

<ul> 
<%          String user = request.getParameter( "u" );

            session.setAttribute( "incidentType", user );
            String p = request.getParameter( "p" );

            session.setAttribute( "incidentType", p );
            int i = 0;
            String fname ="";
              try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root",null);
            String sql = "Select * from users where userID = '"+user+"' and passwd = '"+p+"'";
            
                 PreparedStatement prep = con.prepareStatement(sql);
                 ResultSet rs = prep.executeQuery();
                while (rs.next()){
                    i = rs.getInt("usertypeID");
                    fname = rs.getString("fname");
                    String path = "evaluateReport.jsp";
                }
            
           
           
        }
        catch (Exception e){
            
        }
            %>    






          
  <li><img src="LOGO.png" align="middle" width="40" height="40" ></li>

  <li><a href="<%=request.getContextPath()%>/Security/home1.jsp" class = "active">Home</a></li>
  <li><a href="<%=request.getContextPath()%>/Security/evaluateReport.jsp"  >View Incidents</a></li>
  
  <li><a href="<%=request.getContextPath()%>/Security/viewReport.jsp">View Penalties</a></li>

  
  
  <li style="float:right"><img src="Security/USER.png" align="middle" width="50" height="50" ></li>
<% 
         int h = ((Users) session.getAttribute("sessionUser")).getUsertype();
         String j = ((Users) session.getAttribute("sessionUser")).getUsername();
         String f = ((Users) session.getAttribute("sessionUser")).getfName();
         
        %>
    

<div style="padding:20px;margin-top:30px;background-color:white;height:1500px;">
<l><h2><center>Welcome, <%= f%>!</center></h2></l>

<div class="container-fluid">
  

  <div class="row">
    <div class="col-sm-2" style="background-color:white;"></div>
    
    <div class="col-sm-8" >
       
      
	
    </div>
 
		
	
	
    <div class="col-sm-2" style="background-color:white;"></div>
       
  </div>
       
</div>
</div>




</body>
</html>

