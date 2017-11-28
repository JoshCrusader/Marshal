/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Andrew Santiago
 */
public class Ref_PenaltyLevel {
    private int level;
    private String desc;
    
    public Ref_PenaltyLevel(int level, String desc){
        this.level = level;
        this.desc = desc;
    }
    
    public int getLevel(){
        return this.level;
    }
    
    public String getDesc(){
        return this.desc;
    }
}
