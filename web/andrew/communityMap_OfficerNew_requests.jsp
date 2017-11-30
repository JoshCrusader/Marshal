<%-- 
    Document   : communityMap
    Created on : Nov 5, 2017, 12:18:55 AM
    Author     : Andrew Santiago
--%>

<%@page import="dao.mappointDAO"%>
<%@page import="model.mapCategory"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Users,model.mappoint, model.Ref_Properties, model.PendingRequest_Map" %>
<!DOCTYPE html>
<%
Users currUser = null;
currUser = mappointDAO.getUserbyUsername("yutakun");
session.setAttribute("loginUser",currUser);
ArrayList<mappoint> currMapPoints = mappointDAO.getAllPoints();
ArrayList<mapCategory> currMapCategories = mappointDAO.getAllCategories();
ArrayList<String> allStreets = mappointDAO.getAllStreets();
ArrayList<PendingRequest_Map> allReqs = mappointDAO.getAllPendingMapPointRequests();
String msg = (String) request.getAttribute("msg");
%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css_standard.css">
	 <link rel="stylesheet" type="text/css" href="test.css">
	 <script src="jquery.js"></script>
	 <script src="css_scripts.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Community Maps - Requests</title>
        
        <style>
            * {
            font-size: 100%;
            font-family: Segoe UI;
           }
           a{
               color:white;
           }
            #map {
                width: 100%;
                height: 400px;
                background-color: grey;
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
            }
            .groupEntity{
                border-width:2px;
                border-style:solid;
                border-color:#74b7c1;
                max-width:25vw;
                padding:0.5rem;
            }
            .grpPic{
                max-width:4rem;
                border-radius: 50%;
                float:left;
            }
            .grpLabel{
                display:inline;
            }
            
            .grpDesc{
                line-height: 0%;
            }
           
            .dropbtn {
                background-color: #4CAF50;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }
            
            .div-left{
                float:left;
                padding-left:10px;
                width: 25vw;
            }
            .div-right{
                float:right;
                padding-right:10px;
            }
            
            .entityContainer{
                height:4.5rem;
                max-width:50vw;
                padding-top:5px;
            }
            
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1500; /* Sit on top */
                padding-top: 20px; /* Location of the box */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow-y: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            }

            /* Modal Content */
            .modal-content {
                position: relative;
                background-color: white;
                color:black;
                margin: auto;
                padding: 0;
                border: 1px solid #888;
                width: 80%;
                box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
                -webkit-animation-name: animatetop;
                -webkit-animation-duration: 0.4s;
                animation-name: animatetop;
                animation-duration: 0.4s;
            }

            /* Add Animation */
            @-webkit-keyframes animatetop {
                from {top:-300px; opacity:0} 
                to {top:0; opacity:1}
            }

            @keyframes animatetop {
                from {top:-300px; opacity:0}
                to {top:0; opacity:1}
            }

            /* The Close Button */
            .close {
                color: white;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
                cursor: pointer;
            }

            .modal-header {
                padding: 2px 16px;
                background-color: #5cb85c;
                color: white;
            }

            .modal-body {padding: 2px 16px;}

            .modal-footer {
                padding: 2px 16px;
                background-color: #5cb85c;
                color: white;
            }
            
            .inputReadOnly{
                border:0;
            }
            
        </style>
    <script src="jsp/jquery-3.2.1.min.js"></script> 
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    </head>
    
    <body>
        <%@ include file="navbar_officer_mapping.jsp" %>
        <h3>Community Maps</h3>
        
        
        <div class="allContainer">
        <% if (msg != null) {%>
            <%= msg%>
        <% }%>
            <h1>All Requests</h1>
            <%for(PendingRequest_Map req : allReqs){ %>
            <div class="entityContainer">
            <div class="div-left">
                    <img src="mapIcon.png" class="grpPic">
                    <div style="position:absolute;margin-left:5rem;">
                       <strong><%= req.getTitle() %></strong>
                        <p class="grpDesc">Requested by: <%=req.getUserID() %></p>
                    </div>
            </div>
            <div class="div-right">
                <div class="dropdown">
                    <button class="dropbtn" specID="<%=req.getRequestID() %>" specBlock="<%=req.getBlocknum() %>" specLotnum="<%=req.getLotnum() %>" 
                    specEndLotnum="<%=req.getEndLotnum() %>" specStreet="<%=req.getStreet() %>" specTitle="<%=req.getTitle() %>" specDesc="<%=req.getDescription() %>"
                    specUserID="<%=req.getUserID() %>" specCategory="<%=req.getCategory() %>" id="myBtn" onclick="opModal(this)"><i class="fa fa-cog" aria-hidden="true"></i></button>
                  </div>
             </div>
           </div>
        <%} %>


        </div>
            
        <!-- The Modal -->
        <div id="myModal" class="modal">

          <!-- Modal content -->
          <div class="modal-content">
            <div class="modal-header">
              <span class="close">&times;</span>
              <h2 id="modalTitle">Request</h2>
            </div>
            <div class="modal-body">
                <form action="mappointController" method="POST" id="addMarkerForm">
                    <input type="hidden" name="action" value="mappointApproval">
                    <input type="hidden" name="approveAction" value="approve" readonly>
                    <input type="hidden" name="reqID" id="reqID" value="" readonly>
                    <b>Requested By: </b><input class="inputReadOnly" type="text" name="userID" id="userID" value="" readonly><br><br>
                    <b>Block Number: </b><input type="number" name="blocknum" id="blocknum" class="integer" min="1" value=""><br><br>
                    <b>Lot Number: </b><input type="number" name="lotnum" id="lotnum" class="integer" min="1" value=""><br><br>
                    <b>End Lot Number: </b><input type="number" name="endlotnum" id="endlotnum" class="integer" min="1" value=""><br><br>
                    <b>Title: </b><input type="text" name="title" id="title" value=""><br><br>
                    <b>Description: </b><input type="text" name="description" id="description" value=""><br><br>
                    <b>Street:</b>
                    <select name="street" id="street" class="propertyVal">
                        <%for(int i = 0;i < allStreets.size();i++){ %>
                        <option value="<%=allStreets.get(i) %>"><%=allStreets.get(i) %></option>
                        <% } %>
                        
                    </select><br><br>
                    <b>Category:</b>
                    <select name="category" id="category">
                        <%for(mapCategory cat : currMapCategories){ %>
                        <option value="<%=cat.getMapCatID() %>"><%=cat.getMapCatDescription() %></option>
                        <% } %>
                        
                      </select>
                        <br><br>
                    <input type="hidden" class="form-control" name="xlong" id="xlong">
                    <input type="hidden" class="form-control" name="ylat" id="ylat">
                    <h1>Add the point to this request below</h1>
                    <div id="map"></div><br>
                    <center><button type="button" onclick="hey()" style="font-size:30px;display:inline;">Approve</button></center><br>
                </form>
                        
                <form action="mappointController" method="POST">
                    <input type="hidden" name="action" value="mappointApproval">
                    <input type="hidden" name="dreqID" id="dreqID" value="" readonly>
                    <input type="hidden" name="approveAction" value="disapprove" readonly>
                    <center><input type="submit" value="Disapprove" style="font-size:30px;"></center>
                </form><br>
            
            </div>
            <div class="modal-footer">
              <h3>Marshal Information System, (c) 2017.</h3>
            </div>
          </div>

        </div>
        
        <!-- SCRIPT FOR MODAL -->
        <script>
            
        // Get the modal
        var modal = document.getElementById('myModal');

        // Get the button that opens the modal
        var btn = document.getElementById("myBtn");

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks the button, open the modal 
        function opModal(obj){
            modal.style.display = "block";
            initMap();
            $("#modalTitle").html("Request Ticket # " + obj.getAttribute("specID"));
            $("#reqID").val(obj.getAttribute("specID"));
            $("#dreqID").val(obj.getAttribute("specID"));
            $("#userID").val(obj.getAttribute("specUserID"));
            $("#blocknum").val(obj.getAttribute("specBlock"));
            $("#lotnum").val(obj.getAttribute("specLotnum"));
            $("#endlotnum").val(obj.getAttribute("specEndLotnum"));
            $("#street").val(obj.getAttribute("specStreet"));
            $("#title").val(obj.getAttribute("specTitle"));
            $("#description").val(obj.getAttribute("specDesc"));
            $("#category").val(obj.getAttribute("specCategory"));
        }

        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
        
        
        
        </script>
        
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
      var uluru;
      var map;
      var infowindow;
      var globalmarker;
      var listener1;
      var limiter = 0;
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
        
        listener1 = map.addListener('click', function(e) {
          placeMarker(e.latLng, map);
      });
    <%for(mappoint point : currMapPoints){ %>
          var marker = new google.maps.Marker({
              position: {lat: <%=point.getYAxisLatitude() %>, lng: <%=point.getXAxisLongitude() %>},
          map: map,
          title: '<%=point.getTitle() %>'
        });
     marker.addListener('click', function(e) {
         infowindow.setContent("<b>Title: </b> <%=point.getTitle() %> <br><b>Description: </b> <%=point.getDescription() %> <br> <b>Category: </b> <%=mappointDAO.getCategorybyID(point.getMapCategoryID()).getMapCatDescription() %>");
          infowindow.open(map,this);
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

      function placeMarker(position, map) {
          if (limiter < 1) {
            globalmarker = new google.maps.Marker({
                position: position,
                draggable: true,
                animation: google.maps.Animation.DROP,
                map: map
            });
            //alert("lat: " + globalmarker.getPosition().lat() + " long: " + globalmarker.getPosition().lng());
            limiter += 1;
            globalmarker.addListener('click', function(e) {
            infowindow.open(map,this);
              });
          }
          else{
              alert("You can only add 1 point in editing. You can drag around the point you added.");
          }
          
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
