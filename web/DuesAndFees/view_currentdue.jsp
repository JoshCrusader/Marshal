<%@ page import="java.util.ArrayList"%>
<%@ page import="DuesAndFees.Model.CurrentMonthlyDue" %>

<html>
<head>
	 <link rel="stylesheet" type="text/css" href="css_standard.css">
	 <link rel="stylesheet" type="text/css" href="test.css">
	 <script src="jquery.js"></script>
	 <script src="css_scripts.js"></script>

	 <!-- remove this afterwards gg -->
	 <meta charset="utf-8">
	 <meta name="viewport" content="width=device-width, initial-scale=1">
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
	 <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	 <!--                           -->

	 <title>DUES & FEES | Admin</title>

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
		<h2>View Current Due for the Month</h2>
			  
                    <div class="panel panel-default col-md-12" style = "height: 95%;">
                        <div class="panel-body">
                            
                            <div class="panel panel-default col-md-12">
                            <div class="panel-body">
			    	
                            <% 
                                
                                ArrayList<String> months = new ArrayList<String>();
                                months.add("January");
                                months.add("February");
                                months.add("March");
                                months.add("April");
                                months.add("May");
                                months.add("June");
                                months.add("July");
                                months.add("August");
                                months.add("September");
                                months.add("October");
                                months.add("November");
                                months.add("December");
                            
                                CurrentMonthlyDue cmd = (CurrentMonthlyDue)request.getAttribute("CurrentDue");
                                
                                out.print("<h4>Due Amount: " + cmd.getAmount() + " Pesos</h4>");
                                out.print("<h4>Date Implemented: " + months.get(cmd.getStartmonth()-1) + " " + cmd.getStartyear() + "</h4>");
                                out.print("<h4>Date of Last Implementation: " + months.get(cmd.getEndmonth()-1) + " " + cmd.getEndyear() + "</h4>");
                            
                            %>

                            </div>
                            </div>
                            
                        </div>
                        <a href="index.jsp">Go Back</a><p>
		  </div>
                
            </div>	
	</div>

</body>

</html>