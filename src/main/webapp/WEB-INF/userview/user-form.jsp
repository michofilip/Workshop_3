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
        <c:set var="title" value="Add user"/>
        <c:set var="target" value="/user-add"/>
        <c:set var="button" value="Add"/>
    </c:if>
    <c:if test="${mode == 'edit'}">
        <c:set var="title" value="Edit user"/>
        <c:set var="target" value="/user-edit"/>
        <c:set var="button" value="Edit"/>
    </c:if>

    <title>${title}</title>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<form action="${target}" method="post">
    <label>
        ID
        <input type="text" name="id" value="${not empty user.id ? user.id : 0}" readonly required>
    </label><br>
    <label>
        Username
        <input type="text" name="username" value="${user.username}" required>
    </label><br>
    <label>
        Email
        <input type="email" name="email" value="${user.email}" required>
    </label><br>
    <label>
        Password
        <input type="text" name="password" value="${user.password}" required>
    </label><br>
    <label>
        Group
        <select name="group">
            <c:forEach var="group" items="${groups}">
                <option value="${group.id}" ${user.group.id == group.id ? 'selected' : ''}>${group.name}</option>
            </c:forEach>
        </select>
    </label><br>
    <input type="submit" value="${button}">
</form>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
