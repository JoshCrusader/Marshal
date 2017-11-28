
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DatabaseUtils;
import model.Penalty;
import model.Policy;
import model.Ref_PenaltyLevel;

/**
 *
 * @author Andrew Santiago
 */
public class PolicyDAO {

    /**
     * Retrieves a single Policy object through an ID
     * @param policyID
     * @return a Policy object
     */
    public static Policy getPolicybyID(int policyID){
            Policy policy = null;
            String sql = "SELECT * FROM policies WHERE policyID = ?;";
            Connection conn = DatabaseUtils.retrieveConnection();
            try{
                    
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    pStmt.setInt(1, policyID);
                    ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                        //(int policyID, String policydesc, int penaltyID, int supDocID, String enactDate, String stopDate, String enableBoard)
                            policy = new Policy(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7));
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
            return policy;
    }
    
    /**
     * Retrieves all Policies from the database
     * @return Arraylist of Policy objects
     */
    public static ArrayList<Policy> getAllPolicies(){
		ArrayList<Policy> policies = new ArrayList<>();
		String sql = "SELECT * FROM policies;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				policies.add(getPolicybyID(rs.getInt(1)));
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
		return policies;
	}
    
    /**
     * Retrieves a specific set of Policies depending on the filter settings of the user
     * @param action
     * @param sql
     * @return Arraylist of Policy objects.
     */
    public static ArrayList<Policy> getPoliciesFiltered(String action, String sql){
		ArrayList<Policy> policies = new ArrayList<>();
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				policies.add(getPolicybyID(rs.getInt(1)));
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
		return policies;
	}

