/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Andrew Santiago
 */
public class Users implements Serializable{
    private String userID;
    private String password;
    private int userType;
    private String lname;
    private String fname;
    private String mame;
    private String bDate;
    private int photoID;
    private int occupationID;
    private String movingIn;
    private int movingOutclearID;
    private int trxID;
    
    public Users(){}
    
    
    public Users(String userID, String password, int userType, String lname, String fname, String mame, String bDate, int photoID, int occupationID, String movingIn, int movingOutclearID, int trxID){
        this.userID = userID;
        this.password = password;
        this.userType = userType;
        this.lname = lname;
        this.fname = fname;
        this.mame = mame;
        this.bDate = bDate;
        this.photoID = photoID;
        this.occupationID = occupationID;
        this.movingIn = movingIn;
        this.movingOutclearID = movingOutclearID;
        this.trxID = trxID;
    }
    
    public String getuserID(){return this.userID;}
    public String getPassword(){return this.password;}
    public int getuserType(){return this.userType;}
    public String getlName(){return this.lname;}
    public String getfName(){return this.fname;}
    public String getmName(){return this.mame;}
    public String getbDate(){return this.bDate;}
    public int getPhotoID(){return this.photoID;}
    public int getOccupationID(){return this.occupationID;}
    public String getMovingIn(){return this.movingIn;}
    public int getMovingOutClearID(){return this.movingOutclearID;}
    public int getTrxID(){return this.trxID;}
    
    //setters
    public void setuserID(String newID){
        this.userID = newID;
    }
    public void setPassword(String newPW){
        this.password = newPW;
    }
    public void setuserType(int newType){
        this.userType = newType;
    }
    public void setlName(String newlName){
        this.lname = newlName;
    }
    public void setfName(String newfName){
        this.fname = newfName;
    }
    public void setmName(String newmName){
        this.mame = newmName;
    }
    public void setbDate(String newbDate){
        this.bDate = newbDate;
    }
    public void setPhotoID(int newphotoID){
        this.photoID = newphotoID;
    }
    public void setOccupationID(int newOccupationID){
        this.occupationID = newOccupationID;
    }
    public void setMovingIn(String newMovingIn){
        this.movingIn = newMovingIn;
    }
    public void setMovingOutClearID(int newMovingOutClearID){
        this.movingOutclearID = newMovingOutClearID;
    }
    public void setTrxID(int newTRXID){
        this.trxID = newTRXID;
    }
    
    public int geuserType(){
        return this.userType;
    }
}
