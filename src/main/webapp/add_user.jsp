<%--
  Created by IntelliJ IDEA.
  User: cs
  Date: 25.05.2019
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="servlet.user.UserActions" %>
<html>
<%@ include file="head.jspf" %>
<body>
<div class="container col-6">
    <h1>Dodaj użytkownika do bloga</h1>
    <form action="user?${UserActions.PARAMETER_ACTION}=<%=UserActions.POST.REGISTER%>" method="post">
        <div class = "form-group">
            <input class ="form-control mb-3" type="email" placeholder="wpisz email" name= "${UserActions.PARAMETER_EMAIL}" />
            <input class ="form-control mb-3" type="text" placeholder="wpisz nick" name= "${UserActions.PARAMETER_NICK}" />
            <input class ="form-control mb-3" type="password" placeholder="wpisz hasło" name = "${UserActions.PARAMETER_PASSWORD}" />
            <input class ="form-control mb-3" type="password" placeholder="powtórz hasło" name = "${UserActions.PARAMETER_REPEATED_PASSWORD}" />
            <button class ="form-control mb-3" type="submit">Zarejestruj</button>
        </div>
    </form>
</div>
</body>
</html>
