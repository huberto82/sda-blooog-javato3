<!DOCTYPE HTML>
<html lang="pl">
<%@include file="head.jspf"%>
<%@page import="servlet.user.UserActions"%>
<%@page import="servlet.article.ArticleActions" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<h2>Hello World!</h2>
<a href = "article?action=<%=ArticleActions.GET.VIEW_ALL%>">Lista artykułów</a>
<a href = "article?action=<%=ArticleActions.GET.ADD%>">Dodaj artykuł</a>
<a href = "user?action=<%=UserActions.GET.REGISTER%>">Zarejestruj</a>
<a href = "user?action=<%=UserActions.GET.LOGIN%>">Zaloguj</a>
<a href = "logged/show?action=logout">Wyloguj</a>
</body>
</html>
