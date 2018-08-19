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
        <c:set var="title" value="Add group"/>
        <c:set var="target" value="/group-add"/>
        <c:set var="button" value="Add"/>
    </c:if>
    <c:if test="${mode == 'edit'}">
        <c:set var="title" value="Edit group"/>
        <c:set var="target" value="/group-edit"/>
        <c:set var="button" value="Edit"/>
    </c:if>

    <title>${title}</title>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<form action="${target}" method="post">
    <label>
        ID
        <input type="text" name="id" value="${not empty group.id ? group.id : 0}" readonly required>
    </label><br>
    <label>
        Name
        <input type="text" name="name" value="${group.name}" required>
    </label><br>
    <input type="submit" value="${button}">
</form>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
