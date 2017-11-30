<%-- 
    Document   : policyManagement
    Created on : Nov 22, 2017, 9:29:42 PM
    Author     : Andrew Santiago
--%>

<%@page import="dao.PolicyDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="model.Users,model.Policy,model.Penalty, model.Document,model.BoardMember,model.Ref_PenaltyLevel" %>
<%
Users currUser = null;
currUser = PolicyDAO.getUserbyUsername("yutakun");
session.setAttribute("loginUser",currUser);
ArrayList<Penalty> allPenalties = PolicyDAO.getAllPenalties();
ArrayList<Document> allDocuments = PolicyDAO.getAllDocumentsFromFolder(1002);
ArrayList<Ref_PenaltyLevel> allLevels = PolicyDAO.getAllPenaltyLevels();
String msg = (String) request.getAttribute("msg");
%>
<html>
    <head>
         <link rel="stylesheet" type="text/css" href="css_standard.css">
	 <link rel="stylesheet" type="text/css" href="test.css">
	 <script src="jquery.js"></script>
	 <script src="css_scripts.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Penalty Management</title>
        <style>
            * {
            font-size: 100%;
            font-family: Segoe UI;
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
           
            .dropdown {
                display: inline;
                float:right;
            }
            
            .dropbtn {
                background-color: #4CAF50;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {background-color: #f1f1f1}

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropdown:hover .dropbtn {
                background-color: #3e8e41;
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
            
            #searchForm{
                text-align:center;
                margin-top:3rem;
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
        <%@ include file="navbar_policy.jsp" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    </head>
    <body>
        <div id="searchForm">
            <form action="PolicyController" method="GET">
                <input type="hidden" name="filterType" value="penalty">
                    <div class="form-group">
                            <input type="hidden" name="action" value="search">
                            <input type="text" class="form-control" placeholder="Search a penalty..." name="searchkeyword" id="grpsearch" size="50">
                            <input type="submit" value="Search">
                    </div>
            </form>
            Filters<br>
            Fee: <select onchange="location = this.value;">
                <option disabled selected>Please choose...</option>
                <option value="PolicyController?action=feeHigh&filterType=penalty">Highest to Lowest</option>
                <option value="PolicyController?action=feeLow&filterType=penalty">Lowest to Highest</option>
                </select>
            
            Level: <select onchange="location = this.value;">
                <option disabled selected>Please choose...</option>
                <option value="PolicyController?action=levelHigh&filterType=penalty">Highest to Lowest</option>
                <option value="PolicyController?action=levelLow&filterType=penalty">Lowest to Highest</option>
            </select><br><br>
            
            <a href="PolicyController?action=ALL&filterType=penalty"><button type="button"> View All Penalties </button></a>
            
            
            <br><br>
            <button type="button" onclick="openAddModal()"> Add Penalty </button>
            
            
        </div>
        
        <div class="allContainer">
            <% if (msg != null) {%>
                <%= msg%>
            <% }%>
            <h1>Penalty Management Module </h1>
              <%for(Penalty penalty : allPenalties){ %>
                <div class="entityContainer">
                <div class="div-left">
                        <img src="policyIcon.png" class="grpPic">
                        <div style="position:absolute;margin-left:5rem;">
                            <strong><%= penalty.getDescription() %></strong>
                            <p class="grpDesc">Level: <%=penalty.getPenaltyLevel() %> Fee: PHP <%= penalty.getFee() %></p>
                        </div>
                </div>
                <div class="div-right">
                    <div class="dropdown">
                        <button class="dropbtn"><i class="fa fa-cog" aria-hidden="true"></i></button>
                        <div class="dropdown-content">
                            <a href="#" specID="<%=penalty.getPenaltyID() %>" specPenaltyLevel="<%=penalty.getPenaltyLevel() %>" specPenaltyDesc="<%=penalty.getDescription() %>" specPenaltyFee="<%=penalty.getFee() %>"
                               specPenaltyAction="<%=penalty.getPenaltyAction() %>" specEnablingDocID="<%=penalty.getEnablingDocumentID() %>" specEnablingDoc="<%=PolicyDAO.getDocumentbyID(penalty.getEnablingDocumentID()).getDocumentLocation() %>" specEnablingDocDesc="<%=PolicyDAO.getDocumentbyID(penalty.getEnablingDocumentID()).getDescription() %>"  onclick="opModal(this)">Edit Penalty</a>
                        </div>
                      </div>
                 </div>
               </div>
            <%} %>
      
        </div>
            
        <!-- The Modal for editing -->
        <div id="myModal" class="modal">

          <!-- Modal content -->
          <div class="modal-content">
            <div class="modal-header">
              <span class="close">&times;</span>
              <h2 id="modalTitle">Edit Penalty</h2>
            </div>
            <div class="modal-body">
                <form action="PolicyController" method="POST">
                    <input type="hidden" name="action" value="editPenalty">
                    <input type="hidden" name="penaltyID" id="penaltyID" value="" readonly>
                    <input type="hidden" name="enablingDocID" value="">
                    <br>
                    <b>Description: </b><input type="text" name="description" id="description" value=""><br><br>
                    <b>Level:</b>
                    <select name="penaltylevel" id="penaltylevel">
                        <%for(Ref_PenaltyLevel level : allLevels){ %>
                        <option value="<%=level.getLevel() %>"><%=level.getLevel() %> - <%=level.getDesc() %></option>
                        <% } %>
                        
                    </select><br><br>
                    <b>Fee: </b><input type="number" name="penaltyFee" id="penaltyFee" step="0.25" value="0.00" min="0"><br><br>
                    <b>Action: </b><input type="text" name="penaltyAction" id="penaltyAction" value=""><br><br>
                    <b>Enabling Document file: </b><input type="text" name="enablingDoc" value="" id="enablingDoc" size="40" readonly><br><br>
                    <b>New Enabling Document: </b><input id="filereplacer" type="file" accept=".doc, .docx, .pdf"><br><br>
                    <b>Enabling Document Description: </b><input type="text" name="enablingDocDesc" value="" id="enablingDocDesc"><br><br>
                    <br><br>
                    <input type="submit">
                    <br>
                </form>
                    <br><br>
            
            </div>
            <div class="modal-footer">
              <h3>Marshal Information System, (c) 2017.</h3>
            </div>
          </div>

        </div>
                    
                    
        <!-- The Modal for Adding Penalty -->
        <div id="addModal" class="modal">

          <!-- Modal content -->
          <div class="modal-content">
            <div class="modal-header">
              <span class="close">&times;</span>
              <h2 id="modalTitle">Add Penalty</h2>
            </div>
            <div class="modal-body">
                <form action="PolicyController" method="POST">
                    <input type="hidden" name="action" value="addPenalty">
                    <br>
                    <b>Description: </b><input type="text" name="description" value=""><br><br>
                    <b>Level:</b>
                    <select name="penaltylevel">
                        <%for(Ref_PenaltyLevel level : allLevels){ %>
                        <option value="<%=level.getLevel() %>"><%=level.getLevel() %> - <%=level.getDesc() %></option>
                        <% } %>
                        
                    </select><br><br>
                    <b>Fee: </b><input type="number" name="penaltyFee" step="0.25" value="0.00" min="0"><br><br>
                    <b>Action: </b><input type="text" name="penaltyAction" value=""><br><br>
                    <b>Enabling Document: </b><input name="enablingDoc" type="file" accept=".doc, .docx, .pdf"><br><br>
                    <b>Enabling Document Description: </b><input type="text" name="enablingDocDesc" value=""><br><br>
                    <input type="submit">
                    <br>
                </form>
                    <br><br>
            
            </div>
            <div class="modal-footer">
              <h3>Marshal Information System, (c) 2017.</h3>
            </div>
          </div>

        </div>
        
        
<!-- SCRIPT FOR EDIT MODAL -->
<script>

// Get the modal
var modal = document.getElementById('myModal');

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal and auto-bind the values from the source policy object to the input fields
function opModal(obj){
    modal.style.display = "block";
    $("#modalTitle").html("Edit Penalty # " + obj.getAttribute("specID"));
    $("#penaltyID").val(obj.getAttribute("specID"));
    $("#description").val(obj.getAttribute("specPenaltyDesc"));
    $("#penaltylevel").val(obj.getAttribute("specPenaltyLevel"));
    $("#penaltyFee").val(obj.getAttribute("specPenaltyFee"));
    $("#penaltyAction").val(obj.getAttribute("specPenaltyAction"));
    $("#enablingDocID").val(obj.getAttribute("specEnablingDocID"));
    $("#enablingDocDesc").val(obj.getAttribute("specEnablingDocDesc"));
    $("#enablingDoc").val(obj.getAttribute("specEnablingDoc"));
    
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

<!-- SCRIPT FOR ADD MODAL -->
<script>

// Get the modal
var addmodal = document.getElementById('addModal');

// Get the <span> element that closes the modal
var addspan = document.getElementsByClassName("close")[1];

// When the user clicks the button, open the modal and auto-bind the values from the source policy object to the input fields
function openAddModal(){
    addmodal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
addspan.onclick = function() {
    addmodal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == addmodal) {
        addmodal.style.display = "none";
    }
}
</script>
            
<script>
$("#filereplacer").change(function() {
    var filename = $('#filereplacer').val().replace(/C:\\fakepath\\/i, '')
        $("#enablingDoc").val(filename);
    });      
    
function removePolicy(obj){
    var polID = obj.getAttribute("specID");
    document.getElementById("disstrack").setAttribute("value",polID);
    
    if (confirm('Are you sure you want to dismiss this policy?')) {
        document.getElementById("removeForm").submit();
    } else {
        // Do nothing!
    }
}


</script>        
        
        
    </body>
</html>
