<%-- 
    Document   : evalPage
    Created on : 2022-06-18, 12:43:35
    Author     : izabe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <jsp:include page="adminPage.jsp"/><br>
         === Summary ===<br>
        
        <%=request.getAttribute("evalCCI")%><br>
         <%=request.getAttribute("evalICI")%><br>
          <%=request.getAttribute("evalKs")%><br>
           <%=request.getAttribute("evalMAE")%><br>
            <%=request.getAttribute("evalRMSE")%><br>
              <%=request.getAttribute("evalTNI")%><br>
              <%=request.getAttribute("evalTNII")%><br>
              <%=request.getAttribute("evalTNIII")%><br>
              <br><br>
              === Confusion Matrix ===<br>
              
        <%=request.getAttribute("matrix1")%><br>
         <%=request.getAttribute("matrix2")%><br>
          <%=request.getAttribute("matrix3")%><br>
           <%=request.getAttribute("matrix4")%><br>
   <br><br>
             === Detailed Accuracy By Class ===<br>
             
             <table>
                 <tr>
                     <th>Recall</th>
                      <th>Precision</th>
                       <th>F-Measure</th>
                        <th>Class</th>
                 </tr>
                  <tr>
                     <th>  <%=request.getAttribute("tpRate0")%></th>
                      <th>  <%=request.getAttribute("precisione0")%></th>
                       <th>  <%=request.getAttribute("fMeasure0")%></th>
                        <th>I</th>
                 </tr>
                  <tr>
                     <th>  <%=request.getAttribute("tpRate1")%></th>
                      <th>  <%=request.getAttribute("precisione1")%></th>
                       <th>  <%=request.getAttribute("fMeasure1")%></th>
                        <th>S</th>
                 </tr>
                  <tr>
                     <th>  <%=request.getAttribute("tpRate2")%></th>
                      <th>  <%=request.getAttribute("precisione2")%></th>
                       <th>  <%=request.getAttribute("fMeasure2")%></th>
                        <th>A</th>
                 </tr>
             </table>
            
    </body>
</html>
