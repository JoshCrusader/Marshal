/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Andrew Santiago
 */
public class Document implements Serializable {
    private int documentID;
    private String description;
    private String documentLocation;
    private int folderID;
    private String create_userID;
    
    public Document(){}
    
    public Document(int docID, String desc, String location, int folderID, String userID){
        this.documentID = docID;
        this.description = desc;
        this.documentLocation = location;
        this.folderID = folderID;
        this.create_userID = userID;
    }
    
    public int getDocumentID(){
        return this.documentID;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public String getDocumentLocation(){
        return this.documentLocation;
    }
    
    public int getfolderID(){
        return this.folderID;
    }
}
