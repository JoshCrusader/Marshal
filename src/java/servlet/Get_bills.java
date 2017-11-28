package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBQueries;
import javax.servlet.annotation.WebServlet;

@WebServlet("/BillColPay/Get_bills")
public class Get_bills extends HttpServlet 
{
	public Get_bills() 
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
		
		dbq.queryHOBills(block, lot);
		request.setAttribute("dbquery", dbq);
		request.setAttribute("address", addressCopy);
		request.setAttribute("HOname", HOname);
		
		RequestDispatcher rd = request.getRequestDispatcher("BillColPay.jsp");
		rd.forward(request, response);
	}
}