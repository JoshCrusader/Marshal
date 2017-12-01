package BCP.DAO;

import BCP.Models.*;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * This class is used for retrieving information with regards to bills,
 * their transactions, their payments, and their journals. A list of methods
 * are provided to generate and retrieve such.
 * 
 * 
 * @author Nigel Tan & Carlos Garcia
 * @since 1.0
 */
public class BillDAO implements Serializable 
{
        /**
         * Initializes a newly created Bill object so that its functions can be accessed
         */
        public BillDAO(){}
        
	/**
    * Returns true if the address exists.
    * @param b the object Bill of the homeowner
    * @return true if the address exists based on the specified parameters, false otherwise
    * @since       1.0
    */
	public static boolean checkAddress(Bill b)
	{
		String sql = "SELECT RP.BLOCKNUM, RP.LOTNUM FROM REF_PROPERTIES RP WHERE RP.BLOCKNUM = '" + b.getBlocknum() + "' AND RP.LOTNUM = '" + b.getLotnum() + "';";
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
    * @param b the object Bill of the homeowner
    * @return true if there are no bills generated for the current date, false otherwise
    * @since       1.0
    */
	public static boolean checkHOBills(Bill b)
	{
		String sql = "SELECT B.* FROM BILLING B JOIN REF_PROPERTIES RP ON B.BLOCKNUM = RP.BLOCKNUM AND B.LOTNUM = RP.LOTNUM WHERE B.BLOCKNUM = '" + b.getBlocknum() + "' AND B.LOTNUM = '" + b.getLotnum() + "' AND BILLDATE = DATE(NOW());";
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
    * @param b the object Bill of the homeowner
    * @return true payment is greater than or equal to the bill's total amount, false otherwise
    * @since       1.0
    */
	public static boolean checkTotalPaid(Bill b)
	{
		String sql = "SELECT TOTALDUE, TOTALPAID FROM BILLING WHERE BILLINGID = '" + b.getBillingID() + "';";
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
			
		if (b.getTotalPaid() >= totaldue && totalpaid == 0)
		{
			return true;
		}
		return false;
	}
	
	/**
    * Inserts values based on the specified block and lotnum.
    * @param b the object Bill of the homeowner
    * @return true if insertion was successful, false otherwise
    * @since       1.0
    */
	public static boolean insertBilling(Bill b)
	{
		String sql = "INSERT INTO BILLING (blocknum, lotnum, precedentBilling, totaldue, totalpaid, billDate, billDue) VALUES (" + b.getBlocknum() + ", " + b.getLotnum() + ", " + b.getBillingID() + ", " + b.getTotalDue() + ", 0, DATE(NOW()), (SELECT DATE_ADD(DATE(NOW()), INTERVAL 14 DAY)))";
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
    * @param b the object Bill of the homeowner
    * @param trxs the list of transactions of the homeowner's bill
    * @since       1.0
    */
	public static void insertBillingDetails(Bill b, ArrayList<trxReferences> trxs)
	{
		String sql = "INSERT INTO BILLINGDETAILS (billingid, trxid) VALUES (" + b.getBillingID() + ", ?);";
		Connection conn = DatabaseUtils.retrieveConnection();
                
		try
		{
                        PreparedStatement pStmt = conn.prepareStatement(sql);
			
			int i;
                        int rs;
                        
			for (i = 0; i < trxs.size(); i ++)
			{
				pStmt.setInt(1, trxs.get(i).getTrxID());
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
    * @param b the object Bill of the homeowner
        * @since       1.0
    */
	public static void insertJournal(Bill b)
	{
		String sql = "INSERT INTO TRANSACTION_JOURNAL (trxDate, trxAmnt, trxAmntPaid) VALUES (DATE(NOW()), '" + b.getTotalDue() + "', '" + b.getTotalPaid() + "');";
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
    * @param trxs the list of transactions of the homeowner
    * @since       1.0
    */
	public static void insertTrxList(ArrayList<trxReferences> trxs)
	{
		String sql = "INSERT INTO TRXLIST VALUES ((SELECT JOURNALID FROM TRANSACTION_JOURNAL ORDER BY 1 DESC LIMIT 1), ?, ?);";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			int i;
			int rs;
			
			for (i = 0; i < trxs.size(); i += 2)
			{
				pStmt.setInt(1, trxs.get(i).getTrxID());
				pStmt.setDouble(2, trxs.get(i).getAmount());
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
    * Inserts values based on the specified bill ID
    * @param b the object Bill of the homeowner
    * @param trxs the list of transactions of the homeowner's bill
        * @since       1.0
    */
	public static void insertPaymentDetails(Bill b, ArrayList<trxReferences> trxs)
	{
		String sql = "INSERT INTO PAYMENTDETAILS VALUES (" + b.getBillingID() + ", (SELECT JOURNALID FROM TRANSACTION_JOURNAL ORDER BY 1 DESC LIMIT 1), ?);";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			int i;
			int rs;
			
			for (i = 0; i < trxs.size(); i ++)
			{
				pStmt.setInt(1, trxs.get(i).getTrxID());
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
    * @return an ArrayList of addresses
    * @since       1.0
    */
	public static ArrayList<String> queryAddresses()
	{
		String sql = "SELECT RP.BLOCKNUM, RP.LOTNUM, RP.STREET FROM REF_PROPERTIES RP JOIN HOMEOWNER HO ON RP.BLOCKNUM = HO.BLOCKNUM AND RP.LOTNUM = HO.LOTNUM;";
		Connection conn = DatabaseUtils.retrieveConnection();
		
                ArrayList<String> addresses = new ArrayList<>();
                
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
                return addresses;
    }
	
	
    
	
	/**
    * Queries for the list of all homeowner bills and saves into ArrayList bills.
    * @return an ArrayList of Bill
    * @since       1.0
    */
	public static ArrayList<Bill> queryBills()
	{
		String sql = "SELECT BILLDATE, TOTALDUE, TOTALPAID, BILLINGID FROM BILLING;";
		Connection conn = DatabaseUtils.retrieveConnection();
		ArrayList<Bill> bills = new ArrayList<>();
                
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
                            Bill bill = new Bill();
                            bill.setBillDate(rs.getString("BILLDATE"));
                            bill.setTotalDue(rs.getDouble(2));
                            bill.setTotalPaid(rs.getDouble(3));
                            bill.setBillingID(rs.getInt(4));
                            bills.add(bill);
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
                return bills;
    }
	
	/**
    * Queries for the list of transactions based on the specified bill ID and saves into ArrayList trxs.
    * @param b the object Bill of the homeowner
    * @return an ArrayList of transactions
        * @since       1.0
    */
	public static ArrayList<trxReferences> queryBillTrxs(Bill b)
	{
		String sql = "SELECT TR.* FROM TRXREFERENCES TR JOIN BILLINGDETAILS BD ON TR.TRXID = BD.TRXID JOIN BILLING B ON BD.BILLINGID = B.BILLINGID WHERE B.BILLINGID = '" + b.getBillingID() + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
                ArrayList<trxReferences> trxs = new ArrayList<>();
                
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next())
			{
                            trxReferences tr = new trxReferences();
                            tr.setTrxID(rs.getInt(1));
                            tr.setAmount(rs.getInt(2));
                            tr.setDescription(rs.getString(5));
                            trxs.add(tr);
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
                
                return trxs;
	}
	
	/**
    * Queries for the list of all homeowner addresses based on the specified name and saves into ArrayList addresses.
	* @param name the name of the homeowner
        * @return and ArrayList of addresses
        * @since       1.0
    */
	public static ArrayList<String> queryHOAddresses(String name)
	{
		String fname = "";
		String lname = "";
		ArrayList<String> addresses = new ArrayList<>();
                
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
                return addresses;
    }
	
	/**
    * Gets the latest bill date based on the blocknum and lotnum parameters.
    * @param b the object Bill of the homeowner
    * @return a Bill object
    * @since       1.0
    */
	public static Bill queryHOBillingDate(Bill b)
	{
		String sql = "SELECT B.BILLDATE FROM BILLING B WHERE BLOCKNUM = '" + b.getBlocknum() + "' AND LOTNUM = '" + b.getLotnum() + "' ORDER BY 1 DESC LIMIT 1;";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				b.setBillDate(rs.getString(1));
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
		return b;
	}
	
	/**
    * Gets the most recent bill ID based on the blocknum and lotnum parameters.
    * @param b the object Bill of the homeowner
    * @return a Bill object
    * @since       1.0
    */
	public static Bill queryHOBillingID(Bill b)
	{
		String sql = "SELECT B.BILLINGID FROM BILLING B WHERE BLOCKNUM = '" + b.getBlocknum() + "' AND LOTNUM = '" + b.getLotnum() + "' ORDER BY 1 DESC LIMIT 1;";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
				b.setBillingID(rs.getInt(1));
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
		return b;
	}
	
	/**
    * Queries for the list of all homeowner bills based on the specified name and saves into ArrayList addresses.
    * @param b the object Bill of the homeowner
    * @return a list of Bill objects
    * @since       1.0
    */
	public static ArrayList<Bill> queryHOBills(Bill b)
	{
		String sql = "SELECT B.BILLDATE, B.TOTALDUE, B.TOTALPAID, B.BILLINGID FROM BILLING B JOIN REF_PROPERTIES RP ON B.BLOCKNUM = RP.BLOCKNUM AND B.LOTNUM = RP.LOTNUM WHERE B.BLOCKNUM = '" + b.getBlocknum() + "' AND B.LOTNUM = '" + b.getLotnum() + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
		ArrayList<Bill> bills = new ArrayList<>();
                
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
                            Bill bill = new Bill();
                            bill.setBillDate(rs.getString("BILLDATE"));
                            bill.setTotalDue(rs.getDouble(2));
                            bill.setTotalPaid(rs.getDouble(3));
                            bill.setBillingID(rs.getInt(4));
                            bills.add(bill);
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
                return bills;
	}
	
	/**
    * Queries for the list of all homeowner names and user Ids and saves into ArrayList userIDs, ArrayList fname and ArrayList lname.
    * @return a list of names
    * @since       1.0
    */
	public static ArrayList<String> queryNames()
	{
		String sql = "SELECT U.USERID, U.FNAME, U.LNAME FROM USERS U JOIN HOMEOWNER HO ON U.USERID = HO.USERID JOIN REF_PROPERTIES RP ON HO.BLOCKNUM = RP.BLOCKNUM AND HO.LOTNUM = RP.LOTNUM GROUP BY 2, 3;";
		Connection conn = DatabaseUtils.retrieveConnection();
		ArrayList<String> names = new ArrayList<>();
                
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
                            names.add(rs.getString(2) + " " + rs.getString(3));
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
                return names;
    }
	
	/**
    * Queries for the list of monthly dues transactions and saves it to ArrayList trxs and adds the amount to the totalDues variable 
	* based on the specified parameters.
    * @param b the object Bill of the homeowner
    * @param trxs the list of transactions of the homeowner's bill
    * @return an ArrayList of transactions
    * @since       1.0
    */
	public static ArrayList<trxReferences> queryTrxMonthlyDues(Bill b, ArrayList<trxReferences> trxs)
	{
		String sql = "SELECT HMD.TRXID, TR.TOTALAMOUNT FROM HOUSEMONTHLYDUES HMD JOIN MONTHLYDUES MD ON HMD.MDID = MD.MDID JOIN TRXREFERENCES TR ON HMD.TRXID = TR.TRXID WHERE HMD.BLOCKNUM = '" + b.getBlocknum() + "' AND HMD.LOTNUM = '" + b.getLotnum() + "' AND CONCAT(MD.YEAR, '-', MD.MONTH, '-01') > '" + b.getBillDate() + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
                
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
                            trxReferences tr = new trxReferences();
				tr.setTrxID(rs.getInt(1));
                                tr.setAmount(rs.getDouble(2));
                                trxs.add(tr);
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
                return trxs;
    }
	
	/**
    * Queries for the list of registration transactions and saves it to ArrayList trxs and adds the amount to the totalDues variable 
	* based on the specified parameters.
    * @param b the object Bill of the homeowner
    * @param trxs the list of transactions of the homeowner's bill
    * @return an ArrayList of transactions
    * @since       1.0
    */
	public static ArrayList<trxReferences> queryTrxRegistrations(Bill b, ArrayList<trxReferences> trxs)
	{
		String sql = "SELECT U.TRXID, TR.TOTALAMOUNT FROM HOMEOWNER HO JOIN USERS U ON HO.USERID = U.USERID JOIN TRXREFERENCES TR ON U.TRXID = TR.TRXID WHERE HO.BLOCKNUM = '" + b.getBlocknum() + "' AND HO.LOTNUM = '" + b.getLotnum() + "' AND U.MOVINGIN > '" + b.getBillDate() + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
                            trxReferences tr = new trxReferences();
				tr.setTrxID(rs.getInt(1));
                                tr.setAmount(rs.getDouble(2));
                                trxs.add(tr);
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
                return trxs;
    }
	
	/**
    * Queries for the list of vehicle sticker transactions and saves it to ArrayList trxs and adds the amount to the totalDues variable 
	* based on the specified parameters.
    * @param b the object Bill of the homeowner
    * @param trxs the list of transactions of the homeowner's bill
    * @return an ArrayList of transactions
    * @since       1.0
    */
	public static ArrayList<trxReferences> queryTrxVehicles(Bill b, ArrayList<trxReferences> trxs)
	{
		String sql = "SELECT UV.TRXID, TR.TOTALAMOUNT FROM HOMEOWNER HO JOIN USERS U ON HO.USERID = U.USERID JOIN USER_VEHICLES UV ON U.USERID = UV.USERID JOIN STICKERINVENTORY SI ON UV.STICKERID = SI.STICKERID JOIN TRXREFERENCES TR ON UV.TRXID = TR.TRXID WHERE HO.BLOCKNUM = '" + b.getBlocknum() + "' AND HO.LOTNUM = '" + b.getLotnum() + "' AND SI.DATERELEASED > '" + b.getBillDate() + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
                            trxReferences tr = new trxReferences();
				tr.setTrxID(rs.getInt(1));
                                tr.setAmount(rs.getDouble(2));
                                trxs.add(tr);
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
                return trxs;
    }
	
	/**
    * Queries for the list of violation transactions and saves it to ArrayList trxs and adds the amount to the totalDues variable 
	* based on the specified parameters.
    * @param b the object Bill of the homeowner
    * @param trxs the list of transactions of the homeowner's bill
    * @return an ArrayList of transactions
    * @since       1.0
    */
	public static ArrayList<trxReferences> queryTrxViolations(Bill b, ArrayList<trxReferences> trxs)
	{
		String sql = "SELECT SV.TRXID, TR.TOTALAMOUNT FROM SECURITY_VIOLATIONS SV JOIN USER2USER U2U ON SV.securityReportID = U2U.securityReportID  join users u on u2u.accused_userid = u.userID join homeowner ho on u.userid = ho.userid JOIN TRXREFERENCES TR ON TR.TRXID = SV.TRXID where ho.blocknum = '" + b.getBlocknum() + "' and ho.lotnum = '" + b.getLotnum() + "' AND SV.REPORTDATE > '" + b.getBillDate() + "';";
		String sql2 = "SELECT SV.TRXID, TR.TOTALAMOUNT FROM SECURITY_VIOLATIONS SV JOIN USER2ANYONE U2A ON SV.securityReportID = U2A.securityReportID join users u on u2A.userid = u.userID join homeowner ho on u.userid = ho.userid JOIN TRXREFERENCES TR ON TR.TRXID = SV.TRXID where ho.blocknum = '" + b.getBlocknum() + "' and ho.lotnum = '" + b.getLotnum() + "' AND SV.REPORTDATE > '" + b.getBillDate() + "';";
		Connection conn = DatabaseUtils.retrieveConnection();
		
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next())
			{
                            trxReferences tr = new trxReferences();
				tr.setTrxID(rs.getInt(1));
                                tr.setAmount(rs.getDouble(2));
                                trxs.add(tr);
			}
			
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);
			ResultSet rs2 = pStmt2.executeQuery();
			
			while(rs2.next())
			{
                            trxReferences tr = new trxReferences();
				tr.setTrxID(rs2.getInt(1));
                                tr.setAmount(rs2.getDouble(2));
                                trxs.add(tr);
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
                return trxs;
    }
	
	/**
    * Updates the bill's payment status if payment is greater than the bill's total dues based on the billing ID parameter.
    * @param b the object Bill of the homeowner
    * @since       1.0
    */
	public static void updatePayment(Bill b)
	{
		String sql = "UPDATE BILLING SET TOTALPAID = '" + b.getTotalPaid() + "' WHERE BILLINGID = '" + b.getBillingID() + "';";
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
         * 
         * Searches the database for the blocknum based on user ID
         * @param userID the user ID of the homeowner
         * @return a blocknum integer
         */
        public static int queryBlocknum(String userID)
        {
                String sql = "SELECT BLOCKNUM FROM HOMEOWNER WHERE USERID = " + userID + ";";
		Connection conn = DatabaseUtils.retrieveConnection();
		int blocknum = 0;
                
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next())
			{
                            blocknum = rs.getInt(1);
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
                return blocknum;
        }
        
        /**
         * 
         * Searches the database for the lotnum based on user ID
         * @param userID the user ID of the homeowner
         * @return a lotnum integer
         */
        public static int queryLotnum(String userID)
        {
                String sql = "SELECT LOTNUM FROM HOMEOWNER WHERE USERID = " + userID + ";";
		Connection conn = DatabaseUtils.retrieveConnection();
		int lotnum = 0;
                
		try
		{
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next())
			{
                            lotnum = rs.getInt(1);
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
                return lotnum;
        }
}