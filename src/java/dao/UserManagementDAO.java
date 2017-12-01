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
import model.Ref_Usertype;
import model.RolesModule;
import model.Users;

/**
 *
 * @author drjeoffreycruzada
 */
public class UserManagementDAO {
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
    * Gets all the types of users
    * @return ArrayList<Ref_Usertype> a list of usertypes
    */
   public static ArrayList<Ref_Usertype> getAllUsertype(){
       ArrayList<Ref_Usertype> usertypes = new ArrayList<Ref_Usertype>();
       String sql = "SELECT * FROM REF_USERTYPE;";
        Connection conn = DatabaseUtils.retrieveConnection();
        try{

                PreparedStatement pStmt = conn.prepareStatement(sql);
                ResultSet rs = pStmt.executeQuery();
                while(rs.next()){
                        Ref_Usertype atype;
                        atype = new Ref_Usertype(rs.getInt(1),rs.getString(2));
                        usertypes.add(atype);
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
       return usertypes;
   }
   public static ArrayList<RolesModule> getAllRoles(){
       ArrayList<RolesModule> roles = new ArrayList<RolesModule>();
       String sql = "SELECT * FROM ROLESMODULE WHERE ;";
        Connection conn = DatabaseUtils.retrieveConnection();
        try{

                PreparedStatement pStmt = conn.prepareStatement(sql);
                ResultSet rs = pStmt.executeQuery();
                while(rs.next()){
                        RolesModule role;
                        role = new RolesModule(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
                        roles.add(role);
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
       return roles;
   }
   public static boolean roleModule(){
       ArrayList<Ref_Usertype> alltypes = getAllUsertype();
       
       return false;
   }
}
