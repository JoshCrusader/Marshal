<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "java.sql.ResultSet" %>
    <%@page import = "Objects.User" %>
    <%@page import = "java.util.ArrayList" %>
    <%
	User user = (User) request.getAttribute("myUser");
    ArrayList<User> ho = (ArrayList<User>) request.getAttribute("ho");
    ArrayList<User> hm = (ArrayList<User>) request.getAttribute("hm");
    ArrayList<User> kb = (ArrayList<User>) request.getAttribute("kb");

    Double lot = (Double)request.getAttribute("lot");
    Double block = (Double)request.getAttribute("block");
    int lotnum = (Integer)request.getAttribute("blocknum");
    int blocknum = (Integer)request.getAttribute("lotnum");
    %>
<html>
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
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	 <!--                           -->

	 <style>
	 /* For mobile phones: */
		td{
			height: 75px;
			line-height: 75px !important;
		}

	 	.UD_conntainer{
	 		width: 100%;
	 		margin: auto;
	 	}
	 	.UD_hh_pp{
	 		height:50px;
	 		width: 50px;
	 		/*background-color: yellow;*/

	 	}
	 	.UD_hh_pp img{
	 		height:50px;
	 		width: 50px;
	 		/*background-color: yellow;*/
	 		border-radius: 50%;
	 	}
	 	.UD_pp{
	 		width: 150px;
	 		height: 200px;
	 		margin: auto;
	 		/*background-color: yellow;*/
	 	}
	 	.UD_pp img{
	 		border-radius: 50%;
	 	}
	 	.UD_modal{
	 		position: fixed;
	 		width: 100%;
	 		height: 100%;
	 		visibility: hidden;
	 	}
	 	.UD_shadow{
	 		position: fixed;
	 		background-color: rgba(0,0,0,0.95);
	 		width: 100%;
	 		height: 100%;
	 	}
	 	.UD_map{
	 		position: fixed;
	 		width: 50%;
	 		height: 50%;
	 		margin-left: 25%;
	 		
	 	}
	 	#map{
	 		width: 100%;
	 		height: 100%;
	 		
	 	}
	 	.UD_modal_exit{
	 		background-color: #1e453e;
	 		width: 50px;
	 		height: 50px;
	 		border-radius: 50%;
	 		margin-top: 80%;
	 		margin-left: 100%;
	 		opacity: 0.5;
	 		cursor: pointer;
	 		transition: opacity 0.5s;
	 	}
	 	.UD_modal_exit:hover{
	 		opacity: 1;
	 	}
		@media only screen and (min-width: 768px) {
		    /* For desktop: */
		    
		    .UD_conntainer{
		 		width: 85%;
		 	}
		 	.UD_pp img{
		 		border-radius: 0%;
		 	}
		 	.UD_modal_exit{
		 		margin-top: 25%;
		 	}
		}
	 	body{
	 		color:black;
	 		font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
	 		font-size: 14px;
	 		line-height: 1.42857143;
	 	}

	 	.UD_left_container{
	 		width:70%;
	 		float:left;
	 		background-color: white;
	 	}
	 	.UD_user_image{
	 		width:250px;
	 		height: 250px;
	 		background-color: blue;
	 		float:left;
	 	}
	 	
	 	.UD_user_basic_info{
	 		padding-top: 15px;
	 		margin-left: 15%;
	 		padding-bottom: 15px;

	 	}
	 	.UD_right_container{
	 		width:30%;
	 		float:left;
	 		background-color: red;
	 	}
	 	
	 	/* Household profile pic*/
	 	
	 	.UD_adiv{
	 		padding: 15px;
	 		padding-bottom: 15px;
	 	}
	 	
	 </style>
	 <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBjFXE9lmZUQwvTlGipGmiZyi_YVRN3c1s&callback=initMap"
    async defer></script>
</head>

