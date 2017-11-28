package VehicleAdmin.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Java class Database
 * The class illustrates how to get the database connection from Java to MySQL 
 * to generate JavaDoc documentation
 *
 * @author Fred Purisima
 * 
 */

public class Database {
    
    private static final String DRIVER_NAME ="com.mysql.jdbc.Driver";
    private static final String JDBC_URL ="jdbc:mysql://localhost:3306/vfinal";
    private static final String DB_USER ="root";
    private static final String DB_PASSWORD ="";
                    
    /**It gets the database connection of MySQL
     *
     * @return A Connection object that gets the database connection
     */
    public static Connection getDBConnection(){
    
        Connection conn=null;
                    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(JDBC_URL,DB_USER ,DB_PASSWORD);
            return conn;
            }     
            catch (Exception e) {
               e.printStackTrace();
            }
               
        return conn;
        
        
    }

    
    
}
