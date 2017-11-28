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
		  <h2>Create Dues</h2>
			  
			  <div class="panel panel-default col-md-12" style = "height: 95%;">
			    <div class="panel-body">
			    	
			    	<form method="POST" action="MonthlyDuesController">

						<div class="panel panel-default col-md-12">
							<div class="panel-body">

							Enter Due Amount (Peso): <input type="text" name="amount">

							</div>
						</div>

						<div class="panel panel-default col-md-12">
							<div class="panel-body">

							Enter Start Month:
							<select name="start_month">
								
								<option>Select Start Month</option>
								<option> ----- </option>
								<option value="1">January</option>
								<option value="2">February</option>
								<option value="3">March</option>
								<option value="4">April</option>
								<option value="5">May</option>
								<option value="6">June</option>
								<option value="7">July</option>
								<option value="8">August</option>
								<option value="9">September</option>
								<option value="10">October</option>
								<option value="11">November</option>
								<option value="12">December</option>

							</select><p><p>

							Enter Start Year: <input type="text" name="start_year" maxlength="4">

							</div>

						</div>

						<div class="panel panel-default col-md-12">
							<div class="panel-body">

							Enter End Month:
							<select name="end_month">
								
								<option>Select End Month</option>
								<option> ----- </option>
								<option value="1">January</option>
								<option value="2">February</option>
								<option value="3">March</option>
								<option value="4">April</option>
								<option value="5">May</option>
								<option value="6">June</option>
								<option value="7">July</option>
								<option value="8">August</option>
								<option value="9">September</option>
								<option value="10">October</option>
								<option value="11">November</option>
								<option value="12">December</option>

							</select><p><p>

							Enter End Year: <input type="text" name="end_year" maxlength="4">

							</div>

						</div>

						<input type="submit" name="submit" value="Submit Planned Dues">

					</form>

					<a href="index.jsp">Go Back</a><p>

				</div>
		  	</div>

		</div>	
	</div>

</body>

</html>