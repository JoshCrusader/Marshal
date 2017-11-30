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
  
    //String occupation; int bNo; int lNo; String street; int tNo; int mNo;
    //String k1; String k2;String v1; String v2;String email;String pw;String pw2;
     String lName= request.getParameter("lName");
     String fName= request.getParameter("fName");
     String mName= request.getParameter("mName");
     String bDate= request.getParameter("bDate");
     String uName= request.getParameter("username");
     String occupation= request.getParameter("occupation");
     String email= request.getParameter("email");
     String pw= request.getParameter("pw");
     String pw2= request.getParameter("pw2");
     String tNum= request.getParameter("tNum");
     String mNum= request.getParameter("mNum");
     int usertype= Integer.parseInt(request.getParameter("usertype"));
     int livingAs= Integer.parseInt(request.getParameter("livingAs"));
     int bNo= Integer.parseInt(request.getParameter("bNo"));
     int lNo= Integer.parseInt(request.getParameter("lNo"));
     int renting= Integer.parseInt(request.getParameter("rent"));
     int tempOID = 0;
     boolean isexistingUname= false;
     boolean isvalidPw= false;

    
        System.out.println("UserType: " + usertype);
     
    try{
         Users existingUser= UserDAO.getUserbyUsername(uName);
         Users existingHo= HomeownerDAO.GetHomeOwner(uName);
         Ref_Properties existingBno= PropertyDAO.getProperties(lNo, bNo);
        
        if (existingUser==null){
            System.out.println(existingUser);
            
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
        
        Class.forName("com.mysql.jdbc.Driver"); //must be changed to DatabaseUtils for it to be shorter
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");

       
        
        if (existingBno==null){
            System.out.println(existingBno);
            System.out.println("Invalid");
            request.setAttribute("existingBno","NO");
           
        }
        else{
            System.out.print("true");             
        }
        
        if ((isvalidPw==true) && (existingBno!=null)){

              String occ= "Select occupationID from ref_occupation where occupation='"+occupation+"'";
              PreparedStatement o= con.prepareStatement(occ);
              ResultSet rs = o.executeQuery();
              if (rs.next()){
                  tempOID = rs.getInt(1);
              }
              else{
                  String occInsert="Insert into ref_occupation (occupation) VALUES ('"+occupation+"')";
                  PreparedStatement ins= con.prepareStatement(occInsert);
                  ins.executeUpdate();
                  String occ1= "Select occupationID from ref_occupation where occupation='"+occupation+"'";
                  PreparedStatement o1= con.prepareStatement(occ1);
                  ResultSet rs1 = o1.executeQuery();
                  rs1.beforeFirst();
                  System.out.print(occ1);
                  tempOID = rs1.getInt(1);
              }
              
              String[] carmodels= request.getParameterValues("c1");
              String[] pnum= request.getParameterValues("v1");
              System.out.println(carmodels.length);
            
              for(int i=0; i<pnum.length; i++){
                   String model= "Insert into vehicles (platenum, model) VALUES ('"+pnum[i]+"','"+carmodels[i]+"')";
                   PreparedStatement m= con.prepareStatement(model);
                   System.out.println("model");
                   m.executeUpdate();
                   /*String userVehicle= "Insert into user_vehicles (platenum, userID) VALUES ('"+pnum[i]+"','"+uName+"')";
                   PreparedStatement user= con.prepareStatement(userVehicle);
                   System.out.println("userVehicle");
                   user.executeUpdate();*/
              }
            String statement= "INSERT INTO users  (userID, passwd, lname, fname, mame, bDate, usertypeID, occupationID, movingIn, telno,phoneno,email) VALUES ('"+uName+"','"+pw+"','"+lName+"','"+mName+"','"+fName+"','"+bDate+"', "+usertype+", '"+tempOID+"', NOW(), '"+tNum+"', '"+mNum+"','"+email+"')";
            PreparedStatement a = con.prepareStatement(statement);
            System.out.print(statement);
            a.executeUpdate();
            User a1= new User(uName);
            a1.setPw(pw);
            a1.setfName(lName); 
            a1.setlName(fName);
            a1.setmName(mName);
            a1.setDate(bDate);  
            a1.setUsertype(usertype);
            a1.settNum(tNum);
            a1.setmNum(mNum); 
            a1.setEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("RegisterdUser",a1);
           
            /*String referenceproperties= "INSERT INTO ref_properties (blocknum,lotnum,mappointID) VALUES ("+bNo+","+lNo+",2)"; //need to insert other rows
            PreparedStatement ref= con.prepareStatement(referenceproperties);
            System.out.print(referenceproperties);
            ref.executeUpdate();
            request.setAttribute("user",a1);*/
            
            if (livingAs==1){ //homeowner

              String sql= "INSERT INTO homeowner  (blocknum,lotnum,userid) VALUES ('"+bNo+"','"+lNo+"','"+uName+"')";
              PreparedStatement ho = con.prepareStatement(sql);
              System.out.print(sql);
              ho.executeUpdate();
              //Homeowner h1= new Homeowner(bNo,lNo);
              //h1.setUserID(uName);
              //request.setAttribute("homeowner",h1);
            }
            else if (livingAs==2){
              String sql= "INSERT INTO homemember  (userid,blocknum,lotnum, renting) VALUES ('"+uName+"',"+bNo+","+lNo+","+renting+")";
              PreparedStatement ho = con.prepareStatement(sql);
              System.out.print(sql);
              ho.executeUpdate();
              //Homemember h1= new Homemember(uName);
              //h1.setbNo(bNo);
              //h1.setlNo(lNo);
              //request.setAttribute("homemember",h1);
            }
            else if ((livingAs==3) && (existingHo!=null)){
              String sql= "INSERT INTO kasambahay (userid,blocknum,lotnum) VALUES ('"+uName+"',"+bNo+","+lNo+")";
              PreparedStatement ho = con.prepareStatement(sql);
              System.out.print(sql);
              ho.executeUpdate();
       
              //Kasambahay h1 = new Kasambahay(uName);
              //h1.setbNo(bNo);
              //h1.setlNo(lNo);
              //request.setAttribute("kasambahay",h1);
            }
            
        }
          
        else{
             request.getRequestDispatcher("reg.jsp").forward(request,response);
        }
          
        con.close();
    } catch (Exception e){
        System.out.println(e);
    }
       
    }

   

   

}

