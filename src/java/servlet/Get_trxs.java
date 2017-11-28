package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBQueries;
import java.util.ArrayList;
import java.text.DecimalFormat;
import javax.servlet.annotation.WebServlet;

@WebServlet("/BillColPay/Get_trxs")
public class Get_trxs extends HttpServlet 
{
	public Get_trxs() 
	{
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String billID = request.getParameter("billID");
		DBQueries dbq = new DBQueries();
		dbq.queryBillTrxs(billID);
		
		ArrayList<String> trxs = dbq.getTrxs();
		int i;
		double totalamt = 0;
		String data = "";
		
		for (i = 0; i < trxs.size(); i += 2)
		{
			data += "<tr>";
			data += "<td align='left'>" + trxs.get(i) + "</td>";
			data += "<td align='right'>" + trxs.get(i + 1) + "</td>";
			data += "</tr>";
			
			totalamt += Double.parseDouble(trxs.get(i + 1));
		} 
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		data += "<tr><th>Total amount:</th><td align='right'>" + df.format(totalamt) + "</td></tr>";
		response.setContentType("text/plain");
		response.getWriter().write(data);
	}
}