<%-- 
    Document   : ConStickTrans
    Created on : 11 25, 17, 1:23:31 AM
    Author     : Fred Purisima
--%>

<%@page import="VehicleAdmin.model.UserVehicle"%>
<%@page import="VehicleAdmin.dao.UserVehicleDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="VehicleAdmin.dao.Database"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>

<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-beta.1.css" type="text/css"> </head>
  <style>
    .dropbtn {
        background-color: #4CAF50;
        color: white;
        padding: 16px;
        font-size: 16px;
        border: none;
        cursor: pointer;
    }

    .dropdown {
        position: relative;
        display: inline-block;
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
    </style>
<body>
  <nav class="navbar navbar-expand-md bg-secondary navbar-dark">
    <div class="container">
      <a class="navbar-brand" href="#">HOA Vehicle Administration</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">  
		<div class="dropdown">
                    <a class="nav-link">Vehicle List</a>
		<div class="dropdown-content">
                    <a href="viewVehicleList.jsp">View User Vehicle List</a>
                    <a href="ConStickTrans.jsp">Confirm Vehicle Pass Request</a>
		</div>
                </div>
            </li> 
		
            <li class="nav-item">
                <a class="nav-link" href="banvehicle.jsp">Ban a Vehicle</a>
            </li>
            
            <li class="nav-item">
		<a class="nav-link" href="ChangeVPassPricing.jsp">Change Vehicle Pass Price</a>
            </li>
			
        </ul>
      </div>
    </div>
  </nav>
  <div class="py-5" style="background-image: url(&quot;https://pingendo.github.io/templates/sections/assets/form_red.jpg&quot;);">
    <div class="container">
      <div class="row">
        <div class="align-self-center col-md-6 text-white">
          <h1 class="text-center text-md-left display-3" contenteditable="true">Confirm Sticker Request</h1>
        </div>
        <div class="col-md-6" id="book">
          <div class="card">
            <div class="card-body p-5">
              <form method="post" action="ConfStickServ">
              <table class="table">
                <thead>
                  <tr>
                    <th>Plate No</th>
                    <th>User ID</th>
                    <th>Owner</th>
                    <th>Confirm Sticker</th>
                  </tr>
                </thead>
                <tbody>
                  <%
	
                        Connection conn=Database.getDBConnection();
                        String sql="SELECT uv.plateNum,uv.userID ,concat(lname,\" \",fname,\" \",mame) as 'fullname' FROM user_vehicles uv join users u on uv.userID=u.userID where uv.stickerPaid=true and uv.stickerissuedBy is null;";

                        PreparedStatement pStmt=conn.prepareStatement(sql);
                        ResultSet rs = pStmt.executeQuery();
                        while(rs.next()){
                                       out.println("<tr>");
                                       out.println("<td>"+rs.getString(1)+"</td>");
                                       out.println("<td>"+rs.getString(2)+"</td>");
                                       out.println("<td>"+rs.getString(3)+"</td>");
                                       out.println("<td><input type='checkbox' name='check_list' value='"+rs.getString(1)+" "+rs.getString(2)+"'></td>");
                                       out.println("</tr>");
                        }
                    
                    

                    %>
                </tbody>
              </table>
              <div align="center"><input type='submit' name='confirmorder' value='Confirm Order'/></div> 
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5 bg-dark text-white">
    <div class="container">
      <div class="row">
        <div class="col-md-9">
          <p class="lead">Sign up to our newsletter for the latest news</p>
          <form class="form-inline">
            <div class="form-group">
              <input type="email" class="form-control" placeholder="Your e-mail here"> </div>
            <button type="submit" class="btn btn-primary ml-3">Subscribe</button>
          </form>
        </div>
        <div class="col-4 col-md-1 align-self-center">
          <a href="https://www.facebook.com" target="_blank"><i class="fa fa-fw fa-facebook fa-3x text-white"></i></a>
        </div>
        <div class="col-4 col-md-1 align-self-center">
          <a href="https://twitter.com" target="_blank"><i class="fa fa-fw fa-twitter fa-3x text-white"></i></a>
        </div>
        <div class="col-4 col-md-1 align-self-center">
          <a href="https://www.instagram.com" target="_blank"><i class="fa fa-fw fa-instagram text-white fa-3x"></i></a>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12 mt-3 text-center">
          <p>© Copyright 2017 Pingendo - All rights reserved.</p>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</body>

</html>