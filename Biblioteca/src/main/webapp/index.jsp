
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Biblioteca JDBC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #f0f4f8, #e6ebef);
            font-family: 'Segoe UI', sans-serif;
        }
        .hero {
            text-align: center;
            padding: 3rem 1rem;
        }
        .hero h1 {
            font-weight: 700;
            color: #0d6efd;
        }
        .card-container {
            display: flex;
            justify-content: center;
            gap: 2rem;
            flex-wrap: wrap;
        }
        .card {
            width: 280px;
            border: none;
            border-radius: 1rem;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            transition: transform 0.2s ease-in-out;
        }
        .card:hover {
            transform: translateY(-5px);
        }
        .btn-custom {
            border-radius: 20px;
        }
        footer {
            text-align: center;
            color: #888;
            margin-top: 3rem;
        }
    </style>
</head>
<body>
<div class="container mt-5">

    <section class="hero">
        <h1>📚 Biblioteca JDBC</h1>
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
        <%
            String lastBook = null;
            Cookie[] cookies2 = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies2) {
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
        <p class="lead text-secondary">Gestión sencilla de autores y libros</p>
    </section>

    <div class="card-container">
        <!-- Card de Libros -->
        <div class="card text-center">
            <div class="card-body">
                <h4 class="card-title text-primary">📖 Libros</h4>
                <p class="card-text text-muted">Consulta, añade o edita libros de la biblioteca.</p>
                <a href="<%= request.getContextPath() %>/books?action=list" class="btn btn-primary btn-custom">Gestionar libros</a>
            </div>
        </div>

        <!-- Card de Autores -->
        <div class="card text-center">
            <div class="card-body">
                <h4 class="card-title text-success">✍️ Autores</h4>
                <p class="card-text text-muted">Administra el catálogo de autores registrados.</p>
                <a href="<%= request.getContextPath() %>/authors?action=list" class="btn btn-success btn-custom">Gestionar autores</a>
            </div>
        </div>
    </div>

    <footer>
        <hr>
        <p class="small">Desarrollado con Servlets + JSP + JDBC</p>
    </footer>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
