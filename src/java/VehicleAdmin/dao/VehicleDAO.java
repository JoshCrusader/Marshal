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


/**
 *
 * @author user
 */
public class VehicleDAO {

    /**
     *
     * @param vehicle
     * @throws SQLException
     */
    public static void insertVehicle(Vehicle vehicle) throws SQLException{
        Connection conn=Database.getDBConnection();
        String sql="INSERT INTO `vehicles` (`platenum`, `model`, `make`, `year`, `banned`) VALUES ('"+vehicle.getPlatenum()+"', '"+vehicle.getModel()+"', '"+vehicle.getMake()+"', '"+vehicle.getYear()+"',"+vehicle.isBanned()+");";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        int isInserted=pStmt.executeUpdate(); 
        
        if(isInserted !=0){
            
        }
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<Vehicle> getAllVehicles() throws SQLException{
        ArrayList<Vehicle> listOfV=new ArrayList<Vehicle>();
        Connection conn=Database.getDBConnection();
        String sql="SELECT * FROM vehicles;";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next()){
            listOfV.add(new Vehicle(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getBoolean(5)));
        }
         return listOfV;
     }
    
    /**
     *
     * @param vehicle
     * @return
     * @throws SQLException
     */
    public static boolean updateVehicle(Vehicle vehicle) throws SQLException{
        Connection conn=Database.getDBConnection();
        String sql="UPDATE `vehicles` SET `model`='"+vehicle.getModel()+"', `make`='"+vehicle.getMake()+"', `year`="+vehicle.getYear()+" WHERE `platenum`='"+vehicle.getPlatenum()+"';";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        int isInserted=pStmt.executeUpdate(); 
        if(isInserted !=0){
            return true;
        }
        return false;
    }
    
    
    
    
    
}
