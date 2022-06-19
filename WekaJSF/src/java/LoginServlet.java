/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;




import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Debug;
import weka.core.DenseInstance;
import weka.core.Instances;


/**
 *
 * @author izabe
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    private LoginDao loginDao;
    private Classification classify;
    private Classificator classificator;
    private User user;
    private int validation;
    private Instances train;
    private Instances test;
    private Instances data;
    private IBk knn;
    
    public void init() {
        loginDao = new LoginDao();
        classify=new Classification();
        classificator=new Classificator();
        user = new User();
        validation=0;
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, Exception {
        response.setContentType("text/html;charset=UTF-8");
       
        String formAdmin=request.getParameter("build");
        String formUser=request.getParameter("L-CORE");
        String loginForm=request.getParameter("username");
        
        if(formAdmin!=null){
            panelAdmin(request, response);
        }
        else if(formUser!=null){
            panelUser(request, response);
        }
        else{
             String username = request.getParameter("username");
            String password = request.getParameter("password");

            user.setUsername(username);
            user.setPassword(password);
            String role=loginDao.validate(user);
            user.setRole(role);

            if (role!="") {
                if(role.equals("user")){
                   response.sendRedirect("userPage.jsp");
                }
                else{
                    response.sendRedirect("adminPage.jsp");
                }

            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

            }
            }
        }
      
       

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void panelAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        BufferedReader reader =new  BufferedReader(new FileReader("C:\\Users\\izabe\\OneDrive\\Pulpit\\sem_3\\IAI\\projekt\\data.arff"));
        
        data =new Instances(reader);
        reader.close();
        data.setClassIndex(data.numAttributes()-1);
        classificator.setInstances(data);
        
        knn=new IBk();
        knn.setKNN(3);
        knn.buildClassifier(data);
        
        classificator.setIBk(knn);
        
        Evaluation eval;
        String build=request.getParameter("build");
        if(build.equals("cv")){
            eval =new Evaluation(data);
            int folds=Integer.parseInt(request.getParameter("folds"));
            eval.crossValidateModel(knn, data, folds, new Debug.Random(1)); 
            validation=0;
        }
        else{
            String per=request.getParameter("procent");
            int percent=Integer.parseInt(per);
            int trainSize = (int) Math.round(data.numInstances() * percent/ 100);
            int testSize = data.numInstances() - trainSize;
            train = new Instances(data, 0, trainSize);
            test = new Instances(data, trainSize, testSize);
            
            eval=new Evaluation(train);
            eval.evaluateModel(knn, test);
            validation=1;
        }
        
        classify.setClassification(classificator);
      
        
        String summ=eval.toSummaryString();
        String[] splitSum=summ.split("\n");
        request.setAttribute("evalCCI", splitSum[1]);
        request.setAttribute("evalICI", splitSum[2]);
        request.setAttribute("evalKs", splitSum[3]);
        request.setAttribute("evalMAE", splitSum[4]);
        request.setAttribute("evalRMSE", splitSum[5]);
        request.setAttribute("evalTNI", splitSum[6]);
        request.setAttribute("evalTNII", splitSum[7]);
        request.setAttribute("evalTNIII", splitSum[8]);
        
        String matrix=eval.toMatrixString();
        String[] splitmMtrix=matrix.split("\n");   
        request.setAttribute("matrix1", splitmMtrix[2]);
        request.setAttribute("matrix2", splitmMtrix[3]);
        request.setAttribute("matrix3", splitmMtrix[4]);
        request.setAttribute("matrix4", splitmMtrix[5]);
        
        request.setAttribute("tpRate0", eval.recall(0));
        request.setAttribute("tpRate1", eval.recall(1));
        request.setAttribute("tpRate2", eval.recall(2));  
        
        request.setAttribute("precisione0", eval.precision(0));
        request.setAttribute("precision1", eval.precision(1));
        request.setAttribute("precision2", eval.precision(2));  
        
        request.setAttribute("fMeasure0", eval.fMeasure(0));
        request.setAttribute("fMeasure1", eval.fMeasure(1));
        request.setAttribute("fMeasure2", eval.fMeasure(2));  
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/evalPage.jsp");

        dispatcher.forward(request, response);
    }
    
     protected void panelUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception{
      float interTemp = Float.parseFloat(request.getParameter("L-CORE"));
        float surfTemp = Float.parseFloat(request.getParameter("L-SURF"));
        float satur = Float.parseFloat(request.getParameter("L-O2"));
        String[] split = request.getParameter("L-BP").split("/");
        float bloodPressure = Float.parseFloat(split[0]);
        String stabcoreTemp = getStabilitishParam(request.getParameter("CORE-STBL"));
        String stabsurfTemp = getStabilitishParam(request.getParameter("SURF-STBL"));
        String stabbloodPressure = getStabilitishParam(request.getParameter("BP-STBL"));
        int comf = Integer.parseInt( request.getParameter("COMFORT"));
        
        
        String coreTemp="high";
        if(interTemp<36.0){coreTemp="low";}
        else if(interTemp>=36.0 && interTemp<37){coreTemp="mid";}
        
        String curfaceTemp="high";
        if(surfTemp<35.0){curfaceTemp="low";}
        else if(surfTemp>=36.5 && surfTemp<37){curfaceTemp="mid";}
        
        String OxygSat="excellent";
        if(satur<80.0){OxygSat="poor";}
        else if(satur>=80.0 && satur<90){OxygSat="fair";}
        else if(satur>=90.0 && satur<98){OxygSat="good";}
        
        String bloodPres="high";
        if(bloodPressure<90.0){bloodPres="low";}
        else if(bloodPressure>=130.0 && bloodPressure<90.0){bloodPres="mid";}
        
        Classification classify=new Classification();
        Classificator klasyf=classify.getClassificator();
        
        request.setAttribute("L-CORE", getPLParam(coreTemp));
        request.setAttribute("L-SURF", getPLParam(curfaceTemp));
        request.setAttribute("L-O2", getPLParam(OxygSat));
        request.setAttribute("L-BP", getPLParam(bloodPres));
        request.setAttribute("SURF-STBL", getPLStabilitishParam(stabcoreTemp));
        request.setAttribute("CORE-STBL", getPLStabilitishParam(stabsurfTemp));
        request.setAttribute("BP-STBL", getPLStabilitishParam(stabbloodPressure));
        request.setAttribute("COMFORT", comf);
        String dec="";
        DenseInstance inst=new DenseInstance(9);
       
        if(validation==0){
            inst.setDataset(data);
             inst.setValue(0 , coreTemp);
            inst.setValue(1,curfaceTemp);
            inst.setValue(2 ,OxygSat );
            inst.setValue(3 , bloodPres);
            inst.setValue(4 , stabsurfTemp);
            inst.setValue(5 , stabcoreTemp);
            inst.setValue(6 , stabbloodPressure);
            inst.setValue(7 , comf);
            data.add(inst);
            
            
            double clsLabel=knn.classifyInstance(data.instance(data.numInstances()-1));
            data.instance(data.numInstances()-1).setClassValue(clsLabel);
            dec=inst.classAttribute().value((int) clsLabel);
            
            System.out.println("Przewidziana klasa instancji "+dec);
            
        }
        else{
            inst.setDataset(train);
             inst.setValue(0 , coreTemp);
            inst.setValue(1,curfaceTemp);
            inst.setValue(2 ,OxygSat );
            inst.setValue(3 , bloodPres);
            inst.setValue(4 , stabsurfTemp);
            inst.setValue(5 , stabcoreTemp);
            inst.setValue(6 , stabbloodPressure);
            inst.setValue(7 , comf);
             train.add(inst);
        inst.setDataset(train);
        
        double clsLabel=knn.classifyInstance(train.instance(train.numInstances()-1));
        train.instance(train.numInstances()-1).setClassValue(clsLabel);
        dec=inst.classAttribute().value((int) clsLabel);
        System.out.println("Przewidziana klasa instancji "+dec);
        }
        
        request.setAttribute("decision", dec);
//        response.sendRedirect("displayClassifyInstance.jsp").forward(request,response);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/displayClassifyInstance.jsp");
// wysłanie obiektu request do naszego display.jsp
        dispatcher.forward(request, response);
     
     }
     
     public void getDecision(){
         
     }
    public String getStabilitishParam(String param){
        if(param.equals("stabilna")) return "stable";
        else if(param.equals("niestabilna"))return "unstable";
        else return "mod-stable";
    }
    
    public String getPLStabilitishParam(String param){
        if(param.equals("stable")) return "stabilna";
        else if(param.equals("unstable"))return "niestabilna";
        else return "średnia stabilność";
    }
    
    public String getPLParam(String param){
        if(param.equals("high")) return "wysoka";
        else if(param.equals("low"))return "niska";
        else return "średnia";
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
