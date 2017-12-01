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
public class UserDAO {

    /**
     *
     * @param userID
     * @return
     * @throws SQLException
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
