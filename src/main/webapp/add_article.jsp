<%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 25.05.2019
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="servlet.article.ArticleActions" %>

<html>
<%@ include file="head.jspf" %>
<body>
<div class="container col-6">
    <h1>Dodaj artykuł do bloga</h1>
    <form action="article?${ArticleAction.PARAMETER_ACTION}=<%=ArticleActions.POST.ADD%>" method="post">
        <div class = "form-group">
            <input class ="form-control mb-3" type="text" placeholder="wpisz tytuł" name=${ArticleAction.PARAMETER_TITLE}/>
            <textarea class ="form-control mb-3"  placeholder="wpisz treść" name=${ArticleAction.PARAMETER_CONTENT}> </textarea>
            <button class ="form-control mb-3" type="submit">Dodaj</button>
        </div>
    </form>
</div>
</body>
</html>
