package DuesAndFees.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DuesAndFees.Model.*;
import DuesAndFees.DAO.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Servlet implementation class MonthlyDues
 */
@WebServlet("/DuesAndFees/MonthlyDuesController")
public class MonthlyDuesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonthlyDuesController() {
        super();
    }

	/**
     * @param request
     * @param response
     * 
     * This <code>doPost()</code> does a rough validation of the input fields and if passes the user inputs
     * to the error checker function at the <code>MonthlyDuesDAO</code> controller. Once passed, it will call the
     * insert function at the same DAO to insert the user inputs to the database.
     * 
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
                Calendar c = Calendar.getInstance();
                
                if (!request.getParameter("amount").equals("") && !request.getParameter("start_month").equals("") && !request.getParameter("start_year").equals("") && !request.getParameter("end_month").equals("") && !request.getParameter("end_year").equals("")) {
                
                    double amount = Double.parseDouble(request.getParameter("amount"));
                    int startMonth = Integer.parseInt(request.getParameter("start_month"));
                    int startYear = Integer.parseInt(request.getParameter("start_year"));
                    int endMonth = Integer.parseInt(request.getParameter("end_month"));
                    int endYear = Integer.parseInt(request.getParameter("end_year"));

                    if (startYear >= c.get(Calendar.YEAR) || (startYear == c.get(Calendar.YEAR) && startMonth >= c.get(Calendar.MONTH))) {
                    
                        if ((startMonth <= endMonth && startYear == endYear) || startYear < endYear) {

                            MonthlyDues m = new MonthlyDues();

                            m.setAmount(amount);
                            m.setStartMonth(startMonth);
                            m.setStartYear(startYear);
                            m.setEndMonth(endMonth);
                            m.setEndYear(endYear);

                            if (MonthlyDuesDAO.errorCheckStartMY(startMonth, startYear)) {
                            
                                int checkFlag = MonthlyDuesDAO.insertDues(m);

                                if (checkFlag != 0) {
                                    
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

                                        out.print("<h3><font color='red'>Success! Implementing " + amount + " Pesos for " + months.get(startMonth-1) + " " + startYear + " until " + months.get(endMonth-1) + " " + endYear + ".</font></h3>");
                                        request.getRequestDispatcher("create_dues.jsp").include(request, response);

                                }

                                else {
                                        out.print("<h3><font color='red'>Please enter Valid Details</font></h3>");
                                        request.getRequestDispatcher("create_dues.jsp").include(request, response);
                                }
                                
                            }
                            
                            else {
                                
                                out.print("<h3><font color='red'>Start month/year and End month/year conflicts with existing Dues plan. Please check all current and future Monthly Dues plan.</font></h3>");
                                request.getRequestDispatcher("create_dues.jsp").include(request, response);
                                
                            }

                        }
                    
                        else {
                        
                            out.print("<h3><font color='red'>Start month/year cannot be greater than End month/year</font></h3>");
                            request.getRequestDispatcher("create_dues.jsp").include(request, response);
                        
                        }
                        
                    }
                    
                    else {
                        
                        out.print("<h3><font color='red'>Start month/year cannot be less than current month/year</font></h3>");
                        request.getRequestDispatcher("create_dues.jsp").include(request, response);
                        
                    }

                }
                
                else {
                    
                    out.print("<h3><font color='red'>You have not completed all input fields!</font></h3>");
                    request.getRequestDispatcher("create_dues.jsp").include(request, response);
                    
                }
                
	}

}
