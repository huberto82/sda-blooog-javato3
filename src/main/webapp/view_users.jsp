<%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 25.05.2019
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<html>
<%@ include file="head.jspf" %>
<body>
<div classs="container col-3">
    <table>
        <tr class = "table">
            <th>
                email
            </th>
            <th>
                nick
            </th>
            <th>
                data rejestracji
            </th>
        </tr>
        <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <td>
                    <a href="user?action=view&id=${user.id}">${user.email}</a>
                </td>
                <td>
                    ${user.nick}
                </td>
                <td>
                    ${user.registered}
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
