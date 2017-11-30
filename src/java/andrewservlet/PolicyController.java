/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andrewservlet;

import dao.PolicyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Penalty;
import model.Policy;
import model.Users;

/**
 *
 * @author Crusader Software
 */
@WebServlet(name = "PolicyController", urlPatterns = {"/andrew/PolicyController"})
public class PolicyController extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String filterType = request.getParameter("filterType");
        String sql;
        Users currUser = (Users) session.getAttribute("loginUser");
        
        //The following will be used for filtering the result set of retrieving the penalties.
        
        if(filterType.equals("penalty")){
            ArrayList<Penalty> penalties = new ArrayList<>();
            if(action.equals("ALL")){
                penalties = PolicyDAO.getAllPenalties();
            }
            else if(action.equals("feeHigh")){
                sql = "SELECT * FROM penalties ORDER BY penaltyfee DESC;";
                penalties = PolicyDAO.getPenaltiesFiltered(sql);
            }
            else if(action.equals("feeLow")){
                sql = "SELECT * FROM penalties ORDER BY penaltyfee ASC;";
                penalties = PolicyDAO.getPenaltiesFiltered(sql);
            }
            else if(action.equals("levelHigh")){
                sql = "SELECT * FROM penalties ORDER BY penaltyLevel DESC;";
                penalties = PolicyDAO.getPenaltiesFiltered(sql);
            }
            else if(action.equals("levelLow")){
                sql = "SELECT * FROM penalties ORDER BY penaltyLevel ASC;";
                penalties = PolicyDAO.getPenaltiesFiltered(sql);
            }

            else if(action.equals("search")){
                String searchkeyword =  request.getParameter("searchkeyword");
                sql = "SELECT * FROM penalties WHERE penaltydescription LIKE '%" + searchkeyword + "%';";
                penalties = PolicyDAO.getPenaltiesFiltered(sql);
            }
            request.setAttribute("filteredPenalties", penalties);
            String msg = "";

            request.setAttribute("msg", msg);
            request.getRequestDispatcher("penaltyManagementFiltered.jsp").forward(request, response);
        
        }
        else if(filterType.equals("policy")){
            ArrayList<Policy> policies = new ArrayList<>();
            if(action.equals("ALL")){
                policies = PolicyDAO.getAllPolicies();
            }
            else if(action.equals("activeOnly")){
                sql = "SELECT * FROM policies WHERE stopimplementDate > DATE(NOW());";
                policies = PolicyDAO.getPoliciesFiltered(action, sql);
            }
            else if(action.equals("inactiveOnly")){
                sql = "SELECT * FROM policies WHERE stopimplementDate <= DATE(NOW());";
                policies = PolicyDAO.getPoliciesFiltered(action, sql);
            }
            else if(action.equals("search")){
                String searchkeyword =  request.getParameter("searchkeyword");
                sql = "SELECT * FROM policies WHERE policydesc LIKE '%" + searchkeyword + "%';";
                policies = PolicyDAO.getPoliciesFiltered(action, sql);
            }
            request.setAttribute("filteredPolicies", policies);
            String msg = "";

            request.setAttribute("msg", msg);
            request.getRequestDispatcher("policyManagementFiltered.jsp").forward(request, response);
        }
        
        //The following will be used for filtering the result set of retrieving the policies.
        
        else if(filterType.equals("policy")){
            ArrayList<Policy> policies = new ArrayList<>();
            if(action.equals("ALL")){
                policies = PolicyDAO.getAllPolicies();
            }
            else if(action.equals("activeOnly")){
                sql = "SELECT * FROM policies WHERE stopimplementDate > DATE(NOW());";
                policies = PolicyDAO.getPoliciesFiltered(action, sql);
            }
            else if(action.equals("inactiveOnly")){
                sql = "SELECT * FROM policies WHERE stopimplementDate < DATE(NOW());";
                policies = PolicyDAO.getPoliciesFiltered(action, sql);
            }
            else if(action.equals("search")){
                String searchkeyword =  request.getParameter("searchkeyword");
                sql = "SELECT * FROM policies WHERE policydesc LIKE '%" + searchkeyword + "%';";
                policies = PolicyDAO.getPoliciesFiltered(action, sql);
            }
            request.setAttribute("filteredPolicies", policies);
            String msg = "";

            request.setAttribute("msg", msg);
            request.getRequestDispatcher("policyManagementFiltered.jsp").forward(request, response);
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
            String msg = "";
            Users currUser = (Users) session.getAttribute("loginUser");
            String action = request.getParameter("action");
            
            if(action.equals("addPolicy")){
                String policydesc = request.getParameter("description");
                int penaltyID = Integer.parseInt(request.getParameter("penalty"));
                String documentLocation = request.getParameter("suppDoc");
                String documentDesc = request.getParameter("suppDocDesc");
                String enactDate = request.getParameter("enactDate");
                String stopDate = request.getParameter("stopDate");
                String enablingBoard= request.getParameter("enablingBoard");

                boolean results = PolicyDAO.addPolicy(policydesc, penaltyID, documentLocation, documentDesc, enactDate, stopDate, enablingBoard, currUser.getuserID());
                if(results == true){
                    msg = "New policy with description " + policydesc + " added.";
                }
                else{
                    msg = "Something went wrong with adding your request to the system. Please contact the administrators about this.";
                }

                request.setAttribute("msg", msg);
                request.getRequestDispatcher("policyManagement.jsp").forward(request, response);
                
            }
            
            else if(action.equals("editPolicy")){
                String policydesc = request.getParameter("description");
                int penaltyID = Integer.parseInt(request.getParameter("penalty"));
                int supportDocID = Integer.parseInt(request.getParameter("suppDocID"));
                String documentLocation = request.getParameter("suppDoc");
                String documentDesc = request.getParameter("suppDocDesc");
                String enactDate = request.getParameter("enactDate");
                String stopDate = request.getParameter("stopDate");
                String enablingBoard= request.getParameter("enablingBoard");
                int policyID = Integer.parseInt(request.getParameter("policyID"));

                boolean results = PolicyDAO.editPolicy(policydesc, penaltyID, documentLocation, documentDesc, enactDate, stopDate, enablingBoard, policyID, supportDocID, currUser.getuserID());
                if(results == true){
                    msg = "Changes to the policy with description " + policydesc + " saved.";
                }
                else{
                    msg = "Something went wrong. Please contact the administrators about this.";
                }

                request.setAttribute("msg", msg);
                request.getRequestDispatcher("policyManagement.jsp").forward(request, response);
            }
            
            else if(action.equals("removePolicy")){
                int policyID = Integer.parseInt(request.getParameter("idToRemove"));

                boolean result = PolicyDAO.dismissPolicy(policyID);
                if(result == true){
                msg = "Policy ID" + policyID + " removed.";
                }
                else{
                msg = "Something went wrong with the removal process.";
                }

                request.setAttribute("msg", msg);
                request.getRequestDispatcher("policyManagement.jsp").forward(request, response);
            }
            
            else if(action.equals("addPenalty")){
                int penaltyLevel = Integer.parseInt(request.getParameter("penaltylevel"));
                String penaltyDescription = request.getParameter("description");
                Double penaltyFee = Double.parseDouble(request.getParameter("penaltyFee"));
                String penaltyAction = request.getParameter("penaltyAction");
                String enablingDoc = request.getParameter("enablingDoc");
                String enablingDocDesc = request.getParameter("enablingDocDesc");
                //(int penaltyLevel, String penaltyDescription, double penaltyFee, String penaltyAction, String docDesc, String docLocation, String userID)
                boolean result = PolicyDAO.addPenalty(penaltyLevel, penaltyDescription, penaltyFee, penaltyAction,enablingDocDesc, enablingDoc, currUser.getuserID());
                if(result == true){
                    msg = "Penalty " + penaltyDescription + " has been successfully added.";
                }
                else{
                    msg = "Something went wrong with adding the penalty.";
                }

                request.setAttribute("msg", msg);
                request.getRequestDispatcher("penaltyManagement.jsp").forward(request, response);
            }
            
            else if(action.equals("editPenalty")){
                int penaltyID = Integer.parseInt(request.getParameter("penaltyID"));
                int penaltyLevel = Integer.parseInt(request.getParameter("penaltylevel"));
                String penaltyDescription = request.getParameter("description");
                Double penaltyFee = Double.parseDouble(request.getParameter("penaltyFee"));
                String penaltyAction = request.getParameter("penaltyAction");
                int enablingDocID = Integer.parseInt(request.getParameter("enablingDocID"));
                String enablingDoc = request.getParameter("enablingDoc");
                String enablingDocDesc = request.getParameter("enablingDocDesc");
                //editPenalty(int penaltyLevel, String penaltyDescription, double penaltyFee, String penaltyAction, int enablingDocID, int penaltyID, String docDesc, String docLocation, String userID)
                boolean result = PolicyDAO.editPenalty(penaltyLevel, penaltyDescription, penaltyFee, penaltyAction, enablingDocID, penaltyID, enablingDocDesc, enablingDoc, currUser.getuserID());
                if(result == true){
                    msg = "Penalty " + penaltyDescription + " has been successfully edited.";
                }
                else{
                    msg = "Changes to penalty " + penaltyDescription + " not saved";
                }

                request.setAttribute("msg", msg);
                request.getRequestDispatcher("penaltyManagement.jsp").forward(request, response);
            }
            
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
