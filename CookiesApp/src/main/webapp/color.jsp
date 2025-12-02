<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="jakarta.servlet.http.Cookie" %>
<!DOCTYPE html>
<html>
<head>
    <title>Color</title>
</head>

<%
    String color = "#ffffff";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("colorFondo".equals(c.getName())) {
                if (c.getValue() != null && !c.getValue().isBlank()) {
                    color = "#" + c.getValue();
                }
                break;
            }
        }
    }
%>

<body style="background-color:<%= color %>;">
<h1>Elige tu color de fondo</h1>

<form action="<%= request.getContextPath() %>/preferencias" method="post">
    <label for="color">Color</label>
    <input type="color" id="color" name="color" value="<%= color %>">
    <button type="submit">Guardar</button>
</form>

<p>
    <a href="<%= request.getContextPath() %>/index.jsp">Volver</a>
    <a href="<%= request.getContextPath() %>/preferencias?accion=borrar">Borrar preferencia</a>
</p>
</body>
</html>



