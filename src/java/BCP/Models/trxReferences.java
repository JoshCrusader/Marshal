package BCP.Models;

public class trxReferences {
    private int trxID;
    private double amount;
    private double intrest;
    private double totalAmount;
    private String description;

    public trxReferences() {}
    
    public trxReferences(int trxID, double amount, double intrest, double totalAmount, String description) {
        this.trxID = trxID;
        this.amount = amount;
        this.intrest = intrest;
        this.totalAmount = totalAmount;
        this.description = description;
    }    
    
    public int getTrxID() {
        return trxID;
    }

    public void setTrxID(int trxID) {
        this.trxID = trxID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getIntrest() {
        return intrest;
    }

    public void setIntrest(double intrest) {
        this.intrest = intrest;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
