/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegistrationController;

import RegistrationController.Homemember;
import java.sql.*;
import java.io.IOException; 
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import RegistrationController.Homeowner;
import Objects.User;
import RegistrationController.Kasambahay;
import dao.HomeownerDAO;
import dao.PropertyDAO;
import dao.UserDAO;
import javax.servlet.http.HttpSession;
import model.Ref_Properties;
import model.Users;
import model.Vehicles;


/**
 *
 * @author User
 */
@WebServlet(urlPatterns = {"/Registration/Servlet"})
public class Servlet extends HttpServlet {
    
    public Servlet(){
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
     String lName= request.getParameter("lName");
     String fName= request.getParameter("fName");
     String mName= request.getParameter("mName");
     String bDate= request.getParameter("bDate");
     String uName= request.getParameter("username");
     String existingUname= request.getParameter("dL");
     String occupation= request.getParameter("occupation");
     String email= request.getParameter("email");
     String pw= request.getParameter("pw");
     String pw2= request.getParameter("pw2");
     String tNum= request.getParameter("tNum");
     String mNum= request.getParameter("mNum");
     int usertype= 1;
     int livingAs= Integer.parseInt(request.getParameter("livingAs"));
     int renting= Integer.parseInt(request.getParameter("rent"));
     boolean isexistingUname= false;
     boolean isvalidPw= false;
     String movingIn = null;
     String[] carmodels= request.getParameterValues("c1");
     String[] pnum= request.getParameterValues("v1");
     int bNo=0;
     int lNo=0;
    System.out.print("LivingAs: " + livingAs);
    System.out.println("UserType: " + usertype);
    System.out.print("Dl: " + existingUname);
    
    try{    
         System.out.print("Uname: " + uName);
            Users existingUser= UserDAO.getUserbyUsername(uName);
            if (livingAs==1){
                   bNo= Integer.parseInt(request.getParameter("bNo"));
                   lNo= Integer.parseInt(request.getParameter("lNo"));
              }
              else if (livingAs==2 || livingAs==3){

                   bNo= UserDAO.getBlocknum(existingUname);
                   System.out.print("bNo: " + bNo);
                   lNo= UserDAO.getLotnum(existingUname);
                   System.out.print("lNo: "+ lNo);
               }
         String existingHo= UserDAO.existingHomeowner(bNo, lNo);
         
         System.out.print("Existing HomeOwner: " + existingHo);
         Ref_Properties existingBno= UserDAO.getProperties(bNo, lNo);
        
        if (existingUser==null){
            System.out.println("Success!");
            
        }
        else{
            System.out.println("Invalid");
            request.setAttribute("Existing","NO");      
        }
        if (pw.equals(pw2)){
            isvalidPw= true;
        }
        else{
            isvalidPw= false;
            System.out.println("Invalid");
            request.setAttribute("Invalid","NO");
        }
        if (existingBno==null){
            System.out.println("Not existing");
            System.out.println("Invalid");
            request.setAttribute("existingBno","NO");
           
        }
        else{
            System.out.print("true");             
        }
        
        if ((isvalidPw==true) && (existingBno!=null)){
            int tempOID= UserDAO.existingOccupation(occupation);
            UserDAO.registerUser(uName, pw, lName, fName, mName, bDate, usertype, tempOID, movingIn, tNum, mNum,  email);
            UserDAO.insertPnum(uName, pnum, carmodels);
            
            if (livingAs==1 && existingHo==null){ //homeowner
              UserDAO.registerHomeowner(uName, bNo, lNo);
            }
            else if (livingAs==2 && (existingHo!=null)){
              UserDAO.registerHomemember(uName, bNo, lNo, renting);
            }
            else if ((livingAs==3) && (existingHo!=null)){
              System.out.print("HEYAH");
              UserDAO.registerKasambahay(uName, bNo, lNo);
            }
             //request.getRequestDispatcher("home.jsp").forward(request,response);
        }
          
        else{
             request.getRequestDispatcher("reg.jsp").forward(request,response);
        }
         
    } catch (Exception e){
        System.out.println(e);
    }
       
    }

   

   

}

