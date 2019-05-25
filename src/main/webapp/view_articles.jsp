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
    <%--
    <sql:setDataSource var="baza"
                       driver="com.mysql.cj.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/blooog?serverTimezone=UTC"
                       user="root"
                       password=""/>
    <sql:query var="articles" dataSource="${baza}">
        SELECT * FROM ARTICLE;
    </sql:query>
    --%>
    <table>
        <tr>
            <th>
                Tytuł
            </th>
        </tr>
        <c:forEach var="article" items="${requestScope.articles}">
            <tr>
                <td>
                    <a href="/blooog_war/article?action=view&id=${article.id}">${article.title}</a>
                </td>
                <td>
                    <a href="article?action=delete&id=${article.id}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
