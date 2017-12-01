/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.model;


import VehicleAdmin.dao.Database;
import VehicleAdmin.model.Vehicle;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**It represents the list of vehicles owned by a single user.
 *
 * @author Fred Purisima
 */
public class UserVehicle{
    
    private ArrayList<Vehicle> vehicles;
    private User user;
    
    /**Creates UserVehicle containing User object 
     *
     * @param user the user who has a list of vehicles owned
     */
    public UserVehicle(User user) {
        this.user = user;
        this.vehicles=new ArrayList<Vehicle>(); 
    }

    /**It gets the list of user vehicles
     *
     * @return An ArrayList representing the list of user vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    /**It gets the user object of UserVehicle
     *
     * @return A User object
     */
    public User getUser() {
        return user;
    }

    /**It sets the user object of UserVehicle
     *
     * @param user A User object
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**It inserts the data of all the vehicles owned by the user from the list of all current vehicles in the community
     *
     * @param v An ArrayList containing the list of all current vehicles in the community
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public void insertVehicles(ArrayList<Vehicle> v) throws SQLException{
        Connection conn=Database.getDBConnection();
        ArrayList <String> listplateno=new ArrayList<String>();
        
        String sql="SELECT plateNum FROM user_vehicles where userID='"+this.user.getUserID()+"';";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next()){
            listplateno.add(rs.getString(1));
        }
        
        for(int i=0;i<listplateno.size();i++){
            for(int j=0;j<v.size();j++){
                if(listplateno.get(i).equals(v.get(j).getPlatenum())){
                    this.vehicles.add(v.get(j));
                }
            }
        
        
        }
    }
    
    /**It inserts the data of all the vehicles owned by the user without a vehicle sticker from the list of all current vehicles in the community
     *
     * @param v An ArrayList containing the list of all current vehicles in the community
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public void insertNoStickerVehicles(ArrayList<Vehicle> v) throws SQLException{
        Connection conn=Database.getDBConnection();
        ArrayList <String> listplateno=new ArrayList<String>();
        
        String sql="SELECT plateNum FROM user_vehicles where userID='"+this.user.getUserID()+"' and (stickerID is null or stickerPaid=false or stickerissuedBy is null);";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next()){
            listplateno.add(rs.getString(1));
        }
        
        for(int i=0;i<listplateno.size();i++){
            for(int j=0;j<v.size();j++){
                if(listplateno.get(i).equals(v.get(j).getPlatenum())){
                    this.vehicles.add(v.get(j));
                }
            }
        
        
        }
    }
     
}
