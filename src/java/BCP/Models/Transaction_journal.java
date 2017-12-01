package BCP.Models;

public class Transaction_journal {
    private int journalID;
    private String trxDate;
    private double trxAmnt;
    private double trxAmtPaid;

    public Transaction_journal(int journalID, String trxDate, double trxAmnt, double trxAmtPaid) {
        this.journalID = journalID;
        this.trxDate = trxDate;
        this.trxAmnt = trxAmnt;
        this.trxAmtPaid = trxAmtPaid;
    }

    public int getJournalID() {
        return journalID;
    }

    public void setJounralID(int jounralID) {
        this.journalID = jounralID;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public double getTrxAmnt() {
        return trxAmnt;
    }

    public void setTrxAmnt(double trxAmnt) {
        this.trxAmnt = trxAmnt;
    }

    public double getTrxAmtPaid() {
        return trxAmtPaid;
    }

    public void setTrxAmtPaid(double trxAmtPaid) {
        this.trxAmtPaid = trxAmtPaid;
    }
    
    
}
