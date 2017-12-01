/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.dao;


import VehicleAdmin.dao.Database;
import VehicleAdmin.model.User;
import VehicleAdmin.model.User;
import VehicleAdmin.model.UserVehicle;
import VehicleAdmin.model.UserVehicle;
import VehicleAdmin.model.Vehicle;
import VehicleAdmin.model.Vehicle;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**Represents the list of vehicles that can be viewed by the user depending on conditions given
 *
 * @author Fred Purisima
 */
public class ViewVehicleList implements Serializable{
    private String filter;
    private ArrayList<UserVehicle> listOfFinalUserV;

    /**
     *
     */
    public ViewVehicleList() {
        this.listOfFinalUserV=new ArrayList<UserVehicle>();
    }
    
    /**It gets the filter chosen by the user of the system
     *
     * @return A String representing the filter chosen by the user of the system
     */
    public String getFilter() {
        return filter;
    }

    /**It sets the filter chosen by the user of the system
     *
     * @param filter A String containing the filter chosen by the user of the system
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }
    
    /**It inserts all the user vehicles from the database to the ArrayList depending on its filter chosen
     *
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public void insertUserV()throws SQLException{
       Connection conn=Database.getDBConnection();
       ArrayList <Vehicle> listOfV=new ArrayList<Vehicle>();
       
       
       switch (this.filter) {
            case "none":
                    String sql="SELECT * FROM vehicles;";
                    PreparedStatement pStmt=conn.prepareStatement(sql);
                    ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                        listOfV.add(new Vehicle(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getBoolean(5)));
                    }
                    
                    sql="SELECT distinct uv.userID,u.lname,u.fname,u.mame from user_vehicles uv join users u on uv.userID=u.userID;";
                    pStmt=conn.prepareStatement(sql);
                    rs = pStmt.executeQuery();
                    while(rs.next()){
                        
                        User u=new User(rs.getString(1));
                        u.setLname(rs.getString(2));
                        u.setFname(rs.getString(3));
                        u.setMame(rs.getString(4));
                        
                        UserVehicle userv=new UserVehicle(u);
                        userv.insertVehicles(listOfV);
                        this.listOfFinalUserV.add(userv);
                        
                    } 

                    break;
            case "banned":
                    sql="SELECT * FROM vehicles where banned=true;";
                    pStmt=conn.prepareStatement(sql);
                    rs = pStmt.executeQuery();
                    while(rs.next()){
                        listOfV.add(new Vehicle(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getBoolean(5)));
                    }
                    
                    sql="SELECT distinct uv.userID,u.lname,u.fname,u.mame from user_vehicles uv join users u on uv.userID=u.userID;";
                    pStmt=conn.prepareStatement(sql);
                    rs = pStmt.executeQuery();
                    while(rs.next()){
                        User u=new User(rs.getString(1));
                        u.setLname(rs.getString(2));
                        u.setFname(rs.getString(3));
                        u.setMame(rs.getString(4));
                        UserVehicle userv=new UserVehicle(u);
                        userv.insertVehicles(listOfV);
                        this.listOfFinalUserV.add(userv);
                        
                    }
                    break;
            case "no sticker":
                    sql="SELECT * FROM vehicles;";
                    pStmt=conn.prepareStatement(sql);
                    rs = pStmt.executeQuery();
                    while(rs.next()){
                        listOfV.add(new Vehicle(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getBoolean(5)));
                    }
                   
                    sql="SELECT distinct uv.userID,u.lname,u.fname,u.mame from user_vehicles uv join users u on uv.userID=u.userID where uv.stickerID is null or uv.stickerPaid=false or uv.stickerissuedBy is null;";
                    pStmt=conn.prepareStatement(sql);
                    rs = pStmt.executeQuery();
                    while(rs.next()){
                        User u=new User(rs.getString(1));
                        u.setLname(rs.getString(2));
                        u.setFname(rs.getString(3));
                        u.setMame(rs.getString(4));
                        UserVehicle userv=new UserVehicle(u);
                        userv.insertNoStickerVehicles(listOfV);
                        this.listOfFinalUserV.add(userv);
                    }   
                     
                    
                    break;
            
           
            default: 
                     break;
        }

    }

    /**It gets the final list of user vehicles gathered from the database
     *
     * @return An ArrayList of the final list of user vehicles
     */
    public ArrayList<UserVehicle> getListOfFinalUserV() {
        return listOfFinalUserV;
    }
 
}
