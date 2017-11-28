/**
* <h1>Hello, World!</h1>
* The HelloWorld program implements an application that
* simply displays "Hello World!" to the standard output.
* <p>This class queries the database for data
* Giving proper comments in your program makes it more
* user friendly and it is assumed as a high quality code.
* 
*
* @author  Nigel Tan
* @version 1.0
* @since   2017-11-26 
*/
package model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;

public class DBQueries implements Serializable 
{
	private ArrayList<String> userIDs = new ArrayList<>();
	private ArrayList<String> fnames = new ArrayList<>();
	private ArrayList<String> lnames = new ArrayList<>();
	private ArrayList<String> bills = new ArrayList<>();
	private ArrayList<String> addresses = new ArrayList<>();
	private ArrayList<String> trxs = new ArrayList<>();
	private ArrayList<String> trxIDs = new ArrayList<>();
	
	private double totalDues = 0;
	private String billingID = "";
	private String billingDate = "";
	
	/**
    * Returns true if the address exists.
	* @param blocknum the block number of the homeowner
    * @param lotnum the lot number of the homeowner
    * @return true if the address exists based on the specified parameters, false otherwise
    */
	public boolean checkAddress(String blocknum, String lotnum)
	{
		String sql = "SELECT RP.BLOCKNUM, RP.LOTNUM FROM REF_PROPERTIES RP WHERE RP.BLOCKNUM = '" + blocknum + "' AND RP.LOTNUM = '" + lotnum + "';";
		System.out.print(sql);
		Connection conn = DatabaseUtils.retrieveConnection();
		int count = 0;
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next())
			{
				count ++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
		
		if (count != 0)
		{
			return true;
		}
		return false;
	}
	
	/**
    * Returns true if there are no bills for the current date.
	* @param blocknum the block number of the homeowner
    * @param lotnum the lot number of the homeowner
    * @return true if there are no bills generated for the current date, false otherwise
    */
	public boolean checkHOBills(String blocknum, String lotnum)
	{
		String sql = "SELECT B.* FROM BILLING B JOIN REF_PROPERTIES RP ON B.BLOCKNUM = RP.BLOCKNUM AND B.LOTNUM = RP.LOTNUM WHERE B.BLOCKNUM = '" + blocknum + "' AND B.LOTNUM = '" + lotnum + "' AND BILLDATE = DATE(NOW());";
		Connection conn = DatabaseUtils.retrieveConnection();
		int count = 0;
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next())
			{
				count ++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
		
		if (count != 0)
		{
			return true;
		}
		return false;
	}
	
	/**
    * Returns true if the payment is greater than or equal to the bill's total amount.
	* @param billingID the bill ID number of the homeowner
    * @param payment the amount paid by the homeowner
    * @return true payment is greater than or equal to the bill's total amount, false otherwise
    */
	public boolean checkPayment(String billingID, double payment)
	{
		String sql = "SELECT TOTALDUE, TOTALPAID FROM BILLING WHERE BILLINGID = '" + billingID + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
		double totaldue = 0;
		double totalpaid = 1;
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next())
			{
				totaldue = rs.getDouble(1);
				totalpaid = rs.getDouble(2);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
			
		if (payment >= totaldue && totalpaid == 0)
		{
			return true;
		}
		return false;
	}
	
	/**
    * Returns an ArrayList of addresses that has block and lot number and street.
	* @return an ArrayList of addresses
    */
	public ArrayList<String> getAddresses() 
	{
		return addresses;
	}
	
	/**
    * Returns an ArrayList of bills.
	* @return an ArrayList of bills
    */
	public ArrayList<String> getBills() 
	{
		return bills;
	}
	
	/**
    * Returns an ArrayList of homeowner first names.
	* @return an ArrayList of first names
    */
	public ArrayList<String> getFirstNames() 
	{
		return fnames;
	}
	
	/**
    * Returns an ArrayList of homeowner last names.
	* @return an ArrayList of last names
    */
	public ArrayList<String> getLastNames() 
	{
		return lnames;
	}
	
	/**
    * Returns an ArrayList of Transactions.
	* @return an ArrayList of transactions
    */
	public ArrayList<String> getTrxs() 
	{
		return trxs;
	}
	
	/**
    * Returns an ArrayList of homeowner user IDs.
	* @return an ArrayList of user IDs
    */
	public ArrayList<String> getUserIDs() 
	{
		return userIDs;
	}
	
