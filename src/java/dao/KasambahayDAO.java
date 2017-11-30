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
import RegistrationController.Kasambahay;
import java.util.ArrayList;

/**
 *
 * @author Andrew Santiago
 */
public class KasambahayDAO {
    
    public static ArrayList<Kasambahay>GetAllKasambahay(){
        Connection conn = DatabaseUtils.retrieveConnection();
        ArrayList<Kasambahay> owners = new ArrayList<Kasambahay>();
        try{
            String sql = "SELECT * FROM USERS U RIGHT JOIN HOMEMEMBER HM ON HM.userID = U.userID;";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                Kasambahay ho = new Kasambahay(rs.getString(1), rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(17),rs.getString(18),rs.getInt(19),rs.getInt(20));
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
    public static Kasambahay GetKasambahay(String userId){
        Connection conn = DatabaseUtils.retrieveConnection();
        Kasambahay owner = null;
        try{
            String sql = "SELECT * FROM USERS U RIGHT JOIN HOMEMEMBER HM ON HM.userID = U.userID where U.userID = '"+userId+"';";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                owner = new Kasambahay(rs.getString(1), rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(17), rs.getString(18),rs.getInt(19),rs.getInt(20));
                
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
        return owner;
    }
    /**
     * This method adds a request and its details to the PendingRequests_Map table in the database.
     * @param blocknum
     * @param lotnum
     * @param endlotnum
     * @param street
     * @param title
     * @param description
     * @param userID
     * @param category
     * @return true if the process is successful.
     */
    public static boolean requestMapPoint(int blocknum, int lotnum, int endlotnum, String street, String title, String description, String userID, int category){
        boolean setter = false;
        int reqID = 0;
        String sqlPass1 = "SELECT MAX(mappointID) FROM mappoint;";
        String sqlPass2 = "INSERT INTO pendingrequests_map VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 0);";

        Connection conn = DatabaseUtils.retrieveConnection();
        try{
                PreparedStatement pStmt = conn.prepareStatement(sqlPass1);
                ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				reqID = rs.getInt(1);
			}
                    pStmt = conn.prepareStatement(sqlPass2);
                    pStmt.setInt(1, (reqID + 1));
                    pStmt.setInt(2, blocknum);
                    pStmt.setInt(3, lotnum);
                    pStmt.setInt(4, endlotnum);
                    pStmt.setString(5,street);
                    pStmt.setString(6, title);
                    pStmt.setString(7, description);
                    pStmt.setString(8, userID);
                    pStmt.setInt(9, category);
                    int result = pStmt.executeUpdate();

                    if(result != 0){
                            setter = true;
                    }
        }catch(Exception e){
            e.printStackTrace();
            setter = false;
        }finally{
                if(conn != null){
                        try{
                                conn.close();
                        }catch(Exception e){}
                }
        }
        return setter;
    }
    
}
