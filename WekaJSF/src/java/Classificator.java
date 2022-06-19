
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author izabe
 */
@Named(value = "classificator")
@Dependent
public class Classificator {
    private Instances instances;
    private IBk model;
    
    public Classificator(){}
    public Classificator(Instances data, IBk model){
    this.instances=data;
    this.model=model;}
    
     public Instances getInstances() {
        return instances;
    }

    public void setInstances(Instances instances) {
        this.instances = instances;
    }

    public IBk getIBk() {
        return model;
    }

    public void setIBk(IBk password) {
        this.model = model;
    }
    
    
}
