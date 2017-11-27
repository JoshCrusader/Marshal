package RegistrationController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Homemember {
    
    public String userID;
    public int bNo;
    public int lNo;
    public int renting;
    
    public Homemember(String userID){
        this.userID=userID;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public int getRenting() {
        return renting;
    }

    public void setRenting(int renting) {
        this.renting = renting;
    }
   
    
    
}
