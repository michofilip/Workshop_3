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
    <title>Exercises</title>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<a href="/exercise-add">Add exercise</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    </tfoot>
    <tbody>
    <c:forEach var="exercise" items="${exercises}">
        <tr>
            <td>${exercise.id}</td>
            <td>${exercise.title}</td>
            <td>${exercise.description}</td>
            <td>
                <a href="/exercise-edit?id=${exercise.id}">Edit</a>
                <a href="/exercise-del?id=${exercise.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
