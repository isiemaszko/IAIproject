
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author izabe
 */
@Named(value = "patient")
@Dependent
public class Patient {
    private String coreTemp;
    private String surfTemp;
    private String satur;
    private String bloodPressure;
    private String stabcoreTemp;
    private String stabsurfTemp;
    private String stabbloodPressure;
    private int comfort;
    private char decision;
    
    public Patient(){};

    public String getcoreTemp() {
        return coreTemp;
    }

    public void setsurfTemp(String surfTemp) {
        this.surfTemp = surfTemp;
    }
    
    public String getsurfTemp() {
        return surfTemp;
    }
    
    public void setsatur(String satur) {
        this.satur = satur;
    }
    
    public String getsatur() {
        return satur;
    }
    
    
    public String getbloodPressure() {
        return bloodPressure;
    }

    public void setbloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }
    
     public String getstabcoreTemp() {
        return stabcoreTemp;
    }

    public void setstabcoreTemp(String stabcoreTemp) {
        this.stabcoreTemp = stabcoreTemp;
    }
    
    public String getstabsurfTemp() {
        return stabsurfTemp;
    }

    public void setstabsurfTemp(String stabsurfTemp) {
        this.stabsurfTemp = stabsurfTemp;
    }
    
    public String getstabbloodPressure() {
        return stabbloodPressure;
    }

    public void setstabbloodPressure(String stabbloodPressure) {
        this.stabbloodPressure = stabbloodPressure;
    }
    
     public int getcomfort() {
        return comfort;
    }

    public void setcomfort(int comfort) {
        this.comfort = comfort;
    }
    
    public char getdecision() {
        return decision;
    }

    public void setdecision(char decision) {
        this.decision = decision;
    }
    
}
