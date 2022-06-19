<%-- 
    Document   : displayClassifyInstance
    Created on : 2022-06-14, 17:28:27
    Author     : izabe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form</title>
    </head>
    <style>
        .left {
       
        position: fixed;
         }

        .right{
            
            margin-left: 20%;
            padding-left: 200px;
        }
    </style>
    <body>
        <div class="left">
             <jsp:include page="userPage.jsp"/><br>
        </div>
        <div class="right">
            <h1>Podsumowanie</h1>
        <h3> Ostatni pomiar temperatury wewnętrznej: <%=request.getAttribute("L-CORE")%></h3>
         <h3> Ostatni pomiar temperatury ciała: <%=request.getAttribute("L-SURF")%></h3>
          <h3> Ostatni pomiar saturacji: <%=request.getAttribute("L-O2")%></h3>
           <h3>Ostatni pomiar ciśnienia krwi: <%=request.getAttribute("L-BP")%></h3>
            <h3> Stabilność temperatury ciała: <%=request.getAttribute("SURF-STBL")%></h3>
             <h3>Stabilność temperatury wewnętrznej: <%=request.getAttribute("CORE-STBL")%></h3>
              <h3> Stabilność ciśnienia krwi: <%=request.getAttribute("BP-STBL")%></h3>
           <h3>Samopoczucie pacjenta przy wypisie: <%=request.getAttribute("COMFORT")%></h3><br>
           
            
           <h1>Wynik: <%=request.getAttribute("decision")%></h1><br>
        </div>
         
           
    </body>
</html>
