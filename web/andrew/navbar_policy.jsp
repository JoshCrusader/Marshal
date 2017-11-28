<%@page import="model.Users"%>
<html>
    <head>
        <style>
            a{
                color:white;
                text-decoration:  none;
            }
        </style>
        
    </head>
<body>
	<div class = "css-navbar"> 
		<div class = "css-burgerbun"><div class = "css-hamburger"><div  class = "css-burgerinside"></div></div></div>
                <div style="position:absolute;top:30%;right:1%"><%= currUser.getfName() %></div>
                <div style="position:absolute;top:30%;left:5%;">
                    <a href="directory?destination=policyManagement">Policy Management</a>&nbsp;&nbsp;
                    <a href="directory?destination=penaltyManagement">Penalty Management</a>
                </div>
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
                                <li>
					<a href = "communityMap_OfficerNew.jsp"> Community Maps</a>
				</li>
                                <li>
					<a href = "directory?destination=policyManagement"> Policy Management</a>
				</li>
			</ul>
		</div>
	</div>
	<div style = "height:4rem;">

	</div>
</body>

</html>