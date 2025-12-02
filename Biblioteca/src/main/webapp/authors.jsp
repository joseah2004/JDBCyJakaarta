<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, es.daw.jakarta.biblioteca.model.Author" %>
<html>
<head>
    <title>Autores</title>
</head>
<body>
<h1>Listado de Autores</h1>
<%
    String lastAuthor = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("lastAuthor".equals(c.getName())) {
                lastAuthor = java.net.URLDecoder.decode(c.getValue(), java.nio.charset.StandardCharsets.UTF_8.name());
                break;
            }
        }
    }

    if (lastAuthor != null) {
%>
<p style="color: green;">Último autor editado: <strong><%= lastAuthor %></strong></p>
<%
    }
%>


<a href="authors?action=new">Nuevo Autor</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Acciones</th>
    </tr>
    <%
        List<Author> authors = (List<Author>) request.getAttribute("authors");
        for (Author a : authors) {
    %>
    <tr>
        <td><%= a.getId() %></td>
        <td><%= a.getName() %></td>
        <td>
            <a href="authors?action=edit&id=<%= a.getId() %>">Editar</a>
            <a href="authors?action=delete&id=<%= a.getId() %>"
               onclick="return confirm('¿Seguro que quieres borrar este autor?')">Borrar</a>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>