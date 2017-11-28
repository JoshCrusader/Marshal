package DuesAndFees.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import DuesAndFees.Model.CurrentMonthlyDue;
import DuesAndFees.Model.FutureDues;
import DuesAndFees.Model.MonthlyDues;
import DuesAndFees.Model.PastDues;
import DuesAndFees.Model.ToBeDues;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class handles all the data access to the database in relation to mohtly dues.
 * Most of the statements here are for querying to the db for updates or selects and some input validation.
 * 
 * @author Patrick
 */
public class MonthlyDuesDAO {
    
    /**
     *
     * This is the error checking done before the user input is passed on to the insert statement at the DB.
     * It validates the input fields submitted by the user on whether or not it conforms with the rules.
     * 
     * @param startmonth
     * @param startyear
     * @return
     */
    public static boolean errorCheckStartMY(int startmonth, int startyear) {
            
            try {
            
                Connection conn = DBConnect.getConnection();
                PreparedStatement sameYearQueryStart = conn.prepareStatement("SELECT endmonth, endyear FROM ref_monthlydues ORDER BY mduesID DESC LIMIT ?");
                sameYearQueryStart.setInt(1, 1);
                
                ResultSet sameYearQueryResult = sameYearQueryStart.executeQuery();
                
                while (sameYearQueryResult.next()) {
                    
                    if (sameYearQueryResult.getInt(1) == 12 && sameYearQueryResult.getInt(2) == startyear) {
                        System.out.println("Last dues plan inputed was for December of " + sameYearQueryResult.getInt(2) + ", please enter for January " + (sameYearQueryResult.getInt(2)+1));
                        return false;
                    }
                    
                    else if (sameYearQueryResult.getInt(2) == startyear && sameYearQueryResult.getInt(1) > startmonth) {
                        System.out.println("Start month is less than last dues inputed");
                        return false;
                    }
                    
                    else if (sameYearQueryResult.getInt(1) + 1 != startmonth) {
                        System.out.println("Proposed due should start a month after the last due inputed ends.");
                        return false;
                    }
                    
                    else if (sameYearQueryResult.getInt(1) + 1 == startmonth){
                        return true;
                    }
                    
                }
                conn.close();
            }
            
            catch (SQLException e) {
                e.printStackTrace();
            }
            
            return false;
            
        }

    /**
     * 
     * This method inserts the validated user statement to the database
     * 
     * @param m
     * @return
     */
    public static int insertDues(MonthlyDues m) {
		
		int flag=0;
		
		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ref_monthlydues (amountapproved, startmonth, startyear, endmonth, endyear) VALUES (?,?,?,?,?)");
			ps.setDouble(1, m.getAmount());
			ps.setInt(2, m.getStartMonth());
			ps.setInt(3, m.getStartYear());
			ps.setInt(4, m.getEndMonth());
			ps.setInt(5, m.getEndYear());
			
			flag=ps.executeUpdate();
			
			conn.close();
			
		}
		
		catch (Exception e) {
                    System.out.println("Cannot insert query.");
		}
		
