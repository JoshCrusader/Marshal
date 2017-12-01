<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import = "model.Users" %>
     <%@page import = "model.Ref_Occupation" %>
     <%@page import = "model.Document" %>
     
     <%@page import = "java.util.ArrayList" %>
    <%
	ArrayList<Users> rs = (ArrayList<Users>) request.getAttribute("searchresult");
    %>
<html>
<head>
	 <link rel="stylesheet" type="text/css" href="Core/css_standard.css">
	 <script src="Core/jquery.js"></script>
	 <script src="Core/css_scripts.js"></script>

	 <!-- remove this afterwards gg -->
	 <meta charset="utf-8">
	 <meta name="viewport" content="width=device-width, initial-scale=1">
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	 <!--                           -->

	 <style>
	 	body{
	 		color:black;
	 	}

	 	.UD_user_image{
	 		width:100px;
	 		height: 100px;
	 		
	 	}
	 	table{
	 		width:100%;
	 	}
	 	tr,th{
	 		text-align: center;
	 	}
	 	td{
			height: 105px;
		}
		.UD_address{
	    	display:none;
	    }
		@media only screen and (min-width: 768px) {
		    /* For desktop: */
		    
		    .UD_address{
		    		display:block;
		    }
		}

	 </style>
</head>

<body>

	<%@include file="navs.jsp"%>
	<div id = "css-id-body">
		<div class="css_container">
		  <table class = "UD_user_table">
		  	<tr>
		  		<th>Profile</th>
		  		<th>Name</th>
		  	</tr>
		  	<%
		  		for(int i = 0; i < rs.size(); i++){
		  			%>
		  			<tr style = "background-color:#f6f7f9;border: 1px solid black;">
			  		
			  		<td><center><div class = "UD_user_image"><img src="<%= rs.get(i).getPhoto().getDocumentLocation() %>" style = "height:100%; width: 100%;"></div></center></td>
			  		<td>
			  			<a href = "UserProfile?uId=<%= rs.get(i).getuserID() %>"><span><%= rs.get(i).getfName() %> <%= rs.get(i).getmName() %> <%= rs.get(i).getlName() %></span></a>
			  			<br>
			  			<span><%= rs.get(i).getOccupation().getOccupation() %></span>
			  		</td>
		  		</tr>	
		  		<%
	  			}
	  		%>
		  	
		  </table>
		</div>

	</div>
</body>

</html>