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
    <title>Groups</title>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<a href="/group-add">Add group</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Users</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Users</th>
        <th>Actions</th>
    </tr>
    </tfoot>
    <tbody>
    <c:forEach var="group" items="${groups}">
        <tr>
            <td>${group.id}</td>
            <td>${group.name}</td>
            <td><a href="/user-show?groupId=${group.id}">Users</a></td>
            <td>
                <a href="/group-edit?id=${group.id}">Edit</a>
                <a href="/group-del?id=${group.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
