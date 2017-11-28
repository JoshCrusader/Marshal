package DocuMgmtControllers.model;
import java.io.Serializable;
import java.util.Date;

public class UserGroupMember implements Serializable{
    private String userID;
    private int userGroupID;
    
    
    public UserGroupMember(String userID, int userGroupID){
        this.userID          = userID;
        this.userGroupID     = userGroupID;
       
    } 

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
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
    
}
