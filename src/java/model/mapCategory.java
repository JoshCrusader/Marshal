/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Andrew Santiago
 */
public class mapCategory {
    private int mapCategoryID;
    private String mappointCategory;
    
    public mapCategory(){}
    
    public mapCategory(int mapCategoryID, String category){
        this.mapCategoryID = mapCategoryID;
        this.mappointCategory = category;
    }
    
    public int getMapCatID(){
        return this.mapCategoryID;
    }
    
    public String getMapCatDescription(){
        return this.mappointCategory;
    }
}
