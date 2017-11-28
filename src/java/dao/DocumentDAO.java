/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DatabaseUtils;
import model.Document;

/**
 *
 * @author Andrew Santiago
 */
public class DocumentDAO {

    /**
     * This method gets all the documents from the database
     * @param folderID
     * @return an array list of Document objects.
     */
    public static ArrayList<Document> getAllDocumentsFromFolder(int folderID){
		ArrayList<Document> docs = new ArrayList<>();
		String sql = "SELECT * FROM documents WHERE folderID = ?;";
		Connection conn = DatabaseUtils.retrieveConnection();
		try{
			PreparedStatement pStmt = conn.prepareStatement(sql);
                        pStmt.setInt(1, folderID);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				docs.add(getDocumentbyID(rs.getInt(1)));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return docs;
	}
    
    /**
     * This method gets a single Document objcet through its ID
     * @param docID
     * @return Document object.
     */
    public static Document getDocumentbyID(int docID){
            Document doc = null;
            String sql = "SELECT * FROM documents WHERE documentID = ?;";
            Connection conn = DatabaseUtils.retrieveConnection();
            try{
                    
                    PreparedStatement pStmt = conn.prepareStatement(sql);
                    pStmt.setInt(1, docID);
                    ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                        //Document(int docID, String desc, String location, int folderID, String userID)
                            doc = new Document(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
                    }
            }catch(Exception e){
                    e.printStackTrace();
            }finally{
                    if(conn != null){
                            try{
                                    conn.close();
                            }catch(Exception e){}
                    }
            }
            return doc;
    }
}
