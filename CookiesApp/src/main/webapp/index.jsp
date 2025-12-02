<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="jakarta.servlet.http.Cookie" %>
<!DOCTYPE html>
<html>
<head>
    <title>Galletitas</title>
</head>

<%
    String color = "#ffffff"; // valor por defecto
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("colorFondo".equals(c.getName())) {
                if (c.getValue() != null && !c.getValue().isBlank()) {
                    color = "#" + c.getValue(); // añadimos el #
                }
                break;
            }
        }
    }
%>

<body style="background-color:<%= color %>;">
<h1>Gallllettaasss!</h1>
<p>Color de fondo actual: <strong><%= color %></strong></p>

<p>¿Quieres cambiar el color del fondo?</p>
<ul>
    <li><a href="<%= request.getContextPath() %>/color.jsp">Sí, cambiar color</a></li>
    <li><a href="<%= request.getContextPath() %>/preferencias?accion=borrar">Borrar la preferencia</a></li>
</ul>
</body>
</html>

