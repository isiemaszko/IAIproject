<%-- 
    Document   : index
    Created on : 2022-03-24, 13:59:27
    Author     : izabe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form</title>
    </head>
    <body>
      <div align="center">
        <h1> Login Form</h1>
        <form action="login" method="post">
         <table style="with: 100%">
          <tr>
           <td>UserName</td>
           <td><input type="text" name="username" /></td>
          </tr>
          <tr>
           <td>Password</td>
           <td><input type="password" name="password" /></td>
          </tr>

         </table>
         <input type="submit" value="Submit" />
        </form>
       </div>
    </body>
</html>
