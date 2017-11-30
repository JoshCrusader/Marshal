<div class = "css-navbar"> 
		<div class = "css-burgerbun pull-left"><div class = "css-hamburger"><div  class = "css-burgerinside"></div></div></div>
		<!-- 
		<input class = "pull-right css-search"type="text" id="directoryinput" name="directoryinput" placeholder="Search">
	-->
	<div class = "css_container">
		<a href = "/Registration/home.jsp">
			<div class = "pull-left css-item" style = "width:50px;height:50px;margin-top:-7px;">
				<img src="<%= request.getContextPath() %>/Core/Marshal.png" style = "height:100%;width:100%;">
			</div>
		</a>
                        <a href="#">
                            <div class = "pull-left css-item" style = "text-alignment:center;margin-left:15px;width:100px;background-color:#009688;height:35px;border-radius: 3px;line-height: 30px;text-align: center;"> Submodule</div>
                        </a>
		
		<form id = "directorysearchform" action = "${pageContext.request.contextPath}/SearchDirectory" method = "GET">
				<div class = "nav-search">
						<input class = "pull-left css-search"type="text" id="directoryinput" name="directoryinput" placeholder="Search" style = "margin-left:15px;">
					
					<select class = "pull-left css-filterd" name = "searchtype">
					  <option value="Users" selected>Users</option>
					  <option value="Services">Services</option>
	
					</select>
					<button class = "pull-left css-searchb" onclick = "submitAform('directorysearchform')"><i class="fa fa-search"></i></button>
				</div>
			</form>
		<div class = "css-navright">

			
		</div>
		</div>
	</div>
	<div id = "sidebartarget" class = "css-sidebarbody" style = "background-color: rgba(0,0,0,0) !important;">
		<div class = "css-darkness"></div>
		<div class = "css-sidebar">
			<div class = "css-profilediv">

			</div>
			<ul class = "css-sidelist">
                            <%@page import = "model.Users" %>
                            <% Users user = (Users) session.getAttribute("currentSessionUser"); %>
                                <% if(user.getuserType() == 2){%>
				<li>
					<a href = "#"> Preventions</a>
				</li>
                                <% } %>
				<li>
					<a href = "#"> Vehicle Management</a>
				</li>
				<li>
					<a href = "#"> Fees Processing</a>
				</li>
				<li>
					<a href = "#"> Payment Processing</a>
				</li>
				<li>
					<a href = "#"> Users</a>
				</li>
                                <li>
					<a href = "#"> Policy and Mapping</a>
				</li>
                                <li>
					<a href = "#"> Document Management</a>
				</li>
			</ul>
		</div>
	</div>