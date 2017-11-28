/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.model;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**It is a class that is used to request a vehicle pass for the vehicle
 *
 * @author Fred Purisima
 */
public class RequestSticker implements Serializable{
    private String platenum,userid;

    /**It gets the plate number of the vehicle being requested for a vehicle pass
     *
     * @return A String representing the plate number of the vehicle being requested for a vehicle pass
     */
    public String getPlatenum() {
        return platenum;
    }

    /**It sets the plate number of the vehicle being requested for a vehicle pass
     *
     * @param platenum A String containing the plate number of the vehicle being requested for a vehicle pass
     */
    public void setPlatenum(String platenum) {
        this.platenum = platenum;
    }

    /**It gets the userID of the user of the vehicle being requested for a vehicle pass
     *
     * @return A String representing the userID of the user of the vehicle being requested for a vehicle pass
     */
    public String getUserid() {
        return userid;
    }

    /**It sets the userID of the user of the vehicle being requested for a vehicle pass
     *
     * @param userid A String containing the userID of the user of the vehicle being requested for a vehicle pass
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    /**It sets the sticker request of the user by inserting to the stickerinventory table his/her future sticker data containing the cost he/she needs to pay. It also updates the transactionid and the stickerID of the user_vehicles for reference in their payment.
     *
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public void setStickerReq() throws SQLException{
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
        
        sql="UPDATE `user_vehicles` SET `stickerID`='"+stid+"', `trxID`='"+trid+"' WHERE `plateNum`='"+this.platenum+"' and `userID`='"+this.userid+"';";
        pStmt=conn.prepareStatement(sql);
        isInserted=pStmt.executeUpdate(); 
        
        if(isInserted !=0){
            
            
        
        }
        
        
        
       
    }
    
    
}
