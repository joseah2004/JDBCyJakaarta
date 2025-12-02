package es.daw.jakarta.jdbcapp.controllers;

import es.daw.jakarta.jdbcapp.repository.GenericDAO;
import es.daw.jakarta.jdbcapp.repository.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/productos/borrar")
public class BorrarProductoServlet extends HttpServlet {

    private GenericDAO daoP;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            daoP = new ProductoDAO();
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            daoP.delete(codigo);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Redirigir de nuevo a la lista actualizada
        response.sendRedirect(request.getContextPath() + "/productos/ver");
    }
}
