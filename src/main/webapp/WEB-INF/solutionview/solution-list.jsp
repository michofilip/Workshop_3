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
    <title>Solutions</title>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<a href="/solution-add">Add solution</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Created</th>
        <th>Updated</th>
        <th>Description</th>
        <th>Exercise</th>
        <th>User</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>ID</th>
        <th>Created</th>
        <th>Updated</th>
        <th>Description</th>
        <th>Exercise</th>
        <th>User</th>
        <th>Actions</th>
    </tr>
    </tfoot>
    <tbody>
    <c:forEach var="solution" items="${solutions}">
        <tr>
            <td>${solution.id}</td>
            <td>${solution.created}</td>
            <td>${solution.updated}</td>
            <td>${solution.description}</td>
            <td><a href="/exercise-show?id=${solution.exercise.id}">${solution.exercise.title}</a></td>
            <td><a href="/user-show?id=${solution.user.id}">${solution.user.username}</a></td>
            <td>
                <a href="/solution-edit?id=${solution.id}">Edit</a>
                <a href="/solution-del?id=${solution.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
