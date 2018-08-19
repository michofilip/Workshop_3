<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: michal
  Date: 18.08.18
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User details</title>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<a href="/user-edit?id=${user.id}">Edit</a>
<a href="/user-del?id=${user.id}">Delete</a>

<table>
    <tbody>
    <tr>
        <th>ID</th>
        <td>${user.id}</td>
    </tr>
    <tr>
        <th>Username</th>
        <td>${user.username}</td>
    </tr>
    <tr>
        <th>Email</th>
        <td>${user.email}</td>
    </tr>
    <tr>
        <th>Password</th>
        <td>${user.password}</td>
    </tr>
    <tr>
        <th>Group</th>
        <td>${user.group.name}</td>
    </tr>
    </tbody>
</table>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Created</th>
        <th>Updated</th>
        <th>Exercise</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="solution" items="${solutions}">
        <tr>
            <td><a href="/solution-show?id=${solution.id}">${solution.id}</a></td>
            <td>${solution.created}</td>
            <td>${solution.updated}</td>
            <td><a href="/exercise-show?id=${solution.exercise.id}">${solution.exercise.title}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
