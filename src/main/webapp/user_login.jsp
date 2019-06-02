<%@page import="servlet.UserAction" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="d-flex align-content-center flex-wrap">
    <div class="container w-50">
        <h1>Login Form</h1>
        <form action='user?action=${UserAction.POST_LOGIN}' method='post'>
            <div class="form-group mb-3">
                <input class="form-control mb-3" type="email" placeholder="user name" name='${UserAction.PARAMETER_EMAIL}'/>
                <input class="form-control mb-3" type='password' placeholder="password" name='${UserAction.PARAMETER_PASSWORD}'/>
                <button class="btn btn-primary mb-3" type='submit'>Login</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>