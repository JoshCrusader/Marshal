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

@WebServlet("/BillColPay/Get_addresses")
public class Get_addresses extends HttpServlet 
{
	public Get_addresses() 
	{
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String HOnames = request.getParameter("name");
		
		DBQueries dbq = new DBQueries();
		dbq.queryHOAddresses(HOnames);
				
		ArrayList<String> addresses = dbq.getAddresses();
		int i;
		String data = "";
		
		for (i = 0; i < addresses.size(); i ++)
		{
			data += "<option value='" + addresses.get(i) + "'></option>";
		}
		
		response.setContentType("text/plain");
		response.getWriter().write(data);
	}
}