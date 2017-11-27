package Objects;

import java.io.Serializable;

/**
 * This object represents the Users of the system
 * 
 * @author yes
 *
 */
public class User implements Serializable {
	
	
	
    protected String userID;
    protected String fName;
    protected String lName;
    protected String mName;
    protected String occupation;
    protected String username;
    protected String pw;
    protected String date;
    protected int usertype;
    protected String tNum;
    protected String mNum;
    protected String email;
    protected String moveInDate;
    protected String typeDesc;
    protected String photolocation;
    protected int blocknum;
    protected int lotnum;
    
    /**
	 * Constructs User object
	 * @param userID the unique ID or usernaame of the User
	 * @param fName First name of the user
	 * @param lName Last name of the user
	 * @param mName Middle name of the user
	 * @param occupation Occupation of the user
	 * @param username the usrname of the user (?)
	 * @param pw The password of the user
	 * @param date Birthday of the user
	 * @param userType usertype NUMBER of the user 1 - system admin, 2 - homeowner, 3 - security 4 board member
	 * @param tNum telephone number of the user
	 * @param mNum mobile number of the user
	 * @param email email of the user
	 * @param moveInDate the date the user moved in
	 * @param typeDesc the type DESCRIPTION of the user 1 - system admin, 2 - homeowner, 3 - security 4 board member
	 * @param photolocation where the photo of the user is located
	 * @param blocknum the blocknumber of the users address
	 * @param lotnum the lotnumber of the users address
	 * 
	 */
    public User(String userID){
        this.userID=userID;
    }
    
    /**
     * This function returns the email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String gettNum() {
        return tNum;
    }

    public void settNum(String tNum) {
        this.tNum = tNum;
    }

    public String getmNum() {
        return mNum;
    }

    public void setmNum(String mNum) {
        this.mNum = mNum;
    }
    
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
    

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }
    
    public String getmoveInDate() {
    		return this.moveInDate;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
    
    public void setmoveInDate(String moveindate) {
    		this.moveInDate = moveindate;
    }
    
    public void settypeDesc(String typedesc) {
    		this.typeDesc = typedesc;
    }
    public String gettypeDesc() {
    		return this.typeDesc;
    }
    public void setphotolocation(String photolocation) {
		this.photolocation = photolocation;
	}
	public String getphotolocation() {
			return this.photolocation;
	}
	public void setblocknum(int blocknum) {
		this.blocknum = blocknum;
	}
	public int getblocknum() {
		return this.blocknum;
	}
	public void setlotnum(int lotnum) {
		this.lotnum = lotnum;
	}
	public int getlotnum() {
		return this.lotnum;
	}
}