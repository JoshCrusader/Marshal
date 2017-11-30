/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author drjeoffreycruzada
 */
public class Vehicles {
    private String platenum;
    private String model;
    private String make;
    private String year;
    private boolean banned;
    
    public Vehicles(String platenum, String model){
        this.platenum = platenum;
        this.model = model;
    }
    public void setPlatenum(String platenum){
        this.platenum = platenum;
    }
    public void setModel(String model){
        this.model = model;
    }
    public void setBanned(boolean banned){
        this.banned = banned;
    }
    public String getPlatenum(){
        return this.platenum;
    }
    public String getModel(){
        return this.getModel();
    }
    public boolean isBanned(){
        return banned;
    }
}
