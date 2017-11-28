/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.model;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**It is a class that is used to approved sticker request that is selected by the board member after they completed their payments
 *
 * @author Fred Purisima
 */
public class ConfirmSticker implements Serializable{
    private String[] userdat;

    /**Gets the String array of userdat 
     *
     * @return A String array that contains the checkbox value clicked by the boardmember
     */
    public String[] getUserdat() {
        return userdat;
    }

    /**Sets the String array of userdat 
     *
     * @param userdat A String array containing the checkbox value clicked by the boardmember from the form table
     */
    public void setUserdat(String[] userdat) {
        this.userdat = userdat;
    }
    
    /**It updates the stickerIssuedBy data in the database by the board member who approves the request based on its plate no. and its userID
     *
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public void setApprovedReq() throws SQLException{
        Connection conn=Database.getDBConnection();
        
        for(int i=0;i<userdat.length;i++){
            String[] parts = this.userdat[i].split(" ");
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
