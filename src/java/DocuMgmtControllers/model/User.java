package DocuMgmtControllers.model;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private String  UserID;
    private int     UserTypeID;
    private String  FName;
    private String  MName;
    private String  LName;
    private Date  BDate;
    private int photoID;
    private int occupation;
    private Date movingIn;
    private Date movingOutclearID;
    private int trxID;
    
    public User( String UserID, int UserTypeID, String  FName, String  MName, String  LName, Date  BDate, int photoID, int occupation, Date movingIn, Date movingOutclearID,int trxID){
        this.UserID     = UserID;
        this.UserTypeID = UserTypeID;
        this.FName = FName;
        this.MName = MName;
        this.LName = LName;
        this.BDate = BDate;
        this.photoID = photoID;
        this.occupation = occupation;
        this.movingIn = movingIn;
        this.movingOutclearID = movingOutclearID;   
    }

    /**
     * @return the UserID
     */
    public String getUserID() {
        return UserID;
    }

    /**
     * @param UserID the UserID to set
     */
    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    /**
     * @return the UserTypeID
     */
    public int getUserTypeID() {
        return UserTypeID;
    }

    /**
     * @param UserTypeID the UserTypeID to set
     */
    public void setUserTypeID(int UserTypeID) {
        this.UserTypeID = UserTypeID;
    }

    /**
     * @return the FName
     */
    public String getFName() {
        return FName;
    }

    /**
     * @param FName the FName to set
     */
    public void setFName(String FName) {
        this.FName = FName;
    }

    /**
     * @return the MName
     */
    public String getMName() {
        return MName;
    }

    /**
     * @param MName the MName to set
     */
    public void setMName(String MName) {
        this.MName = MName;
    }

    /**
     * @return the LName
     */
    public String getLName() {
        return LName;
    }

    /**
     * @param LName the LName to set
     */
    public void setLName(String LName) {
        this.LName = LName;
    }

    /**
     * @return the BDate
     */
    public Date getBDate() {
        return BDate;
    }

    /**
     * @param BDate the BDate to set
     */
    public void setBDate(Date BDate) {
        this.BDate = BDate;
    }

    /**
     * @return the photoID
     */
    public int getPhotoID() {
        return photoID;
    }

    /**
     * @param photoID the photoID to set
     */
    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    /**
     * @return the occupation
     */
    public int getOccupation() {
        return occupation;
    }

    /**
     * @param occupation the occupation to set
     */
    public void setOccupation(int occupation) {
        this.occupation = occupation;
    }

    /**
     * @return the movingIn
     */
    public Date getMovingIn() {
        return movingIn;
    }

    /**
     * @param movingIn the movingIn to set
     */
    public void setMovingIn(Date movingIn) {
        this.movingIn = movingIn;
    }

    /**
     * @return the movingOutclearID
     */
    public Date getMovingOutclearID() {
        return movingOutclearID;
    }

    /**
     * @param movingOutclearID the movingOutclearID to set
     */
    public void setMovingOutclearID(Date movingOutclearID) {
        this.movingOutclearID = movingOutclearID;
    }

    /**
     * @return the trxID
     */
    public int getTrxID() {
        return trxID;
    }

    /**
     * @param trxID the trxID to set
     */
    public void setTrxID(int trxID) {
        this.trxID = trxID;
    }
    
    
    
    
}
