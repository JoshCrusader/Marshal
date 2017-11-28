/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.*;

/**
 *
 * @author Serus Caligo
 */
public class Database {
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "hoav2";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
    
    public static Connection getDBConnection(){
        Connection conn = null;
        try{
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(JDBC_URL+DB_NAME, DB_USER, DB_PASSWORD);
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }
}
