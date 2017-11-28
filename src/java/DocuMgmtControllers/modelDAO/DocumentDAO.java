/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DocuMgmtControllers.modelDao;
import java.sql.*;
import DocuMgmtControllers.model.*;


/**
 *
 * @author Serus Caligo
 */
public class DocumentDAO {
    
    public static ResultSet getDocument(){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Connection connection = Database.getDBConnection();
            statement   = connection.createStatement();

            String sql = "SELECT * FROM DOCUMENTS"; //SQL code to run
            resultSet = statement.executeQuery(sql);

            return resultSet;     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public static void addDocument(Document d){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Connection connection = Database.getDBConnection();
            statement   = connection.createStatement();

            String sql = "INSERT INTO	DOCUMENTS (documentID, description,documentLocation,folderID,create_userID) VALUES ("+ d.getDocumentID() + "," + d.getDescription() + ","+ d.getDocumentLocation() + ","+ d.getFolderID() + "," + d.getCreate_userID() + ";"; //SQL code to run
            resultSet = statement.executeQuery(sql);
     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteDocument(Document d){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Connection connection = Database.getDBConnection();
            statement   = connection.createStatement();

            String sql = "DELETE FROM DOCUMENTS WHERE"+ d.getDocumentID() +";"; //SQL code to run
            resultSet = statement.executeQuery(sql);
     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editDocument(Document d){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Connection connection = Database.getDBConnection();
            statement   = connection.createStatement();

            String sql = "UPDATE DOCUMENTS SET description ='" + d.getDescription() +"', ,documentLocation='" + d.getDocumentLocation() + "' ,folderID ='" + d.getFolderID() + "', create_userID ='" + d.getCreate_userID()+"' WHERE documentID ='" + d.getDocumentID()+"'" ; //SQL code to run
            resultSet = statement.executeQuery(sql);
     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
