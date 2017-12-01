package BCP.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BCP.Models.*;
import BCP.DAO.*;


@WebServlet(urlPatterns = {"/BillColPay/BCP_controller"})
public class BCP_controller extends HttpServlet {

    public BCP_controller() 
{
		super();
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String value = request.getParameter("BCP");
                
                if (value.equals("Generate Bill"))
                {
                    String HOname = request.getParameter("HOnames");
                    String address = request.getParameter("addresses");
                    String addressCopy = address;

                    int block = Integer.parseInt(address.substring(6, address.indexOf(",")));
                    address = address.substring(address.indexOf(","));
                    int lot = Integer.parseInt(address.substring(6, address.lastIndexOf(",")));

                    Bill b = new Bill();
                    b.setBlocknum(block);
                    b.setLotnum(lot);
                    
                    if (BillDAO.checkAddress(b))
                    {
                            request.setAttribute("addressExists", true);

                            if (BillDAO.checkHOBills(b) == false)
                            {
                                    b = BillDAO.queryHOBillingDate(b); //gets the latest bills date
                                    b = BillDAO.queryHOBillingID(b); //gets the latest billing id
                                    
                                    ArrayList<trxReferences> trxs = new ArrayList<>();
                                    
                                    trxs = BillDAO.queryTrxMonthlyDues(b, trxs); //gets the monthly dues
                                    trxs = BillDAO.queryTrxRegistrations(b, trxs); //gets the register transactions
                                    trxs = BillDAO.queryTrxVehicles(b, trxs); //gets the vehicle trxs
                                    trxs = BillDAO.queryTrxViolations(b, trxs); //get the violations
                                    
                                    double totaldues = 0;
                                    int i;
                                    
                                    for (i = 0; i < trxs.size(); i ++)
                                    {
                                        totaldues += trxs.get(i).getAmount();
                                    }
                                    
                                    b.setTotalDue(totaldues);
                                    request.setAttribute("generated", BillDAO.insertBilling(b)); //generates a new bill based on the transactions and billing id
                                 
                                    b = BillDAO.queryHOBillingID(b); //gets the id of the inserted bill
                                    BillDAO.insertBillingDetails(b, trxs); //inserts into billing details
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


                    request.setAttribute("bill", b);
                    request.setAttribute("address", addressCopy);
                    request.setAttribute("HOname", HOname);

                    RequestDispatcher rd = request.getRequestDispatcher("BCP_view.jsp");
                    rd.forward(request, response);
                }
                
                
                else if (value.equals("Generate All"))
                {
                    ArrayList<String> addresses = BillDAO.queryAddresses();
                    ArrayList<Bill> bills = new ArrayList<>();

                    int i;

                    for (i = 0; i < addresses.size(); i ++)
                    {
                            Bill b = new Bill();
                            String address = addresses.get(i);
                            b.setBlocknum(Integer.parseInt(address.substring(6, address.indexOf(","))));
                            address = address.substring(address.indexOf(","));
                            b.setLotnum(Integer.parseInt(address.substring(6, address.lastIndexOf(","))));
                            bills.add(b);
                    }

                    for (i = 0; i < bills.size(); i ++)
                    {

                            if (BillDAO.checkAddress(bills.get(i)))
                            {
                                    request.setAttribute("addressExists", true);

                                    if (BillDAO.checkHOBills(bills.get(i)) == false)
                                    {
                                        Bill b = bills.get(i);
                                            b = BillDAO.queryHOBillingDate(b); //gets the latest bills date
                                            b = BillDAO.queryHOBillingID(b); //gets the latest billing id
                                            
                                            ArrayList<trxReferences> trxs = new ArrayList<>();
                                    
                                            trxs = BillDAO.queryTrxMonthlyDues(b, trxs); //gets the monthly dues
                                            trxs = BillDAO.queryTrxRegistrations(b, trxs); //gets the register transactions
                                            trxs = BillDAO.queryTrxVehicles(b, trxs); //gets the vehicle trxs
                                            trxs = BillDAO.queryTrxViolations(b, trxs); //get the violations

                                            double totaldues = 0;
                                            int j;

                                            for (j = 0; j < trxs.size(); j ++)
                                            {
                                                totaldues += trxs.get(j).getAmount();
                                            }

                                            b.setTotalDue(totaldues);
                                            request.setAttribute("generated", BillDAO.insertBilling(b)); //generates a new bill based on the transactions and billing id

                                            b = BillDAO.queryHOBillingID(b); //gets the id of the inserted bill
                                            BillDAO.insertBillingDetails(b, trxs); //inserts into billing details
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

                    RequestDispatcher rd = request.getRequestDispatcher("BCP_view.jsp");
                    rd.forward(request, response);
                }
                
                
                else if (value.equals("getTrxs"))
                {
                    int billID = Integer.parseInt(request.getParameter("billID"));
                    Bill b = new Bill();

                    b.setBillingID(billID);
                    ArrayList<trxReferences> trxs = BillDAO.queryBillTrxs(b);;
                    
                    int i;
                    double totalamt = 0;
                    String data = "";

                    for (i = 0; i < trxs.size(); i ++)
                    {
                            data += "<tr>";
                            data += "<td align='left'>" + trxs.get(i).getDescription() + "</td>";
                            data += "<td align='right'>" + trxs.get(i).getAmount() + "</td>";
                            data += "</tr>";

                            totalamt += trxs.get(i).getAmount();
                    } 

                    DecimalFormat df = new DecimalFormat("0.00");

                    data += "<tr><th>Total amount:</th><td align='right'>" + df.format(totalamt) + "</td></tr>";
                    response.setContentType("text/plain");
                    response.getWriter().write(data);
                }
                
                
                else if (value.equals("updatePayment"))
                {
                    double payment = Double.parseDouble(request.getParameter("payment"));
                    int billingID = Integer.parseInt(request.getParameter("billingID"));
                    double totalDues = Double.parseDouble(request.getParameter("totalDues"));

                    Bill b = new Bill();
                    
                    b.setBillingID(billingID);
                    b.setTotalPaid(payment);
                    b.setTotalDue(totalDues);
                    request.setAttribute("updated", false);

                    if (BillDAO.checkTotalPaid(b))
                    {
                            BillDAO.updatePayment(b);
                            BillDAO.insertJournal(b);
                                            
                            ArrayList<trxReferences> trxs = BillDAO.queryBillTrxs(b);
                            BillDAO.insertTrxList(trxs);
                            BillDAO.insertPaymentDetails(b, trxs);
                            request.setAttribute("updated", true);
                    }

                    request.setAttribute("amount", totalDues);
                    request.setAttribute("payment", payment);

                    RequestDispatcher rd = request.getRequestDispatcher("BCP_view.jsp");
                    rd.forward(request, response);
                }
                
                
                else if (value.equals("Get Bills"))
                {
                    String HOname = request.getParameter("HOnames");
                    String address = request.getParameter("addresses");
                    String addressCopy = address;

                    int block = Integer.parseInt(address.substring(6, address.indexOf(",")));
                    address = address.substring(address.indexOf(","));
                    int lot = Integer.parseInt(address.substring(6, address.lastIndexOf(",")));

                    Bill b = new Bill();

                    b.setBlocknum(block);
                    b.setLotnum(lot);
                    
                    ArrayList<Bill> bills = BillDAO.queryHOBills(b);
                    request.setAttribute("bills", bills);
                    request.setAttribute("address", addressCopy);
                    request.setAttribute("HOname", HOname);

                    RequestDispatcher rd = request.getRequestDispatcher("BCP_view.jsp");
                    rd.forward(request, response);
                }
                
                
                else if (value.equals("getAddresses"))
                {
                    String HOnames = request.getParameter("name");
		
                    Bill b = new Bill();
                    ArrayList<String> addresses = BillDAO.queryHOAddresses(HOnames);

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

}
