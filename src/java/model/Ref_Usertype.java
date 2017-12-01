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
public class Ref_Usertype implements Serializable {
    private static int usertypeid;
    private static String usertype;
    public Ref_Usertype(){
        
    }
    public Ref_Usertype(int usertypeid, String usertype){
        this.usertypeid = usertypeid;
        this.usertype = usertype;
    }
    public int getUsertypeid(){
        return this.usertypeid;
    }
    public String getUsertype(){
        return this.usertype;
    }
    public void setUsertypeid(int usertypeid){
        this.usertypeid = usertypeid;
    }
    public void setUsertype(String usertype){
        this.usertype = usertype;
    }
}
