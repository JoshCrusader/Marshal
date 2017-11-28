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
currUser = UserDAO.getUserbyUsername("yutakun");
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
        </style>
    <script src="jsp/jquery-3.2.1.min.js"></script> 
    
    </head>
    
    <body>
        <%@ include file="navbar_officer_mapping.jsp" %>
        <h3>Community Maps</h3>
        <div style="padding-left:0.1rem;padding-top: 0.1rem;"><button onclick="editMode()">Edit mode</button>&nbsp;<button onclick="exiteditMode()">Exit edit mode</button></div>
        <div id="map"></div>
        
        <div class="allContainer">
        <% if (msg != null) {%>
            <%= msg%>
        <% }%>
        
        <form action="removeMappoint" method="POST" id="removePointForm">
            <input type="hidden" name="idToReomve" id="mapIDbind">
        </form>
        
        <h1>Add a point</h1>
        <input type="checkbox" id="propertyFormControl" onclick="manipulateForm(this)">This is a property map point<br><br>
        <form action="addMapPoint" method="POST" id="addMarkerForm">
            <input type="hidden" name="action" value="propertyNo" id="propertyCheck">
            Title:<br>
            <input type="text" name="maptitle" id="maptitle" value=""><br>
            Description:<br>
            <input type="text" name="mapdesc" id="mapdesc" value=""><br>
            <div id="propertyVals" style="display:none;">
            Block Number:<br>
            <input type="number" name="blocknum" class="propertyVal integer" value="" min="1" disabled><br>
            Lot Number:<br>
            <input type="number" name="lotnum" class="propertyVal integer" value="" min="1" disabled><br>
            End Lot Number:<br>
            <input type="number" name="endlotnum" class="propertyVal integer" value="" min="1" disabled><br>
            Street:<br>
            <select name="street" class="propertyVal" disabled>
                <%for(int i = 0;i < allStreets.size();i++){ %>
                <option value="<%=allStreets.get(i) %>"><%=allStreets.get(i) %></option>
                <% } %>

              </select>
            </div>
            Category: <br>
            <select name="mapcategory" id="idmapcat1">
                <%for(mapCategory cat : currMapCategories){ %>
                    <% if (cat.getMapCatDescription().equals("House Property")){ %>
                        <option value="<%=cat.getMapCatID() %>" class="enabledOption"><%=cat.getMapCatDescription() %></option>
                    <%} else{ %>
                        <option value="<%=cat.getMapCatID() %>" class="disabledOption"><%=cat.getMapCatDescription() %></option>
                    <% } %>
                <% } %>

              </select>
                <br><br>
            <input type="hidden" class="form-control" name="xlong" id="xlong">
            <input type="hidden" class="form-control" name="ylat" id="ylat">
            <!--<input type="submit" value="Submit">-->
            <button onclick="hey()">Submit</button>
        </form>

                <br> <h5>Get Directions:</h5>
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
                <br><br>

        </div>
                
        <!-- The Modal for Editing Property Map Points -->
        <div id="myModal" class="modal">

          <!-- Modal content -->
          <div class="modal-content">
            <div class="modal-header">
              <span class="close">&times;</span>
              <h2 id="modalTitle">Request</h2>
            </div>
            <div class="modal-body">
                <form action="editMapPoint" method="POST">
                    <input type="hidden" name="action" value="propertyYes">
                    <input type="hidden" name="mapID" id="mapID" value="">
                    Title:<br>
                    <input type="text" name="mapTitle" id="mapTitle"  value=""><br>
                    Description:<br>
                    <input type="text" name="mapDesc" id="mapDesc" value=""><br>
                    Block Number:<br>
                    <input type="number" name="blockNum" id="blockNum" class="integer" value="" min="1"><br>
                    Lot Number:<br>
                    <input type="number" name="lotNum" id="lotNum" class="integer" value="" min="1"><br>
                    End Lot Number:<br>
                    <input type="number" name="endLotnum" id="endLotnum" class="integer" value="" min="1"><br>
                    Street:<br>
                    <select name="stReet" id="stReet">
                        <%for(int i = 0;i < allStreets.size();i++){ %>
                        <option value="<%=allStreets.get(i) %>"><%=allStreets.get(i) %></option>
                        <% } %>

                    </select><br>
                    Category: <br>
                    <select name="mapcategory">
                        <%for(mapCategory cat : currMapCategories){ %>
                        <% if (cat.getMapCatDescription().equals("House Property")){ %>
                        <option value="<%=cat.getMapCatID() %>"><%=cat.getMapCatDescription() %></option>
                    <%} else{ %>
                        <option value="<%=cat.getMapCatID() %>" disabled><%=cat.getMapCatDescription() %></option>
                    <% } %>
                        <% } %>

                      </select>
                        <br><br>
                    <!--<input type="submit" value="Submit">-->
                    <input type="submit" value="Submit">
                </form>
                    <br><br>
            
            </div>
            <div class="modal-footer">
              <h3>Marshal Information System, (c) 2017.</h3>
            </div>
          </div>

        </div>
                        
        <!-- The Modal for Editing Property Map Points -->
        <div id="editModalN" class="modal">

          <!-- Modal content -->
          <div class="modal-content">
            <div class="modal-header">
              <span class="close">&times;</span>
              <h2 id="modalTitle2">Request</h2>
            </div>
            <div class="modal-body">
                <form action="editMapPoint" method="POST">
                    <input type="hidden" name="action" value="propertyNo">
                    <input type="hidden" name="mapID" id="mapID2" value="">
                    Title:<br>
                    <input type="text" name="mapTitle" id="mapTitle2"  value=""><br>
                    Description:<br>
                    <input type="text" name="mapDesc" id="mapDesc2" value=""><br>
                    Category: <br>
                    <select name="mapcategory" id="mapcategory2">
                        <%for(mapCategory cat : currMapCategories){ %>
                            <% if (cat.getMapCatDescription().equals("House Property")){ %>
                            <option value="<%=cat.getMapCatID() %>" disabled><%=cat.getMapCatDescription() %></option>
                            <%} else{ %>
                                <option value="<%=cat.getMapCatID() %>"><%=cat.getMapCatDescription() %></option>
                            <% } %>
                        <% } %>

                      </select>
                        <br><br>
                    <input type="submit" value="Submit">
                </form>
                    <br><br>
            
            </div>
            <div class="modal-footer">
              <h3>Marshal Information System, (c) 2017.</h3>
            </div>
          </div>

        </div>
                        
        <!-- SCRIPT FOR EDIT POINT-PROPERTY MODAL -->
        <script>

        // Get the modal
        var modal = document.getElementById('myModal');

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks the button, open the modal and auto-bind the values from the source policy object to the input fields
        function opEdit(obj){
            modal.style.display = "block";
            $("#modalTitle").html("Edit Map Point ID # " + obj.getAttribute("specID"));
            $("#mapID").val(obj.getAttribute("specID"));
            $("#mapTitle").val(obj.getAttribute("specTitle"));
            $("#mapDesc").val(obj.getAttribute("specDesc"));
            $("#mapcategory").val(obj.getAttribute("specCatID"));
            $("#blockNum").val(obj.getAttribute("specBlocknum"));
            $("#lotNum").val(obj.getAttribute("specLotnum"));
            $("#endLotnum").val(obj.getAttribute("specEndlotnum"));
            $("#stReet").val(obj.getAttribute("specStreet"));

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
        
        <!-- SCRIPT FOR EDITNON-PROPERTY-MAPPOINT MODAL -->
        <script>

        // Get the modal
        var modal2 = document.getElementById('editModalN');

        // Get the <span> element that closes the modal
        var span2 = document.getElementsByClassName("close")[1];

        // When the user clicks the button, open the modal and auto-bind the values from the source policy object to the input fields
        function opModalN(obj){
            modal2.style.display = "block";
            $("#modalTitle2").html("Edit Map Point ID # " + obj.getAttribute("specID"));
            $("#mapID2").val(obj.getAttribute("specID"));
            $("#mapTitle2").val(obj.getAttribute("specTitle"));
            $("#mapDesc2").val(obj.getAttribute("specDesc"));
            $("#mapcategory2").val(obj.getAttribute("specCatID"));
        }

        // When the user clicks on <span> (x), close the modal
        span2.onclick = function() {
            modal2.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal2) {
                modal2.style.display = "none";
            }
        }
        </script>

        <script>
        function manipulateForm(obj){
            if(obj.checked == true){
                document.getElementById("idmapcat1").selectedIndex = "0";
                $(".disabledOption").attr('disabled','disabled');
                $(".enabledOption").removeAttr('disabled');
            }
            else{
                document.getElementById("idmapcat1").selectedIndex = "1";
                $(".disabledOption").removeAttr('disabled');
                $(".enabledOption").attr('disabled','disabled');
            }
            
            
        }
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
     marker.addListener('click', function(e) {
         <% if(point.getMapCategoryID() == 1){ %>
                 infowindow.setContent
                         (
                         "<div style='color:black;'><b>Title: </b> <%=point.getTitle() %> <br>\n\
                            <b>Description: </b> <%=point.getDescription() %> <br>\n\
                            <b>Category: </b> <%=mappointDAO.getCategorybyID(point.getMapCategoryID()).getMapCatDescription() %> </div><br>\n\
                            <button type='button' onclick='opEdit(this)' specID='<%=point.getMapID()%>' specTitle='<%=point.getTitle() %>' specDesc='<%=point.getDescription() %>' specCatID='<%=point.getMapCategoryID() %>' specBlocknum='<%=point.getPropertyObject().getBlockNum() %>' specLotnum='<%=point.getPropertyObject().getLotNum() %>' specEndlotnum='<%=point.getPropertyObject().getEndLotNum() %>' specStreet='<%=point.getPropertyObject().getStreet() %>'> Edit </button>\n\
                         "
                         );
                 <% } else{ %>
                 infowindow.setContent
                         (
                         "<div style='color:black;'><b>Title: </b> <%=point.getTitle() %> <br>\n\
                            <b>Description: </b> <%=point.getDescription() %> <br>\n\
                            <b>Category: </b> <%=mappointDAO.getCategorybyID(point.getMapCategoryID()).getMapCatDescription() %> </div><br>\n\
                            <button type='button' onclick='deleteThis(this)' specID='<%=point.getMapID()%>'> Delete </button> \n\
                            <button type='button' onclick='opModalN(this)' specID='<%=point.getMapID()%>' specTitle='<%=point.getTitle() %>' specDesc='<%=point.getDescription() %>' specCatID='<%=point.getMapCategoryID() %>'> Edit </button>\n\
                         "
                         );
                 <% } %>
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
      
      function exiteditMode(){
          google.maps.event.removeListener(listener1);
      }
      
      function editMode(){
          initMap();
      }
      
      function deleteThis(obj){
          var mapID = obj.getAttribute("specID");
          $("#mapIDbind").val(mapID);
          $("#removePointForm").submit();
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
