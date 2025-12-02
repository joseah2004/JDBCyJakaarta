<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de usuarios</title>
</head>
<body>
<h1><%= "Formulario de usuarios JSP" %></h1>

<!-- PENDIENTE PINTAR LISTA DE ERRORES -->
<%
    Map<String,String> errores = (Map<String, String>) request.getAttribute("errores");

    String usernameVal = request.getParameter("username") != null? request.getParameter("username"):"";
    String passwordVal = request.getParameter("password") != null? request.getParameter("password"):"";
%>

<% if (errores != null && !errores.isEmpty()) {%>
    <ul>
        <% for(String error: errores.values()){%>
            <li><%=error%></li>
        <%}%>
    </ul>
<%}%>

<%--<%--%>
<%--    // OTRA FORMA DE HACERLO PERO LAS ETIQUETAS HTML ESTÁN COMO TEXTO... SE MANEJA PEOR--%>
<%--    if (errores != null && !errores.isEmpty()) {--%>
<%--        StringBuilder sb = new StringBuilder("<ul>");--%>
<%--        for (String error : errores.values()) {--%>
<%--            sb.append("<li>").append(error).append("</li>");--%>
<%--        }--%>
<%--        sb.append("</ul>");--%>
<%--        out.print(sb.toString());--%>
<%--    }--%>
<%--%>--%>

<form action="registro2" method="post">

    <div>
        <label for="username">Usuario</label>
        <div><input type="text" name="username" id="username" value="<%=usernameVal%>"></div>

        <%
            if (errores != null && errores.containsKey("username")){
                out.println("<div style='color:red'>"+errores.get("username")+"</div>");
            }
        %>
    </div>
    <div>
        <label for="password">Password</label>
        <div><input type="password" name="password" id="password" value="${param.password}"></div>
        <!-- pendiente: pintar un div con el mensaje de error -->
        <% if (errores != null && errores.containsKey("password")){%>
            <div style='color:red'><%=errores.get("password")%></div>
        <%}%>
    </div>
    <div>
        <label for="email">Email</label>
        <div><input type="text" name="email" id="email"></div>
    </div>
    <div>
        <label for="pais">País</label>
        <div>
            <select name="pais" id="pais">
                <option value="">-- seleccionar --</option>
                <option value="ES">España</option>
                <option value="MX">México</option>
                <option value="CL" selected>Chile</option>
                <option value="AR">Argentina</option>
                <option value="PE">Perú</option>
                <option value="CO">Colombia</option>
                <option value="VE">Venezuela</option>
            </select>
        </div>
    </div>

    <div>
        <label for="lenguajes">Lenguajes de programación</label>
        <div>
            <select name="lenguajes" id="lenguajes" multiple>
                <option value="java" selected>Java SE</option>
                <option value="jakartaee" selected>Jakarta EE9</option>
                <option value="spring">Spring Boot</option>
                <option value="js">JavaScript</option>
                <option value="angular" selected>Angular</option>
                <option value="react">React</option>
            </select>
        </div>
    </div>

    <div>
        <label>Roles</label>
        <div>
            <input type="checkbox" name="roles" value="ROLE_ADMIN">
            <label>Administrador</label>
        </div>
        <div>
            <input type="checkbox" name="roles" value="ROLE_USER" checked>
            <label>Usuario</label>
        </div>
        <div>
            <input type="checkbox" name="roles" value="ROLE_MODERATOR">
            <label>Moderador</label>
        </div>
    </div>
    <div>
        <label>Idiomas</label>
        <div>
            <input type="radio" name="idioma" value="es">
            <label>Español</label>
        </div>
        <div>
            <input type="radio" name="idioma" value="en">
            <label>Inglés</label>
        </div>
        <div>
            <input type="radio" name="idioma" value="fr">
            <label>Frances</label>
        </div>
    </div>
    <div>
        <label for="habilitar">Habilitar</label>
        <div>
            <input type="checkbox" name="habilitar" id="habilitar" checked>
        </div>
    </div>
    <div>
        <div>
            <input type="submit" value="Enviar">
        </div>
    </div>
    <input type="hidden" name="secreto" value="12345">
</form>
</body>
</html>