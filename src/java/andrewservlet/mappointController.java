/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrewservlets;

import dao.mappointDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;

/**
 *
 * @author Crusader Software
 */
@WebServlet("/andrew/mappointController")
public class mappointController extends HttpServlet 
{
	public mappointController() 
	{
		super();
	}

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String msg = "";
        String action = request.getParameter("action");
        Users currUser = (Users) session.getAttribute("loginUser");
        
        if(action.equals("addMapPoint")){
            String addAction = request.getParameter("addAction");
            if(addAction.equals(("propertyYes"))){
                double xAxis =  Double.parseDouble(request.getParameter("xlong"));
                double yAxis =  Double.parseDouble(request.getParameter("ylat"));
                String title = request.getParameter("maptitle");
                String desc = request.getParameter("mapdesc");
                int blocknum = Integer.parseInt(request.getParameter("blocknum"));
                int lotnum = Integer.parseInt(request.getParameter("lotnum"));
                int endlotnum = Integer.parseInt(request.getParameter("endlotnum"));
                String street = request.getParameter("street");

                String userID = currUser.getuserID();
                int categoryID = Integer.parseInt(request.getParameter("mapcategory"));
                boolean result = mappointDAO.addMapPoint(action, xAxis, yAxis, title, desc, userID, categoryID, blocknum, lotnum, endlotnum, street);
                if(result == true){
                    msg = "Map point for " + title + " added.";
                    }
                }
                else if(addAction.equals("propertyNo")){
                double xAxis =  Double.parseDouble(request.getParameter("xlong"));
                double yAxis =  Double.parseDouble(request.getParameter("ylat"));
                String title = request.getParameter("maptitle");
                String desc = request.getParameter("mapdesc");

                String userID = currUser.getuserID();
                int categoryID = Integer.parseInt(request.getParameter("mapcategory"));
                boolean result = mappointDAO.addMapPoint(action, xAxis, yAxis, title, desc, userID, categoryID, 0, 0, 0, "blank");
                if(result == true){
                    msg = "Map point for " + title + " added.";
                    }

                }
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("communityMap_OfficerNew.jsp").forward(request, response);
        }
        
        else if(action.equals("editMapPoint")){
            String editAction = request.getParameter("editAction");
            int mapID = Integer.parseInt(request.getParameter("mapID"));
            if(editAction.equals(("propertyYes"))){
            String title = request.getParameter("mapTitle");
            String desc = request.getParameter("mapDesc");
            int blocknum = Integer.parseInt(request.getParameter("blockNum"));
            int lotnum = Integer.parseInt(request.getParameter("lotNum"));
            int endlotnum = Integer.parseInt(request.getParameter("endLotnum"));
            String street = request.getParameter("stReet");

            String userID = currUser.getuserID();
            int categoryID = Integer.parseInt(request.getParameter("mapcategory"));
            boolean result = mappointDAO.editMapPoint(action, title, desc, userID, categoryID, blocknum, lotnum, endlotnum, street, mapID);
            if(result == true){
                msg = "Changes for Map point ID#" + mapID + " saved.";
                }
            }
            else if(editAction.equals("propertyNo")){
            String title = request.getParameter("mapTitle");
            String desc = request.getParameter("mapDesc");
            int blocknum = 0;
            int lotnum = 0;
            int endlotnum = 0;
            String street = "";

            String userID = currUser.getuserID();
            int categoryID = Integer.parseInt(request.getParameter("mapcategory"));
            boolean result = mappointDAO.editMapPoint(action, title, desc, userID, categoryID, blocknum, lotnum, endlotnum, street, mapID);
            if(result == true){
                msg = "Changes for Map point ID#" + mapID + " saved.";
                }

            }
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("communityMap_OfficerNew.jsp").forward(request, response);
        }
        else if(action.equals("removeMappoint")){
            int mapID = Integer.parseInt(request.getParameter("idToReomve"));
            
            boolean result = mappointDAO.removeMapPoint(mapID);
            if(result == true){
                msg = "Map point removed.";
            }
            else{
                msg = "Something went wrong with removing the point.";
            }
           
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("communityMap_OfficerNew.jsp").forward(request, response);
        }
        
        else if(action.equals("makeMapRequest")){
            String title = request.getParameter("maptitle");
            String description = request.getParameter("mapdesc");
            int blocknum = Integer.parseInt(request.getParameter("blocknum"));
            int lotnum = Integer.parseInt(request.getParameter("lotnum"));
            int endlotnum = Integer.parseInt(request.getParameter("endlotnum"));
            String street = request.getParameter("street");
            int category = Integer.parseInt(request.getParameter("mapcategory"));
            boolean results = mappointDAO.requestMapPoint(blocknum, lotnum, endlotnum, street, title, description, currUser.getuserID(), category);
            if(results == true){
                msg = "Request has been sent to the officers. Please wait for them to approve your request.";
            }
            else{
                msg = "Something went wrong with adding your request to the system. Please contact the administrators about this.";
            }
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("communityMap.jsp").forward(request, response);
        }
        
        else if(action.equals("mappointApproval")){
            String approveAction = request.getParameter("approveAction");
            if (approveAction.equals("approve")){
                int reqID = Integer.parseInt(request.getParameter("reqID"));
                int blocknum = Integer.parseInt(request.getParameter("blocknum"));
                int lotnum = Integer.parseInt(request.getParameter("lotnum"));
                int endlotnum = Integer.parseInt(request.getParameter("endlotnum"));
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String street = request.getParameter("street");
                int category = Integer.parseInt(request.getParameter("category"));

                double xAxis = Double.parseDouble(request.getParameter("xlong"));
                double yAxis = Double.parseDouble(request.getParameter("ylat"));
                //approveMapPoint(int requestID, double xAxis, double yAxis, String title, String desc, String userID, int mapCategoryID, int blocknum, int lotnum, int endlotnum, String street){
                boolean results = mappointDAO.approveMapPoint(reqID, xAxis, yAxis, title, description, currUser.getuserID(), category, blocknum, lotnum, endlotnum, street);
                if(results == true){
                    msg = "Request ticket #" + reqID + " approved. Map point added to the database";
                }
                else{
                    msg = "Something went wrong with adding your request to the system. Please contact the administrators about this.";
                }
            }
            
            else{
                int dreqID = Integer.parseInt(request.getParameter("dreqID"));
                mappointDAO.disApproveMapPoint(dreqID);
                
            }
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("communityMap_OfficerNew_requests.jsp").forward(request, response);
        }
        
        
    }
}