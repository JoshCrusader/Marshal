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
public class Kasambahay extends Users {


    private int bNo;
    private int lNo;
    private String startdate;
    private String enddate;
    
    public Kasambahay(String userID, String password, int userType, String lname, String fname, String mame, String bDate, int photoID, int occupationID, String movingIn, int movingOutclearID, int trxID, String telno, String phoneno, String email,String startdate, String enddate,int bNo, int lNo){
        super(userID,password,userType,lname,fname,mame,bDate,photoID,occupationID,movingIn,movingOutclearID,trxID,telno,phoneno,email);
        this.bNo=bNo;
        this.lNo=lNo;
        this.startdate = startdate;
        this.enddate = enddate;
        
    }
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
    public void setStart(String startdate){
        this.startdate = startdate;
    }
    public String getStartDate(){
        return this.startdate;
    }
    public void setEnd(String endDate){
        this.enddate = endDate;
    }
    
    public String getEndDate(){
        return this.enddate;
    }

            
}
