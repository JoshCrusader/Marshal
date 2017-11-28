/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuesAndFees.Model;

import java.util.ArrayList;

/**
 *
 * @author Patrick
 */
public class FutureDues {
    
    ArrayList<Double> amount = new ArrayList<Double>();
    ArrayList<Integer> startmonth = new ArrayList<Integer>();
    ArrayList<Integer> startyear = new ArrayList<Integer>();
    ArrayList<Integer> endmonth = new ArrayList<Integer>();
    ArrayList<Integer> endyear = new ArrayList<Integer>();
    ArrayList<Integer> mduesID = new ArrayList<Integer>();

    public ArrayList<Integer> getMduesID() {
        return mduesID;
    }

    public void setMduesID(ArrayList<Integer> mduesID) {
        this.mduesID = mduesID;
    }
 
    public FutureDues() {}

    public ArrayList<Double> getAmount() {
        return amount;
    }

    public void setAmount(ArrayList<Double> amount) {
        this.amount = amount;
    }

    public ArrayList<Integer> getStartmonth() {
        return startmonth;
    }

    public void setStartmonth(ArrayList<Integer> startmonth) {
        this.startmonth = startmonth;
    }

    public ArrayList<Integer> getStartyear() {
        return startyear;
    }

    public void setStartyear(ArrayList<Integer> startyear) {
        this.startyear = startyear;
    }

    public ArrayList<Integer> getEndmonth() {
        return endmonth;
    }

    public void setEndmonth(ArrayList<Integer> endmonth) {
        this.endmonth = endmonth;
    }

    public ArrayList<Integer> getEndyear() {
        return endyear;
    }

    public void setEndyear(ArrayList<Integer> endyear) {
        this.endyear = endyear;
    }
    
}
