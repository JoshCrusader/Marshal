/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author drjeoffreycruzada
 */
public class RolesModule implements Serializable {
    private static int moduleID;
    private static int usertypeid;
    private static String assigned;
    private static String removed;
    public RolesModule(){
        
    }
    public RolesModule(int moduleID, int usertypeid, String assigned, String removed){
        this.moduleID = moduleID;
        this.usertypeid = usertypeid;
        this.assigned = assigned;
        this.removed = removed;
    }
    
    public int getmoduleID(){
        return moduleID;
    }
    public int getUsertypeid(){
        return this.usertypeid;
    }
    public String getAssigned(){
        return this.assigned;
    }
    public String getRemoved(){
        return this.removed;
    }
    public void setmoduleID(int moduleID){
        this.moduleID = moduleID;
    }
    public void setUsertypeid(int usertypeid){
        this.usertypeid = usertypeid;
    }
    public void setAssigned(String assigned){
        this.assigned = assigned;
    }
    public void setRemoved(String removed){
        this.removed = removed;
        
    }
}
