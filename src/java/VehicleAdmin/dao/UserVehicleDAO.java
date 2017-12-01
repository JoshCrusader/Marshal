/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.dao;

import VehicleAdmin.model.User;
import VehicleAdmin.model.UserVehicle;
import VehicleAdmin.model.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author user
 */
public class UserVehicleDAO {

    /**
     *
     * @param vehicle
     * @param userid
     * @throws SQLException
     */
    public static void insertUserVehicle(Vehicle vehicle,String userid) throws SQLException{
        Connection conn=Database.getDBConnection();
        String sql="INSERT INTO `user_vehicles` (`plateNum`, `userID`,`stickerPaid`) VALUES ('"+vehicle.getPlatenum()+"', '"+userid+"',false);";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        int isInserted=pStmt.executeUpdate(); 
        
        if(isInserted !=0){
            
            
        }
    }

    /**
     *
     * @param platenum
     * @param userid
     * @return
     * @throws SQLException
     */
    public static boolean isUserVehicle(String platenum,String userid) throws SQLException{
        Connection conn=Database.getDBConnection();
        String sql="SELECT plateNum FROM user_vehicles where plateNum='"+platenum+"' and userID='"+userid+"';";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        ResultSet rs=pStmt.executeQuery();
        rs.next();
        if(rs.getString(1)==null){
            return false;
        }
       return true; 
       
    } 

    /**
     *
     * @param platenum
     * @param userid
     * @return
     * @throws SQLException
     */
    public static Vehicle getUserVehicle(String platenum,String userid) throws SQLException{
        Connection conn=Database.getDBConnection();
        String sql="SELECT uv.plateNum,v.model,v.make,v.year,v.banned FROM user_vehicles uv join vehicles v on uv.plateNum=v.plateNum where uv.plateNum='"+platenum+"' and uv.userID='"+userid+"';";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        ResultSet rs=pStmt.executeQuery();
        rs.next();
        Vehicle userv=new Vehicle(rs.getString(1), rs.getString(2),rs.getString(3) ,rs.getDate(4), rs.getBoolean(5));
        return userv;
    
    }
    
    /**
     *
     * @param platenum
     * @param userid
     * @return
     * @throws SQLException
     */
    public static boolean deleteUserVehicle(String platenum,String userid) throws SQLException{
        Connection conn=Database.getDBConnection();
        String sql="DELETE FROM `user_vehicles` WHERE `plateNum`='"+platenum+"' and `userID`='"+userid+"';";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        int isInserted=pStmt.executeUpdate(); 
        
        if(isInserted !=0){
            return true;
            
        }
       return false;
       
    
    }
    
    /**
     *
     * @param listOfV
     * @param userid
     * @return
     * @throws SQLException
     */
    public static ArrayList<UserVehicle> getUserVehicles(ArrayList<Vehicle> listOfV,String userid)throws SQLException{
       ArrayList<UserVehicle> listOfFinalUserV=new ArrayList<UserVehicle>();
       Connection conn=Database.getDBConnection(); 
       String sql="SELECT distinct uv.userID,u.lname,u.fname,u.mame from user_vehicles uv join users u on uv.userID=u.userID where uv.userID="+userid+";";
       PreparedStatement pStmt = conn.prepareStatement(sql);
       ResultSet rs = pStmt.executeQuery();
       while(rs.next()){
           User u=new User(rs.getString(1));
            u.setLname(rs.getString(2));
            u.setFname(rs.getString(3));
            u.setMame(rs.getString(4));
                        
            UserVehicle userv=new UserVehicle(u);
            userv.insertVehicles(listOfV);
            listOfFinalUserV.add(userv);
       }
       return listOfFinalUserV;
    }
    
    /**
     *
     * @param listOfV
     * @return
     * @throws SQLException
     */
    public static ArrayList<UserVehicle> getAllUserVehicles(ArrayList<Vehicle> listOfV)throws SQLException{
        ArrayList<UserVehicle> listOfFinalUserV=new ArrayList<UserVehicle>();
        Connection conn=Database.getDBConnection();
        String sql="SELECT distinct uv.userID,u.lname,u.fname,u.mame from user_vehicles uv join users u on uv.userID=u.userID;";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next()){
                        
            User u=new User(rs.getString(1));
            u.setLname(rs.getString(2));
            u.setFname(rs.getString(3));
            u.setMame(rs.getString(4));
                        
            UserVehicle userv=new UserVehicle(u);
            userv.insertVehicles(listOfV);
            listOfFinalUserV.add(userv);
                        
        } 
        return listOfFinalUserV;
    }
    
}
