<%--
  Created by IntelliJ IDEA.
  User: klvdo
  Date: 30.04.2020
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>
</head>
<body>

<div style="text-align: center;">
    <h1><c:out value="${message}" default="guest" /></h1>
    <td><a href="/">Try again</a>
    </td>
</div>
</body>
</html>
