/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DatabaseUtils;
import model.Document;
import model.Ref_Occupation;
import model.Users;

/**
 *
 * @author drjeoffreycruzada
 */
public class SearchDirectoryDAO {
    
    /**
     * Gets user by a username
     * 
     * @param username
     * @return Users with that unique username id
     */
    public static Users getUserbyUsername(String username){
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
    /**
     * Gets all the users and information to be diplayed on screen
     * 
     * @param filter 1 - no filter, 2 - Filter through occupation, 3 - Filter throough name
     * @param searchrequest - Gets occupation or name that contains this variable
     * @return ArrayList<Users> of users that may or may not have been filtered.
     */
    public static ArrayList<Users> getUnfilteredUsers(int filter, String searchrequest){
        ArrayList<Users> users = new ArrayList<Users>();
        String sql;
        if(filter == 2){
        sql = "select fname,lname,D.documentLocation,U.userid, RO.occupation,mame from users U\n" + 
						"LEFT JOIN DOCUMENTS D ON D.documentID = U.photoID\n" + 
						"LEFT JOIN REF_OCCUPATION RO ON RO.occupationID = U.occupationID\n"+
						"WHERE RO.occupation LIKE '%"+searchrequest+"%'\n" + 
						"order by lname, fname;";
        }
        else if(filter == 3){
        sql = "select fname,lname,D.documentLocation,U.userid, RO.occupation,mame from users U\n" + 
						"LEFT JOIN DOCUMENTS D ON D.documentID = U.photoID\n" + 
						"LEFT JOIN REF_OCCUPATION RO ON RO.occupationID = U.occupationID\n"+
						"WHERE CONCAT(fname,' ',mame,' ',lname) LIKE '%"+searchrequest+"%'\n" + 
						"order by lname, fname;";
        }
        else {
        sql = "select * from users U\n" + 
					"LEFT JOIN DOCUMENTS D ON D.documentID = U.photoID\n" + 
					"LEFT JOIN REF_OCCUPATION RO ON RO.occupationID = U.occupationID\n"+
					"order by lname, fname;";
        }
        
        Connection conn = DatabaseUtils.retrieveConnection();
            try{
                    
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                            Users user = new Users(rs.getString(1), rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getString(13),rs.getString(14),rs.getString(15));
                            user.setPhoto(new Document(rs.getInt(16),rs.getString(17),rs.getString(18),rs.getInt(19),rs.getString(20)));
                            user.setOccupation(new Ref_Occupation(rs.getInt(21),rs.getString(22)));
                            users.add(user);
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
        return users;
    }
    
}
