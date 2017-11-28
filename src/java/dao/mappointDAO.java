/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DatabaseUtils;
import model.Ref_Properties;
import model.mapCategory;
import model.mappoint;

/**
 *
 * @author Andrew Santiago
 */
public class mappointDAO {

    /**
     * Retrieves a map point based on the passed mapID parameter
     * @param mapID
     * @return a single map point object
     */
    public static mappoint getPointbyID(int mapID){
		mappoint point = null;
		String sql = "SELECT * FROM mappoint WHERE mappointID = ?;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, mapID);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				point = new mappoint(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getInt(9));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return point;
	}
    
    /**
     * Retrieves a map point that is associated to a property
     * @param mapID
     * @return Single map point object with Ref_Property values.
     */
    public static mappoint getPointwithPropertybyID(int mapID){
		mappoint point = null;
		String sql = "SELECT * FROM mappoint WHERE mappointID = ?;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, mapID);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				point = new mappoint(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getInt(9),rs.getInt(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return point;
	}
    
    /**
     * Retrieves all the map points from the database
     * @return an array list of map point objects
     */
    public static ArrayList<mappoint> getAllPoints(){
		ArrayList<mappoint> mappoints = new ArrayList<>();
		String sql = "SELECT * FROM mappoint WHERE removed = 0;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
                                if(rs.getInt(9) == 1){
                                    mappoints.add(getPointwithPropertybyID(rs.getInt(1)));
                                }
                                else{
                                    mappoints.add(getPointbyID(rs.getInt(1)));
                                }
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return mappoints;
	}
    
    /**
     * Retrieves all the pending map points in the database
     * @return Array list of map point objects
     */
    public static ArrayList<mappoint> getAllPendingPoints(){
		ArrayList<mappoint> mappoints = new ArrayList<>();
		String sql = "SELECT * FROM pendingrequests_map WHERE status = 0;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				mappoints.add(getPointbyID(rs.getInt(2)));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return mappoints;
	}
    
    /**
     * Retrieves all the streets registered in the database
     * @return Array list of string objects.
     */
    public static ArrayList<String> getAllStreets(){
		ArrayList<String> streetset = new ArrayList<>();
		String sql = "SELECT * FROM REF_STREET;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				streetset.add(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return streetset;
	}
    
    /**
     * Retrieves a single mapCategory object through the passed ID parameter.
     * @param categoryID
     * @return a single mapCategory object.
     */
    public static mapCategory getCategorybyID(int categoryID){
		mapCategory cat = null;
		String sql = "SELECT * FROM ref_mappointcategory WHERE mappointcategoryID = ?";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
                        pStmt.setInt(1, categoryID);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				cat = new mapCategory(rs.getInt(1),rs.getString(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return cat;
	}
    
    /**
     * Retrieves all the categories from the database
     * @return array list of mapCategory objects.
     */
    public static ArrayList<mapCategory> getAllCategories(){
		ArrayList<mapCategory> cats = new ArrayList<>();
		String sql = "SELECT * FROM ref_mappointcategory;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				cats.add(getCategorybyID(rs.getInt(1)));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return cats;
	}
    
    /**
     * Retrieves a single ref_properties object based on the passed map point ID variable.
     * @param mapID
     * @return a single Ref_Properties object.
     */
    public static Ref_Properties getRefPropertybyMapID(int mapID){
		Ref_Properties property = null;
		String sql = "SELECT * FROM ref_properties WHERE mappointID = ?;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, mapID);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
                            //Ref_Properties(int blocknum, int lotnum, int endlotnum, String street, int property, int mappointID){
				property = new Ref_Properties(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return property;
	}
    
}
