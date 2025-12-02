<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="es.daw.jakarta.biblioteca.model.Author" %>
<html>
<head>
    <title>Formulario Autor</title>
</head>
<body>

<%
    Author author = (Author) request.getAttribute("author");
    String error = (String) request.getAttribute("error");
    boolean esNuevo = (author == null || author.getId() == 0);
%>

<h1><%= esNuevo ? "Nuevo Autor" : "Editar Autor" %></h1>

<% if (error != null) { %>
<p style="color:red;"><%= error %></p>
<% } %>

<form action="authors" method="post">
    <% if (!esNuevo) { %>
    <input type="hidden" name="id" value="<%= author.getId() %>" />
    <% } %>
    <label for="name">Nombre:</label>
    <input type="text" name="name" value="<%= (author != null && author.getName() != null) ? author.getName() : "" %>" />
    <button type="submit">Guardar</button>
    <a href="authors?action=list">Cancelar</a>
</form>

</body>
</html>