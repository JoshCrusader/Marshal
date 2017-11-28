package model;
import java.io.Serializable;
import java.util.Date;

public class UserGroup implements Serializable{
    private int userGroupID;
    private String description;
    
    
    public UserGroup(int userGroupID, String description){
        this.userGroupID     = userGroupID;
        this.description = description;
       
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

    
    
    
    
}
