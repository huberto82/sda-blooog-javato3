<%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 26.05.2019
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="head.jspf"%>
<body>
    <div class ="container col-6">
        <form action="article?action=update&id=${requestScope.article.id}" method="post">
            <div class="form-group ">
                <input  class = "form-control mb-3" type="text" name = "title" value="${requestScope.article.title}"/>
                <textarea class ="form-control mb-3" disabled name = "content">
                    ${requestScope.article.content}
                </textarea>
                <button class="form-control btn btn-primary mb-3" type="submit">Zmień</button>
            </div>
        </form>
    </div>
</body>
</html>
