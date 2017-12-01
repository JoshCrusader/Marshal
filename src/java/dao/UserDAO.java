/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Objects.User;
import RegistrationController.Homemember;
import RegistrationController.Homeowner;
import RegistrationController.Kasambahay;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import model.DatabaseUtils;
import model.Ref_Properties;
import model.Users;
import model.Vehicles;

/**
 *
 * @author Andrew Santiago
 */
public class UserDAO {

    /**
     * Verifies if the passed userID is part of the board members or not
     * @param username
     * @return true if the user is part of the board members
     */
    public static Users verifyBoardMembership(String username){
            Users user = null;
            String sql = "SELECT userID FROM boardmember WHERE userID = ?;";
            Connection conn = DatabaseUtils.retrieveConnection();
            try{
                    
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    pStmt.setString(1, username);
                    ResultSet rs = pStmt.executeQuery();
                    if (rs.next() ) { //Execute if such a user exists
                        System.out.println("user exists");
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
            return user;
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
    public static ArrayList<Homeowner>GetAllHomeOwner(){
        Connection conn = DatabaseUtils.retrieveConnection();
        ArrayList<Homeowner> owners = new ArrayList<Homeowner>();
        try{
            String sql = "SELECT * FROM USERS U RIGHT JOIN HOMEOWNER HO ON HO.userID = U.userID;";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                Homeowner ho = new Homeowner(rs.getString(1), rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getInt(16),rs.getInt(17));
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
      public static Homeowner GetHomeOwner(String userId){
        Connection conn = DatabaseUtils.retrieveConnection();
        Homeowner owner = null;
        try{
            String sql = "SELECT * FROM USERS U RIGHT JOIN HOMEOWNER HO ON HO.userID = U.userID where U.userID = '"+userId+"';";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            System.out.print(rs);
            while(rs.next()){
                owner = new Homeowner(rs.getString(1), rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getInt(16),rs.getInt(17));
                
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
     public static void registerUser(String userID, String passwd, String lname, String fname, String mame, String bDate, int usertypeID, int occupationID, String movingIn, String telno, String phoneno, String email){
           
            String statement= "INSERT INTO users  (userID, passwd, lname, fname, mame, bDate, usertypeID, occupationID, movingIn, telno,phoneno,email) VALUES (?,?,?,?,?,?,?,?, NOW(),?,?,?)";
            Connection conn = DatabaseUtils.retrieveConnection();
            try{
                
                PreparedStatement a = conn.prepareStatement(statement);
                System.out.print(statement);
                    a.setString(1, userID);
                    a.setString(2, passwd);
                    a.setString(3, lname);
                    a.setString(4, fname);
                    a.setString(5,mame);
                    a.setString(6, bDate);
                    a.setInt(7, usertypeID);
                    a.setInt(8, occupationID);
                    a.setString(9, telno);
                    a.setString(10, phoneno);
                    a.setString(11, email);
                    a.executeUpdate();
            }catch(Exception e){
                    e.printStackTrace();
            }finally{
                    if(conn != null){
                            try{
                                    conn.close();
                            }catch(Exception e){}
                    }
            }
    }
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
      public static void registerHomeowner( String userID, int blocknum, int lotnum){
     
       String statement=  "INSERT INTO homeowner  (userID,blocknum,lotnum) VALUES ('"+userID+"','"+blocknum+"','"+lotnum+"')";
       Connection conn = DatabaseUtils.retrieveConnection();
       try{
             PreparedStatement ho = conn.prepareStatement(statement);
             System.out.print(statement);
             ho.executeUpdate();
       }catch(Exception e){
               e.printStackTrace();
       }finally{
               if(conn != null){
                       try{
                               conn.close();
                       }catch(Exception e){}
               }
       }
       
    }
      
      public static void registerHomemember(String userID, int blocknum, int lotnum, int renting){
       Homemember user = null;
       String statement=  "INSERT INTO homemember (userID, blocknum, lotnum, renting) VALUES ('"+userID+"','"+blocknum+"','"+lotnum+"','"+renting+"')";
       Connection conn = DatabaseUtils.retrieveConnection();
       try{
             PreparedStatement ho = conn.prepareStatement(statement);
             System.out.print(statement);
             ho.executeUpdate();
       }catch(Exception e){
               e.printStackTrace();
       }finally{
               if(conn != null){
                       try{
                               conn.close();
                       }catch(Exception e){}
               }
       }
     
    }
       public static void registerKasambahay(String userID,int blocknum, int lotnum){
       Kasambahay user = null;
       String sql= "INSERT INTO kasambahay (userID,blocknum,lotnum) VALUES ('"+userID+"',"+blocknum+","+lotnum+")";
       Connection conn = DatabaseUtils.retrieveConnection();
       try{
             PreparedStatement ho = conn.prepareStatement(sql);
             System.out.print(sql);
             ho.executeUpdate();
       }catch(Exception e){
               e.printStackTrace();
       }finally{
               if(conn != null){
                       try{
                               conn.close();
                       }catch(Exception e){}
               }
       }
    }
    public static void insertPnum(String userID, String[] platenum, String[] carmodels){
        Vehicles pnum= null;
        Connection con = DatabaseUtils.retrieveConnection();
        try{
         for(int i=0; i<platenum.length; i++){
                   
                   String model= "Insert into vehicles (platenum, model) VALUES ('"+platenum[i]+"','"+carmodels[i]+"')";
                   PreparedStatement m= con.prepareStatement(model);
                   System.out.println("model");
                   m.executeUpdate();
                   String userVehicle= "Insert into user_vehicles (platenum, userID) VALUES ('"+platenum[i]+"','"+userID+"')";
                   PreparedStatement user= con.prepareStatement(userVehicle);
                   System.out.println("userVehicle");
                   user.executeUpdate();
              }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
                    if(con != null){
                            try{
                                    con.close();
                            }catch(Exception e){}
                }
         }
    }
     public static int existingOccupation(String occupation){
        int tempOID= 0;
        String occ= "Select occupationID from ref_occupation where occupation='"+occupation+"'";
        Connection con = DatabaseUtils.retrieveConnection();
        try{
              PreparedStatement o= con.prepareStatement(occ);
              ResultSet rs = o.executeQuery();
              if (rs.next()){
                  tempOID = rs.getInt(1);
              }
              else{
                  String occInsert="Insert into ref_occupation (occupation) VALUES ('"+occupation+"')";
                  PreparedStatement ins= con.prepareStatement(occInsert);
                  ins.executeUpdate();
                  String occ1= "Select occupationID from ref_occupation where occupation='"+occupation+"'";
                  PreparedStatement o1= con.prepareStatement(occ1);
                  ResultSet rs1 = o1.executeQuery();
                  rs1.beforeFirst();
                  System.out.print(occ1);
                  tempOID = rs1.getInt(1);
              }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
                    if(con != null){
                            try{
                                    con.close();
                            }catch(Exception e){}
                }
         }
        return tempOID;
     }
      public static String existingHomeowner(int blocknum, int lotnum){
        Connection conn = DatabaseUtils.retrieveConnection();
        String user = null;
        String sql = "SELECT * FROM HOMEOWNER WHERE BLOCKNUM = "+blocknum+" AND LOTNUM = "+lotnum+";";
        try{
            PreparedStatement o= conn.prepareStatement(sql);
              ResultSet rs = o.executeQuery();
              if (rs.next()){
                  user = rs.getString(1);
              }
              else{
                  user= null;
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
        return user;
    }
}
