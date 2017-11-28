<%@ page import="model.DBQueries"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.Date" %>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import ="java.text.DateFormat" %>

<%
	DBQueries dbq = new DBQueries();
	DBQueries dbq2 = (DBQueries) request.getAttribute("dbquery");
	
	String HOname = (String) request.getAttribute("HOname");
	String address = (String) request.getAttribute("address");
	Boolean generated = (Boolean) request.getAttribute("generated");
	Boolean updated = (Boolean) request.getAttribute("updated");
	Boolean addressExists = (Boolean) request.getAttribute("addressExists");
	Double amount = (Double) request.getAttribute("amount");
	Double payment = (Double) request.getAttribute("payment");
	Double change = null;
	DateFormat df = new SimpleDateFormat("yyyy/MM/dd"); 
	String formattedDate = df.format(new Date());
	
	if (amount != null && payment != null)
	{
		change = payment - amount;
	}
	
	ArrayList<String> userIDs;
	ArrayList<String> fnames;
	ArrayList<String> lnames;
	ArrayList<String> addresses;
	ArrayList<String> bills = dbq.getBills();
%>

<html>
<head>
	<title>Billing, Collection and Payment</title>
	
	<!-- Custom stylesheet -->
	<link href = "BillColPay.css" rel = "stylesheet">
	<link rel="stylesheet" type="text/css" href="css_standard.css">
	
	<!-- Custom Javascript scripts -->
	<script src="jquery.js"></script>
	<script src="css_scripts.js"></script>
	 
	<!-- Bootstrap 3 CDN -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<!-- JQuery CDN -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- DataTables CDN -->
	<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
	<script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
	
	<!-- Select2 CDN -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
	
	<!-- w3schools tab styles -->
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	
	<!-- Font-Awesome Icons --> 
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	 
	<!-- include navbar and sidebar -->
	<%@ include file="navs.jsp" %>
	
	<style>
		.background {
			background-color: #1e453e;
			color: white;
		}
	</style>
