<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 19.02.2019
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
<%
    User user = (User) request.getSession().getAttribute("user");
    List<User> allUsers = (List<User>) request.getAttribute("allUsers");
    Set<User> allRequest = (Set<User>) request.getAttribute("allRequest");
    Set<User> allFriends = (Set<User>) request.getAttribute("allFriends");
%>
<div class="main">

    <div class="profil">
        <img src="/getImage?picUrl=<%=user.getPicUrl()%>" width="200">
        <p><%=user.getName()%>
        </p>
        <p><%=user.getSurname()%>
        </p>

        <h4><a href="/logout">LOGOUT</a> </h4>

    </div>
    <div class="user">
        <h3>All USERS: </h3>
        <table>
            <tr>
                <td>Name</td>
                <td>Surname</td>
                <td>Photo</td>
                <td>Request</td>
            </tr>
        </table>

        <% for (User allUser : allUsers) {
//        if (allUser.getId()== allUser.getId()){


        %>
        <table>


            <tr>

                <td><%=allUser.getName()%>
                </td>
                <td><%=allUser.getSurname()%>
                </td>
                <td><img src="/getImage?picUrl=<%=allUser.getPicUrl()%>" width="100 px"></td>
                <td><a href="/sendFriendsRequset?to_id=<%=allUser.getId()%>">SEND</a></td>
                <%--<td><a href="/sendMessenger?" </td>--%>

            </tr>
            <% }%>
            <%--<% } %>--%>
        </table>
    </div>

    <% if (allFriends != null) { %>
    <div class="friends">
        <table>
            <h3>FRIENDS LIST: </h3>
            <tr>
                <td>Name</td>
                <td>Surname</td>
                <td>Photo</td>
                <%--<td>Request</td>--%>
                <td>Send Message</td>
            </tr>
        </table>
        <%
            for (User allFriend : allFriends) {%>


        <table>


            <tr>

                <td><%=allFriend.getName()%>
                </td>
                <td><%=allFriend.getSurname()%>
                </td>
                <td><img src="/getImage?picUrl=<%=allFriend.getPicUrl()%>" width="70 px"></td>
                <td><a href="/sendMessage?id=<%= allFriend.getId()%>">Message</a></td>


            </tr>
            <% }%>

        </table>
    </div>
    <% } %>

    <% if (allRequest != null) { %>
    <div class="request" >
        <h3> All Request: </h3>
        <table>
            <tr>
                <td>Name</td>
                <td>Surname</td>
                <td>Picture</td>
                <td>Accept</td>
                <td>Reject</td>

            </tr>
        </table>
        <% for (User user1 : allRequest) { %>


        <table border="1">


            <tr>

                <td><%=user1.getName()%>
                </td>
                <td><%=user1.getSurname()%>
                </td>
                <td><img src="/getImage?picUrl=<%=user1.getPicUrl()%>" width="70 px"></td>
                <td><a href="/acceptOrReject?from_id=<%=user1.getId()%>&action=Accept">Accept</a></td>
                <td><a href="/acceptOrReject?from_id=<%=user1.getId()%>&action=Reject">Reject</a></td>

            </tr>
            <% }%>
        </table>
    </div>
    <% } %>
</div>
</body>
</html>
