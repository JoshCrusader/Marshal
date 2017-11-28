package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Patrick
 */
public class DBConnect {

    /**
     *
     * This method connects to the database with the following credentials and info placed by the
     * developer.
     * 
     * @return
     */
    public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/marshal", "root" , "");
			
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		return conn;
		
	}
	
}
