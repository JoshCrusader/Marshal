package org.apache.jsp.Security;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public final class incidentType_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<title>View Incident</title>\n");
      out.write(" <meta charset=\"utf-8\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n");
      out.write("  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n");
      out.write("  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
      out.write("<head>\n");
      out.write("<style>\n");
      out.write("body {margin:0;}\n");
      out.write(".dropdown {\n");
      out.write("    float: left;\n");
      out.write("\tfont-family: Verdana;\n");
      out.write("\tfont-face: url(sansation_light.woff);\n");
      out.write("   \n");
      out.write("}\n");
      out.write("\n");
      out.write(".dropdown .dropbtn {\n");
      out.write("    font-size: 16px;    \n");
      out.write("    border: none;\n");
      out.write("    outline: none;\n");
      out.write("    color: #03313e;\n");
      out.write("    padding: 16px 16px;\n");
      out.write("    background-color: inherit;\n");
      out.write("\tfont-family: Verdana;\n");
      out.write("\tfont-face: url(sansation_light.woff);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".navbar a:hover, .dropdown:hover .dropbtn {\n");
      out.write("    background-color: #eeeeee;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".dropdown-content {\n");
      out.write("    display: none;\n");
      out.write("    position: absolute;\n");
      out.write("    background-color: white;\n");
      out.write("    min-width: 180px;\n");
      out.write("    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);\n");
      out.write("    z-index: 1;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".dropdown-content a {\n");
      out.write("    float: none;\n");
      out.write("    color: #03313e;\n");
      out.write("    padding: 12px 16px;\n");
      out.write("    text-decoration: none;\n");
      out.write("    display: block;\n");
      out.write("    text-align: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".dropdown-content a:hover {\n");
      out.write("    background-color: #eeeeee;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".dropdown:hover .dropdown-content {\n");
      out.write("    display: block;\n");
      out.write("}\n");
      out.write("ul {\n");
      out.write("    list-style-type: none;\n");
      out.write("    margin: 0;\n");
      out.write("    padding: 0;\n");
      out.write("    overflow: hidden;\n");
      out.write("    background-color:white;\n");
      out.write("    position: fixed;\n");
      out.write("    top: 0;\n");
      out.write("    width: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write("li {\n");
      out.write("    float: left;\n");
      out.write("\tfont-family: Verdana;\n");
      out.write("\tfont-face: url(sansation_light.woff);\n");
      out.write("}\n");
      out.write("l {\n");
      out.write("    \n");
      out.write("\tfont-family: Verdana;\n");
      out.write("\tfont-face: url(sansation_light.woff);\n");
      out.write("}\n");
      out.write("\n");
      out.write("li a {\n");
      out.write("    display: block;\n");
      out.write("    color: #03313e;\n");
      out.write("    text-align: center;\n");
      out.write("    padding: 14px 16px;\n");
      out.write("    text-decoration: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write("li a:hover:not(.active) {\n");
      out.write("    background-color: \t#eeeeee;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".active {\n");
      out.write("    color: \t#bf5d27;\n");
      out.write("d {\n");
      out.write("    width: 320px;\n");
      out.write("    padding: 10px;\n");
      out.write("    border: 5px solid gray;\n");
      out.write("    margin: 0;\n");
      out.write("}\n");
      out.write("table, td, th {    \n");
      out.write("    border: 1px solid #ddd;\n");
      out.write("    text-align: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write("table {\n");
      out.write("    border-collapse: collapse;\n");
      out.write("    width: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write("th, td {\n");
      out.write("    padding: 15px;\n");
      out.write("}\t\n");
      out.write("\t\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<ul> \n");
      out.write("\n");
      out.write("  <li><img src=\"LOGO.png\" align=\"middle\" width=\"40\" height=\"40\" ></li>\n");
      out.write("\n");
      out.write("  <li><a href=\"home.jsp\">Home</a></li>\n");
      out.write("  <li><a href=\"viewIncident.jsp\" class = \"active\" >View Incidents</a></li>\n");
      out.write("  \n");
      out.write("  <li><a href=\"viewPenalties.jsp\">View Penalties</a></li>\n");
      out.write("\n");
      out.write("  \n");
      out.write("  \n");
      out.write("  <li style=\"float:right\"><img src=\"USER.png\" align=\"middle\" width=\"50\" height=\"50\" ></li>\n");
      out.write("\n");
      out.write("\n");
      out.write("<div style=\"padding:20px;margin-top:30px;background-color:white;height:1500px;\">\n");
      out.write("<l><h2><center>View Incidents</center></h2></l>\n");
      out.write("\n");
      out.write("<div class=\"container-fluid\">\n");
      out.write("  \n");
      out.write("\n");
      out.write("  <div class=\"row\">\n");
      out.write("    <div class=\"col-sm-2\" style=\"background-color:white;\"></div>\n");
      out.write("    \n");
      out.write("    <div class=\"col-sm-8\" \">\n");
      out.write("        <table style = \"width: 100%\" border = \"1\">\n");
      out.write("        <tr bgcolor = \"white\">\n");
      out.write("          <th  style='text-align:center' >Security Report ID</th>\n");
      out.write("          <th  style='text-align:center'>Report Date</th>\n");
      out.write("          <th  style='text-align:center'>Incident Type</th>\n");
      out.write("          <th  style='text-align:center'>Complaint</th>\n");
      out.write("          <th  style='text-align:center'>Status</th>\n");
      out.write("          <th></th>\n");
      out.write("     \n");
      out.write("        </tr>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        ");

            String type = request.getParameter( "type" );
            session.setAttribute( "incidentType", type );
            int typeID = Integer.parseInt(type);
          
            int kindID = 0;
            
         
      out.write("\n");
      out.write("        \n");
      out.write("        \n");
      out.write("         ");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root",null);
            String sql = "";
            if (typeID == 1){
                String kind = request.getParameter( "kind" );
                session.setAttribute( "incidentType", kind );
                kindID = Integer.parseInt(kind);
                
                if (kindID == 1) {
                    out.println("List of your Complaints:");
                     
                     sql ="SELECT * FROM SECURITY_VIOLATIONS sv join USER2USER uu on sv.securityReportID = uu.securityReportID where incidentTypeID = '"+typeID+"' and uu.complainant_userID = 'olie@gmail.com'";       
                }
                else {
                    out.println("List of your Accusations:");
                     
                    sql ="SELECT * FROM SECURITY_VIOLATIONS sv join USER2USER uu on sv.securityReportID = uu.securityReportID where incidentTypeID = '"+typeID+"' and uu.accused_userID = 'olie@gmail.com'"; 
                }
               }
            else if (typeID == 2){
               sql ="SELECT * FROM SECURITY_VIOLATIONS sv join USER2anyone ua on sv.securityReportID = ua.securityReportID where incidentTypeID = '"+typeID+"' and ua.userID = 'mharj@gmail.com'";        
            }
            else if (typeID == 3){
                String kind = request.getParameter( "kind" );
                session.setAttribute( "incidentType", kind );
                kindID = Integer.parseInt(kind);
                out.println(kindID);
                if (kindID == 1){
                    out.println("List of your Vehicle's Complaints:");
                     
                    sql ="SELECT * FROM SECURITY_VIOLATIONS sv join VEHICLE2VEHICLE vv on sv.securityReportID = vv.securityReportID where incidentTypeID = '"+typeID+"' and vv.complainantplatenum = 'KYA143'";        
                }
                else {
                    out.println("List of your Vehicle's Accusations:");
                     
                     sql ="SELECT * FROM SECURITY_VIOLATIONS sv join VEHICLE2VEHICLE vv on sv.securityReportID = vv.securityReportID where incidentTypeID = '"+typeID+"' and vv.accusedplatenum = 'TEH115'";   
                }
               }
            else {
               sql ="SELECT * FROM SECURITY_VIOLATIONS sv join VEHICLE2User vu on sv.securityReportID = vu.securityReportID where incidentTypeID = '"+typeID+"' and vu.platenum = 'ULO221'";         
            }
            PreparedStatement pStmt= con.prepareStatement(sql);
            ResultSet resultSet = pStmt.executeQuery(sql);
            while(resultSet.next()){
            
      out.write("\n");
      out.write("            <li> \n");
      out.write("                \n");
      out.write("            <tr bgcolor=\"white\">\n");
      out.write("\n");
      out.write("            <td  style='text-align:center'>");
      out.print(resultSet.getInt("securityReportID") );
      out.write("</td>\n");
      out.write("            <td  style='text-align:center'>");
      out.print(resultSet.getString("reportDate") );
      out.write("</td>\n");
      out.write("            <td  style='text-align:center'>");
 if (typeID == 1){
                out.println("User to User");            }
            else if (typeID == 2) {
                out.println("User to Anyone");
            }
            else if (typeID == 3) {
                out.println("Vehicle to Vehicle");
            }
            else {
                out.println("Vehicle to User");
            }
      out.write("</td>\n");
      out.write("            <td  style='text-align:center'>");
      out.print(resultSet.getString("complaint") );
      out.write("</td>\n");
      out.write("            <td  style='text-align:center'>");
      out.print(resultSet.getInt("status") );
      out.write("</td>\n");
      out.write("            <form name ='viewRep' method ='get' action ='report.jsp'>\n");
      out.write("            <td><center><button type =\"submit\" name=\"viewreport\" value =\"");
      out.print(resultSet.getInt("securityReportID") );
      out.write("\" class=\"btn btn-default\" >View</button></center></td>\n");
      out.write("            </form>\n");
      out.write("            </tr>\n");
      out.write("            ");
 
                String report = request.getParameter("viewreport");
        }
}       
        catch (Exception e) {
            e.printStackTrace();


        }
        
      out.write("\n");
      out.write("        </table><br>\n");
      out.write("        <center><button type=\"button\" class=\"btn btn-default\" onclick=\"location.href='viewIncident.jsp'\" >Back</button></center>\n");
      out.write("        \n");
      out.write("\t\n");
      out.write("    </div>\n");
      out.write("  </div>\n");
      out.write("\t\t\n");
      out.write("\t\n");
      out.write("\t</div>\n");
      out.write("    <div class=\"col-sm-2\" style=\"background-color:white;\"></div>\n");
      out.write("       \n");
      out.write("  </div>\n");
      out.write("       \n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
