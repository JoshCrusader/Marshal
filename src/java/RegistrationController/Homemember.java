/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegistrationController;
import model.Users;

/**
 *
 * @author User
 */
public class Homemember extends Users {


    private int bNo;
    private int lNo;
    private boolean renting;
   
    /**
    * Home Member constructor passes all the important details of the user being registered 
    * @param userID unique username of the home member
    * @param password password that will be used to access the account of the user
    * @param userType homeowner
    * @param lname last name of the home member
    * @param fname first name of the home member
    * @param mame middle name of the home member
    * @param bDate birth date of the home member
    * @param photoID reference in the documents 
    * @param occupationID the occupation has a corresponding ID 
    * @param movingIn the date the user account was created
    * @param movingOutclearID the date the user would move out 
    * @param trxID corresponding transaction ID 
    * @param telno telephone number of the user
    * @param phoneno mobile number of the user
    * @param email email of the user
    * @param renting to know whether the user is renting or not
    * @param bNo block number of the home member
    * @param lNo lot number of the home member
    */
    public Homemember(String userID, String password, int userType, String lname, String fname, String mame, String bDate, int photoID, int occupationID, String movingIn, int movingOutclearID, int trxID, String telno, String phoneno, String email,boolean renting,int bNo, int lNo){
        super(userID,password,userType,lname,fname,mame,bDate,photoID,occupationID,movingIn,movingOutclearID,trxID,telno,phoneno,email);
        this.bNo=bNo;
        this.lNo=lNo;
        this.renting = renting;
    }
    /*
    * 
    */
        
        public int getbNo() {
        return bNo;
    }

    public void setbNo(int bNo) {
        this.bNo = bNo;
    }

    public int getlNo() {
        return lNo;
    }

   
    
    public void setlNo(int lNo) {
        this.lNo = lNo;
    }
    public void setRenting(boolean renting){
        this.renting = renting;
    }
    public boolean isRenting(){
        return this.renting;
    }

            
}
