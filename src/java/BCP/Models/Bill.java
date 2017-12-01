package BCP.Models;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;

public class Bill implements Serializable 
{
	private int billingID;
	private int blocknum;
        private int lotnum;
        private int precedentBilling;
        private double totalDue;
        private double totalPaid;
	private String billDate;
        private String billDue;

    public Bill(){}
    
    public Bill(int billingID, int blocknum, int lotnum, int precedentBilling, double totalDue, double totalPaid, String billDate, String billDue) {
        this.billingID = billingID;
        this.blocknum = blocknum;
        this.lotnum = lotnum;
        this.precedentBilling = precedentBilling;
        this.totalDue = totalDue;
        this.totalPaid = totalPaid;
        this.billDate = billDate;
        this.billDue = billDue;
    }

    public int getBillingID() {
        return billingID;
    }

    public void setBillingID(int billingID) {
        this.billingID = billingID;
    }

    public int getBlocknum() {
        return blocknum;
    }

    public void setBlocknum(int blocknum) {
        this.blocknum = blocknum;
    }

    public int getLotnum() {
        return lotnum;
    }

    public void setLotnum(int lotnum) {
        this.lotnum = lotnum;
    }

    public int getPrecedentBilling() {
        return precedentBilling;
    }

    public void setPrecedentBilling(int precedentBilling) {
        this.precedentBilling = precedentBilling;
    }

    public double getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(double totalDue) {
        this.totalDue = totalDue;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillDue() {
        return billDue;
    }

    public void setBillDue(String billDue) {
        this.billDue = billDue;
    }
        
    
}