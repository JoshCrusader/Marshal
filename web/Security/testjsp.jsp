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
			    	
 Select type of incident: <select name ="incident_type" id = "testselect"> <!-- Select the type of report -->
                
                <option value = 0>-SELECT OFFENSE TYPE-</option>
                <option value = 1> User-to-User </option>
                <option value = 2> User-to-Anyone </option>
                <option value = 3> Vehicle-to-Vehicle </option>
                
                
            </select>
        
        <form action ="forwardServlet" method ="post">
            
            <p id = "test"> </p> 
            <p id ="AjaxResponse"> </p>
            <p id ="mapVal"> </p>
              </form>
            Select Incident Location:   
            <div id="map"></div> <!-- This is where the Google Map is in -->
			    </div>
			  </div>
       
        <div id ="setPolicy"></div>
        
        <!-- This script implements google maps -->    <script>
      var latitude;
      var longitude;
      function initMap() {
        var uluru = {lat: 14.382563, lng: 121.048059};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 20,
          center: uluru,
          mapTypeId: 'satellite'
        });
        
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
        google.maps.event.addListener(map, 'click', function(event) {
            latitude = event.latLng.lat();
            longitude = event.latLng.lng();
            placeMarker(event.latLng);
});

function placeMarker(location) { /* Allows the user to place a marker on the map */
        if (marker && marker.setMap) {
    marker.setMap(null);
}
        marker = new google.maps.Marker({
        position: location, 
        map: map
    });
      }

}
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBG-MD22zr0bO5f1kSH37MeIE3vevvVH4M&callback=initMap">
    </script>
        <script>
                
                    var myJSON;
                    var myObj;
                    var x;
                    
                $(document).ready(function(){
                 
                    $("#map").click(function (){ /* On click, returns the marker's coordinates */
                        
                        document.getElementById("AjaxResponse").innerHTML = "Latitude: " + latitude + " Longitude: " + longitude;
                        document.getElementById("mapVal").innerHTML = "<input type = 'hidden' name = 'mapLat' value =" +latitude+">\n\
                                                                       <input type = 'hidden' name = 'mapLong' value = "+longitude+">\n\
                                                                        <input type = 'submit' name = 'submit' value = 'Continue!'></input>";
                    /*Coordinates are placed in a hidden value to be passed to servlet*/
                        
                    });
                    
                   
                    $("#testselect").change(function(event){ /*On selecting a report type, changes the fields to be inputted and their contents.*/
                        
                        if (document.getElementById("testselect").value < 3){
                            /*Ajax uses its respective servlet to retrieve all user ID's and populate its dropdowns*/
                            $.get('UserTableServlet', 
                                {selecttest: testselect},
                                function(data){
                                   
                                x="";   
                                myJSON = '{ "name":'+ data +'}';
                                myObj = JSON.parse(myJSON);
                                    
                                for(i in myObj.name){
                                    
                                    x+="<option value ='"+myObj.name[i]+"'>"+myObj.name[i]+"</option>";
                                    
                                    
                                }
                                        
                                if(document.getElementById("testselect").value == 0){
                            
                            document.getElementById("test").innerHTML = "";
                            
                        }
                        
                        else if(document.getElementById("testselect").value == 1){
                            
                            
                            
                            document.getElementById("test").innerHTML = 
                            "Select offending user: <select name = 'offender'> "+x+" </select><br>\
                             Select offended user: <select name = 'offended'>"+x+"</select> <br>\n\
                             Complaint Description: <br><textarea rows = '7' cols = '70' name = 'desc'></textarea> <br>\n\
                             <input type = 'hidden' value = '1' name = 'report_type'>";
                             
                            
                            
                        }
                        
                        else if(document.getElementById("testselect").value == 2){
                            
                            document.getElementById("test").innerHTML = 
                            "Select offending user: <select name = 'offender'> "+x+" </select><br>\
                             Input offended party:<br><input name = 'offended' type = 'text' class = 'offended_text' maxlength = '100'></input> <br>\n\
                             Complaint Description: <br><textarea rows = '7' cols = '70' name = 'desc'></textarea> <br>\n\
                            <input type = 'hidden' value = '2' name = 'report_type'>";
                            
                            
                        }
                              
                                }
                                
                            )
                        
                        
                        var testselect = $('#testselect').val(); 
                            
                        }
                        
                        if (document.getElementById("testselect").value == 3){
                            /*Ajx gets all platenumbers through it's servlet and uses the data to populate the platenumber dropdown*/
                            $.get('PlateNumberTableServlet', 
                                {selecttest: testselect},
                                function(data){
                                   
                                x="";   
                                myJSON = '{ "name":'+ data +'}';
                                myObj = JSON.parse(myJSON);
                                
                                for(i in myObj.name){
                                    
                                    x+="<option value ='"+myObj.name[i]+"'>"+myObj.name[i]+"</option>";
                                    
                                    
                                }
                                
                                document.getElementById("test").innerHTML = 
                                "Select offending vehicle: <select name = 'offender'> "+x+" </select><br>\
                                Input offended vehicle: <select name = 'offended'>"+x+"</input> <br>\n\
                                Complaint Description: <br><textarea rows = '7' cols = '70' name = 'desc'></textarea> <br>\n\
                                <input type = 'hidden' value = '3' name = 'report_type'>";
                            
                            
                        
                                
                                
                                });
                            
                        }     
                        
                        
                        
                    });
                    
                    
                });
                
                
            </script>
    </body>
</html>
