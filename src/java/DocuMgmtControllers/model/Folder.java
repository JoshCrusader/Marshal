/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;



/**
 *
 * @author Serus Caligo
 */
public class Folder implements Serializable{
    private int folderID;
    private String folderName;
    private String folderdesc;
    private int parentID;
    private String create_userID;
    
    public Folder(int folderID, String folderName, String folderdesc, int parentID,String create_userID){
        this.folderID = folderID;
        this.folderName = folderName;
        this.folderdesc = folderdesc;
        this.parentID = parentID;
        this.create_userID = create_userID;
    }

    /**
     * @return the folderID
     */
    public int getFolderID() {
        return folderID;
    }

    /**
     * @param folderID the folderID to set
     */
    public void setFolderID(int folderID) {
        this.folderID = folderID;
    }

    /**
     * @return the folderName
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     * @param folderName the folderName to set
     */
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    /**
     * @return the folderDesc
     */
    public String getFolderDesc() {
        return folderdesc;
    }

    /**
     * @param folderDesc the folderDesc to set
     */
    public void setFolderDesc(String folderdesc) {
        this.folderdesc = folderdesc;
    }

    /**
     * @return the parentID
     */
    public int getParentID() {
        return parentID;
    }

    /**
     * @param parentID the parentID to set
     */
    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    /**
     * @return the create_userID
     */
    public String getCreate_userID() {
        return create_userID;
    }

    /**
     * @param create_userID the create_userID to set
     */
    public void setCreate_userID(String create_userID) {
        this.create_userID = create_userID;
    }

    
}
