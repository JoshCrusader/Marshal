/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.DatabaseUtils;
import model.Vehicles;

/**
 *
 * @author Andrew Santiago
 */
public class VehiclesDAO {

    /**
     * Retrieves a vehicle based on platenum
     * @param platenum
     * @return a single Vehicle object
     */
    
    public static Vehicles GetVehicleByPlateNum(String platenum){
        Vehicles vehicle = null;
        String sql = "SELECT * FROM VEHICLES WHERE PLATENUM = ?;";
        Connection conn = DatabaseUtils.retrieveConnection();
        try{
            
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, platenum);
            ResultSet rs = pStmt.executeQuery();
            if(rs.next()){
                vehicle = new Vehicles(rs.getString("platenum"),rs.getString("model"));
                vehicle.setBanned(rs.getBoolean("banned"));
            }
            else{
                System.out.println("Vehicle with platenum "+platenum+" does not exist");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
                    if(conn != null){
                            try{
                                    conn.close();
                            }catch(Exception e){}
                }
         }
        
        return vehicle;
    }
}