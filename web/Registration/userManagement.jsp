<%-- 
    Document   : userManagement
    Created on : 11 28, 17, 5:09:32 PM
    Author     : User
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="Objects.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Core/css_standard.css">
	 <script src="<%= request.getContextPath() %>/Core/jquery.js"></script>
	 <script src="<%= request.getContextPath() %>/Core/css_scripts.js"></script>

	 <!-- remove this afterwards gg -->
	 <meta charset="utf-8">
	 <meta name="viewport" content="width=device-width, initial-scale=1">
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	 <!--                           -->
<style>
#users {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 80%;
    border: 3px solid #669366;
   
}

td, th {
    border: 1px solid #ddd;
    padding: 8px;
}

#users tr:nth-child(even){background-color: #D1DED1;}

#users tr:hover {background-color: #ddd;}

#users th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #4CAF50;
    color: white;
}
</style>
 	<script src="js/jquery-3.2.1.min.js"></script>
        
</head>
<body>  
              <%@include file= "/Core/navs.jsp"%>
              <div id = "css-id-body">
              <script>
            
            $(document).ready(function(){
                    <%  
                        Class.forName("com.mysql.jdbc.Driver");
                        java.sql.Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
                        String existingUname= "select * from users";
                        PreparedStatement pStmt= con.prepareStatement(existingUname);
                        ResultSet rs= pStmt.executeQuery();
                        ArrayList<User> users= new ArrayList();
                        %>
                                
                       <% while(rs.next()){
                           
                            User a1= new User(rs.getString("userID"));
                            
                            a1.setfName(rs.getString("lname")); 
                            a1.setlName(rs.getString("fname"));
                            a1.setmName(rs.getString("mame"));
                            a1.setDate(rs.getString("bDate"));  
                         
                            a1.setUsertype(rs.getInt("usertypeid"));
                            a1.settNum(rs.getString("telno"));
                            a1.setmNum(rs.getString("phoneno")); 
                            a1.setEmail(rs.getString("email"));
                      
                            
                            users.add(a1);
                        }
                    
                    %>
                             
                    <% for(int i = 0; i < users.size(); i++){%>
                        var lname= " <%= users.get(i).getlName()%> "
                        var mname= " <%= users.get(i).getmName()%> "
                        var fname= " <%= users.get(i).getfName()%> "
                        var bdate= " <%= users.get(i).getDate()%> "

                        var utype= " <%= users.get(i).getUsertype()%> "
                        var telno= " <%= users.get(i).gettNum()%> "
                        var mno= " <%= users.get(i).getmNum()%> "
                        var email= " <%= users.get(i).getEmail()%> "

                        var markup = "<tr><td rowspan = '3'> <input type = 'button' class = 'button' id = '"+ <%= i %>  +"'><img src = 'http://vvcexpl.com/wordpress/wp-content/uploads/2013/09/profile-default-male.png'> </td><td>Name: " + fname + mname + lname +"</td><td> Usertype: " +utype+ "</td></tr><tr><td> Birthday:" + bdate + "</td><td> Telelphone Number: " + telno + "</td></tr><tr><td> Mobile Number: " +mno+"</td><td> Email: " + email + "</td></tr>";
                        $("#users").append(markup);
                    <%}%>
                        
                        
                    $( ".button" ).click(function(){
                        var name = $(this).attr('id');
                        var num =  parseInt(name); // this already gets the number of the user in the array in index zero.
                       
                        
                        
                    });
            });
</script>

    <div class="row">
        <center>
            <table id="users">


            </table>
        </center>
        
    </div>
    
</div>
    
</body>
</html>
