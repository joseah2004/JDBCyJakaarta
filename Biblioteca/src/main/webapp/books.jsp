<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.daw.jakarta.biblioteca.model.Book" %>
<html>
<head>
    <title>Listado de Libros</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { padding: 8px; border: 1px solid #ccc; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
<h1>Libros</h1>
<%
    String lastBook = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("lastBook".equals(c.getName())) {
                lastBook = java.net.URLDecoder.decode(c.getValue(), java.nio.charset.StandardCharsets.UTF_8.name());
                break;
            }
        }
    }

    if (lastBook != null) {
%>
<p style="color: blue;">Último libro editado: <strong><%= lastBook %></strong></p>
<%
    }
%>

<a href="books?action=new">➕ Añadir nuevo libro</a>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
    }
%>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    if (books != null && !books.isEmpty()) {
%>
<table>
    <tr>
        <th>ID</th>
        <th>Título</th>
        <th>ID Autor</th>
        <th>Fecha de Publicación</th>
        <th>Acciones</th>
    </tr>
    <% for (Book book : books) { %>
    <tr>
        <td><%= book.getId() %></td>
        <td><%= book.getTitle() %></td>
        <td><%= book.getAuthorId() %></td>
        <td><%= book.getPublicationDate() %></td>
        <td>
            <a href="books?action=edit&id=<%= book.getId() %>">✏️ Editar</a>
            <a href="books?action=delete&id=<%= book.getId() %>" onclick="return confirm('¿Seguro que quieres borrar este libro?')">🗑️ Borrar</a>
        </td>
    </tr>
    <% } %>
</table>
<%
} else {
%>
<p>No hay libros registrados.</p>
<%
    }
%>

</body>
</html>