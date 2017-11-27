<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "java.sql.ResultSet" %>
    <%
ResultSet rs = (ResultSet) request.getAttribute("rs");
    rs.next();
    ResultSet ho = (ResultSet) request.getAttribute("ho");
    ResultSet hm = (ResultSet) request.getAttribute("hm");
    ResultSet kb = (ResultSet) request.getAttribute("kb");

    Double lot = (Double)request.getAttribute("lot");
    Double block = (Double)request.getAttribute("block");
    int lotnum = (int)request.getAttribute("blocknum");
    int blocknum = (int)request.getAttribute("blocknum");
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
</head>

<body>
	<%@include file="navs.jsp"%>
	<div id = "css-id-body">
		<div class = "css_container">
			<h2></h2>
			<div class = "col-12 pull-left UD_adiv" style = "background-color:#f6f7f9; box-shadow: 0px 5px 3px #888888;">
				<div class = "col-5 pull-left">
						<div class = "UD_pp">
							<img src="<%=rs.getString(9) %>" style = "height:100%; width: 100%;">
						</div>
				</div>
				<div class = "col-4 pull-left">
						<center>
							<div>
								<input type = "text" value = "<%= rs.getString(3)%>" style = "width: 100px;"/>
								<input type = "text" value = "<%= rs.getString(4)%>" style = "width: 100px;"/>
								<input type = "text" value = "<%= rs.getString(2)%>" style = "width: 100px;"/>
							</div>
						</center>
						
						<center><input type = "text" value = "<%= rs.getString(6)%>"/></center>
				</div><br>
				<div class = "col-12 pull-left" style = "margin-top:15px;">
					<table class = "table">
						<tbody>
						<tr>
							<td>Mobile Number</td>
							<td><input type = "text" value = "<%= rs.getString(11)%>"/></td>
						</tr>
						<tr>
							<td>Telephone Number</td>
							<td><input type = "text" value = "<%= rs.getString(12)%>"/></td>
						</tr>
						<tr>
							<td>Email Address </td>
							<td><input type = "text" value = "<%= rs.getString(10)%>"/></td>
						</tr>
						<tr>
							<td>Home Address</td>
							<td onclick = "displayme()"><span style = "color:#337ab7; cursor:pointer;">Block <input type = "text" value = "<%= blocknum%>" style = "width:50px;"/> Lot <input type = "text" value = "<%= lotnum%>" style = "width:50px;"/></span></td>
						</tr>
						<tr>
							<td>User Type</td>
							<td><input type = "text" value = "<%= rs.getString(8)%>"/></td>
						</tr>
						<tr>
							<td>Birth date</td>
							<td><input type = "text" value = "<%= rs.getString(5)%>"/></td>
						</tr>
					</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>