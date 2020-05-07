<%--
  Created by IntelliJ IDEA.
  User: klvdo
  Date: 07.05.2020
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Data</title>
</head>
<body>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>FirstName</th>
            <th>SecondName</th>
            <th>Email</th>
            <th>Password</th>
        </tr>
            <tr>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.secondName}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.password}"/></td>
            </tr>
    </table>
</div>
</body>
</html>
