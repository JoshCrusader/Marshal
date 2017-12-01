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
public class Ref_Occupation {
    private static int occupationID;
    private static String occupation;
    
    public Ref_Occupation(){
        
    }
    public Ref_Occupation(int occupationID, String occupation){
        this.occupationID = occupationID;
        this.occupation = occupation;
    }
    public int getOccupationID(){
        return this.occupationID;
    }
    public String getOccupation(){
        return this.occupation;
    }
    public void setOccupationID(int occupationID){
        this.occupationID = occupationID;
    }
    public void setOccupation(String occupation){
        this.occupation = occupation;
    }
}
