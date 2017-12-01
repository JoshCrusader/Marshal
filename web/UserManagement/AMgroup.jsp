<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import = "java.sql.ResultSet" %>
 <%
ResultSet rs = (ResultSet) request.getAttribute("rs");
ResultSet roles = (ResultSet) request.getAttribute("roles");
ResultSet modules = (ResultSet) request.getAttribute("modules");
int[][] modulematrix = (int[][]) request.getAttribute("modulematrix"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <link rel="stylesheet" type="text/css" href="Core/css_standard.css">
	 <link rel="stylesheet" type="text/css" href="Core/test.css">
	 <script src="Core/jquery.js"></script>
	 <script src="Core/css_scripts.js"></script>

	 <!-- remove this afterwards gg -->
	 <meta charset="utf-8">
	 <meta name="viewport" content="width=device-width, initial-scale=1">
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
	 <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	 <!--                           -->

</head>

<body>
	<div class = "css-navbar"> 
		<div class = "css-burgerbun"><div class = "css-hamburger"><div  class = "css-burgerinside"></div></div></div>
	</div>
	<div id = "sidebartarget" class = "css-sidebarbody">
		<div class = "css-darkness"></div>
		<div class = "css-sidebar">
			<div class = "css-profilediv">

			</div>
			<ul class = "css-sidelist">
				<li>
					<a href = "#"> User Management</a>
				</li>
				<li>
					<a href = "#"> Security</a>
				</li>
				<li>
					<a href = "#"> Records Management</a>
				</li>
				<li>
					<a href = "#"> Module Configuration</a>
				</li>
				<li>
					<a href = "#"> SMS Management</a>
				</li>
			</ul>
		</div>
	</div>
	<div id = "css-id-body" style = "top:70px;Position:absolute;width:100%;">
		<div class="container">
		  <h2>Admin management</h2>
			  
			  <div class="panel panel-default col-md-12 pull-right" style = "height: 95%;">
			    <div class="panel-body">
			    	<form action="UserManagement" method="post">
			    	<%
					String oldsubj = "";
			    		boolean first = true;
			    		int i = 0;
					while(modules.next()){
					if(!modules.getString(3).equals(oldsubj)){
						if(first){
							first = false;
						}
						else{
							%>
						    </tbody>
						  </table>
						  <br>
							<%
						}
						%>
						<h4> <%=modules.getString(3) %> </h4>
						<hr>
					    	<table class="table table-striped">
							    <thead>
							      <tr>
							        <th class = "col-md-4">Module</th>
							        <%
							        		while(roles.next()){
							        			%>
							        				<th class = "col-md-2" style = "text-align: center;"><%= roles.getString(2) %></th>
							        			<%
							        		}
							        		roles.beforeFirst();
							        %>
							        
							      </tr>
							    </thead>
							    <tbody>
						<%
						oldsubj = modules.getString(3);
					}
				%>
					<tr>
					        <td><%= modules.getString(2) %></td>
					        <%
					        	for(int j = 0; j < modulematrix[i].length; j++){
					        		if(modulematrix[i][j] == 1){
					        			%>
					        				<td><center><input type="checkbox" name="Mod" value = "<%= modules.getInt(1) %>x<%= j+1 %>" checked></center></td>
					        			<%
					        		}
					        		else{
					        			%>
					        				<td><center><input type="checkbox" name="Mod" value = "<%= modules.getInt(1) %>x<%= j+1 %>"></center></td>
					        			<%	
					        		}
					        	}
					        %>
					 </tr>		
				<%
					i++;
					}
				%>
					</tbody>
			  	</table>
					      
					<button type="submit" class="btn btn-default">Submit</button>
					</form>
				</div>
		  	</div>

		</div>	
	</div>
</body>
</html>