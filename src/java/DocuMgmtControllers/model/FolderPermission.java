/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DocuMgmtControllers.model;
import java.io.Serializable;



/**
 *
 * @author Serus Caligo
 */
public class FolderPermission implements Serializable{
    private int folderID;
    private int userGroupID;
    private boolean read;
    private boolean update;
    private boolean delete;
    
    public FolderPermission(int folderID, int userGroupID, boolean read, boolean update, boolean delete){
        this.folderID     = folderID;
        this.userGroupID    = userGroupID;
        this.read           = read;
        this.update         = update;
        this.delete         = delete;
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
     * @return the userGroupID
     */
    public int getUserGroupID() {
        return userGroupID;
    }

    /**
     * @param userGroupID the userGroupID to set
     */
    public void setUserGroupID(int userGroupID) {
        this.userGroupID = userGroupID;
    }

    /**
     * @return the read
     */
    public boolean isRead() {
        return read;
    }

    /**
     * @param read the read to set
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * @return the update
     */
    public boolean isUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(boolean update) {
        this.update = update;
    }

    /**
     * @return the delete
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }
    
   
}

    