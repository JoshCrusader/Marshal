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
import model.Document;
import model.Ref_Occupation;
import model.Users;

/**
 *
 * @author drjeoffreycruzada
 */
public class UserProfileDAO {
    
    public static Users getUser(String userId){
        Users user = null;
        String sql = "SELECT *from (SELECT * FROM USERS WHERE userID = '"+userId+"') U\n" +
"								  LEFT JOIN REF_USERTYPE RT ON RT.usertypeid = U.usertypeid\n" +
"								  LEFT JOIN DOCUMENTS D ON D.documentID = U.photoID";
        Connection conn = DatabaseUtils.retrieveConnection();
            try{
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                        user = new Users(rs.getString(1), rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getString(13),rs.getString(14),rs.getString(15));
                            user.setPhoto(new Document(rs.getInt(16),rs.getString(17),rs.getString(18),rs.getInt(19),rs.getString(20)));
                            user.setOccupation(new Ref_Occupation(rs.getInt(21),rs.getString(22)));
                            
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
    public static int[] getblocknum(String userId){
            int[] addr = new int[2];
            String sql = "SELECT CONCAT_WS('',HO.LOTNUM,HM.LOTNUM,K.LOTNUM) as 'lotnum', CONCAT_WS('',HO.BLOCKNUM,HM.BLOCKNUM,K.BLOCKNUM) as 'blocknum' FROM (SELECT USERID FROM USERS WHERE USERID = '"+userId+"') U\n" + 
					"		 LEFT JOIN HOMEOWNER HO ON HO.USERID = U.USERID\n" + 
					"         LEFT JOIN HOMEMEMBER HM ON HM.USERID = U.USERID\n" + 
					"         LEFt JOIN KASAMBAHAY K ON K.USERID = U.USERID;";
            Connection conn = DatabaseUtils.retrieveConnection();
            try{
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                        addr[0] = rs.getInt(1);
                        addr[1] = rs.getInt(2);
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
            return addr;
    }
}