<body>
	<%@include file="navs.jsp"%>
	<div id = "css-id-body">
		<div class = "css_container">
			<h2></h2>
			<div class = "col-8a pull-left UD_adiv" style = "background-color:#f6f7f9; box-shadow: 0px 5px 3px #888888;">
				<div class = "col-5 pull-left">
						<div class = "UD_pp">
							<img src="<%=user.getphotolocation() %>" style = "height:100%; width: 100%;">
						</div>
				</div>
				<div class = "col-4 pull-left">
						<center><a href = "UserProfile?uId=<%= user.getUserID() %>"><h3><%=user.getfName() %> <%=user.getmName() %> <%= user.getlName() %></h3> </a></center>
						<center><h4><%= user.getOccupation() %> </h4></center>
				</div><br>
				<div class = "col-12 pull-left" style = "margin-top:15px;">
					<table class = "table">
						<tbody>
						
						<% 
							if(1 == 1){
						%>
						
						<%
							}
						%>
						<tr>
							<td>Mobile Number</td>
							<td><%= user.getmNum() %></td>
						</tr>
						<tr>
							<td>Telephone Number</td>
							<td><%= user.gettNum() %></td>
						</tr>
						<tr>
							<td>Email Address </td>
							<td><%= user.getEmail() %></td>
						</tr>
						<tr>
							<td>Home Address</td>
							<td onclick = "displayme()"><span style = "color:#337ab7; cursor:pointer;">Block <%=blocknum %> Lot <%=lotnum %></span></td>
						</tr>
						<tr>
							<td>User Type</td>
							<td><%= user.gettypeDesc() %></td>
						</tr>
						<tr>
							<td>Birth date</td>
							<td><%=user.getDate() %></td>
						</tr>
					</tbody>
					</table>
				</div>
			</div>
			<div class = "col-4 pull-right" style = "">
				<div class = "col-12 UD_adiv pull-left" style = "background-color:#f6f7f9; box-shadow: 0px 0px 3px #888888;">
					<center><h4>Home Owner</h4></center>
					<% for(int i = 0; i < ho.size(); i++){ %>
						<div class = "col-12 pull-left margtop">
							<div class = "UD_hh_pp pull-left"><img src="<%= ho.get(i).getphotolocation() %>" style = "height:100%; width: 100%;"></div>
							<div class = "pull-left margleft">
								<a href="UserProfile?uId=<%= ho.get(i).getUserID() %>"><span><%= ho.get(i).getfName() %> <%= ho.get(i).getmName() %> <%= ho.get(i).getlName() %></span></a><br>
								<%= ho.get(i).getOccupation() %>
							</div>
						</div>
						
					<% }%>
					
					
				</div>
				<div class = "col-12 UD_adiv pull-left" style = "background-color:#f6f7f9; box-shadow: 0px 0px 3px #888888; margin-top:9px;">
					<center><h4>Home Members</h4></center>
					<% System.out.println(hm.size()); for(int i = 0; i < hm.size(); i++){ %>
						<div class = "col-12 pull-left margtop">
							<div class = "UD_hh_pp pull-left"><img src="<%= hm.get(i).getphotolocation() %>" style = "height:100%; width: 100%;"></div>
							<div class = "pull-left margleft">
								<a href="UserProfile?uId=<%= hm.get(i).getUserID() %>"><span><%= hm.get(i).getfName() %> <%= hm.get(i).getmName() %> <%= hm.get(i).getlName() %></span></a><br>
								<%= hm.get(i).getOccupation() %>
							</div>
						</div>
						
					<% }%>
					
					
				</div>
				<div class = "col-12 UD_adiv pull-left" style = "background-color:#f6f7f9; box-shadow: 0px 0px 3px #888888; margin-top:9px;">
					<center><h4>Kasambahay</h4></center>
					<% for(int i = 0; i < kb.size(); i++){ %>
						<div class = "col-12 pull-left margtop">
							<div class = "UD_hh_pp pull-left"><img src="<%= kb.get(i).getphotolocation() %>" style = "height:100%; width: 100%;"></div>
							<div class = "pull-left margleft">
								<a href="UserProfile?uId=<%= kb.get(i).getUserID() %>"><span><%= kb.get(i).getfName() %> <%= kb.get(i).getmName() %> <%= kb.get(i).getlName() %></span></a><br>
								<%= kb.get(i).getOccupation() %>
							</div>
						</div>
						
					<% }%>
					
					
				</div>
				<div class = "col-12 UD_adiv pull-left" style = "background-color:#f6f7f9; box-shadow: 0px 0px 3px #888888;margin-top:15px;">
					<center><h4>Options</h4></center>
						<div class = "col-12 pull-left margtop">
							<input class = "pull-left" type = "button" name = "Reset" value = "Reset Password" style = "background-color:#f6f7f9;height:50px;width:100%;font-size: 23px;"/>
							<input class = "pull-left" type = "button" name = "Delete" value = "Delete Account" style = "background-color:#AA0114;height:50px;width:100%;font-size: 23px;"/>
						</div>
				</div>
			</div>
		</div>
	</div>
	<div class = "UD_modal">
		<div class = "UD_shadow" onclick = "dontdisplayme()"></div>
		<div class = "UD_map">
			<div class = "UD_modal_exit" onclick = "dontdisplayme()"><i class="fa fa-times" aria-hidden="true" style = "width:100%;height:100%;font-size:30px;text-align:center;color:white;line-height:50px;"></i></div>
			<div id = "map"></div>
			
		</div>
	</div>
                                        
</body>
<script>
	  function dontdisplayme(){
	  	$(".UD_modal").css({
	  		"visibility":"hidden"
	  	});
	  }
	  function displayme(){
	  	$(".UD_modal").css({
	  		"visibility":"visible"
	  	});
	  }
      var map;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: <%= block%>, lng: <%= lot%>},
          zoom: 20,
          mapTypeId: 'satellite'
        });
        var marker = new google.maps.Marker({
          position: {lat: <%= block%>, lng: <%= lot%>},
          map: map
        });
      }
    </script>
</html>