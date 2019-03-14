<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 17.02.2019
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>

<div class="login">
    <div class="login1">

        <% if (request.getAttribute("message") != null) {%>
        <p style="color: red"><%= request.getAttribute("message") %>
        </p>
        <% }%>
        <form action="/login" method="post">
            email: <input class="ex3" type="text" name="email"><br>
            password: <input class="ex4" type="password" name="password"><br>
            <input class="ex1" type="submit" value="Login">
            <a class="ex2" href="/register.jsp">Register</a>


        </form>
    </div>
</div>

</body>
</html>
