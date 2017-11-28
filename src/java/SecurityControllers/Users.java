/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityControllers;

/**
 *
 * @author paul
 */
public class Users {
    
    protected String username;
    protected String password;
    protected String userID;
    protected String fName;
    protected String lName;
    protected String mName;
    protected String occupation;
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
    
    public Users(){}
    
    public Users(String username, String password){
    
        this.username = username;
        this.password = password;
    
    }
    
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

    public String getPassword() {
        return password;
    }
    
    public String getmoveInDate() {
    		return this.moveInDate;
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
