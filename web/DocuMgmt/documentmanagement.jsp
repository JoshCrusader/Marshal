<%-- 
    Document   : documentmanagement
    Created on : 11 17, 17, 11:20:27 PM
    Author     : Serus Caligo
--%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %>
<%@ page import="DocuMgmtControllers.modelDao.*"%>
<% 
    ResultSet rs = DocumentDAO.getDocument();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Document Management</title>
        <link href="${pageContext.request.contextPath}/DocuMgmt/css/marshal.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/DocuMgmt/css/css_standard.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/DocuMgmt/css/flexboxgrid.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/DocuMgmt/css/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/DocuMgmt/lib/DataTables/media/css/jquery.dataTables.css">
    </head>
    <body>
        
        <!-- navigation -->
        <div class = "css-navbar"> 
		<div class = "css-burgerbun"><div class = "css-hamburger"><div  class = "css-burgerinside"></div></div></div>
	</div>
	<div id = "sidebartarget" class = "css-sidebarbody">
            <div class = "css-darkness"></div>
            <div class = "css-sidebar">
                    <div class = "css-profilediv">

                    </div>
                    <ul class = "css-sidelist">
                            <li>
                                    <a href = "#"> User Management</a>
                            </li>
                            <li>
                                    <a href = "#"> Security</a>
                            </li>
                            <li>
                                    <a href = "#"> Records Management</a>
                            </li>
                            <li>
                                    <a href = "#"> Module Configuration</a>
                            </li>
                            <li>
                                    <a href = "#"> SMS Management</a>
                            </li>
                    </ul>
            </div>
	</div>
        
        <!-- content -->
        <div class="container-fluid">
            <div class="row">
                
                <!-- Document View -->
                <div class="col-md-9">
                    <!-- Table View of Documents -->
                    <div id="viewTable" class="panel-docu">
                        <table width="100%" align="center" id="docu">
                            <thead>
				<tr>
                                    <th></th>
                                    <th>File Name</th>
                                    <th>Owner</th>
				</tr>
                            </thead>
                            <tbody>
                                <!-- Retrieval of Documents -->
                              
                                <% 
                                    
                                    Statement statement = null;
                                    ResultSet rsF = null;
                                    try{
                                        Connection connection = Database.getDBConnection();
                                        statement   = connection.createStatement();

                                        String sql = "SELECT * FROM FOLDERS"; //SQL code to run
                                        rsF = statement.executeQuery(sql);

                                            while(rsF.next()){
                                                String name = rsF.getString("folderName");

                                                out.println("<tr>");
                                                %>
                                                <td><img class="fileThumbnail-sm" src="${pageContext.request.contextPath}/DocuMgmt/img/folder.png"></td>
                                                <%
                                                out.println("<td>" + name + "</td>");
                                                out.println("<td></td>");
                                                out.println("</tr>");
                                            }
                                    }
                                    catch(Exception e) {
                                        e.printStackTrace();
                                    }
                                    while(rs.next()){
                                    String loc = rs.getString("documentLocation");
                                    String name = rs.getString("description");
                                    String owner = rs.getString("create_userID");

                                    if (loc.toLowerCase().contains(".txt")){
                                        loc = "DocuMgmt/img/document.png"; 
                                    }

                                    out.println("<tr>");
                                    out.println("<td><img class='fileThumbnail-sm' src='"+ loc + "'></td>");
                                    out.println("<td>" + name + "</td>");
                                    out.println("<td>" + owner + "</td>");
                                    out.println("</tr>");
                                }
                                %>
                            </tbody>
                        </table>
                        
                    </div>
                    
                    <!-- Grid View of Documents -->
                    <div id="viewGrid" class="panel-docu" style="display:none;">
                        <div class="row" style="margin-left: .5rem; margin-right: .5rem;">
                            <!-- Retrieval of Documents -->
                            <% 
                                
                                try{
                                    Connection connection = Database.getDBConnection();
                                    statement   = connection.createStatement();

                                    String sql = "SELECT * FROM FOLDERS"; //SQL code to run
                                    rsF = statement.executeQuery(sql);

                                        while(rsF.next()){
                                            String name = rsF.getString("folderName");
                                            
                                            out.println("<div class='file-blocks'>");
                                                out.println("<div class='file-block-pic'>");
                                                %>
                                                <td><img class="fileThumbnail-sm" src="${pageContext.request.contextPath}/DocuMgmt/img/folder.png"></td>
                                                <%
                                                out.println("</div>");
                                                out.println("<div class='file-block-desc'>");
                                                    out.println(name);
                                                out.println("</div>");
                                            out.println("</div>");
                                        }   
                                }
                                catch(Exception e) {
                                    e.printStackTrace();
                                }
                                
                                rs = DocumentDAO.getDocument();
                                while(rs.next()){
                                String loc = rs.getString("documentLocation");
                                String name = rs.getString("description");
                                String owner = rs.getString("create_userID");

                                if (loc.toLowerCase().contains(".txt")){
                                    loc = "DocuMgmt/img/document.png";
                                }

                                out.println("<div class='file-blocks'>");
                                    out.println("<div class='file-block-pic'>");
                                        out.println("<img class='fileThumbnail-sm' src='"+ loc + "'>");
                                    out.println("</div>");
                                    out.println("<div class='file-block-desc'>");
                                        out.println(name);
                                        out.println(owner);
                                    out.println("</div>");
                                out.println("</div>");
                            }
                            %>
                        </div>
                    </div>
                </div>
                        
                <!-- SideBarrr -->
                <div class="col-md-3">
                    <div class="panel-docudesc bg-info">
                        
                        <button id="btnVGrid" onClick="viewFiles(this)">Grid</button>
                        <button id="btnVTable" onClick="viewFiles(this)">Table</button>
                        
                        
                        <!-- File Upload -->
                        <!-- Trigger/Open The Modal -->
                        <button id="btnUpload" onClick="showModal(this)">Upload</button>
                        
                        <!-- The Modal -->
                        <div id="modUpload" class="modal">

                            <!-- Modal content -->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close">&times;</span>
                                    <h2>File Upload</h2>
                                </div>
                                <div class="modal-body">
                                    Select a file to upload: <br />
                                    <form action="${pageContext.request.contextPath}/DocuMgmt/fileupload.jsp" method="post" enctype="multipart/form-data">
                                        <input type="file" name="file" size="50" />
                                        <br />
                                        <input type="submit" value="Upload File" />
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <h3>Modal Footer</h3>
                                </div>
                            </div> 
                        </div> 
                        <!-- END File Upload-->
                        
                        <!-- Add Folder -->
                        <!-- Trigger/Open The Modal -->
                        <button id="btnAddFolder" onClick="showModal(this)">Add Folder</button>
                        
                        <!-- The Modals -->
                        <div id="modAddFolder" class="modal">

                            <!-- Modal content -->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close">&times;</span>
                                    <h2>Add Folder</h2>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/DocuMgmt/documentmanagement.jsp" method="POST">
                                        <input type="text" name="foldername" id="foldername"/>
                                        <button id="create">Create Test Directory</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <h3>Modal Footer</h3>
                                </div>
                            </div> 
                        </div> 
                        <!-- END Add Folder-->
                        
                        <!-- Delete Folder -->
                        <!-- Trigger/Open The Modal -->
                        <button id="btnDeleteFolder" onClick="showModal(this)">Delete Folder</button>
                        
                        <!-- The Modals -->
                        <div id="modDeleteFolder" class="modal">

                            <!-- Modal content -->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close">&times;</span>
                                    <h2>Delete Folder</h2>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/DocuMgmt/documentmanagement.jsp" method="POST">
                                        <input type="text" name="dfoldername" id="dfoldername"/>
                                        <button id="create">Delete Test Directory</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <h3>Modal Footer</h3>
                                </div>
                            </div> 
                        </div> 
                        <!-- END Delete Folder-->
                        
                        <!-- Edit Folder -->
                        <!-- Trigger/Open The Modal -->
                        <button id="btnEditFolder" onClick="showModal(this)">Edit Folder</button>
                        
                        <!-- The Modals -->
                        <div id="modEditFolder" class="modal">

                            <!-- Modal content -->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close">&times;</span>
                                    <h2>Delete Folder</h2>
                                </div>
                                <div class="modal-body" style="color:black;">
                                    <form action="DocuMgmt/documentmanagement.jsp" method="POST">
                                        Old Folder Name:
                                        <input type="text" name="oldname" id="oldname"/><br>
                                        New Folder Name: 
                                        <input type="text" name="newname" id="newname"/><br>
                                        <button id="create">Change folder name</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <h3>Modal Footer</h3>
                                </div>
                            </div> 
                        </div> 
                        <!-- END Edit Folder-->
                        
                        <%
                            try{
                                if (request.getParameter("foldername") != null){ 
                                    String nfoldername = request.getParameter("foldername");
                                    if (nfoldername.length() != 0) {
                                        new File("C:/Users/Serus Caligo/Documents/NetBeansProjects/Marshal/web/DocuMgmt/docu/Forms/"+nfoldername).mkdir();//Modify this path to specify where to create a folder
                                    }
                                }
                            }
                            catch(Exception ex) {
                                System.out.println(ex);
                            }
                            
                            try{
                                if (request.getParameter("dfoldername") != null){ 
                                    String foldername = request.getParameter("dfoldername");
                                    if (foldername.length() != 0) {
                                        new File("C:/Users/Serus Caligo/Documents/NetBeansProjects/Marshal/web/DocuMgmt/docu/Forms/"+foldername).delete(); //Modify this path to specify where to delete a folder
                                    }
                                }
                            }
                            catch(Exception ex) {
                                System.out.println(ex);
                            }
                            
                            try{
                                if (request.getParameter("oldname") != null && request.getParameter("newname") != null){ 
                                    String old = request.getParameter("oldname");
                                    String newname = request.getParameter("newname");
                                    if (old.length() != 0) {
                                        new File("C:/Users/Serus Caligo/Documents/NetBeansProjects/Marshal/web/DocuMgmt/docu/Forms/"+old).renameTo(new File("C:/Users/Serus Caligo/Documents/NetBeansProjects/Marshal/web/DocuMgmt/docu/Forms/"+newname)); //Modify this path to specify where to modify a folder
                                    }
                                }
                            }
                            catch(Exception ex) {
                                System.out.println(ex);
                            }
                            
                            
                            
                        %>
                        <!-- Assign Permissions -->
                        <!-- Trigger/Open The Modal -->
                        <button id="btnAssignPermission" onClick="showModal(this)">Assign Permission</button>
                        
                        <!-- The Modals -->
                        <div id="modAssignPermission" class="modal">

                            <!-- Modal content -->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close">&times;</span>
                                    <h2>Assign Permissions</h2>
                                </div>
                                <div class="modal-body">
                                    <p>Some text in the Modal Body</p>
                                    <p></p>
                                </div>
                                <div class="modal-footer">
                                    <h3>Modal Footer</h3>
                                </div>
                            </div> 
                        </div> 
                        <!-- END Assign Permissions-->
                        
                    </div>
                </div>
            </div>
        </div>
        
           
        <script type="text/javascript" charset="utf8" src="DocuMgmt/js/jquery.js"></script>
        <script type="text/javascript" charset="utf8" src="DocuMgmt/js/css_scripts.js"></script>
        <script type="text/javascript" charset="utf8" src="DocuMgmt/lib/DataTables/media/js/jquery.dataTables.js"></script>
        <script>
            //DataTable
            $('#docu').DataTable();
            
            //View
            document.getElementById('viewGrid').setAttribute("style", "display:none;");
            function viewFiles(btnView){
                switch(btnView){
                    case btnVTable:
                        document.getElementById('viewTable').setAttribute("style", "display:block;");
                        document.getElementById('viewGrid').setAttribute("style", "display:none;");
                        break;
                    case btnVGrid:
                        document.getElementById('viewGrid').setAttribute("style", "display:block;");
                        document.getElementById('viewTable').setAttribute("style", "display:none;");
                        break;
                }
            }
            
            //Modal
            function showModal(btnID){
                var modal = 0;  // Instantiate the modal var
                var iSpan = 0;      // Instantiate the index for Span

                switch(btnID){
                    case btnUpload:
                        // Get the modal
                        modal = document.getElementById('modUpload');
                        iSpan = 0;
                        break;
                    case btnAddFolder:
                        // Get the modal
                        modal = document.getElementById('modAddFolder');
                        iSpan = 1;
                        break;
                    case btnDeleteFolder:
                        // Get the modal
                        modal = document.getElementById('modDeleteFolder');
                        iSpan = 2;
                        break;
                    case btnEditFolder:
                        // Get the modal
                        modal = document.getElementById('modEditFolder');
                        iSpan = 3;
                        break;
                    case btnAssignPermission:
                        // Get the modal
                        modal = document.getElementById('modAssignPermission');
                        iSpan = 4;
                        break;
                }
                
                // Get the <span> element that closes the modal
                var span = document.getElementsByClassName("close")[iSpan];
                
                // Shows the modal once clicked
                modal.style.display = "block";
                
                // When the user clicks on <span> (x), close the modal
                span.onclick = function() {
                    modal.style.display = "none";
                }

                // When the user clicks anywhere outside of the modal, close it
                window.onclick = function(event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                } 
            }
        </script>
    </body>
</html>
