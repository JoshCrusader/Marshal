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

/**It is used to check users in the database
 *
 * @author Fred Purisima
 */
public class UserDAO {

    /**It is used to check the authenticity of the userid that being inputted
     *
     * @param userID the userID that is being checked
     * @return true if the user exists and false if it doesn't
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public static boolean isUserExist(String userID)throws SQLException{
       Connection conn=Database.getDBConnection();
       String sql1="SELECT userID FROM users where userID='"+userID+"';";
       PreparedStatement pStmt1 = conn.prepareStatement(sql1);   
       ResultSet rs1=pStmt1.executeQuery();
       rs1.next();
        if(rs1.getString(1)==null){
            return false;
        }
       return true; 
    }
}
