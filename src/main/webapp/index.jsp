<!DOCTYPE HTML>
<html lang="pl">
<%@include file="head.jspf"%>
<%@page import="servlet.UserAction"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<h2>Hello World!</h2>
<a href = "article?action=">Lista artykułów</a>
<a href = "article?action=">Dodaj artykuł</a>
<a href = "user?action=<%=UserAction.GET_REGISTER%>">Zarejestruj</a>
<a href = "user?action=<%=UserAction.GET_LOGIN%>">Zaloguj</a>
<a href = "logged/show?action=logout">Wyloguj</a>
</body>
</html>
