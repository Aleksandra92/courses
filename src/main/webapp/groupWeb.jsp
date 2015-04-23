<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" %>
<html>
<head>
    <title>Список групп</title>
</head>
<body>
<c:if test="${!empty groups}">
    <table class="data">
        <tr>
            <th>"Id"</th>
            <th>"Group name"</th>
            <th>"Curetor"</th>
            <th>"Speciality"</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${groups}" var="group">
        <tr>
            <td>${group.id}</td>
            <td>${group.groupName}</td>
            <td>${group.curator}</td>
            <td>${group.speciality}</td>
            </c:forEach>
    </table>
</c:if>

</body>
</html>