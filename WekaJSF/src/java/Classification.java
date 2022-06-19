/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author izabe
 */
public class Classification {
    private Classificator classificator;
    
    public Classification(){}
    public void setClassification(Classificator classificator){
     this.classificator=classificator;
     }
     
    public Classificator getClassificator(){
        return this.classificator;
    }
    
    
}
