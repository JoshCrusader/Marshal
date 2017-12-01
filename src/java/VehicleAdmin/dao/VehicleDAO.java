/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.dao;

import VehicleAdmin.model.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**It is used to insert,update,and get all vehicles
 *
 * @author Fred Purisima
 */
public class VehicleDAO {

    /**It is used to insert vehicle to the database
     *
     * @param vehicle A vehicle that is being inputted
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public static void insertVehicle(Vehicle vehicle) throws SQLException{
        Connection conn=Database.getDBConnection();
        String sql="INSERT INTO `vehicles` (`platenum`, `model`, `make`, `year`, `banned`) VALUES ('"+vehicle.getPlatenum()+"', '"+vehicle.getModel()+"', '"+vehicle.getMake()+"', '"+vehicle.getYear()+"',"+vehicle.isBanned()+");";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        int isInserted=pStmt.executeUpdate(); 
        
        if(isInserted !=0){
            
        }
    }
    
    /**It is used to get all vehicles in the community from the database
     *
     * @return list of all vehicles in the community
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public static ArrayList<Vehicle> getAllVehicles() throws SQLException{
        ArrayList<Vehicle> listOfV=new ArrayList<Vehicle>();
        Connection conn=Database.getDBConnection();
        String sql="SELECT * FROM vehicles;";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next()){
            listOfV.add(new Vehicle(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getBoolean(5)));
        }
         return listOfV;
     }
    
    /**It is used to update vehicle information of a specific user vehicle
     *
     * @param vehicle vehicle that is being updated
     * @return true if it updates successfully and false if otherwise
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public static boolean updateVehicle(Vehicle vehicle) throws SQLException{
        Connection conn=Database.getDBConnection();
        String sql="UPDATE `vehicles` SET `model`='"+vehicle.getModel()+"', `make`='"+vehicle.getMake()+"', `year`='"+vehicle.getYear()+"' WHERE `platenum`='"+vehicle.getPlatenum()+"';";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        int isInserted=pStmt.executeUpdate(); 
        if(isInserted !=0){
            return true;
        }
        return false;
    }
    
    /**It gets all vehicles that have way too many security reports
     *
     * @return a list of vehicles that are soon to be banned
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public static ArrayList<Vehicle> getExceedViolationVehicles()throws SQLException{
        
        ArrayList<Vehicle> listOfV=new ArrayList<Vehicle>();
        Connection conn=Database.getDBConnection();
        String sql="SELECT * FROM vehicles;";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next()){
            
            sql="SELECT count(securityReportID) FROM vehicle2vehicle where accusedplatenum='"+rs.getString(1)+"';";
            pStmt=conn.prepareStatement(sql);
            ResultSet rs1 = pStmt.executeQuery();
            rs1.next();
            sql="SELECT count(securityReportID) FROM vehicle2user where platenum='"+rs.getString(1)+"';";
            pStmt=conn.prepareStatement(sql);
            ResultSet rs2 = pStmt.executeQuery();
            rs2.next();
            if(rs1.getInt(1)+rs2.getInt(1)>=2){
                listOfV.add(new Vehicle(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getBoolean(5)));
            }
        }
        return listOfV;
    }
  
}
