<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: michal
  Date: 18.08.18
  Time: 09:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<a href="/user-add">Add user</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Group</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Group</th>
        <th>Actions</th>
    </tr>
    </tfoot>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td><a href="/group-show?id=${user.group.id}">${user.group.name}</a></td>
            <td>
                <a href="/user-details?id=${user.id}">Details</a>
                <a href="/user-edit?id=${user.id}">Edit</a>
                <a href="/user-del?id=${user.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
