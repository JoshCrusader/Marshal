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
ArrayList<UserGroups> UserGrpSearchResults = (ArrayList<UserGroups>) request.getAttribute("searchResults");
%>
<html>
<title>Community Groups - Search Results</title>
    <head>
        
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
        
        <form action="joinGroup" method="POST" id="joinForm">
            <div class="form-group">
                    <input type="hidden" class="form-control" name="idToJoin" id="joinID">
            </div>
        </form>
        
        <form action="searchGroup" method="POST" id="searchForm">
                <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search for a group..." name="searchkeyword" id="grpsearch" size="50">
                        <input type="submit" value="Search">
                </div>
        </form>
        
        <div class="allContainer">
            <h1>Community Groups - Search Results (logged in as <%=currUser.getfName() + " " + currUser.getlName() %>)</h1>
              <%for(UserGroups grp : UserGrpSearchResults){ %>
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
                            <a href="#" specID="<%=grp.getGroupID()%>" onclick="joinGroupFunction(this)">Join Group</a>
                        </div>
                      </div>
                 </div>
               </div>
            <%} %>
      
        </div>
<script src="jquery-3.2.1.min.js"></script>        
<script>
function joinGroupFunction(obj){
    var test = obj.getAttribute("specID");
    document.getElementById("joinID").setAttribute("value",test);
    
    if (confirm('Are you sure you want to join this group?')) {
        document.getElementById("joinForm").submit();
    } else {
        // Do nothing!
    }
    
}


</script>        
        
        
    </body>
</html>
