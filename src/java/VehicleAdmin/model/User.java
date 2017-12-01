/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.model;

import java.io.Serializable;

/**Represents the user of the system
 *
 * @author Fred Purisima
 */
public class User implements Serializable {
    private String userID,lname,fname,mame;
    private int trxID;

    /**Creates the user containing the userid of the user
     *
     * @param userID userID of the user
     */
    public User(String userID) {
        this.userID = userID;
    }

    /**Creates the user
     *
     */
    public User() {
        
    }
    
    /**Gets the userID of the user
     *
     * @return A String representing the userID of the user
     */
    public String getUserID() {
        return userID;
    }

    /**Sets the userID of the user
     *
     * @param userID A String containing the userID of the user
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**Gets the transactionID of the user
     *
     * @return An int representing the transactionID of the user
     */
    public int getTrxID() {
        return trxID;
    }

    /**Sets the transactionID of the user
     *
     * @param trxID An int containing the transactionID of the user
     */
    public void setTrxID(int trxID) {
        this.trxID = trxID;
    }

    /**Gets the last name of the user
     *
     * @return A String representing the last name of the user
     */
    public String getLname() {
        return lname;
    }

    /**Sets the last name of the user
     *
     * @param lname A String containing the last name of the user
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**Gets the first name of the user
     *
     * @return A String representing the first name of the user
     */
    public String getFname() {
        return fname;
    }

    /**Sets the first name of the user
     *
     * @param fname A String containing the first name of the user
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**Gets the middle name of the user
     *
     * @return A String representing the middle name of the user
     */
    public String getMame() {
        return mame;
    }

    /**Sets the middle name of the user
     *
     * @param mame A String containing the middle name of the user
     */
    public void setMame(String mame) {
        this.mame = mame;
    }
    
}
