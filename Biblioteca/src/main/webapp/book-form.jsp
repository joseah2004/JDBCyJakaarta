<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.daw.jakarta.biblioteca.model.Book" %>
<%@ page import="es.daw.jakarta.biblioteca.model.Author" %>
<html>
<head>
    <title>Formulario de Libro</title>
    <style>
        label { display: block; margin-top: 10px; }
        input, select { width: 300px; padding: 5px; }
        .error { color: red; margin-top: 10px; }
    </style>
</head>
<body>
<h1><%= request.getParameter("id") == null ? "Nuevo Libro" : "Editar Libro" %></h1>

<%
    Book book = (Book) request.getAttribute("book");
    List<Author> authors = (List<Author>) request.getAttribute("authors");
    String error = (String) request.getAttribute("error");
%>

<% if (error != null) { %>
<div class="error"><%= error %></div>
<% } %>

<form action="books" method="post">
    <% if (book != null && book.getId() != 0) { %>
    <input type="hidden" name="id" value="<%= book.getId() %>"/>
    <% } %>

    <label for="title">Título:</label>
    <input type="text" name="title" value="<%= book != null && book.getTitle() != null ? book.getTitle() : "" %>" required/>

    <label for="authorId">Autor:</label>
    <select name="authorId" required>
        <% if (authors != null) {
            for (Author author : authors) { %>
        <option value="<%= author.getId() %>" <%= book != null && author.getId() == book.getAuthorId() ? "selected" : "" %>>
            <%= author.getName() %>
        </option>
        <%  }
        } %>
    </select>

    <label for="publicationDate">Fecha de publicación:</label>
    <input type="date" name="publicationDate"
           value="<%= book != null && book.getPublicationDate() != null ? book.getPublicationDate().toString() : "" %>" required/>

    <br/><br/>
    <button type="submit">Guardar</button>
    <a href="books?action=list">Cancelar</a>
</form>

</body>
</html>