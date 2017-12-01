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

/**It is used to set vehicle pass requests and to approved paid requests
 *
 * @author Fred Purisima
 */
public class StickerDAO {
   
    /**It is used to set the sticker request of the user for payment
     *
     * @param platenum plate number of the vehicle being requested to have a sticker
     * @param userid userid of the vehicle being requested to have a sticker
     * @param price price of the sticker
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */

    public static void setStickerReq(String platenum,String userid,double price) throws SQLException{
        Connection conn=Database.getDBConnection();
        
        String sql="INSERT INTO `stickerinventory` (`cost`) VALUES ('"+price+"');";
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
    
    /**It is used to approve paid sticker requests
     *
     * @param userdat A checkbox value of the ones that are approved by the board member
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
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
