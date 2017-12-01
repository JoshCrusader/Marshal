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


/**It is used to have access to the database containing user vehicles data
 *
 * @author Fred Purisima
 */
public class UserVehicleDAO {

    /**It is used to insert user vehicle to the database
     *
     * @param vehicle the vehicle that is being inserted to the database 
     * @param userid owner of the vehicle
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public static void insertUserVehicle(Vehicle vehicle,String userid) throws SQLException{
        Connection conn=Database.getDBConnection();
        String sql="INSERT INTO `user_vehicles` (`plateNum`, `userID`,`stickerPaid`) VALUES ('"+vehicle.getPlatenum()+"', '"+userid+"',false);";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        int isInserted=pStmt.executeUpdate(); 
        
        if(isInserted !=0){
            
            
        }
    }

    /**It is used to check if the user owns the vehicle accurately
     *
     * @param platenum plate number of the vehicle that is being checked
     * @param userid userid of the allegedly user that is being checked
     * @return true if the user owns the vehicle and false if it doesn't 
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
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

    /**It is used to get the information of a specific vehicle 
     *
     * @param platenum plate number of the vehicle being checked on
     * @param userid userid of the vehicle being checked on
     * @return the Vehicle object containing information regarding that specific vehicle
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
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
    
    /**It is used to delete a specific user vehicle according to user input
     *
     * @param platenum plate number of the vehicle being deleted
     * @param userid the user who wants its vehicle to be deleted
     * @return true if it is deleted successfully and false if its otherwise
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
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
    
    /**It is used to get all vehicles owned by a single user
     *
     * @param listOfV all the vehicles in the community
     * @param userid the user who is being checked on
     * @return a list of user vehicles that the user owns
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
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
    
    /**It is used to get all of the user's user vehicles
     *
     * @param listOfV all the vehicles in the community
     * @return a list of all user vehicles that each user owns in the community
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
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
