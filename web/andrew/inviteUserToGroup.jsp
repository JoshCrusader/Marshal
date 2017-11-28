<%-- 
    Document   : inviteUserToGroup
    Created on : Nov 16, 2017, 3:15:12 PM
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
int currGroup = (Integer) session.getAttribute("inviteGroupChoose");
ArrayList<Users> usersNotInGrp = UserGroups.getAllUsersNotInGroup(currGroup);
ArrayList<Users> suggestedUsers = UserGroups.getAllUsersNotInGroupRandom(currGroup);
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
                margin-top:10%;
                padding: 1rem;
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
        
        
        <div class="allContainer">
            <center>
            <h1>Community Groups - Invite to Group ID <%= currGroup%> (logged in as <%=currUser.getfName() + " " + currUser.getlName() %>)</h1>
            
            <form action="inviteToGroup" method="POST" id="inviteForm">
            <h5>Search user:</h5>
                <input type="hidden" class="form-control" name="action" value="invite">
                <input type="hidden" class="form-control" name="groupID" value="<%= currGroup %>">
                    <input list="browsers" name="targetUserID" id="targetUserController">
                        <datalist id="browsers">
                          <%for(Users user : usersNotInGrp){ %>
                          <option value="<%=user.getuserID() %>"><%=user.getfName() + " " + user.getlName() %></option>
                          <%} %>
                        </datalist>
                <input type="submit" value="Invite to Group">
            </form>
            </center>
                        <hr>
            <h1>Suggested members</h1>
            
            <%for(Users suggest : suggestedUsers){ %>
                <div class="entityContainer">
                <div class="div-left">
                        <img src="commgroups/groupIcon.jpg" class="grpPic">
                        <div style="position:absolute;margin-left:5rem;">
                            <a class="grpLabel" href="#"><strong><%= suggest.getfName() + " " + suggest.getlName() %></strong></a>
                        </div>
                </div>
                <div class="div-right">
                    <button class="dropbtn" specID="<%=suggest.getuserID() %>" onclick="inviteThis(this)"><i class="fa fa-plus" aria-hidden="true"></i></button>
                 </div>
               </div>
            <%} %>
        </div>
<script src="jsp/jquery-3.2.1.min.js"></script>        
<script>
function inviteThis(obj){
    var userID = obj.getAttribute("specID");
    document.getElementById("targetUserController").setAttribute("value",userID);
    
    if (confirm('Are you sure you want to invite this user?')) {
        document.getElementById("inviteForm").submit();
    } else {
        // Do nothing!
    }
    
}

</script>        
        
        
    </body>
</html>
