<%-- 
    Document   : userPage
    Created on : 2022-06-14, 15:50:46
    Author     : izabe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USER</title>
    </head>
    <body>
        <h1>Form</h1>
        <form name="myFormUser" action="login" method="POST">
            Ostatni pomiar temperatury wewnętrznej:<br>
            <input type="text" name="L-CORE" value=""  /><br>
            Ostatni pomiar temperatury ciała:<br>
            <input type="text" name="L-SURF" value=""  /><br>
            Ostatni pomiar saturacji:<br>
            <input type="text" name="L-O2" value=""  /><br>
             Ostatni pomiar ciśnienia krwi:<br>
            <input type="text" name="L-BP" value=""  /><br>
             Stabilność temperatury ciała:<br>
             <select name="SURF-STBL">
                <option>stabilna</option>
                <option>średnia stabilność</option>
                <option>niestabilna</option>
               
            </select><br>
            Stabilność temperatury wewnętrznej:<br>
            <select name="CORE-STBL">
                <option>stabilna</option>
                <option>średnia stabilność</option>
                <option>niestabilna</option>
               
            </select><br>
           
            Stabilność ciśnienia krwi:<br>
             <select name="BP-STBL">
                <option>stabilna</option>
                <option>średnia stabilność</option>
                <option>niestabilna</option>
            </select><br>
            Samopoczucie pacjenta przy wypisie:<br>
             <select name="COMFORT">
                <option>0</option> <option>1</option> <option>2</option> <option>3</option> <option>4</option> <option>5</option> <option>6</option> <option>7</option> <option>8</option> <option>9</option> <option>10</option>
          <option>11</option> <option>12</option> <option>13</option> <option>14</option> <option>15</option> <option>16</option> <option>17</option> <option>18</option> <option>19</option> <option>20</option>   
             </select><br>
            
             <br>
            <input type="submit" value="Klasyfikacja" name="submit" />
        </form>
          <jsp:include page="logout.jsp"/><br>
    </body>
</html>
