/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Model.MonthlyDues;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Patrick
 */
public class InsertMonthly implements Job {

    /**
     *
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        
        Calendar c = Calendar.getInstance();
        
        try {
            
            Connection conn = DBConnect.getConnection();
            
            int month = c.get(Calendar.MONTH) + 1;
            int year = c.get(Calendar.YEAR);
        
            PreparedStatement ps = conn.prepareStatement("SELECT r.mduesID, r.amountapproved FROM ref_monthlydues r WHERE (? <= r.endMonth AND ? >= r.startMonth) AND (? <= r.endYear AND ? >= r.startYear)");
            ps.setInt(1, month);
            ps.setInt(2, month);
            ps.setInt(3, year);
            ps.setInt(4, year);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                
                rs.next();
                int mduesID = rs.getInt(1);
                double amount = rs.getDouble(2);
                
                PreparedStatement insertMonthlyDues = conn.prepareStatement("INSERT INTO monthlydues (month, year, amount, mduesID) VALUES (?, ?, ?, ?)");
                insertMonthlyDues.setInt(1, month);
                insertMonthlyDues.setInt(2, year);
                insertMonthlyDues.setDouble(3, amount);
                insertMonthlyDues.setInt(4, mduesID);
                
                insertMonthlyDues.executeUpdate();
                
                PreparedStatement insertMonthlyDuesTxn = conn.prepareStatement("INSERT INTO trxreferences (amount, interest, totalamount, description) VALUES (?, ?, ?, ?)");
                insertMonthlyDuesTxn.setDouble(1, amount);
                insertMonthlyDuesTxn.setDouble(2, 0);
                insertMonthlyDuesTxn.setDouble(3, amount);
                
                ArrayList<String> months = new ArrayList<String>();
                months.add("January");
                months.add("February");
                months.add("March");
                months.add("April");
                months.add("May");
                months.add("June");
                months.add("July");
                months.add("August");
                months.add("September");
                months.add("October");
                months.add("November");
                months.add("December");
                
                String desc = "Fee for Monthly Dues for " + months.get(month-1) + " " + year;
                
                insertMonthlyDuesTxn.setString(4, desc);
                
                insertMonthlyDuesTxn.executeUpdate();
                
                System.out.println("Added another row for the month @ monthlydues & trxreferences tables!");
                
            }
            
            else {
                System.out.println("No dues planned");
            }
          
            conn.close();
            
        } 
        
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
