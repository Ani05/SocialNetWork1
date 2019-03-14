<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
<%%>
<div class="reg">
    <div class="register">
        <form action="/register" method="post" enctype="multipart/form-data">
            name:<input type="text" name="name"><br>
            surname:<input type="text" name="surname"><br>
            email:<input type="text" name="email"><br>
            password:<input type="password" name="password"><br>
            Image: <input type="file" name="picUrl"/><br><br>


            <input type="submit" value="Registration">
        </form>
    </div>

</div>

</body>
</html>
