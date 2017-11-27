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
  <li><a href="viewIncident.jsp" class = "active" >View Incidents</a></li>
  
  <li><a href="viewPenalties.jsp">View Penalties</a></li>

  
  
  <li style="float:right"><img src="USER.png" align="middle" width="50" height="50" ></li>


<div style="padding:20px;margin-top:30px;background-color:white;height:1500px;">
<l><h2><center>View Incidents</center></h2></l>

<div class="container-fluid">
  

  <div class="row">
    <div class="col-sm-3" style="background-color:white;"></div>
    
    <div class="col-sm-6" style="background-color:lavenderblush;">
        <div class="center">
            <form name ='selectType' method ='get' action ='incidentType.jsp'>
            <li><h4>Select type of incident:
                    <select id = "type" name = "type" onchange="changeSelect(this.value)"> 
                    <option id = "1" value = "1">User to User</option>
                    <option id = "2" value = "2">User to Anyone</option>
                    <option id = "3" value = "3">Vehicle to Vehicle</option>
                    <option id = "4" value = "4">Vehicle to User</option>		
                </select>
                     <button type="submit" name="submit" class="btn btn-default" >Select</button>
                    
            </h4></li>  
             <li><div id="change">
                 <h4>Select kind:
                    <select id = "kind" name = "kind"> 
                    <option id = "1" value = "1">Complaints</option>
                    <option id = "2" value = "2">Accusations</option>
                   	
                </select>
                    
                 </h4></div>
             </li>  
            
            
             
             
            </form>
            
            
            <%      
                String kind = request.getParameter("kind");
                String incidentType = request.getParameter("type");
             
            %>

             
	</div>
        
	
	</div>
    <div class="col-sm-3" style="background-color:white;"></div>
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

