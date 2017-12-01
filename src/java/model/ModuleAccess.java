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
public class ModuleAccess {
    private int moduleID;
    private String description;
    private String category;
    
    public ModuleAccess(int moduleID, String description, String category){
        this.moduleID = moduleID;
        this.description = description;
        this.category = category;
        
    }
    public void setModuleID(int moduleID){
        this.moduleID = moduleID;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public int getModuleID(){
        return this.moduleID;
    }
    public String getDescription(){
        return this.description;
    }
    public String getCategory(){
        return this.category;
    }
}
