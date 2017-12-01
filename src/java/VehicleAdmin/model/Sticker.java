/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleAdmin.model;

/**
 *
 * @author Fred Purisima
 */
public class Sticker {
    private double price;

    /**
     *
     * @return
     */
    public double getPrice() {
        if(this.price==0){
            return 30.00;
        
        }
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
