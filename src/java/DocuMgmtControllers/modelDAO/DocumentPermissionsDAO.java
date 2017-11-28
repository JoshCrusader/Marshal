/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DocuMgmtControllers.modelDAO;
import java.sql.*;
import DocuMgmtControllers.model.*;


/**
 *
 * @author Serus Caligo
 */
public class DocumentPermissionsDAO {
    
    public static ResultSet getDocumentPermissions(){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Connection connection = Database.getDBConnection();
            statement   = connection.createStatement();

            String sql = "SELECT * FROM DOCUMENTPERMISSIONS"; //SQL code to run
            resultSet = statement.executeQuery(sql);

            return resultSet;     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    public static void addDocumentPermission(DocumentPermission d){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Connection connection = Database.getDBConnection();
            statement   = connection.createStatement();

            String sql = "INSERT INTO	DOCUMENTS (documentID, userGroupID,read,update,delete) VALUES ("+ d.getDocumentID() + "," + d.getUserGroupID() + ","+ d.isRead() + ","+ d.isUpdate() + "," + d.isDelete()+ ";"; //SQL code to run
            resultSet = statement.executeQuery(sql);
     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteDocumentPermission(DocumentPermission d){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Connection connection = Database.getDBConnection();
            statement   = connection.createStatement();

            String sql = "DELETE FROM DOCUMENTS WHERE"+ d.getDocumentID() +" AND"+ d.getUserGroupID()+";"; //SQL code to run
            resultSet = statement.executeQuery(sql);
     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editDocumentPermission(DocumentPermission d){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Connection connection = Database.getDBConnection();
            statement   = connection.createStatement();

            String sql = "UPDATE DOCUMENTS SET DOCUMENTPERMISSIONS.READ ='" + d.isRead()+"', DOCUMENTPERMISSIONS.UPDATE='" + d.isUpdate()+ "' ,DOCUMENTPERMISSIONS.DELETE ='" + d.isDelete()+ "' WHERE documentID ='" + d.getDocumentID()+"'" ; //SQL code to run
            resultSet = statement.executeQuery(sql);
     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
