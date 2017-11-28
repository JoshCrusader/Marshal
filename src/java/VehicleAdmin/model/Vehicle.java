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

/**It represents a vehicle
 *
 * @author Fred Purisima
 */
public class Vehicle{
    
    private String platenum,model,make;
    private int year;
    private boolean banned;

    /**Creates a vehicle containing its necessary information needed for it to be called a vehicle in the systerm
     *
     * @param platenum The platenumber of the vehicle
     * @param model The model of the vehicle
     * @param make The make data of the vehicle
     * @param year The year when the vehicle is bought
     * @param banned The banned status of the vehicle
     */
    public Vehicle(String platenum, String model, String make, int year, boolean banned) {
        this.platenum = platenum;
        this.model = model;
        this.make = make;
        this.year = year;
        this.banned = banned;
    }

    public Vehicle() {
        
    }
    
    /**It gets the plate number of the vehicle 
     *
     * @return A String representing the plate number of the vehicle
     */
    public String getPlatenum() {
        return platenum;
    }

    /**It sets the plate number of the vehicle 
     *
     * @param platenum A String containing the plate number of the vehicle
     */
    public void setPlatenum(String platenum) {
        this.platenum = platenum;
    }

    /**It gets the model of the vehicle 
     *
     * @return A String representing the model of the vehicle
     */
    public String getModel() {
        return model;
    }

    /**It sets the model of the vehicle 
     *
     * @param model A String containing the model of the vehicle
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**It gets the make data of the vehicle 
     *
     * @return A String representing the make data of the vehicle
     */
    public String getMake() {
        return make;
    }

    /**It sets the make data of the vehicle 
     *
     * @param make A String containing the make data of the vehicle
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**It gets the year when the vehicle is bought
     *
     * @return An int representing the year when the vehicle is bought
     */
    public int getYear() {
        return year;
    }

    /**It sets the year when the vehicle is bought
     *
     * @param year An int containing the year when the vehicle is bought
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**It gets the status if the vehicle is banned
     *
     * @return A boolean representing the vehicle's banned status
     */
    public boolean isBanned() {
        return banned;
    }

    /**It sets the status if the vehicle is banned
     *
     * @param banned A boolean containing the vehicle's banned status
     */
    public void setBanned(boolean banned) {
        this.banned = banned;
    }
    
    
    
}
