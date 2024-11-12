<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход в систему</title>
</head>
<body>
<h1>Вход в систему</h1>
<form action="j_security_check" method="post">
    <label for="j_username">Имя пользователя:</label>
    <input type="text" id="j_username" name="j_username"><br>
    <label for="j_password">Пароль:</label>
    <input type="password" id="j_password" name="j_password"><br>
    <input type="submit" value="Войти">
</form>
</body>
</html>