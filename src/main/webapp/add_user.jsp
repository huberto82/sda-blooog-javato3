<%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 25.05.2019
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="head.jspf" %>
<body>
<div class="container col-6">
    <h1>Dodaj artykuł do bloga</h1>
    <form action="user?action=add" method="post">
        <div class = "form-group">
            <input class ="form-control mb-3" type="email" name="email" placeholder="wpisz email"/>
            <input class ="form-control mb-3" type="text" name="nick" placeholder="wpisz nick"/>
            <input class ="form-control mb-3" type="password" name="password" placeholder="wpisz hasło"/>
            <input class ="form-control mb-3" type="password" name="repeatedPassword" placeholder="wpisz hasło"/>
            <button class ="form-control mb-3" type="submit">Zarejestruj</button>
        </div>
    </form>
</div>
</body>
</html>
