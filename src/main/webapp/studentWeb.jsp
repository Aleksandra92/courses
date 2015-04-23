<%--
  Created by IntelliJ IDEA.
  User: Александра
  Date: 16.04.2015
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cписок студентов</title>
</head>
<body>
<c:if test="${!empty students}">
  <table class="data">
    <tr>
      <th>Id</th>
      <th>Name</th>
      <th>Last name</th>
      <th>Middle name</th>
      <th>Dare of birth</th>
      <th>Sex</th>
      <th>Group id</th>
      <th>Education year</th>
      <th>&nbsp;</th>
    </tr>
    <c:forEach items="${students}" var="student">
    <tr>
      <td>${student.id}</td>
      <td>${student.firstName}</td>
      <td>${student.lastName}</td>
      <td>${student.middleName}</td>
      <td>${student.dateOfBirth}</td>
      <td>${student.sex}</td>
      <td>${student.groupId}</td>
      <td>${student.educationYear}</td>
      </c:forEach>
  </table>
</c:if>

</body>
</html>
