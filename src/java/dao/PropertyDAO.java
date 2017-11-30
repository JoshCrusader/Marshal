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
import model.Ref_Properties;
import java.util.ArrayList;

/**
 *
 * @author Andrew Santiago
 */
public class PropertyDAO {
    /*
    public static ArrayList<Homemember>GetAllHomeOMembers(){
        Connection conn = DatabaseUtils.retrieveConnection();
        ArrayList<Homemember> owners = new ArrayList<Homemember>();
        try{
            String sql = "SELECT * FROM USERS U RIGHT JOIN HOMEMEMBER HM ON HM.userID = U.userID;";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                Homemember ho = new Homemember(rs.getString(1), rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getBoolean(17),rs.getInt(18),rs.getInt(19));
               owners.add(ho);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(conn != null){
                try{
                        conn.close();
                }catch(Exception e){}
            }
        }
        return owners;
    }
    */
    public static Ref_Properties getProperties(int blocknum, int lotnum){
        Connection conn = DatabaseUtils.retrieveConnection();
        Ref_Properties property = null;
        try{
            String sql = "SELECT * FROM REF_PROPERTIES WHERE BLOCKNUM = "+blocknum+" AND LOTNUM = "+lotnum+";";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                property = new Ref_Properties(rs.getInt(1),rs.getInt(2),rs.getByte(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
                
            } 
       }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(conn != null){
                try{
                        conn.close();
                }catch(Exception e){}
            }
        }
        return property;
    }
}
