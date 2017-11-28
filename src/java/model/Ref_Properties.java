/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.mappointDAO;
import java.io.Serializable;

/**
 *
 * @author Andrew Santiago
 */
public class Ref_Properties extends mappoint implements Serializable {
    private int blocknum;
    private int lotnum;
    private int endlotnum;
    private String street;
    private int propertyStatusID;
    private int mappointID;
    
    public Ref_Properties(){}
    
    public Ref_Properties(int blocknum, int lotnum, int endlotnum, String street, int property, int mappointID){
        super();
        this.blocknum = blocknum;
        this.lotnum = lotnum;
        this.endlotnum = endlotnum;
        this.street = street;
        this.propertyStatusID = property;
        this.mappointID = mappointID;
        mappoint obj = mappointDAO.getPointbyID(mappointID);
        
        super.setCreateDate(obj.getCreateDate());
        super.setDescription(obj.getDescription());
        super.setMapCategoryID(obj.getMapCategoryID());
        super.setRemovedStatus(obj.getRemovedStatus());
        super.setTitle(obj.getTitle());
        super.setUserID(obj.getUserID());
        super.setXAxisLongitude(0);
        super.setYAxisLatitude(0);
    }
    
    public Ref_Properties(int blocknum, int lotnum, int endlotnum, String street, int property, String mapTitle, String mapDesc, String userID){
        super();
        this.blocknum = blocknum;
        this.lotnum = lotnum;
        this.endlotnum = endlotnum;
        this.street = street;
        this.propertyStatusID = property;
        this.mappointID = mappointID;
        
        super.setTitle(mapTitle);
        super.setDescription(mapDesc);
        super.setUserID(userID);
    }
    
    public Ref_Properties(int blocknum, int lotnum, int endlotnum, String street, int property){
        this.blocknum = blocknum;
        this.lotnum = lotnum;
        this.endlotnum = endlotnum;
        this.street = street;
        this.propertyStatusID = property;
        this.mappointID = mappointID;
    }
    
    public int getBlockNum(){
        return this.blocknum;
    }
    
    public int getLotNum(){
        return this.lotnum;
    }
    
    public int getEndLotNum(){
        return this.endlotnum;
    }
    
    public String getStreet(){
        return this.street;
    }
    
    public int getPropertyStatusID(){
        return this.propertyStatusID;
    }
    
    public int getMapPointID(){
        return this.mappointID;
    }
    
 
}
