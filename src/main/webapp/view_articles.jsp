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
<div classs="container">
    <table>
        <tr>
            <th>
                Tytuł
            </th>
        </tr>
        <c:forEach var="article" items="${requestScope.articles}">
            <tr>
                <td>
                    <a href="article?action=VIEW&id=${article.id}">${article.title}</a>
                </td>
                <td>
                    <a href="article?action=DELETE&id=${article.id}">Usuń</a>
                </td>
                <td>
                    <a href="article?action=CHANGE_TITLE&id=${article.id}">Zmień tytuł</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
