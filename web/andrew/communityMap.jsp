<%-- 
    Document   : communityMap
    Created on : Nov 5, 2017, 12:18:55 AM
    Author     : Andrew Santiago
--%>

<%@page import="dao.UserDAO"%>
<%@page import="dao.mappointDAO"%>
<%@page import="model.mapCategory"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Users,model.mappoint" %>
<!DOCTYPE html>
<%
Users currUser = null;
currUser = UserDAO.getUserbyUsername("santiago_ja");
session.setAttribute("loginUser",currUser);
ArrayList<mappoint> currMapPoints = mappointDAO.getAllPoints();
ArrayList<mapCategory> currMapCategories = mappointDAO.getAllCategories();
ArrayList<String> allStreets = mappointDAO.getAllStreets();
String msg = (String) request.getAttribute("msg");
%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css_standard.css">
	 <link rel="stylesheet" type="text/css" href="test.css">
	 <script src="jquery.js"></script>
	 <script src="css_scripts.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Community Maps</title>
        
        <style>
            * {
            font-size: 100%;
            font-family: Segoe UI;
           }
            #map {
                width: 100%;
                height: 400px;
                background-color: grey;
                margin-top:1rem;
              }
            .allContainer{
                background-color: darkslategray;
                width: 50vw;
                margin-left:auto;
                margin-right:auto;
                -webkit-box-shadow: 0px 0px 8px 0px rgba(0,0,0,0.4);
                -moz-box-shadow: 0px 0px 8px 0px rgba(0,0,0,0.4);
                box-shadow: 0px 0px 8px 0px rgba(0,0,0,0.4);
                padding:1rem;
                margin-top:1rem;
            }
        </style>
    <script src="jsp/jquery-3.2.1.min.js"></script> 
    
    </head>
    
    <body>
        <%if(currUser.getuserType() == 4){ %>
        <%@include file="navbar_officer_mapping.jsp" %>
        <%} else if(currUser.getuserType() == 2){ %> 
        <%@include file="navbar.jsp" %> 
        <% } %>
        <h3>Community Maps</h3>
        <div id="map"></div>
        
        <div class="allContainer">
            <% if (msg != null) {%>
                <%= msg%>
            <% }%>
            <h5>Get Directions:</h5>
                    From: 
                    <select name="dirFrom" id="dirFrom">
                        <%for(mappoint point : currMapPoints){ %>
                        <option value="<%=point.getYAxisLatitude() + "," + point.getXAxisLongitude() %>"><%=point.getTitle() %></option>
                        <% } %>
                        
                    </select>
                        
                    To: 
                    <select name="dirTo" id="dirTo">
                        <%for(mappoint point : currMapPoints){ %>
                        <option value="<%=point.getYAxisLatitude() + "," + point.getXAxisLongitude() %>"><%=point.getTitle() %></option>
                        <% } %>
                        
                    </select>
                        <br>
                <h1>Can't see your property? Fill up the request form below!</h1>
                <form action="makeMapRequest" method="POST" id="addMarkerForm">
                    <input type="hidden" name="action" value="propertyNo" id="propertyCheck">
                    Title:<br>
                    <input type="text" name="maptitle" id="maptitle" value=""><br>
                    Description:<br>
                    <input type="text" name="mapdesc" id="mapdesc" value=""><br>
                    <div>
                    Block Number:<br>
                    <input type="number" name="blocknum" value="" min="1" class="integer" step="1"><br>
                    Lot Number:<br>
                    <input type="number" name="lotnum" value="" min="1" class="integer" step="1"><br>
                    End Lot Number:<br>
                    <input type="number" name="endlotnum" value="" class="integer"><br>
                    Street:<br>
                    <select name="street">
                        <%for(int i = 0;i < allStreets.size();i++){ %>
                        <option value="<%=allStreets.get(i) %>"><%=allStreets.get(i) %></option>
                        <% } %>
                        
                      </select>
                    </div>
                    Category: <br>
                    <select name="mapcategory">
                        <%for(mapCategory cat : currMapCategories){ %>
                        <option value="<%=cat.getMapCatID() %>"><%=cat.getMapCatDescription() %></option>
                        <% } %>
                        
                      </select>
                        <br><br>
                    <button onclick="hey()">Submit</button>
                </form>
                        <br><br>

        </div>

        <script>
        $("#propertyFormControl").click(function(){
            if ($('#propertyFormControl').is(":checked"))
            {
                $("#propertyVals").slideDown();
                $(".propertyVal").attr("disabled", false);
                $("#propertyCheck").val("propertyYes");
                
            }
            else{
                
                $("#propertyVals").slideUp();
                $(".propertyVal").attr("disabled", true);
                $(".propertyVal").val("");
                $("#propertyCheck").val("propertyNo");
            }
            
        });
            
            //lat is y I GUESS
      var uluru;
      var map;
      var infowindow;
      var globalmarker;
      var listener1;
      function initMap() {
        infowindow = new google.maps.InfoWindow({
               content:"388-A , Road no 22, Jubilee Hills, Hyderabad Telangana, INDIA-500033"
            });
        var directionsService = new google.maps.DirectionsService;
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var uluru = {lat: 13.980697, lng: 121.166072};
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 18,
          center: uluru
        });
        //directions code
        directionsDisplay.setMap(map);

        var onChangeHandler = function() {
          calculateAndDisplayRoute(directionsService, directionsDisplay);
        };
        document.getElementById('dirFrom').addEventListener('change', onChangeHandler);
        document.getElementById('dirTo').addEventListener('change', onChangeHandler);
      
        //directions code end        
        
        listener1 = map.addListener('click', function(e) {
          placeMarker(e.latLng, map);
      });
    <%for(mappoint point : currMapPoints){ %>
          var marker = new google.maps.Marker({
              position: {lat: <%=point.getYAxisLatitude() %>, lng: <%=point.getXAxisLongitude() %>},
          map: map,
          title: '<%=point.getTitle() %>'
        });
    <%} %>
      }
      
    function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        directionsService.route({
          origin: document.getElementById('dirFrom').value,
          destination: document.getElementById('dirTo').value,
          travelMode: 'DRIVING'
        }, function(response, status) {
          if (status === 'OK') {
            directionsDisplay.setDirections(response);
          } else {
            window.alert('Directions request failed due to ' + status);
          }
        });
      }
      
      function hey(){
          $("#ylat").val(globalmarker.getPosition().lat());
          $("#xlong").val(globalmarker.getPosition().lng());
          $("#addMarkerForm").submit();
      }
      
      
    //Prevents fields with classes 'integer' from accepting negative values, decimal points, and having 0 as the first digit.
    $(".integer").keypress(function(e) {
    // between 0 and 9
    if (e.which < 48 || e.which > 57) {
        return(false);  // stop processing
    }

    });
    $(".integer").keyup(function(e) {
    var test = $(this).val().substr(0, 1);
    console.log(test);
    if(test == '0'){
        $(this).val("");
    }
    });
      
    </script>

        <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBm0OLysIoUWWdvFnIaZO0I32cBjT5iFwM&callback=initMap">
        </script>

        
    </body>
</html>
