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
        <c:set var="title" value="Add exercise"/>
        <c:set var="target" value="/exercise-add"/>
        <c:set var="button" value="Add"/>
    </c:if>
    <c:if test="${mode == 'edit'}">
        <c:set var="title" value="Edit exercise"/>
        <c:set var="target" value="/exercise-edit"/>
        <c:set var="button" value="Edit"/>
    </c:if>

    <title>${title}</title>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<form action="${target}" method="post">
    <label>
        ID
        <input type="text" name="id" value="${not empty exercise.id ? exercise.id : 0}" readonly required>
    </label><br>
    <label>
        Title
        <input type="text" name="title" value="${exercise.title}" required>
    </label><br>
    <label>
        Description<br>
        <textarea name="description">${exercise.description}</textarea>
    </label><br>
    <input type="submit" value="${button}">
</form>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
