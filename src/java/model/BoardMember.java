/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.UserDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Andrew Santiago
 */
public class BoardMember extends Users implements Serializable {
    private String userID;
    private int positionID;
    private String effectiveDate;
    private String endDate;
    private int statusID;
    private int electionID;
    
    public BoardMember(){}
    
    public BoardMember(String userID, int positionID, String effectiveDate, String endDate, int statusID, int electionID){
    super();
    this.userID = userID;
    this.positionID = positionID;
    this.effectiveDate = effectiveDate;
    this.endDate = endDate;
    this.statusID = statusID;
    this.electionID = electionID;
    
    Users userObj = UserDAO.getUserbyUsername(userID);
    super.setuserID(userObj.getuserID());
    super.setPassword(userObj.getPassword());
    super.setuserType(userObj.getuserType());
    super.setlName(userObj.getlName());
    super.setfName(userObj.getfName());
    super.setmName(userObj.getmName());
    super.setbDate(userObj.getbDate());
    super.setPhotoID(userObj.getPhotoID());
    super.setOccupationID(userObj.getOccupationID());
    super.setMovingIn(userObj.getMovingIn());
    super.setMovingOutClearID(userObj.getMovingOutClearID());
    super.setTrxID(userObj.getTrxID());
    }
    
    public String getUserID(){
        return this.userID;
    }
    
    public int getPositionID(){
        return this.positionID;
    }
    
    public String getEffectiveDate(){
        return this.effectiveDate;
    }
    
    public String getEndDate(){
        return this.endDate;
    }
    
    public int getStatusID(){
        return this.statusID;
    }
    
    public int getElectionID(){
        return this.electionID;
    }
}

