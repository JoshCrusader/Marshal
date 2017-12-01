/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class StickerDAO {
   
    /**
     *
     * @param platenum
     * @param userid
     * @throws SQLException
     */

    public static void setStickerReq(String platenum,String userid) throws SQLException{
       Connection conn=Database.getDBConnection();
        
        String sql="INSERT INTO `stickerinventory` (`cost`) VALUES ('30.00');";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        int isInserted=pStmt.executeUpdate(); 
        
        if(isInserted !=0){

        } 
        String sql1="SELECT trxID FROM trxreferences order by trxID desc;";
        PreparedStatement pStmt1=conn.prepareStatement(sql1);
        ResultSet rs1 = pStmt1.executeQuery();
        rs1.next();
        int trid=rs1.getInt(1);
                
        String sql2="SELECT stickerID FROM stickerinventory order by stickerID desc;";
        PreparedStatement pStmt2=conn.prepareStatement(sql2);
        ResultSet rs2 = pStmt2.executeQuery();
        rs2.next();
        int stid=rs2.getInt(1);
        
        sql="UPDATE `user_vehicles` SET `stickerID`='"+stid+"', `trxID`='"+trid+"' WHERE `plateNum`='"+platenum+"' and `userID`='"+userid+"';";
        pStmt=conn.prepareStatement(sql);
        isInserted=pStmt.executeUpdate(); 
        
        if(isInserted !=0){
            
            
        
        }
    
    }
    
    /**
     *
     * @param userdat
     * @throws SQLException
     */
    public static void setApprovedReq(String[] userdat) throws SQLException{
        Connection conn=Database.getDBConnection();
        
        for(int i=0;i<userdat.length;i++){
            String[] parts = userdat[i].split(" ");
            String plateno=parts[0];
            String user=parts[1];
            String sql="UPDATE `user_vehicles` SET `stickerissuedBy`='4' WHERE `plateNum`='"+plateno+"' and `userID`='"+user+"';";
            PreparedStatement pStmt=conn.prepareStatement(sql);
            int isInserted=pStmt.executeUpdate(); 
        
            if(isInserted !=0){
            
            
        
            }
        
        
        }
        
        
        
        
    
    }
}
