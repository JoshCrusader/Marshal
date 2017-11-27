package org.apache.jsp.Security;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class viewIncident_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("}\t\n");
      out.write(".center {\n");
      out.write("    margin: auto;\n");
      out.write("    width: 60%;\n");
      out.write("    border: 3px solid #73AD21;\n");
      out.write("    padding: 10px;\n");
      out.write("}\t\n");
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
      out.write("    <div class=\"col-sm-3\" style=\"background-color:white;\"></div>\n");
      out.write("    \n");
      out.write("    <div class=\"col-sm-6\" style=\"background-color:lavenderblush;\">\n");
      out.write("        <div class=\"center\">\n");
      out.write("            <form name ='selectType' method ='get' action ='incidentType.jsp'>\n");
      out.write("            <li><h4>Select type of incident:\n");
      out.write("                    <select id = \"type\" name = \"type\" onchange=\"changeSelect(this.value)\"> \n");
      out.write("                    <option id = \"1\" value = \"1\">User to User</option>\n");
      out.write("                    <option id = \"2\" value = \"2\">User to Anyone</option>\n");
      out.write("                    <option id = \"3\" value = \"3\">Vehicle to Vehicle</option>\n");
      out.write("                    <option id = \"4\" value = \"4\">Vehicle to User</option>\t\t\n");
      out.write("                </select>\n");
      out.write("                     <button type=\"submit\" name=\"submit\" class=\"btn btn-default\" >Select</button>\n");
      out.write("                    \n");
      out.write("            </h4></li>  \n");
      out.write("             <li><div id=\"change\">\n");
      out.write("                 <h4>Select kind:\n");
      out.write("                    <select id = \"kind\" name = \"kind\"> \n");
      out.write("                    <option id = \"1\" value = \"1\">Complaints</option>\n");
      out.write("                    <option id = \"2\" value = \"2\">Accusations</option>\n");
      out.write("                   \t\n");
      out.write("                </select>\n");
      out.write("                    \n");
      out.write("                 </h4></div>\n");
      out.write("             </li>  \n");
      out.write("            \n");
      out.write("            \n");
      out.write("             \n");
      out.write("             \n");
      out.write("            </form>\n");
      out.write("            \n");
      out.write("            \n");
      out.write("            ");
      
                String kind = request.getParameter("kind");
                String incidentType = request.getParameter("type");
             
            
      out.write("\n");
      out.write("\n");
      out.write("             \n");
      out.write("\t</div>\n");
      out.write("        \n");
      out.write("\t\n");
      out.write("\t</div>\n");
      out.write("    <div class=\"col-sm-3\" style=\"background-color:white;\"></div>\n");
      out.write("  </div>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("    function changeSelect(val1){\n");
      out.write("        if(val1 == \"1\" || val1 == \"3\"){\n");
      out.write("            $('#change').attr(\"hidden\", false);\n");
      out.write("        }else{\n");
      out.write("            $('#change').attr(\"hidden\", true);\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("</script>\n");
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
