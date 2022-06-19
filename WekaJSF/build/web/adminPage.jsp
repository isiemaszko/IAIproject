<%-- 
    Document   : adminPage
    Created on : 2022-06-14, 18:57:48
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
        <form name="myFormAdmin" action="login" method="POST">
            <input type="radio" name="build" value="cv" checked="checked" />
            <label for="html">cross-validation</label> folds: <input type="text" name="folds" value="10"  /><br>
            <input type="radio" name="build" value="ps"  />
            <label for="html">percentage split</label> %: <input type="text" name="procent" value="66" /><br>
            
            <input type="submit" value="Evaluate" name="submit" />
        </form>
        
        
         <jsp:include page="logout.jsp"/><br>
        


    </body>
</html>
