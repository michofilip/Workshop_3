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
    <c:if test="${mode == 'add'}">
        <c:set var="title" value="Add solution"/>
        <c:set var="target" value="/solution-add"/>
        <c:set var="button" value="Add"/>
    </c:if>
    <c:if test="${mode == 'edit'}">
        <c:set var="title" value="Edit solution"/>
        <c:set var="target" value="/solution-edit"/>
        <c:set var="button" value="Edit"/>
    </c:if>

    <title>${title}</title>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<form action="${target}" method="post">
    <label>
        ID
        <input type="text" name="id" value="${not empty solution.id ? solution.id : 0}" readonly required>
    </label><br>
    <label>
        Created
        <input type="date" name="createdDate" value="${solution.created.toLocalDate()}" required>
        <input type="time" name="createdTime" value="${solution.created.toLocalTime()}" required>
    </label><br>
    <label>
        Updated
        <input type="date" name="updatedDate" value="${solution.updated.toLocalDate()}">
        <input type="time" name="updatedTime" value="${solution.updated.toLocalTime()}">
    </label><br>
    <label>
        Description<br>
        <textarea name="description">${solution.description}</textarea>
    </label><br>
    <label>
        Exercise
        <select name="exercise">
            <c:forEach var="exercise" items="${exercises}">
                <option value="${exercise.id}" ${solution.exercise.id == exercise.id ? 'selected' : ''}>${exercise.title}</option>
            </c:forEach>
        </select>
    </label><br>
    <label>
        User
        <select name="user">
            <c:forEach var="user" items="${users}">
                <option value="${user.id}" ${solution.user.id == user.id ? 'selected' : ''}>${user.username}</option>
            </c:forEach>
        </select>
    </label><br>
    <input type="submit" value="${button}">
</form>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
