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
import model.Users;

/**
 *
 * @author drjeoffreycruzada
 */
public class SearchDirectoryDAO {
     Users user = null;
            String sql = "SELECT * FROM USERS WHERE userID = ?;";
            Connection conn = DatabaseUtils.retrieveConnection();
            try{
                    
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    pStmt.setString(1, username);
                    ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                            user = new Users(rs.getString(1), rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getString(13),rs.getString(14),rs.getString(15));
                    }
            }catch(Exception e){
                    e.printStackTrace();
            }finally{
                    if(conn != null){
                            try{
                                    conn.close();
                            }catch(Exception e){}
                    }
            }
            return user;
    }
}
