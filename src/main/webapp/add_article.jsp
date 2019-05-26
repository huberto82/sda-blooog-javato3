<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<%@ include file="head.jspf" %>
<body>
<div class="container col-6">
    <h1>Dodaj artykuł do bloga</h1>
    <form action="article?action=add" method="post">
        <div class = "form-group">
            <input class ="form-control mb-3" type="text" name="title" placeholder="wpisz tytuł"/>
            <textarea class ="form-control mb-3" name="content" placeholder="wpisz treść"></textarea>
            <button class ="form-control mb-3" type="submit">Dodaj</button>
        </div>
    </form>
</div>
</body>
</html>
