package es.daw.jakarta.jdbcapp.controllers;

import es.daw.jakarta.jdbcapp.model.Producto;
import es.daw.jakarta.jdbcapp.repository.FabricanteDAO;
import es.daw.jakarta.jdbcapp.repository.GenericDAO;
import es.daw.jakarta.jdbcapp.repository.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet("/productos/actualizar")
public class ActualizarProductoServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(EditarProductoServlet.class.getName());

    // EXPLICAR IMPLICACIONES!!!!!
    //private ProductoDAO daoP;
    private GenericDAO<Producto,Integer> daoP;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            daoP = new ProductoDAO();
        } catch (Exception e) {
            throw new ServletException("Error inicializando DAOs", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
        int codigoFabricante = Integer.parseInt(request.getParameter("codigo_fabricante"));

        Producto p = new Producto(codigo, nombre, precio, codigoFabricante);
        try {
            daoP.update(p);
        } catch (SQLException e) {
            log.severe(e.getMessage());
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

        response.sendRedirect(request.getContextPath() + "/productos/ver");
    }
}

