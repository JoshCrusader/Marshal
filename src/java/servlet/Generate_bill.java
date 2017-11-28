package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBQueries;
import javax.servlet.annotation.WebServlet;

@WebServlet("/BillColPay/Generate_bill")
public class Generate_bill extends HttpServlet 
{
	public Generate_bill() 
	{
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String HOname = request.getParameter("HOnames");
		String address = request.getParameter("addresses");
		String addressCopy = address;
		
		String block = address.substring(6, address.indexOf(","));
		address = address.substring(address.indexOf(","));
		String lot = address.substring(6, address.lastIndexOf(","));
		
		DBQueries dbq = new DBQueries();
		
		if (dbq.checkAddress(block, lot))
		{
			request.setAttribute("addressExists", true);
			
			if (dbq.checkHOBills(block, lot) == false)
			{
				dbq.queryHOBillingDate(block, lot); //gets the latest bills date
				dbq.queryHOBillingID(block, lot); //gets the latest billing id
				dbq.queryTrxMonthlyDues(block, lot); //gets the monthly dues
				dbq.queryTrxRegistrations(block, lot); //gets the register transactions
				dbq.queryTrxVehicles(block, lot); //gets the vehicle trxs
				dbq.queryTrxViolations(block, lot); //get the violations
				request.setAttribute("generated", dbq.insertBilling(block, lot)); //generates a new bill based on the transactions and billing id
				dbq.queryHOBillingID(block, lot); //gets the id of the inserted bill
				dbq.insertBillingDetails(block, lot); //inserts into billing details
			}
			else
			{
				System.out.println("Bill already generated today!");
				request.setAttribute("generated", false);
			}
		}
		else
		{
			System.out.println("Address does not exist!");
			request.setAttribute("addressExists", false);
		}
		
		
		request.setAttribute("dbquery", dbq);
		request.setAttribute("address", addressCopy);
		request.setAttribute("HOname", HOname);
		
		RequestDispatcher rd = request.getRequestDispatcher("BillColPay.jsp");
		rd.forward(request, response);
	}
}