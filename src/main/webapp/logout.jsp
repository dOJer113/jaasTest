<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Уничтожаем сессию пользователя
    session.invalidate();

    // Перенаправляем на страницу входа
    response.sendRedirect("login.jsp");
%>