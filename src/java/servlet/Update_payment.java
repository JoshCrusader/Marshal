package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBQueries;
import javax.servlet.annotation.WebServlet;

@WebServlet("/BillColPay/Update_payment")
public class Update_payment extends HttpServlet 
{
	public Update_payment() 
	{
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		double payment = Double.parseDouble(request.getParameter("payment"));
		String billingID = request.getParameter("billingID");
		double totalDues = Double.parseDouble(request.getParameter("totalDues"));
		
		DBQueries dbq = new DBQueries();
		request.setAttribute("updated", false);
		
		if (dbq.checkPayment(billingID, payment))
		{
			dbq.updatePayment(billingID, payment);
			dbq.insertJournal(billingID);
			dbq.queryBillTrxs(billingID);
			dbq.insertTrxList();
			dbq.insertPaymentDetails(billingID);
			request.setAttribute("updated", true);
		}
		
		request.setAttribute("amount", totalDues);
		request.setAttribute("payment", payment);
		
		RequestDispatcher rd = request.getRequestDispatcher("BillColPay.jsp");
		rd.forward(request, response);
	}
}