    /**
     * Adds a policy to the Policies table in the database
     * @param policydesc
     * @param penaltyID
     * @param supportDocID
     * @param enactDate
     * @param stopDate
     * @param enablingBoard
     * @return true if the process is successful
     */
    public static boolean addPolicy(String policydesc, int penaltyID, int supportDocID, String enactDate, String stopDate, String enablingBoard){
        boolean setter = false;
        int polID = 0;
        int trxID = 0;
        double fee = getPenaltybyID(penaltyID).getFee();
        String sqlPass1 = "SELECT MAX(policyID) FROM policies;";
        String sqlPass2 = "INSERT INTO policies VALUES (?, ?, ?, ?, ? , ?, ?);";
        
        String sqlPass3 = "SELECT MAX(trxID) FROM trxReferences;";
        String sqlPass4 = "INSERT INTO trxReferences VALUES (?, ?, ?, ?, ?)";

        Connection conn = DatabaseUtils.retrieveConnection();
        try{
            PreparedStatement pStmt = conn.prepareStatement(sqlPass1);
            ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                            polID = rs.getInt(1) + 1;
                    }
            pStmt = conn.prepareStatement(sqlPass2);
            pStmt.setInt(1, polID);
            pStmt.setString(2, policydesc);
            pStmt.setInt(3, penaltyID);
            pStmt.setInt(4, supportDocID);
            pStmt.setString(5, enactDate);
            pStmt.setString(6, stopDate);
            pStmt.setString(7, enablingBoard);
            int result = pStmt.executeUpdate();

            if(result != 0){
                    setter = true;
            }
            
            pStmt = conn.prepareStatement(sqlPass3);
            rs = pStmt.executeQuery();
                    while(rs.next()){
                            trxID = rs.getInt(1) + 1;
                    }
            pStmt = conn.prepareStatement(sqlPass4);
            pStmt.setInt(1, trxID);
            pStmt.setDouble(2, fee);
            pStmt.setDouble(3, 0);
            pStmt.setDouble(4, fee);
            pStmt.setString(5, "Violation fee for Policy ID " + polID);
            result = pStmt.executeUpdate();

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
     * Edits an existing policy in the database
     * @param policydesc
     * @param penaltyID
     * @param supportDocID
     * @param enactDate
     * @param stopDate
     * @param enablingBoard
     * @param policyID
     * @return true if the changes are saved.
     */
    public static boolean editPolicy(String policydesc, int penaltyID, int supportDocID, String enactDate, String stopDate, String enablingBoard, int policyID){
        boolean setter = false;
        String sql = "UPDATE policies SET policydesc = ?, penaltyID = ?, supportingdocumentID = ?, enactmentDate = ?, stopimplementDate = ?, enablingBoard = ? WHERE policyID = ?; ";

        Connection conn = DatabaseUtils.retrieveConnection();
        try{
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, policydesc);
            pStmt.setInt(2, penaltyID);
            pStmt.setInt(3, supportDocID);
            pStmt.setString(4, enactDate);
            pStmt.setString(5, stopDate);
            pStmt.setString(6, enablingBoard);
            pStmt.setInt(7, policyID);
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
     * Dismisses a policy by settings its stop date now.
     * @param policyID
     * @return true if the process is successful
     */
    public static boolean dismissPolicy(int policyID){
        boolean setter = false;
        String sql = "UPDATE policies SET stopimplementDate = DATE(NOW()) WHERE policyID = ?; ";

        Connection conn = DatabaseUtils.retrieveConnection();
        try{
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, policyID);
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
    
    
    /*
    ----------------------
    ----------------------
    ----------------------
    Methods for Penalties (these objects are associated with policies so they are included here)
    ----------------------
    ----------------------
    ----------------------
    */

    /**
     * Retrieves a single Penalty object through an ID
     * @param penaltyID
     * @return Penalty object
     */

    public static Penalty getPenaltybyID(int penaltyID){
            Penalty penalty = null;
            String sql = "SELECT * FROM penalties WHERE penaltyID = ?;";
            Connection conn = DatabaseUtils.retrieveConnection();
            try{
                    
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    pStmt.setInt(1, penaltyID);
                    ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                        //Penalty(int penaltyID, int penaltyLevel, String desc, double fee, String action, int docID){
                            penalty = new Penalty(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getInt(6));
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
            return penalty;
    }
    
    /**
     * Retrieves all the penalties registered in the database
     * @return Array list of Penalty objects
     */
    public static ArrayList<Penalty> getAllPenalties(){
		ArrayList<Penalty> penalties = new ArrayList<>();
		String sql = "SELECT * FROM penalties;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				penalties.add(getPenaltybyID(rs.getInt(1)));
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
		return penalties;
	}
    
    /**
     * Retrieves a specific set of Penalty objects based on the filter settings of the user
     * @param sql
     * @return Array list of Policy objects.
     */
    public static ArrayList<Penalty> getPenaltiesFiltered(String sql){
		ArrayList<Penalty> penalties = new ArrayList<>();
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				penalties.add(getPenaltybyID(rs.getInt(1)));
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
		return penalties;
	}
    
    /**
     * Retrieves all the penalty levels registered in the database
     * @return Arraylist of Ref_PenaltyLevel objects
     */ 
    public static ArrayList<Ref_PenaltyLevel> getAllPenaltyLevels(){
		ArrayList<Ref_PenaltyLevel> levels = new ArrayList<>();
		String sql = "SELECT * FROM REF_PENALTYLEVEL;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				levels.add(new Ref_PenaltyLevel(rs.getInt(1),rs.getString(2)));
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
		return levels;
	}
    
    /**
     * Adds a penalty to the database
     * @param penaltyLevel
     * @param penaltyDescription
     * @param penaltyFee
     * @param penaltyAction
     * @param enablingDocID
     * @return true if the process is successful
     */
    public static boolean addPenalty(int penaltyLevel, String penaltyDescription, double penaltyFee, String penaltyAction, int enablingDocID){
        boolean setter = false;
        int penID = 0;
        String sqlPass1 = "SELECT MAX(penaltyID) FROM penalties;";
        String sqlPass2 = "INSERT INTO penalties VALUES (?, ?, ?, ?, ?, ?);";

        Connection conn = DatabaseUtils.retrieveConnection();
        try{
            PreparedStatement pStmt = conn.prepareStatement(sqlPass1);
            ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                            penID = rs.getInt(1) + 1;
                    }
            pStmt = conn.prepareStatement(sqlPass2);
            pStmt.setInt(1, penID);
            pStmt.setInt(2, penaltyLevel);
            pStmt.setString(3, penaltyDescription);
            pStmt.setDouble(4, penaltyFee);
            pStmt.setString(5, penaltyAction);
            pStmt.setInt(6, enablingDocID);
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
     * Edits the details of an existing penalty in the database
     * @param penaltyLevel
     * @param penaltyDescription
     * @param penaltyFee
     * @param penaltyAction
     * @param enablingDocID
     * @param policyID
     * @return true if the changes are saved
     */
    public static boolean editPenalty(int penaltyLevel, String penaltyDescription, double penaltyFee, String penaltyAction, int enablingDocID, int policyID){
        boolean setter = false;
        String sql = "UPDATE penalties SET penaltyLevel = ?, penaltydescription = ?, penaltyfee = ?, penaltyaction = ?, enablingdocumentID = ? WHERE penaltyID = ?; ";

        Connection conn = DatabaseUtils.retrieveConnection();
        try{
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, penaltyLevel);
            pStmt.setString(2, penaltyDescription);
            pStmt.setDouble(3, penaltyFee);
            pStmt.setString(4, penaltyAction);
            pStmt.setInt(5, enablingDocID);
            pStmt.setInt(6, policyID);
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
