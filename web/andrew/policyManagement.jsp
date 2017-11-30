<%-- 
    Document   : policyManagement
    Created on : Nov 22, 2017, 9:29:42 PM
    Author     : Andrew Santiago
--%>

<%@page import="dao.PolicyDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="model.Users,model.Policy,model.Penalty, model.Document,model.BoardMember" %>
<%
Users currUser = null;
currUser = PolicyDAO.getUserbyUsername("yutakun");
session.setAttribute("loginUser",currUser);
ArrayList<Policy> allPolicies = PolicyDAO.getAllPolicies();
ArrayList<Penalty> allPenalties = PolicyDAO.getAllPenalties();
ArrayList<Document> allDocuments = PolicyDAO.getAllDocumentsFromFolder(1001);
ArrayList<BoardMember> allBoardMembers = PolicyDAO.getAllActiveBoardMembers();
String msg = (String) request.getAttribute("msg");
%>
<html>
    <head>
         <link rel="stylesheet" type="text/css" href="css_standard.css">
	 <link rel="stylesheet" type="text/css" href="test.css">
	 <script src="jquery.js"></script>
	 <script src="css_scripts.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Policy Management</title>
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
        
        <form action="PolicyController" method="POST" id="removeForm">
            <input type="hidden" name="action" value="removePolicy">
            <div class="form-group">
                    <input type="hidden" class="form-control" name="idToRemove" id="disstrack">
            </div>
        </form>
        
        <div id="searchForm">
            <form action="PolicyController" method="GET">
                <input type="hidden" name="filterType" value="policy">
                    <div class="form-group">
                            <input type="hidden" name="action" value="search">
                            <input type="text" class="form-control" placeholder="Search a policy..." name="searchkeyword" id="grpsearch" size="50">
                            <input type="submit" value="Search">
                    </div>
            </form>
            <br>
            <a href="PolicyController?action=ALL&filterType=policy"><button type="button"> View All Policies </button></a>
            <a href="PolicyController?action=activeOnly&filterType=policy"><button type="button" > View Active Policies </button></a>
            <a href="PolicyController?action=inactiveOnly%filterType=policy"><button type="button" > View Inactive Policies </button></a><br><br>
            <button type="button" onclick="openAddModal()"> Add Policy </button>
            
            
        </div>
        
        <div class="allContainer">
            <% if (msg != null) {%>
                <%= msg%>
            <% }%>
            <h1>Policy Management Module </h1>
              <%for(Policy policy : allPolicies){ %>
                <div class="entityContainer">
                <div class="div-left">
                        <img src="policyIcon.png" class="grpPic">
                        <div style="position:absolute;margin-left:5rem;">
                            <strong><%= policy.getPolicyDescription()%></strong>
                            <p class="grpDesc">Active until <%= policy.getStopImplementDate() %></p>
                        </div>
                </div>
                <div class="div-right">
                    <div class="dropdown">
                        <button class="dropbtn"><i class="fa fa-cog" aria-hidden="true"></i></button>
                        <div class="dropdown-content">
                            <a href="#" specID="<%=policy.getPolicyID() %>" specDesc="<%= policy.getPolicyDescription() %>" specPenaltyID="<%=policy.getPenaltyID() %>" specSuppDocID="<%=policy.getSupportingDocumentID() %>"
                               specEnactDate="<%= policy.getEnactmentDate() %>" specSuppDocID="<%=policy.getSupportingDocumentID() %>" specStopDate="<%= policy.getStopImplementDate() %>" specEnablingBoard="<%= policy.getEnablingBoard() %>" specSuppDoc="<%=PolicyDAO.getDocumentbyID(policy.getSupportingDocumentID()).getDocumentLocation() %>" specSuppDocDesc="<%=PolicyDAO.getDocumentbyID(policy.getSupportingDocumentID()).getDescription() %>" onclick="opModal(this)">Edit Policy</a>
                            <a href="#" specID="<%=policy.getPolicyID()%>" onclick="removePolicy(this)">Dismiss/Remove Policy</a>
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
              <h2 id="modalTitle">Request</h2>
            </div>
            <div class="modal-body">
                <form action="PolicyController" method="POST">
                    <input type="hidden" name="action" value="editPolicy">
                    <input type="hidden" name="policyID" id="policyID" value="" readonly>
                    <input type="hidden" name="suppDocID" id="suppDocID" value="">
                    <br>
                    <b>Description: </b><input type="text" name="description" id="description" value=""><br><br>
                    <b>Penalty:</b>
                    <select name="penalty" id="penalty">
                        <%for(Penalty penalty : allPenalties){ %>
                        <option value="<%=penalty.getPenaltyID()%>"><%=penalty.getDescription() %></option>
                        <% } %>
                        
                    </select><br><br>
                    <b>Supporting Document file: </b><input type="text" name="suppDoc" value="" id="suppDoc" size="40" readonly><br><br>
                    <b>New Supporting Document: </b><input id="filereplacer" type="file" accept=".doc, .docx, .pdf"><br><br>
                    <b>Supporting Document Description: </b><input type="text" name="suppDocDesc" value="" id="suppDocDesc"><br><br>
                    
                    <b>Enactment Date: </b><input type="date" name="enactDate" id="enactDate" value=""><br><br>
                    <b>Expiration Date: </b><input type="date" name="stopDate" id="stopDate" value=""><br><br>
                    <b>Enabled by: </b>
                    <input list="boardmembers" name="enablingBoard" id="enablingboard">
                    <datalist id="boardmembers">
                      <%for(BoardMember bm : allBoardMembers){ %>
                        <option value="<%=bm.getUserID() %>" ><%= bm.getfName() %> <%= bm.getlName() %>  </option>
                        <% } %>
                    </datalist>
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
                    
                    
        <!-- The Modal for Adding Policy -->
        <div id="addModal" class="modal">

          <!-- Modal content -->
          <div class="modal-content">
            <div class="modal-header">
              <span class="close">&times;</span>
              <h2>Add New Policy</h2>
            </div>
            <div class="modal-body">
                <form action="PolicyController" method="POST" id="addPolicyForm">
                    <input type="hidden" name="action" value="addPolicy">
                    <input type="hidden" name="policyID" id="policyID" value="" readonly>
                    <br>
                    <b>Description: </b><input type="text" name="description" value=""><br><br>
                    <b>Penalty:</b>
                    <select name="penalty">
                        <%for(Penalty penalty : allPenalties){ %>
                        <option value="<%=penalty.getPenaltyID()%>"><%=penalty.getDescription() %></option>
                        <% } %>
                        
                    </select><br><br>
                    <b>Supporting Document: </b><input name="suppDoc" type="file" accept=".doc, .docx, .pdf">
                    <b>Supporting Document Description: </b><input type="text" name="suppDocDesc" value=""><br><br>
                    <b>Enactment Date: </b><input type="date" name="enactDate" value=""><br><br>
                    <b>Expiration Date: </b><input type="date" name="stopDate" value=""><br><br>
                    <b>Enabled by: </b>
                    <input list="boardmembers" name="enablingBoard">
                    <datalist id="boardmembers">
                      <%for(BoardMember bm : allBoardMembers){ %>
                        <option value="<%=bm.getUserID() %>" ><%= bm.getfName() %> <%= bm.getlName() %>  </option>
                        <% } %>
                    </datalist>
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
    $("#modalTitle").html("Edit Policy # " + obj.getAttribute("specID"));
    $("#policyID").val(obj.getAttribute("specID"));
    $("#description").val(obj.getAttribute("specdesc"));
    $("#penalty").val(obj.getAttribute("specPenaltyID"));
    $("#suppDocID").val(obj.getAttribute("specSuppDocID"));
    $("#enactDate").val(obj.getAttribute("specEnactDate"));
    $("#stopDate").val(obj.getAttribute("specstopdate"));
    $("#enablingboard").val(obj.getAttribute("specEnablingBoard"));
    $("#suppDocDesc").val(obj.getAttribute("specSuppDocDesc"));
    $("#suppDoc").val(obj.getAttribute("specSuppDoc"));
    
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
        $("#suppDoc").val(filename);
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
