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
public class Document implements Serializable{
    private int documentID;
    private String description;
    private String documentLocation;
    private int folderID;
    private String create_userID;
    
    public Document(int documentID, String description, String documentLocation, int folderID, String create_userID){
        this.documentID = documentID;
        this.description = description;
        this.documentLocation = documentLocation;
        this.folderID = folderID;
        this.create_userID = create_userID;
    }

    /**
     * @return the documentID
     */
    public int getDocumentID() {
        return documentID;
    }

    /**
     * @param documentID the documentID to set
     */
    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the documentLocation
     */
    public String getDocumentLocation() {
        return documentLocation;
    }

    /**
     * @param documentLocation the documentLocation to set
     */
    public void setDocumentLocation(String documentLocation) {
        this.documentLocation = documentLocation;
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
