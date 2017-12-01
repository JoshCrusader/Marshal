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
public class navDAO {
    public static boolean isAllowed(int moduleid, int usertypeid){
            Users user = null;
            String sql = "SELECT * FROM (SELECT * FROM ROLES_MODULE WHERE REMOVED IS NULL) RM WHERE moduleID = "+moduleid+" AND usertypeid = "+usertypeid+";";
            Connection conn = DatabaseUtils.retrieveConnection();
            try{
                    
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                        return true;
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
            return false;
    }
   
}
