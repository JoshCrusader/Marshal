/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.model;

/**Represents the Vehicle Pass of the community
 *
 * @author Fred Purisima
 */
public class Sticker {
    private double price;

    /**It gets the price of the vehicle, the default price is 30 which also can be set by the board member
     *
     * @return A double representing the price of the sticker
     */
    public double getPrice() {
        if(this.price==0){
            return 30.00;
        
        }
        return price;
    }

    /**It sets the price of the vehicle
     *
     * @param price A double containing the price of the sticker
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