	/**
    * Inserts values based on the specified block and lotnum.
	* @param blocknum the block number of the homeowner
    * @param lotnum the lot number of the homeowner
    * @return true if insertion was successful, false otherwise
    */
	public boolean insertBilling(String blocknum, String lotnum)
	{
		String sql = "INSERT INTO BILLING (blocknum, lotnum, precedentBilling, totaldue, totalpaid, billDate, billDue) VALUES (" + blocknum + ", " + lotnum + ", " + billingID + ", " + totalDues + ", 0, DATE(NOW()), (SELECT DATE_ADD(DATE(NOW()), INTERVAL 14 DAY)))";
		Connection conn = DatabaseUtils.retrieveConnection();
		PreparedStatement pStmt;
		int rs;
		
		try
		{
			pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
		return true;
	}
	
	/**
    * Inserts values based on the specified block and lotnum.
	* @param blocknum the block number of the homeowner
    * @param lotnum the lot number of the homeowner
    */
	public void insertBillingDetails(String blocknum, String lotnum)
	{
		String sql = "INSERT INTO BILLINGDETAILS (billingid, trxid) VALUES (" + billingID + ", ?);";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			int i;
			int rs;
			
			for (i = 0; i < trxs.size(); i ++)
			{
				pStmt.setString(1, trxs.get(i));
				rs = pStmt.executeUpdate();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
	}
	
	/**
    * Inserts values based on the specified bill ID in the transaction journal
	* @param billingID the ID of the bill to be inserted
    */
	public void insertJournal(String billingID)
	{
		String sql = "INSERT INTO TRANSACTION_JOURNAL (trxDate, trxAmnt, trxAmntPaid) VALUES (DATE(NOW()), (SELECT TOTALDUE FROM BILLING WHERE BILLINGID = '" + billingID + "'), (SELECT TOTALPAID FROM BILLING WHERE BILLINGID = '" + billingID + "'));";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			int rs = pStmt.executeUpdate();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
	}
	
	/**
    * Inserts values in the transaction list
    */
	public void insertTrxList()
	{
		String sql = "INSERT INTO TRXLIST VALUES ((SELECT JOURNALID FROM TRANSACTION_JOURNAL ORDER BY 1 DESC LIMIT 1), ?, ?);";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			int i;
			int rs;
			int j = 0;
			
			for (i = 0; i < trxs.size(); i += 2)
			{
				pStmt.setString(1, trxIDs.get(i - j));
				pStmt.setString(2, trxs.get(i + 1));
				rs = pStmt.executeUpdate();
				j ++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
	}
	
	/**
    * Inserts values based on the specified bill ID
	* @param billingID the ID of the bill to be inserted
    */
	public void insertPaymentDetails(String billingID)
	{
		String sql = "INSERT INTO PAYMENTDETAILS VALUES (" + billingID + ", (SELECT JOURNALID FROM TRANSACTION_JOURNAL ORDER BY 1 DESC LIMIT 1), ?);";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			int i;
			int rs;
			
			for (i = 0; i < trxIDs.size(); i ++)
			{
				pStmt.setString(1, trxIDs.get(i));
				rs = pStmt.executeUpdate();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
	}
	
	/**
    * Queries for the list of all homeowner addresses and saves into ArrayList address.
    */
	public void queryAddresses()
	{
		String sql = "SELECT RP.BLOCKNUM, RP.LOTNUM, RP.STREET FROM REF_PROPERTIES	RP 	JOIN HOMEOWNER HO ON RP.BLOCKNUM = HO.BLOCKNUM AND RP.LOTNUM = HO.LOTNUM;";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				addresses.add("Block " + rs.getString(1) + ", Lot " + rs.getString(2) + ", " + rs.getString(3) + " Street");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
    }
	
	
    
	
	/**
    * Queries for the list of all homeowner bills and saves into ArrayList bills.
    */
	public void queryBills()
	{
		String sql = "SELECT BILLDATE, TOTALDUE, TOTALPAID, BILLINGID FROM BILLING;";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				bills.add(rs.getString("BILLDATE"));
				bills.add(rs.getString(2));
				bills.add(rs.getString(3));
				bills.add(rs.getString(4));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
    }
	
	/**
    * Queries for the list of transactions based on the specified bill ID and saves into ArrayList trxs.
	* @param billID the bill ID of the homeowner
    */
	public void queryBillTrxs(String billID)
	{
		System.out.println(billID);
		String sql = "SELECT TR.* FROM TRXREFERENCES TR JOIN BILLINGDETAILS BD ON TR.TRXID = BD.TRXID JOIN BILLING B ON BD.BILLINGID = B.BILLINGID WHERE B.BILLINGID = '" + billID + "';";
		
		System.out.println(sql);
		Connection conn = DatabaseUtils.retrieveConnection();

		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next())
			{
				trxs.add(rs.getString(5));
				trxs.add(rs.getString(2));
				trxIDs.add(rs.getString(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
	}
	
	/**
    * Queries for the list of all homeowner addresses based on the specified name and saves into ArrayList addresses.
	* @param name the name of the homeowner
    */
	public void queryHOAddresses(String name)
	{
		String fname = "";
		String lname = "";
		
		try
		{
			fname = name.substring(0, name.indexOf(" "));
			lname = name.substring(name.indexOf(" ") + 1);
		
			String sql = "SELECT RP.BLOCKNUM, RP.LOTNUM, RP.STREET FROM REF_PROPERTIES	RP 	JOIN HOMEOWNER HO ON RP.BLOCKNUM = HO.BLOCKNUM AND RP.LOTNUM = HO.LOTNUM JOIN USERS U ON HO.USERID = U.USERID WHERE U.FNAME = '" + fname + "' AND U.LNAME = '" + lname + "';";
			Connection conn = DatabaseUtils.retrieveConnection();
			
			try
			{
				PreparedStatement pStmt = conn.prepareStatement(sql);
				ResultSet rs = pStmt.executeQuery();
				
				while(rs.next())
				{
					addresses.add("Block " + rs.getString(1) + ", Lot " + rs.getString(2) + ", " + rs.getString(3) + " Street");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(conn != null){
					try
					{
						conn.close();
					}
					catch(Exception e){}
				}
			}
		}
		catch (Exception e)
		{
			queryAddresses();
		}
    }
	
	/**
    * Gets the latest bill date based on the blocknum and lotnum parameters.
	* @param blocknum the block number of the homeowner
    * @param lotnum the lot number of the homeowner
    * @return a String of the latest bill date
    */
	public String queryHOBillingDate(String blocknum, String lotnum)
	{
		String sql = "SELECT B.BILLDATE FROM BILLING B WHERE BLOCKNUM = '" + blocknum + "' AND LOTNUM = '" + lotnum + "' ORDER BY 1 DESC LIMIT 1;";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				billingDate = rs.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
		return billingDate;
	}
	
	/**
    * Gets the most recent bill ID based on the blocknum and lotnum parameters.
	* @param blocknum the block number of the homeowner
    * @param lotnum the lot number of the homeowner
    * @return a String of the latest bill ID
    */
	public String queryHOBillingID(String blocknum, String lotnum)
	{
		String sql = "SELECT B.BILLINGID FROM BILLING B WHERE BLOCKNUM = '" + blocknum + "' AND LOTNUM = '" + lotnum + "' ORDER BY 1 DESC LIMIT 1;";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				billingID = rs.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
		return billingID;
	}
	
	/**
    * Queries for the list of all homeowner bills based on the specified name and saves into ArrayList addresses.
	* @param blocknum the block number of the homeowner
    * @param lotnum the lot number of the homeowner
    */
	public void queryHOBills(String blocknum, String lotnum)
	{
		String sql = "SELECT B.BILLDATE, B.TOTALDUE, B.TOTALPAID, B.BILLINGID FROM BILLING B JOIN REF_PROPERTIES RP ON B.BLOCKNUM = RP.BLOCKNUM AND B.LOTNUM = RP.LOTNUM WHERE B.BLOCKNUM = '" + blocknum + "' AND B.LOTNUM = '" + lotnum + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				bills.add(rs.getString("BILLDATE"));
				bills.add(rs.getString(2));
				bills.add(rs.getString(3));
				bills.add(rs.getString(4));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
	}
	
	/**
    * Queries for the list of all homeowner names and user Ids and saves into ArrayList userIDs, ArrayList fname and ArrayList lname.
    */
	public void queryNames()
	{
		String sql = "SELECT U.USERID, U.FNAME, U.LNAME FROM USERS U JOIN HOMEOWNER HO ON U.USERID = HO.USERID JOIN REF_PROPERTIES RP ON HO.BLOCKNUM = RP.BLOCKNUM AND HO.LOTNUM = RP.LOTNUM GROUP BY 2, 3;";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				userIDs.add(rs.getString(1));
				fnames.add(rs.getString(2));
				lnames.add(rs.getString(3));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
    }
	
	/**
    * Queries for the list of monthly dues transactions and saves it to ArrayList trxs and adds the amount to the totalDues variable 
	* based on the specified parameters.
	* @param blocknum the block number of the homeowner
    * @param lotnum the lot number of the homeowner
    */
	public void queryTrxMonthlyDues(String blocknum, String lotnum)
	{
		String sql = "SELECT HMD.TRXID, TR.TOTALAMOUNT FROM HOUSEMONTHLYDUES HMD JOIN MONTHLYDUES MD ON HMD.MDID = MD.MDID JOIN TRXREFERENCES TR ON HMD.TRXID = TR.TRXID WHERE HMD.BLOCKNUM = '" + blocknum + "' AND HMD.LOTNUM = '" + lotnum + "' AND CONCAT(MD.YEAR, '-', MD.MONTH, '-01') > '" + billingDate + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				trxs.add(rs.getString(1));
				totalDues += rs.getDouble(2);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
    }
	
	/**
    * Queries for the list of registration transactions and saves it to ArrayList trxs and adds the amount to the totalDues variable 
	* based on the specified parameters.
	* @param blocknum the block number of the homeowner
    * @param lotnum the lot number of the homeowner
    */
	public void queryTrxRegistrations(String blocknum, String lotnum)
	{
		String sql = "SELECT U.TRXID, TR.TOTALAMOUNT FROM HOMEOWNER HO JOIN USERS U ON HO.USERID = U.USERID JOIN TRXREFERENCES TR ON U.TRXID = TR.TRXID WHERE HO.BLOCKNUM = '" + blocknum + "' AND HO.LOTNUM = '" + lotnum + "' AND U.MOVINGIN > '" + billingDate + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				trxs.add(rs.getString(1));
				totalDues += rs.getDouble(2);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
    }
	
	/**
    * Queries for the list of vehicle sticker transactions and saves it to ArrayList trxs and adds the amount to the totalDues variable 
	* based on the specified parameters.
	* @param blocknum the block number of the homeowner
    * @param lotnum the lot number of the homeowner
    */
	public void queryTrxVehicles(String blocknum, String lotnum)
	{
		String sql = "SELECT UV.TRXID, TR.TOTALAMOUNT FROM HOMEOWNER HO JOIN USERS U ON HO.USERID = U.USERID JOIN USER_VEHICLES UV ON U.USERID = UV.USERID JOIN STICKERINVENTORY SI ON UV.STICKERID = SI.STICKERID JOIN TRXREFERENCES TR ON UV.TRXID = TR.TRXID WHERE HO.BLOCKNUM = '" + blocknum + "' AND HO.LOTNUM = '" + lotnum + "' AND SI.DATERELEASED > '" + billingDate + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				trxs.add(rs.getString(1));
				totalDues += rs.getDouble(2);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
    }
	
	/**
    * Queries for the list of violation transactions and saves it to ArrayList trxs and adds the amount to the totalDues variable 
	* based on the specified parameters.
	* @param blocknum the block number of the homeowner
    * @param lotnum the lot number of the homeowner
    */
	public void queryTrxViolations(String blocknum, String lotnum)
	{
		String sql = "SELECT SV.TRXID, TR.TOTALAMOUNT FROM SECURITY_VIOLATIONS SV JOIN USER2USER U2U ON SV.securityReportID = U2U.securityReportID  join users u on u2u.accused_userid = u.userID join homeowner ho on u.userid = ho.userid JOIN TRXREFERENCES TR ON TR.TRXID = SV.TRXID where ho.blocknum = '" + blocknum + "' and ho.lotnum = '" + lotnum + "' AND SV.REPORTDATE > '" + billingDate + "';";
		String sql2 = "SELECT SV.TRXID, TR.TOTALAMOUNT FROM SECURITY_VIOLATIONS SV JOIN USER2ANYONE U2A ON SV.securityReportID = U2A.securityReportID join users u on u2A.userid = u.userID join homeowner ho on u.userid = ho.userid JOIN TRXREFERENCES TR ON TR.TRXID = SV.TRXID where ho.blocknum = '" + blocknum + "' and ho.lotnum = '" + lotnum + "' AND SV.REPORTDATE > '" + billingDate + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				trxs.add(rs.getString(1));
				totalDues += rs.getDouble(2);
			}
			
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);
			ResultSet rs2 = pStmt2.executeQuery();
			
			while(rs2.next())
			{
				trxs.add(rs2.getString(1));
				totalDues += rs2.getDouble(2);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
    }
	
	/**
    * Updates the bill's payment status if payment is greater than the bill's total dues based on the billing ID parameter.
	* @param billingID the bill ID number of the homeowner
    * @param payment the amount paid by the homeowner
    */
	public void updatePayment(String billingID, double payment)
	{
		String sql = "UPDATE BILLING SET TOTALPAID = '" + payment + "' WHERE BILLINGID = '" + billingID + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			int rs = pStmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null){
				try
				{
					conn.close();
				}
				catch(Exception e){}
			}
		}
	}
	
	/*
    * Updates the bill's total dues if the bill due date is less than the current date.
    *
	/**
   * This method is used to add two integers. This is
   * a the simplest form of a class method, just to
   * show the usage of various javadoc Tags.
   * @param numA This is the first parameter to updateTotalDue method
   * @param numB This is the second parameter to updateTotalDue method
   * @return int This returns sum of numA and numB.
   *
	public int updateTotalDue(String numA, String numB){return numA + numB;}*/
}