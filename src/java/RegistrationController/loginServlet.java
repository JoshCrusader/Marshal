package RegistrationController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Objects.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(urlPatterns = {"/Registration/loginServlet"})
public class loginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String uName= request.getParameter("uname");
            String pw=request.getParameter("password");
            boolean isValid= false;
            
           
            try{	
                
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
                    
                    
                    String searchQuery ="select * from users where userID='"+uName+"' AND passwd='"+pw+"'";
                    PreparedStatement pStmt= con.prepareStatement(searchQuery);
                    ResultSet result= pStmt.executeQuery();
                    boolean more = result.next();
                    System.out.println(more);
                    User user = new User(uName);
                    user.setPw(pw);
                     
                    
                    System.out.println("Your user name is " + uName);          
                    System.out.println("Your password is " + pw);
                    System.out.println("Query: "+searchQuery);
                   
                    if(more==false){
                          System.out.println("Sorry, you are not a registered user! Please sign up first");
                          isValid=false;
                          System.out.println("Invalid");
                          request.setAttribute("Invalid","NO");
                          request.getRequestDispatcher("index.jsp").forward(request,response);
                         
                          
                         
                    }
                    else{
                          isValid= true;
                          HttpSession session = request.getSession(true);
                          user.setUsertype(result.getInt("usertypeID"));
                          System.out.println(result.getInt("usertypeID"));

                          session.setAttribute("currentSessionUser",user); 
                          response.sendRedirect("reg.jsp"); //logged-in page      

                          session.setAttribute("currentSessionUser",user);
                          response.sendRedirect("home.jsp"); //logged-in page      

                    }

                } 


                catch (Throwable theException) 	    
                {
                     System.out.println(theException); 
                }
                       
    }
}
    
