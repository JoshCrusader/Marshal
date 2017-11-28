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
public class PendingRequest_Map {
    private int requestID;
    private int blocknum;
    private int lotnum;
    private int endlotnum;
    private String street;
    private String title;
    private String description;
    private String userID;
    private int category;
    private int status;
    
    public PendingRequest_Map(){}
    
    public PendingRequest_Map(int requestID, int blocknum, int lotnum, int endlotnum, String street, String title, String desc, String userID, int category, int status){
        this.requestID = requestID;
        this.blocknum = blocknum;
        this.lotnum = lotnum;
        this.endlotnum = endlotnum;
        this.street = street;
        this.title = title;
        this.description = desc;
        this.category = category;
        this.userID = userID;
        this.status = status;
    }
    
    public int getRequestID(){
        return this.requestID;
    }
    
    public int getBlocknum(){
        return this.blocknum;
    }
    
    public int getLotnum(){
        return this.lotnum;
    }
    
    public int getEndLotnum(){
        return this.endlotnum;
    }
    
    public String getStreet(){
        return this.street;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public String getUserID(){
        return this.userID;
    }
    
    public int getStatus(){
        return this.status;
    }
    
    public int getCategory(){
        return this.category;
    }
    
}
