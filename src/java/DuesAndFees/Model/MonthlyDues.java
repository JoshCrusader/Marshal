package Model;

import java.io.Serializable;

/**
 *
 * @author Patrick
 */
public class MonthlyDues implements Serializable {

    protected double amount;
    protected int startMonth;
    protected int startYear;
    protected int endMonth;
    protected int endYear;
    protected int mduesID;
        
    /**
     *
     */
    public MonthlyDues() {}
	
    
    
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

    /**
     *
     * @return
     */
    public int getStartMonth() {
		return startMonth;
	}

    /**
     *
     * @param startMonth
     */
    public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}

    /**
     *
     * @return
     */
    public int getStartYear() {
		return startYear;
	}

    /**
     *
     * @param startYear
     */
    public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

    /**
     *
     * @return
     */
    public int getEndMonth() {
		return endMonth;
	}

    /**
     *
     * @param endMonth
     */
    public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}

    /**
     *
     * @return
     */
    public int getEndYear() {
		return endYear;
	}

    /**
     *
     * @param endYear
     */
    public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	
}
