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

/**
 *
 * @author Andrew Santiago
 */
public class HomeownerDAO {

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
