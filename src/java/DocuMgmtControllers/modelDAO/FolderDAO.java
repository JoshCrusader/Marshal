/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;
import java.sql.*;
import model.*;


/**
 *
 * @author Serus Caligo
 */
public class FolderDAO {
    
    public static ResultSet getFolder(){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Connection connection = Database.getDBConnection();
            statement   = connection.createStatement();

            String sql = "SELECT * FROM FOLDERS"; //SQL code to run
            resultSet = statement.executeQuery(sql);

            return resultSet;     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public static void addFolder(Folder f){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Connection connection = Database.getDBConnection();
            statement   = connection.createStatement();

            String sql = "INSERT INTO	FOLDERS (folderID, folderName, folderdesc,parentID, create_userID) VALUES ("+ f.getFolderID() + "," + f.getFolderName() + ","+ f.getFolderDesc() + ","+ f.getParentID() + "," + f.getCreate_userID() + ";"; //SQL code to run
            resultSet = statement.executeQuery(sql);
     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteFolder(Folder f){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Connection connection = Database.getDBConnection();
            statement   = connection.createStatement();

            String sql = "DELETE FROM DOCUMENTS WHERE"+ f.getFolderID() +";"; //SQL code to run
            resultSet = statement.executeQuery(sql);
     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editFolder(Folder f){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Connection connection = Database.getDBConnection();
            statement   = connection.createStatement();

            String sql = "UPDATE DOCUMENTS SET folderName ='" + f.getFolderName()+"' ,folderDesc='" + f.getFolderName()+ "' ,parentID ='" + f.getParentID()+ "', create_userID ='" + f.getCreate_userID()+"' WHERE documentID ='" + f.getFolderID()+"'" ; //SQL code to run
            resultSet = statement.executeQuery(sql);
     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
