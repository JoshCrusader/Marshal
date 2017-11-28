<%-- 
    Document   : manageGroups
    Created on : Nov 1, 2017, 5:16:42 PM
    Author     : Andrew Santiago
--%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="model.Users,model.UserGroups" %>
<%
Users currUser = null;
currUser = currUser.getUserbyUsername("santiago_ja");
session.setAttribute("loginUser",currUser);
ArrayList<UserGroups> currUserGrps = Users.getAllUserGroupsOfAUser(currUser.getuserID());
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Community Groups - Manage All</title>
        <style>
            * {
            font-size: 100%;
            font-family: Segoe UI;
           }
            body{
                background-color: #F0F0F0;
                
            }
            .allContainer{
                background-color: white;
                width: 50vw;
                margin-left:auto;
                margin-right:auto;
                -webkit-box-shadow: 0px 0px 8px 0px rgba(0,0,0,0.4);
                -moz-box-shadow: 0px 0px 8px 0px rgba(0,0,0,0.4);
                box-shadow: 0px 0px 8px 0px rgba(0,0,0,0.4);
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

        </style>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    </head>
    <body>
        
        <form action="LeaveGroup" method="POST" id="leaveFormZ">
            <div class="form-group">
                    <input type="hidden" class="form-control" name="idToRemove" id="disstrack">
                    
            </div>
        </form>
        
        <form action="inviteToGroup" method="POST" id="inviteControllerForm">
            <div class="form-group">
                <input type="hidden" class="form-control" name="action" value="proceedtoPage">
                <input type="hidden" class="form-control" name="idToInviteTo" id="disstrack2">
            </div>
        </form>
        
        <form action="searchGroup" method="POST" id="searchForm">
                <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search for a group to a join..." name="searchkeyword" id="grpsearch" size="50">
                        <input type="submit" value="Search">
                </div>
        </form>
        
        <div class="allContainer">
            <h1>Community Groups - Manage All (logged in as <%=currUser.getfName() + " " + currUser.getlName() %>)</h1>
              <%for(UserGroups grp : currUserGrps){ %>
                <div class="entityContainer">
                <div class="div-left">
                        <img src="commgroups/groupIcon.jpg" class="grpPic">
                        <div style="position:absolute;margin-left:5rem;">
                            <a class="grpLabel" href="#"><strong><%= grp.getDescription() %></strong></a>
                            <p class="grpDesc"><%= grp.getUserGroupAmountofMembers(grp.getGroupID()) %> member/s</p>
                        </div>
                </div>
                <div class="div-right">
                    <div class="dropdown">
                        <button class="dropbtn"><i class="fa fa-cog" aria-hidden="true"></i></button>
                        <div class="dropdown-content">
                            <a href="#" specID="<%=grp.getGroupID()%>" onclick="leaveGroupFunction(this)">Leave Group</a>
                            <a href="#" specID="<%=grp.getGroupID()%>" onclick="inviteUser(this)">Invite a user</a>
                        </div>
                      </div>
                 </div>
               </div>
            <%} %>
      
        </div>
<script src="jsp/jquery-3.2.1.min.js"></script>        
<script>
function leaveGroupFunction(obj){
    var grpID = obj.getAttribute("specID");
    document.getElementById("disstrack").setAttribute("value",grpID);
    
    if (confirm('Are you sure you want to leave this group?')) {
        document.getElementById("leaveFormZ").submit();
    } else {
        // Do nothing!
    }
    
}

function inviteUser(obj){
    var grpID = obj.getAttribute("specID");
    document.getElementById("disstrack2").setAttribute("value",grpID);
    
    document.getElementById("inviteControllerForm").submit();
    
}


</script>        
        
        
    </body>
</html>
