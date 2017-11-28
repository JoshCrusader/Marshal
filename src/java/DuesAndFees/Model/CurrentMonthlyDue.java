/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuesAndFees.Model;

/**
 *
 * @author Patrick
 */
public class CurrentMonthlyDue {
    
    protected double amount;
    protected int startmonth;
    protected int startyear;
    protected int endmonth;
    protected int endyear;
    protected int mduesID;
        
    /**
     *
     */
    public CurrentMonthlyDue() {}
	
    
    
    public int getMduesID() {
		return mduesID;
	}

    /**
     *
     * @return
     */
    public void setMduesID(int mduesID) {
        this.mduesID = mduesID;
    }

    public double getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(double amount) {
		this.amount = amount;
	}

    public int getStartmonth() {
        return startmonth;
    }

    public void setStartmonth(int startmonth) {
        this.startmonth = startmonth;
    }

    public int getStartyear() {
        return startyear;
    }

    public void setStartyear(int startyear) {
        this.startyear = startyear;
    }

    public int getEndmonth() {
        return endmonth;
    }

    public void setEndmonth(int endmonth) {
        this.endmonth = endmonth;
    }

    public int getEndyear() {
        return endyear;
    }

    public void setEndyear(int endyear) {
        this.endyear = endyear;
    }

    /**
     *
     * @return
     */
    
    
}
