<%@ page import="java.util.List" %>
<%@ page import="model.Message" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 23.02.2019
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
hello

<%--<h4>--%>
<%--<%= message.getFromId()%>   <%= message.getDate()%>--%>
<%--</h4>--%>
<%--<h3>--%>
<%--<%= message.getMessage()%>--%>
<%--</h3>--%>

<form action="/sendMessage" method="post" enctype="multipart/form-data">


    <%
        List<Message> messages = (List<Message>) request.getAttribute("message");
        for (Message message : messages) {%>
    <%--<input type="hidden" name="toId" value="<%=message.getFromId()%>">--%>

    <%}%>
    <textarea rows="4" cols="50">
</textarea>
    <input type="file" value="file">
    <input type="submit" value="Message"><br><br>
    <a href="/userHome">Go back</a>

</form>


</body>
</html>
