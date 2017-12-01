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
import model.PendingRequest_Map;
import model.Ref_Properties;
import model.Users;
import model.mapCategory;
import model.mappoint;

/**
 *
 * @author Andrew Santiago
 */
public class mappointDAO {
    
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
                addMapPoint("propertyYes", xAxis, yAxis, title, desc, userID, mapCategoryID, blocknum, lotnum, endlotnum, street);
                
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
     * Retrieves a map point based on the passed mapID parameter
     * @param mapID
     * @return a single map point object
     */
    public static mappoint getPointbyID(int mapID){
		mappoint point = null;
		String sql = "SELECT * FROM mappoint WHERE mappointID = ?;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, mapID);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				point = new mappoint(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getInt(9));
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
		return point;
	}
    
    /**
     * Retrieves a map point that is associated to a property
     * @param mapID
     * @return Single map point object with Ref_Property values.
     */
    public static mappoint getPointwithPropertybyID(int mapID){
		mappoint point = null;
		String sql = "SELECT * FROM mappoint WHERE mappointID = ?;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, mapID);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				point = new mappoint(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getInt(9),rs.getInt(1));
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
		return point;
	}
    
    /**
     * Retrieves all the map points from the database
     * @return an array list of map point objects
     */
    public static ArrayList<mappoint> getAllPoints(){
		ArrayList<mappoint> mappoints = new ArrayList<>();
		String sql = "SELECT * FROM mappoint WHERE removed = 0;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
                                if(rs.getInt(9) == 1){
                                    mappoints.add(getPointwithPropertybyID(rs.getInt(1)));
                                }
                                else{
                                    mappoints.add(getPointbyID(rs.getInt(1)));
                                }
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
		return mappoints;
	}
    
    /**
     * Retrieves all the pending map points in the database
     * @return Array list of map point objects
     */
    public static ArrayList<mappoint> getAllPendingPoints(){
		ArrayList<mappoint> mappoints = new ArrayList<>();
		String sql = "SELECT * FROM pendingrequests_map WHERE status = 0;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				mappoints.add(getPointbyID(rs.getInt(2)));
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
		return mappoints;
	}
    
    /**
     * Retrieves all the streets registered in the database
     * @return Array list of string objects.
     */
    public static ArrayList<String> getAllStreets(){
		ArrayList<String> streetset = new ArrayList<>();
		String sql = "SELECT * FROM REF_STREET;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				streetset.add(rs.getString(1));
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
		return streetset;
	}
    
    /**
     * Retrieves a single mapCategory object through the passed ID parameter.
     * @param categoryID
     * @return a single mapCategory object.
     */
    public static mapCategory getCategorybyID(int categoryID){
		mapCategory cat = null;
		String sql = "SELECT * FROM ref_mappointcategory WHERE mappointcategoryID = ?";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
                        pStmt.setInt(1, categoryID);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				cat = new mapCategory(rs.getInt(1),rs.getString(2));
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
		return cat;
	}
    
    /**
     * Retrieves all the categories from the database
     * @return array list of mapCategory objects.
     */
    public static ArrayList<mapCategory> getAllCategories(){
		ArrayList<mapCategory> cats = new ArrayList<>();
		String sql = "SELECT * FROM ref_mappointcategory;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				cats.add(getCategorybyID(rs.getInt(1)));
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
		return cats;
	}
    
    /**
     * Retrieves a single ref_properties object based on the passed map point ID variable.
     * @param mapID
     * @return a single Ref_Properties object.
     */
    public static Ref_Properties getRefPropertybyMapID(int mapID){
		Ref_Properties property = null;
		String sql = "SELECT * FROM ref_properties WHERE mappointID = ?;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, mapID);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
                            //Ref_Properties(int blocknum, int lotnum, int endlotnum, String street, int property, int mappointID){
				property = new Ref_Properties(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
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
		return property;
	}
    
    // From HomeownerDAO.java
    
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
    
     /**
     * Retrieves a user based on the passed userID
     * @param username
     * @return a single User object
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
}
