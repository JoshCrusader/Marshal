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
public class Policy implements Serializable {
    private int policyID;
    private String policydesc;
    private int penaltyID;
    private int supportingdocumentID;
    private String enactmentDate;
    private String stopimplementDate;
    private String enablingBoard;
    
    public Policy(){}
    
    public Policy(int policyID, String policydesc, int penaltyID, int supDocID, String enactDate, String stopDate, String enableBoard){
        this.policyID = policyID;
        this.policydesc = policydesc;
        this.penaltyID = penaltyID;
        this.supportingdocumentID = supDocID;
        this.enactmentDate = enactDate;
        this.stopimplementDate = stopDate;
        this.enablingBoard = enableBoard;
    }
    
    public int getPolicyID(){
        return this.policyID;
    }
    
    public String getPolicyDescription(){
        return this.policydesc;
    }
    
    public int getPenaltyID(){
        return this.penaltyID;
    }
    
    public int getSupportingDocumentID(){
        return this.supportingdocumentID;
    }
    
    public String getEnactmentDate(){
        return this.enactmentDate;
    }
    
    public String getStopImplementDate(){
        return this.stopimplementDate;
    }
    
    public String getEnablingBoard(){
        return this.enablingBoard;
    }
    
}
