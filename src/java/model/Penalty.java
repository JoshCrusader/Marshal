/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Andrew Santiago
 */
public class Penalty {
    private int penaltyID;
    private int penaltyLevel;
    private String penaltyDescription;
    private double penaltyfee;
    private String penaltyAction;
    private int enablingdocumentID;
    
    public Penalty(){}
    
    public Penalty(int penaltyID, int penaltyLevel, String desc, double fee, String action, int docID){
        this.penaltyID = penaltyID;
        this.penaltyLevel = penaltyLevel;
        this.penaltyDescription = desc;
        this.penaltyfee = fee;
        this.penaltyAction = action;
        this.enablingdocumentID = docID;
    }
    
    public int getPenaltyID(){
        return this.penaltyID;
    }
    
    public int getPenaltyLevel(){
        return this.penaltyLevel;
    }
    
    public String getDescription(){
        return this.penaltyDescription;
    }
    
    public double getFee(){
        return this.penaltyfee;
    }
    
    public String getPenaltyAction(){
        return this.penaltyAction;
    }
    
    public int getEnablingDocumentID(){
        return this.enablingdocumentID;
    }
}
