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
public class Homeowner extends Users {


    private int bNo;
    private int lNo;
   
    
    public Homeowner(String userID, String password, int userType, String lname, String fname, String mame, String bDate, int photoID, int occupationID, String movingIn, int movingOutclearID, int trxID, String telno, String phoneno, String email,int bNo, int lNo){
        super(userID,password,userType,lname,fname,mame,bDate,photoID,occupationID,movingIn,movingOutclearID,trxID,telno,phoneno,email);
        this.bNo=bNo;
        this.lNo=lNo;
        
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

            
}