</head>
<body>
	<div id = "css-id-body">
		<div class="container">
			<h2>Billing, Collection and Payment</h2>
			<p>Welcome, Officer Yuta!</p>
			
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#viewBills">View Bills</a></li>
				<li><a data-toggle="tab" href="#generateBill">Generate Bills</a></li>
			  </ul>

			<div class="tab-content">
				<div id="generateBill" class="tab-pane fade">
					<h3>Generate Bills</h3>
				  
					<form action="Generate_bill" method="POST">
								Homeowner's name:
								
									<input type='text'
										   data-min-length='1'
										   list='HOnames'
										   name='HOnames'
										   id="HOname"
										   size="30"
										   onchange="filterAddresses(this.value)"
										   value="<% if (HOname != null) { out.print(HOname); } %>">
											   
									<datalist id="HOnames">
										<%
											dbq.queryNames();
											userIDs = dbq.getUserIDs();
											fnames = dbq.getFirstNames();
											lnames = dbq.getLastNames();
												
											int i;
											int size = fnames.size();
											
											for (i = 0; i < size; i ++)
											{
												out.print("<option value='" + fnames.get(i) + " " + lnames.get(i) + "'></option>");
											}
										%>
									</datalist>
								
							<br><br>
							
							Address:
								
									<input type='text'
										   data-min-length='1'
										   list='addresses'
										   name='addresses'
										   size="30"
										   value="<% if (address != null) { out.print(address); } %>"
										   style="margin-left: 5.95rem;"
										   required>
										   
									<datalist id="addresses">
										<%
											dbq.queryAddresses();
												
											addresses = dbq.getAddresses();
											
											
											for (i = 0; i < addresses.size(); i ++)
											{
												out.print("<option value='" + addresses.get(i) + "'></option>");
											}
										%>
									</datalist>
						<br><br>
						
						<input type="submit" class="btn btn-success" value="Generate" name="generate">
					</form>
					
					<form action="Generate_all_bills" method="post">
						<input type="submit" class="btn btn-success" value="Generate All" name="generateAll">
					</form>
				</div>
				
				<div id="viewBills" class="tab-pane fade in active">
					<h3>View Bills</h3>
				  
					<form action="Get_bills" method="POST">
						Homeowner's name:
							
						<input type='text'
							   data-min-length='1'
							   list='HOnames'
							   name='HOnames'
							   id="HOname"
							   size="30"
							   onchange="filterAddresses(this.value)"
							   value="<% if (HOname != null) { out.print(HOname); } %>">
						
						Address:
						
						<input type='text'
							   data-min-length='1'
							   list='addresses'
							   name='addresses'
							   size="30"
							   value="<% if (address != null) { out.print(address); } %>"
							   required>
						
						<input type="submit" class="btn btn-success" value="Submit">
					</form>
					  
					<br>
					
					<div class="panel panel-default col-md-7">
						<div class="panel-body">
							<table id="table" class="display" cellspacing="0">
								<caption><h3>List of Bills</h3></caption>
								<thead>
									<tr>
										<th>Date</th>
										<th>Total Dues</th>
										<th>Total Paid</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
								  <%
									dbq.queryBills();
									bills = dbq.getBills();
										
									if (dbq2 != null)
									{
										bills = dbq2.getBills();
									}
									
									for (i = 0; i < bills.size(); i += 4)
									{
										out.print("<tr>");
										out.print("<td align='center'>" + bills.get(i) + "</td>");
										out.print("<td align='right'>" + bills.get(i + 1) + "</td>");
										out.print("<td align='right'>" + bills.get(i + 2) + "</td>");
										out.print("<td align='center'><button class='btn btn-success' value='" + bills.get(i) + ","  + bills.get(i + 1) + "," + bills.get(i + 3) + "' onclick='filterTrx(this.value)'>Select</button></td>");
										out.print("</tr>");
									}
								  %>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="panel panel-default col-md-5">
						<div class="panel-body">
							<div id="hello">
								<table id="table2" class="display" cellspacing="0">
									<caption><h3 id='billDesc'>Bill for ...</h3></caption>
									<tr>
										<th><center>Description</center></th>
										<th><center>Amount</center></th>
									</tr>
									<tbody id="trxList">
									</tbody>
								</table>
							</div>
				  
							<div>
								<button class="btn btn-success" value="hello" onclick="printDiv(this.value)">Print</button>
								<button class="btn btn-success" onclick="getData()" data-toggle="modal" data-target="#paymentModal">Pay</button>
							</div>

							<!-- Modal -->
							<div class="modal fade" id="paymentModal" tabindex="-1" role="dialog" aria-labelledby="paymentModalLabel" aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header background">
											<h5 class="modal-title" id="paymentModalLabel">Payment of Bill</h5>
										</div>
										
										<form action="Update_payment" method="POST">
										    <div class="modal-body">
												<h5>Billing Date: <text id="billingDate"></text></h5>
												<h5>Amount Due:   <text id="totalDues"></text></h5><input id="totalDuesVal" name="totalDues" hidden>
												<h5>Payment:	  <input type="number" step=".01" min="0.01" max="999999999.99" name="payment" onchange="getChange(this.value)" required></h5>
												<h5>Change:	  	  <text id="change"></text></h5>
										    </div>
										  
										    <div class="modal-footer">
												<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
												<button type="submit" class="btn btn-success" id="billingID" name="billingID">Pay</button>
										    </div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id="receipt" style="display: none;" value="receipt">
			<center>
				<table id="table3" cellspacing="0">
					<caption><h3 id='billDesc'>Bill Receipt</h3><p><% out.print(formattedDate); %></p></caption>
					<tbody>
						<tr>
							<th align="left">Amount</th>
							<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
							<td align="right"><% out.print(amount); %></td>
						</tr>
						<tr>
							<th align="left">Payment</th>
							<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
							<td align="right"><% out.print(payment); %></td>
						</tr>
						<tr>
							<th align="left">Change</th>
							<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
							<td align="right"><% out.print(change); %></td>
						</tr>
					</tbody>
				</table>
			</center>
		</div>
		<h6><center>Powered by Rhinos PowerEngine</center></h6>
	</div>
					
    <script>
		var billingDate;
		var totalDues;
		var billingID;
		
		if (<% out.print(addressExists); %> == false)
		{
			alert("Address does not exist.");
		}
		
			if (<% out.print(generated); %> == false)
			{
				alert("Bill already generated for the day.");
			}
		
		
		
		if (<% out.print(updated); %> == false)
		{
			alert("Bill was already paid.");
		}
		if (<% out.print(updated); %>)
		{
			printReceipt();
		}
		
		function filterAddresses(name){
			$.ajax({
				type: "post",
				url: "Get_addresses",
				data: { name: name },
				success: function(data) {
					document.getElementById("addresses").innerHTML = data;
				},
				error: function(request,error){
				}
			});
			return false;
		}
		
		function filterTrx(billID) {
			billingDate = billID.substring(0, 10);
			totalDues = billID.substring(11, billID.lastIndexOf(","));
			billingID = billID.substring(billID.lastIndexOf(",") + 1);
			
			$.ajax({
				type: "post",
				url: "Get_trxs",
				data: { billID: billingID },
				success: function(data) {
					document.getElementById("trxList").innerHTML = data;
				},
				error: function(request,error){
				}
			});
			
			document.getElementById("billDesc").innerHTML = "Bill for " + billingDate;
			return false;
		}
		
		$(document).ready(function(){
			$('#table').DataTable();
			$('#table2').DataTable();
			$('#table3').DataTable();
		});
		
		$(document).ready(function() {
			$('.js-example-basic-single').select2();
		});
		
		function printDiv(divName) {
			
			 var printContents = document.getElementById(divName).innerHTML;
			 var originalContents = document.body.innerHTML;
			 
			 document.body.innerHTML = printContents;
			 
			 window.print();
			 document.body.innerHTML = originalContents;
		}
		
		function printReceipt() {
		  var win = window.open();
		  self.focus();
		  win.document.open();
		  win.document.write('<'+'html'+'><'+'body'+'>');
		  win.document.write(document.getElementById('receipt').innerHTML);
		  win.document.write('<'+'/body'+'><'+'/html'+'>');
		  win.document.close();
		  win.print();
		  win.close();
		}
		
		function getData()
		{
			document.getElementById('billingDate').innerHTML = billingDate;
			document.getElementById('totalDues').innerHTML = totalDues;
			document.getElementById('totalDuesVal').setAttribute('value', totalDues);
			document.getElementById('billingID').value = billingID;
		}
		
		function getChange(payment)
		{	
			var x = document.getElementById('billingID');
			
			if (payment - totalDues >= 0)
			{
				x.removeAttribute('disabled');
				document.getElementById('change').innerHTML = payment - totalDues;
			}
			else
			{
				x.setAttribute('disabled', true);
				document.getElementById('change').innerHTML = "Insufficient funds";
			}
			
		}
	</script>
</body>
</html> 