		return flag;
		
	}
        
    /**
     *
     * This method returns the current monthly due in effect throughout the system.
     * 
     * @return
     */
    public static CurrentMonthlyDue getCurrentDue() {
            
            CurrentMonthlyDue cmd = new CurrentMonthlyDue();
            
            try {
            
                Connection conn = DBConnect.getConnection();
                
                PreparedStatement maxIDQuery = conn.prepareStatement("SELECT MAX(mdID), mduesID FROM monthlydues");
                ResultSet maxIDResult = maxIDQuery.executeQuery();
                maxIDResult.next();
                
                int mduesID = maxIDResult.getInt(2);
                
                PreparedStatement currentDueQuery = conn.prepareStatement("SELECT rm.startMonth, rm.startYear, rm.endMonth, rm.endYear, rm.amountapproved "
                                                                          + "FROM ref_monthlydues rm WHERE rm.mduesID = ? ");
                currentDueQuery.setInt(1, mduesID);
                ResultSet currentDueResult = currentDueQuery.executeQuery();
                
                
                if (currentDueResult.next()) {
                
                cmd.setStartmonth(currentDueResult.getInt(1));
                cmd.setStartyear(currentDueResult.getInt(2));
                cmd.setEndmonth(currentDueResult.getInt(3));
                cmd.setEndyear(currentDueResult.getInt(4));
                cmd.setAmount(currentDueResult.getDouble(5));
                
                }
                
                return cmd;
                
            }
            
            catch (SQLException e) {
                e.printStackTrace();
            }
            
            return cmd;
            
        }
        
    /**
     *
     * This method retrieves the monthly dues that were already finished and expired in the database and passes it on to the controller
     * 
     * @return
     */
    public static PastDues getPastDues() {
            
            PastDues pd = new PastDues();
            Calendar c = Calendar.getInstance();
            
            try {
            
                Connection conn = DBConnect.getConnection();
                
                int month = c.get(Calendar.MONTH) + 1;
                int year = c.get(Calendar.YEAR);
                
                PreparedStatement ps = conn.prepareStatement("SELECT amountapproved, startMonth, startYear, endMonth, endYear FROM ref_monthlydues WHERE ? > endMonth AND ? >= endYear");
                ps.setInt(1, month);
                ps.setInt(2, year);
                
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()) {
                    
                    pd.getAmount().add(rs.getDouble(1));
                    pd.getStartmonth().add(rs.getInt(2));
                    pd.getStartyear().add(rs.getInt(3));
                    pd.getEndmonth().add(rs.getInt(4));
                    pd.getEndyear().add(rs.getInt(5));
                    
                }
                
                return pd;
                
            }
            
            catch (SQLException e) {
                e.printStackTrace();
            }
            
            return pd;
            
        }
        
    /**
     * 
     * This method retrieves the monthly dues that will be implemented and also the current dues of this month.
     *
     * @return
     */
    public static ToBeDues getToBeDues() {
            
            ToBeDues tb = new ToBeDues();
            Calendar c = Calendar.getInstance();
            
            try {
            
                Connection conn = DBConnect.getConnection();
                
                int month = c.get(Calendar.MONTH) + 1;
                int year = c.get(Calendar.YEAR);
                
                PreparedStatement ps = conn.prepareStatement("SELECT amountapproved, startMonth, startYear, endMonth, endYear FROM ref_monthlydues WHERE (? <= startMonth AND startYear = ?) OR (startYear > ?);");
                ps.setInt(1, month);
                ps.setInt(2, year);
                ps.setInt(3, year);
                
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()) {
                    
                    tb.getAmount().add(rs.getDouble(1));
                    tb.getStartmonth().add(rs.getInt(2));
                    tb.getStartyear().add(rs.getInt(3));
                    tb.getEndmonth().add(rs.getInt(4));
                    tb.getEndyear().add(rs.getInt(5));
                    
                }
                
                return tb;
                
            }
            
            catch (SQLException e) {
                e.printStackTrace();
            }
            
        return tb;
            
    }
    
    /**
     * 
     * This method returns the list of future dues that has not been implemented yet
     * 
     * @return
     */
    public static FutureDues futureDues() {
        
        FutureDues fd = new FutureDues();
        Calendar c = Calendar.getInstance();
        
        try {
            
            Connection conn = DBConnect.getConnection();
            
            int month = c.get(Calendar.MONTH) + 1;
            int year = c.get(Calendar.YEAR);
                
            PreparedStatement ps = conn.prepareStatement("SELECT mduesID , amountapproved, startMonth, startYear, endMonth, endYear FROM ref_monthlydues WHERE (? < startMonth AND startYear = ?) OR (startYear > ?);");
            ps.setInt(1, month);
            ps.setInt(2, year);
            ps.setInt(3, year);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                
                fd.getMduesID().add(rs.getInt(1));
                fd.getAmount().add(rs.getDouble(2));
                fd.getStartmonth().add(rs.getInt(3));
                fd.getStartyear().add(rs.getInt(4));
                fd.getEndmonth().add(rs.getInt(5));
                fd.getEndyear().add(rs.getInt(6));
                    
            }
            
            return fd;
            
        }
        
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return fd;
        
    }
    
    /**
     *
     * @param mduesID 
     * @return
     */
    public static MonthlyDues getSelectedFutureDue(int mduesID) {
        
        MonthlyDues md = new MonthlyDues();
        
        try {
            
            Connection conn = DBConnect.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("SELECT mduesID , amountapproved, startMonth, startYear, endMonth, endYear FROM ref_monthlydues WHERE mduesID = ?");
            ps.setInt(1, mduesID);
            
            ResultSet rs = ps.executeQuery();
            
            
            
            if (rs.next()) {
            
                md.setMduesID(rs.getInt(1));
                md.setAmount(rs.getDouble(2));
                md.setStartMonth(rs.getInt(3));
                md.setStartYear(rs.getInt(4));
                md.setEndMonth(rs.getInt(5));
                md.setEndYear(rs.getInt(6));
            
            }
            
            return md;
            
        }
        
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return md;
        
    }
    
    /**
     *
     * @param mduesID
     * @return
     */
    public static int removeDue(int mduesID) {
        
        int flag = 0;
        
        try {
            
            Connection conn = DBConnect.getConnection();
            
            PreparedStatement ps1 = conn.prepareStatement("SELECT mduesID , amountapproved, startMonth, startYear, endMonth, endYear FROM ref_monthlydues WHERE mduesID = ?");
            ps1.setInt(1, mduesID);
            
            ResultSet rs = ps1.executeQuery();
            
            rs.next();
            
            int toBeDeleted_startMonth = rs.getInt(3);
            int toBeDeleted_startYear = rs.getInt(4);
            int toBeDeleted_endMonth = 0;
            int toBeDeleted_endYear = 0;
            
            if (rs.getInt(5) == 12) {
            
                toBeDeleted_endMonth = 1; /* query should be +1 */
                toBeDeleted_endYear = rs.getInt(6) + 1;
                
            }
            
            else {
                
                toBeDeleted_endMonth = rs.getInt(5) + 1;
                toBeDeleted_endYear = rs.getInt(6);
                
            }
            
            PreparedStatement ps = conn.prepareStatement("DELETE FROM ref_monthlydues WHERE mduesID = ?");
            ps.setInt(1, mduesID);
            flag = ps.executeUpdate();
            
            PreparedStatement ps2 = conn.prepareStatement("SELECT mduesID FROM ref_monthlydues WHERE startMonth = ? && startYear = ?");
            ps2.setInt(1, toBeDeleted_endMonth);
            ps2.setInt(2, toBeDeleted_endYear);
            
            ResultSet rs2 = ps2.executeQuery();
            
            if (rs2.next()) {
                
                PreparedStatement ps3 = conn.prepareStatement("UPDATE ref_monthlydues SET startMonth = ?, startYear = ? WHERE ? = mduesID");
                ps3.setInt(1, toBeDeleted_startMonth);
                ps3.setInt(2, toBeDeleted_startYear);
                ps3.setInt(3, rs2.getInt(1));
                
                flag = ps3.executeUpdate() + 10;
                
            }
            
            System.out.println(flag);
            return flag;
            
        }
        
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println(flag);
        return flag;
        
    }
    
    /**
     *
     * @param mduesID
     * @param amount
     * @return
     */
    public static int editAmount(int mduesID, double amount) {
        
        int flag = 0;
        
        try {
            
            Connection conn = DBConnect.getConnection();
            
            PreparedStatement ps3 = conn.prepareStatement("UPDATE ref_monthlydues SET amountapproved = CAST(? AS DECIMAL(9,2)) WHERE ? = mduesID");
            ps3.setDouble(1, amount);
            ps3.setInt(2, mduesID);
            
            flag = ps3.executeUpdate();
            
            return flag;
            
        }
        
        catch (SQLException e) {
            e.printStackTrace();
        }    
        
        return flag;
        
    }
	
}
