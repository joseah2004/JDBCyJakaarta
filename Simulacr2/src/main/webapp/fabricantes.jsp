<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.daw.jakarta.jdbcapp.model.Fabricante" %>
<%@ page import="es.daw.jakarta.jdbcapp.model.Producto" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fabricantes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">

    <h2 class="mb-4 text-primary">Listado de fabricantes</h2>
    <a href="<%= request.getContextPath() %>/fabricantes/crear" class="btn btn-success mb-3">Añadir fabricante</a>

    <%
        List<Fabricante> fabricantes = (List<Fabricante>) request.getAttribute("fabricantes");
        List<Producto> productos = (List<Producto>) request.getAttribute("productos");

        String mostrar = "si"; // valor por defecto
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("mostrar".equals(c.getName())) {
                    mostrar = c.getValue();
                    break;
                }
            }
        }

        boolean mostrarProductos = !"no".equalsIgnoreCase(mostrar);
    %>

    <form action="<%= request.getContextPath() %>/fabricantes/ver" method="post" class="text-center mb-4">
        <label for="mostrar" class="form-label fw-bold">Mostrar productos por fabricante:</label>
        <select name="mostrar" id="mostrar" class="form-select d-inline-block w-auto">
            <option value="si" <%= "si".equals(mostrar) ? "selected" : "" %>>Sí</option>
            <option value="no" <%= "no".equals(mostrar) ? "selected" : "" %>>No</option>
        </select>
        <button type="submit" class="btn btn-outline-primary ms-2">Guardar</button>
    </form>

    <% if (fabricantes != null && !fabricantes.isEmpty()) { %>

    <table class="table table-bordered">
        <thead class="table-dark">
        <tr>
            <th>Código</th>
            <th>Nombre</th>
            <% if (mostrarProductos) { %>
            <th>Productos</th>
            <% } %>
            <th>Editar</th>
            <th>Borrar</th>
        </tr>
        </thead>
        <tbody>
        <% for (Fabricante f : fabricantes) { %>
        <tr>
            <td><%= f.getCodigo() %></td>
            <td><%= f.getNombre() %></td>
            <% if (mostrarProductos) { %>
            <td>
                <ul style="margin-bottom: 0;">
                    <%
                        boolean tiene = false;
                        for (Producto p : productos) {
                            if (p.getCodigo_fabricante().equals(f.getCodigo())) {
                                tiene = true;
                    %>
                    <li><%= p.getNombre() %> - <%= p.getPrecio() %> €</li>
                    <%     }
                    }
                        if (!tiene) {
                    %>
                    <li class="text-muted">Sin productos</li>
                    <% } %>
                </ul>
            </td>
            <% } %>
            <td>
                <form action="<%= request.getContextPath() %>/fabricantes/editar" method="get">
                    <input type="hidden" name="codigo" value="<%= f.getCodigo() %>">
                    <button type="submit" class="btn btn-warning btn-sm">Editar</button>
                </form>
            </td>
            <td>
                <%
                    boolean tieneProductos = false;
                    for (Producto p : productos) {
                        if (p.getCodigo_fabricante().equals(f.getCodigo())) {
                            tieneProductos = true;
                            break;
                        }
                    }

                    if (!tieneProductos) {
                %>
                <form action="<%= request.getContextPath() %>/productos/borrar" method="post" onsubmit="return confirm('¿Seguro que deseas borrar este fabricante?');">
                    <input type="hidden" name="codigo" value="<%= f.getCodigo() %>">
                    <button type="submit" class="btn btn-danger btn-sm">Borrar</button>
                </form>
                <% } else { %>
                <p>bea</p>
                <% } %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <% } else { %>
    <div class="alert alert-warning">No hay fabricantes registrados.</div>
    <% } %>

    <a href="<%= request.getContextPath() %>/" class="btn btn-secondary mt-4">Volver al inicio</a>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>