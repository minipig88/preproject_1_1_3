<%--
  Created by IntelliJ IDEA.
  User: klvdo
  Date: 29.04.2020
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<div style="text-align: center;">
    <h1>User Management</h1>
    <h2>
        <form action="/" method="GET">
            <input type="submit" value="List All Users">
        </form> &nbsp;&nbsp;&nbsp;
        <form action="/new" method="GET">
            <input type="submit" value="Add New User">
        </form>

    </h2>
</div>
<div align="center">
    <c:if test="${user != null}">
    <form action="/edit" method="post">
        </c:if>
        <c:if test="${user == null}">
        <form action="/new" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${user != null}">
                            Edit User
                        </c:if>
                        <c:if test="${user == null}">
                            Add New User
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                </c:if>
                <tr>
                    <th>User First Name: </th>
                    <td>
                        <input type="text" name="firstName" size="45"
                               value="<c:out value='${user.firstName}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>User Second Name: </th>
                    <td>
                        <input type="text" name="secondName" size="45"
                               value="<c:out value='${user.secondName}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Email: </th>
                    <td>
                        <input type="text" name="email" size="15"
                               value="<c:out value='${user.email}' />"
                        />
                    </td>
                </tr>
                <tr><tr>
                    <th>Password: </th>
                    <td>
                        <input type="text" name="password" size="15"
                               value="<c:out value='${user.password}' />"
                        />
                    </td>
                </tr>
                <tr><tr>
                    <th>Role: </th>
                    <td>
                        <input type="text" name="role" size="15"
                               value="<c:out value='${user.role}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>