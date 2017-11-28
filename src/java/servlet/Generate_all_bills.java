package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBQueries;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;

@WebServlet("/BillColPay/Generate_all_bills")
public class Generate_all_bills extends HttpServlet 
{
	public Generate_all_bills() 
	{
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		DBQueries dbq = new DBQueries();
		
		dbq.queryAddresses();
		
		ArrayList<String> addresses = dbq.getAddresses();
		
		ArrayList<String> blocks = new ArrayList<>();
		ArrayList<String> lots = new ArrayList<>();
		
		int i;
		
		for (i = 0; i < addresses.size(); i ++)
		{
			String address = addresses.get(i);
			blocks.add(address.substring(6, address.indexOf(",")));
			address = address.substring(address.indexOf(","));
			lots.add(address.substring(6, address.lastIndexOf(",")));
		}
		
		for (i = 0; i < blocks.size(); i ++)
		{
			dbq = new DBQueries();
			
			if (dbq.checkAddress(blocks.get(i), lots.get(i)))
			{
				request.setAttribute("addressExists", true);
				
				if (dbq.checkHOBills(blocks.get(i), lots.get(i)) == false)
				{
					dbq.queryHOBillingDate(blocks.get(i), lots.get(i)); //gets the latest bills date
					dbq.queryHOBillingID(blocks.get(i), lots.get(i)); //gets the latest billing id
					dbq.queryTrxMonthlyDues(blocks.get(i), lots.get(i)); //gets the monthly dues
					dbq.queryTrxRegistrations(blocks.get(i), lots.get(i)); //gets the register transactions
					dbq.queryTrxVehicles(blocks.get(i), lots.get(i)); //gets the vehicle trxs
					dbq.queryTrxViolations(blocks.get(i), lots.get(i)); //get the violations
					request.setAttribute("generated", dbq.insertBilling(blocks.get(i), lots.get(i))); //generates a new bill based on the transactions and billing id
					dbq.queryHOBillingID(blocks.get(i), lots.get(i)); //gets the id of the inserted bill
					dbq.insertBillingDetails(blocks.get(i), lots.get(i)); //inserts into billing details
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
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("BillColPay.jsp");
		rd.forward(request, response);
	}
}