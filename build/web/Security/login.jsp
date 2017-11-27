<%-- 
    Document   : login
    Created on : Nov 24, 2017, 5:18:49 PM
    Author     : Rafael Pangan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action ="LoginServlet" method ="post">
            Username: <input type ="text" name ="username"> <br>
            Password: <input type ="password" name ="password"> <br>
            <input type ="submit" name ="submit" value ="Login!">
        </form>
    </body>
</html>
