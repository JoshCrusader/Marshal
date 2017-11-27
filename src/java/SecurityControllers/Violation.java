/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityControllers;
import java.io.Serializable;

/**
 *
 * @author paul
 */
public class Violation {
    
    private String offender;
    private String offended;
    private String desc;
    private int report_type;
    private Users security;
    
    public Violation(){}
    
    public Violation(String offender, String offended, String desc, int report_type, Users security){
    
        this.offender = offender;
        this.offended = offended;
        this.desc = desc;
        this.report_type = report_type;
        this.security = security;
    }
    
    public String getOffender(){
    
        return this.offender;
        
    }
    
    public String getOffended(){
    
        return this.offended;
        
    }
    
    public String getDescription(){
    
        return this.desc;
    
    }
    
    public int getReportType(){
    
        return this.report_type;
        
    }
    
}
