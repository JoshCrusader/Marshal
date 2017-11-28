/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.mappointDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Andrew Santiago
 */
public class mappoint implements Serializable {
    private int mappointID;
    private double xAxis;
    private double yAxis;
    private String title;
    private String description;
    private String userID;
    private String createDate;
    private int removed;
    private int mappointcategoryID;
    private Ref_Properties property;
    
    public mappoint(){
    }
    
    public mappoint(mappoint obj){
        this.mappointID = obj.getMapID();
        this.xAxis = obj.getXAxisLongitude();
        this.yAxis = obj.getYAxisLatitude();
        this.title = obj.getTitle();
        this.description = obj.getDescription();
        this.userID = obj.getUserID();
        this.createDate = obj.getCreateDate();
        this.mappointcategoryID = obj.getMapCategoryID();
        this.removed = obj.getRemovedStatus();
    }
    
    public mappoint(int mappointID, double xAxis, double yAxis, String title, String desc, String userID, String createDate, int mappointcategoryID){
        this.mappointID = mappointID;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.title = title;
        this.description = desc;
        this.userID = userID;
        this.createDate = createDate;
        this.mappointcategoryID = mappointcategoryID;
        this.removed = 0;
    }
    
    public mappoint(int mappointID, double xAxis, double yAxis, String title, String desc, String userID, String createDate, int removed, int mappointcategoryID){
        this.mappointID = mappointID;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.title = title;
        this.description = desc;
        this.userID = userID;
        this.createDate = createDate;
        this.mappointcategoryID = mappointcategoryID;
        this.removed = removed;
    }
    
    public mappoint(int mappointID, double xAxis, double yAxis, String title, String desc, String userID, String createDate, int removed, int mappointcategoryID, int specID){
        this.mappointID = mappointID;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.title = title;
        this.description = desc;
        this.userID = userID;
        this.createDate = createDate;
        this.mappointcategoryID = mappointcategoryID;
        this.removed = removed;
        this.property = mappointDAO.getRefPropertybyMapID(specID);
    }
    
    public int getMapID(){
        return this.mappointID;
    }
    
    public double getXAxisLongitude(){
        return this.xAxis;
    }
    
    public double getYAxisLatitude(){
        return this.yAxis;
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
    
    public String getCreateDate(){
        return this.createDate;
    }
    
    public int getMapCategoryID(){
        return this.mappointcategoryID;
    }
    
    public int getRemovedStatus(){
        return this.removed;
    }
    
    public Ref_Properties getPropertyObject(){
        return this.property;
    }
    
    public void setXAxisLongitude(double newX){
        this.xAxis = newX;
    }
    
    public void setYAxisLatitude(double newY){
        this.yAxis = newY;
    }
    
    public void setTitle(String newTitle){
        this.title = newTitle;
    }
    
    public void setDescription(String newDesc){
        this.description = newDesc;
    }
    
    public void setUserID(String newUserID){
        this.userID = newUserID;
    }
    
    public void setCreateDate(String newDate){
        this.createDate = newDate;
    }
    
    public void setMapCategoryID(int newCat){
        this.mappointcategoryID = newCat;
    }
    
    public void setRemovedStatus(int newStatus){
        this.removed = newStatus;
    }
   
    
}
