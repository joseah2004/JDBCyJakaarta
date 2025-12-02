<%@ page import="java.util.List" %>
<%@ page import="es.daw.jakarta.cabecerasapp.model.Producto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado de productos</title>
    <style>
        table{
            border-collapse: collapse;
        }
        th, td{
            border: 5px solid fuchsia;
        }
    </style>
</head>
<body>
<h1>Listado de productos</h1>
<table>
    <thead>
    <th>ID</th>
    <th>NOMBRE</th>
    <th>TIPO</th>
    <th>PRECIO</th>
    </thead>
    <tbody>

    <%
        List<Producto> productos =  (List<Producto>)request.getAttribute("productos");

        for (Producto p: productos) {%>
    <tr>
        <td><%=p.getId()%></td>
        <td><%=p.getNombre()%></td>
        <td><%=p.getTipo()%></td>
        <td><%=p.getPrecio()%></td>
    </tr>
    <%}
    %>

%>

    </tbody>
</table>
</body>
</html>