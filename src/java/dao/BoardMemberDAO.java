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
import model.BoardMember;
import model.DatabaseUtils;
import model.PendingRequest_Map;
import model.Ref_Properties;
import model.Users;

/**
 *
 * @author Andrew Santiago
 */
public class BoardMemberDAO {
    
    /**
     * This method gets a Board Member object from the database that accepts a userID as the parameter.
     * @param username
     * @return Board Member object.
     */
    public static BoardMember getBoardbyUsername(String username){
            BoardMember boardmember = null;
            String sql = "SELECT * FROM boardmember WHERE userID = ?;";
            Connection conn = DatabaseUtils.retrieveConnection();
            try{
                    
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    pStmt.setString(1, username);
                    ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                        //(String userID, int positionID, String effectiveDate, String endDate, int statusID, int electionID)
                            boardmember = new BoardMember(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
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
            return boardmember;
    }
    
    /**
     * This method simply gets all the active Board Members in the database. Active members are which their end dates are not less than today.
     * @return Array list of Board Member objects
     */
    public static ArrayList<BoardMember> getAllActiveBoardMembers(){
		ArrayList<BoardMember> boardmembers = new ArrayList<>();
		String sql = "SELECT * FROM boardmember WHERE statusID = 1;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				boardmembers.add(getBoardbyUsername(rs.getString(1)));
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
		return boardmembers;
	}
    
    /*------------------------
    --------------------------
    --------------------------
    COMMUNITY MAPPING FUNCTIONS
    --------------------------
    --------------------------
    --------------------------
    */

    /**
     * This method removes a map point from the database. 
     * @param pointID
     * @return true if the removal is successful. Otherwise its false.
     */

    public static boolean removeMapPoint(int pointID){
        boolean setter = false;
        String sql = "UPDATE mappoint SET removed = 1 WHERE mappointID = ?;";
        Connection conn = DatabaseUtils.retrieveConnection();
        try{
                PreparedStatement pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, pointID);
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
    
    /**
     * This method approves a map point request from a certain homeowner/homemember's property. It inserts into the mappoint and ref_properties tables in the database.
     * @param requestID
     * @param xAxis
     * @param yAxis
     * @param title
     * @param desc
     * @param userID
     * @param mapCategoryID
     * @param blocknum
     * @param lotnum
     * @param endlotnum
     * @param street
     * @return true if the approval is successful.
     */
    public static boolean approveMapPoint(int requestID, double xAxis, double yAxis, String title, String desc, String userID, int mapCategoryID, int blocknum, int lotnum, int endlotnum, String street){
        boolean setter = false;
        String sql = "UPDATE pendingrequests_map SET status = 1 WHERE requestID = ?;";
        Connection conn = DatabaseUtils.retrieveConnection();
        try{
                PreparedStatement pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, requestID);
                int result = pStmt.executeUpdate();
                if(result != 0){
                        setter = true;
                }
                BoardMemberDAO.addMapPoint("propertyYes", xAxis, yAxis, title, desc, userID, mapCategoryID, blocknum, lotnum, endlotnum, street);
                
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
    
    /**
     * This method simply disapproves a map point request of a homeowner.
     * @param reqID
     * @return true if the disapproval is successful.
     */
    public static boolean disApproveMapPoint(int reqID){
        boolean setter = false;
        String sql = "UPDATE pendingrequests_map SET status = 2 WHERE requestID = ?;";
        Connection conn = DatabaseUtils.retrieveConnection();
        try{
                PreparedStatement pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, reqID);
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
        
    /**
     * This method simply adds a map point in the database. It will also add into the ref_properties table if the point is a property (in the category).
     * @param action
     * @param xAxis
     * @param yAxis
     * @param title
     * @param desc
     * @param userID
     * @param mapCategoryID
     * @param blocknum
     * @param lotnum
     * @param endlotnum
     * @param street
     * @return true if the adding is successful.
     */
    public static boolean addMapPoint(String action, double xAxis, double yAxis, String title, String desc, String userID, int mapCategoryID, int blocknum, int lotnum, int endlotnum, String street){
        boolean setter = false;
        int mapID = 0;
        String sqlPass1 = "SELECT MAX(mappointID) FROM mappoint;";
        String sqlPass2 = "INSERT INTO mappoint VALUES (?, ?, ?, ?, ?, ?, CURDATE(), 0, ?);";
        String sqlPass3 = "INSERT INTO ref_properties VALUES(?, ?, ?, ?, 1, ?);";

        Connection conn = DatabaseUtils.retrieveConnection();
        try{
                PreparedStatement pStmt = conn.prepareStatement(sqlPass1);
                ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				mapID = rs.getInt(1);
                                System.out.println("MAX MAP POINT ID:  " + mapID);
			}
                if(action.equals("propertyYes")){
                    pStmt = conn.prepareStatement(sqlPass2);
                    pStmt.setInt(1, (mapID + 1));
                    pStmt.setDouble(2, xAxis);
                    pStmt.setDouble(3, yAxis);
                    pStmt.setString(4, title);
                    pStmt.setString(5, desc);
                    pStmt.setString(6, userID);
                    pStmt.setInt(7, mapCategoryID);
                    int result = pStmt.executeUpdate();

                    if(result != 0){
                            setter = true;
                    }

                    pStmt = conn.prepareStatement(sqlPass3);
                    pStmt.setInt(1, blocknum);
                    pStmt.setInt(2, lotnum);
                    pStmt.setInt(3, endlotnum);
                    pStmt.setString(4, street);
                    pStmt.setInt(5, (mapID + 1));
                    result = pStmt.executeUpdate();

                    if(result != 0){
                            setter = true;
                    }
                }
                else if (action.equals("propertyNo")){
                    pStmt = conn.prepareStatement(sqlPass2);
                    pStmt.setInt(1, (mapID + 1));
                    pStmt.setDouble(2, xAxis);
                    pStmt.setDouble(3, yAxis);
                    pStmt.setString(4, title);
                    pStmt.setString(5, desc);
                    pStmt.setString(6, userID);
                    pStmt.setInt(7, mapCategoryID);
                    int result = pStmt.executeUpdate();

                    if(result != 0){
                            setter = true;
                    }
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
    
    /**
     * This method replaces the details of an existing map point.
     * @param action
     * @param title
     * @param desc
     * @param userID
     * @param mapCategoryID
     * @param blocknum
     * @param lotnum
     * @param endlotnum
     * @param street
     * @param mapID
     * @return true if the changes are saved.
     */
    public static boolean editMapPoint(String action, String title, String desc, String userID, int mapCategoryID, int blocknum, int lotnum, int endlotnum, String street, int mapID){
        boolean setter = false;
        
        String sqlPass1 = "UPDATE mappoint SET title = ?, description = ?, userID = ?, mappointcategoryID = ? WHERE mappointID = ?";
        String sqlPass2 = "UPDATE ref_properties SET blocknum = ?, lotnum = ?, endlotnum = ?, street = ? WHERE mappointID = ?";

        Connection conn = DatabaseUtils.retrieveConnection();
        PreparedStatement pStmt;
        try{
                if(action.equals("propertyYes")){
                    pStmt = conn.prepareStatement(sqlPass1);
                    pStmt.setString(1, title);
                    pStmt.setString(2, desc);
                    pStmt.setString(3, userID);
                    pStmt.setInt(4, mapCategoryID);
                    pStmt.setInt(5, mapID);
                    int result = pStmt.executeUpdate();

                    if(result != 0){
                            setter = true;
                    }

                    pStmt = conn.prepareStatement(sqlPass2);
                    pStmt.setInt(1, blocknum);
                    pStmt.setInt(2, lotnum);
                    pStmt.setInt(3, endlotnum);
                    pStmt.setString(4, street);
                    pStmt.setInt(5, mapID);
                    result = pStmt.executeUpdate();

                    if(result != 0){
                            setter = true;
                    }
                }
                else if (action.equals("propertyNo")){
                    pStmt = conn.prepareStatement(sqlPass1);
                    pStmt.setString(1, title);
                    pStmt.setString(2, desc);
                    pStmt.setString(3, userID);
                    pStmt.setInt(4, mapCategoryID);
                    pStmt.setInt(5, mapID);
                    int result = pStmt.executeUpdate();

                    if(result != 0){
                            setter = true;
                    }
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
    
    /**
     * This method gets all the pending map point requests.
     * @return an array list of PendingRequest_Map objects.
     */ 
    public static ArrayList<PendingRequest_Map> getAllPendingMapPointRequests(){
		ArrayList<PendingRequest_Map> pendingset = new ArrayList<>();
		String sql = "SELECT * FROM pendingrequests_map WHERE status = 0;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
                            //(int requestID, int blocknum, int lotnum, int endlotnum, String street, String title, String desc, String userID)
				pendingset.add(new PendingRequest_Map( rs.getInt(1), rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10) ));
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
		return pendingset;
	}
    
    /**
     * This method gets a single PendingRequest_Map object
     * @param reqID
     * @return single PendingRequest_Map object
     */
    public static PendingRequest_Map getRequestByReqID(int reqID){
            PendingRequest_Map request = null;
            String sql = "SELECT * FROM PENDINGREQUESTS_MAP WHERE requestID = ?;";
            Connection conn = DatabaseUtils.retrieveConnection();
            try{
                    
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    pStmt.setInt(1, reqID);
                    ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                            request = new PendingRequest_Map(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getInt(9), rs.getInt(10));
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
            return request;
    }
    
    //END OF COMMUNITY MAPPING FUNCTIONS//

    /**
     * This method verifies if a Board Member is still active.
     * @param username
     * @return true if the board member is still active.
     */
    
    public static boolean verifyBoardMembership(String username){
            boolean toReturn = false;
            String sql = "SELECT userID FROM boardmember WHERE userID = ? AND statusID = 1;";
            Connection conn = DatabaseUtils.retrieveConnection();
            try{
                    System.out.println("Verifying Board Membership of username: " + username);
                    
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    pStmt.setString(1, username);
                    ResultSet rs = pStmt.executeQuery();
                    if (rs.next() ) { //Execute if such a user exists
                        System.out.println("User " + username + " exists in the Board Member");
                        toReturn = true;
                    }
                    
                    else{
                        System.out.println("User doesn't exist.");
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
            return toReturn;
    }
    
}
