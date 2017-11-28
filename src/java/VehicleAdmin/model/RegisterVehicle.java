/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.model;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**The class represents the Register Vehicle transaction that inserts the data to the user_vehicle gathered from the form
 *
 * @author Fred Purisima
 */
public class RegisterVehicle implements Serializable {
   
    private String platenum,model,make,userid;
    private int year;
    private boolean banned;

    /**It gets the plate number of the registered vehicle 
     *
     * @return A String representing the plate number of the registered vehicle
     */
    public String getPlatenum() {
        return platenum;
    }

    /**It sets the plate number of the registered vehicle 
     *
     * @param platenum A String containing the plate number of the registered vehicle 
     */
    public void setPlatenum(String platenum) {
        this.platenum = platenum;
    }

    /**It gets the model of the registered vehicle 
     *
     * @return A String representing the model of the registered vehicle
     */
    public String getModel() {
        return model;
    }

    /**It sets the model of the registered vehicle 
     *
     * @param model A String containing the model of the registered vehicle
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**It gets the make data of the registered vehicle 
     *
     * @return A String representing the make data of the registered vehicle
     */
    public String getMake() {
        return make;
    }

    /**It sets the make data of the registered vehicle 
     *
     * @param make A String containing the make data of the registered vehicle
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**It gets the userID of the user of the registered vehicle 
     *
     * @return A String representing the userID of the registered vehicle
     */
    public String getUserid() {
        return userid;
    }

    /**It sets the userID of the user of the registered vehicle 
     *
     * @param userid A String containing the userID of the registered vehicle
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**It gets the year when the registered vehicle is bought
     *
     * @return An int representing the year when the registered vehicle is bought
     */
    public int getYear() {
        return year;
    }

    /**It sets the year when the registered vehicle is bought
     *
     * @param year An int containing the year when the registered vehicle is bought
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**It gets the status if the registered vehicle is banned
     *
     * @return A boolean representing the registered vehicle's banned status
     */
    public boolean isBanned() {
        return banned;
    }

    /**It sets the status if the registered vehicle is banned
     *
     * @param banned A boolean containing the registered vehicle's banned status
     */
    public void setBanned(boolean banned) {
        this.banned = banned;
    }
    
    /**It is a method that inserts the data of the vehicle gathered from the form to the database
     *
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public void insertVehicle() throws SQLException{
        Connection conn=Database.getDBConnection();
        String sql="INSERT INTO `vehicles` (`platenum`, `model`, `make`, `year`, `banned`) VALUES ('"+this.platenum+"', '"+this.model+"', '"+this.make+"', '"+this.year+"',"+this.banned+");";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        int isInserted=pStmt.executeUpdate(); 
        
        if(isInserted !=0){
            
        }

    }
    
    /**It is a method that inserts the data of the user and their vehicle gathered from the form to the database
     *
     * @throws SQLException It throws SQL Exception if a database access error or other errors occur.
     */
    public void insertUserVehicle() throws SQLException{
        Connection conn=Database.getDBConnection();
        String sql="INSERT INTO `user_vehicles` (`plateNum`, `userID`,`stickerPaid`) VALUES ('"+this.platenum+"', '"+this.userid+"',false);";
        PreparedStatement pStmt=conn.prepareStatement(sql);
        int isInserted=pStmt.executeUpdate(); 
        
        if(isInserted !=0){
            
            
        }

    } 
    
}
