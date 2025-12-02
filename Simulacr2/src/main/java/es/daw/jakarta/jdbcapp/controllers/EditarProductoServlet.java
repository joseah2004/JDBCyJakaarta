package es.daw.jakarta.jdbcapp.controllers;

import es.daw.jakarta.jdbcapp.model.Fabricante;
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
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/productos/editar")
public class EditarProductoServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(EditarProductoServlet.class.getName());

    private GenericDAO daoP;
    private GenericDAO daoF;

    @Override
    public void init() throws ServletException {
        try {
            daoP = new ProductoDAO();
            daoF = new FabricanteDAO();
        } catch (Exception e) {
            throw new ServletException("Error inicializando DAOs", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1) Validar parámetro
        final String codigoParam = request.getParameter("codigo");
        if (codigoParam == null || codigoParam.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro 'codigo'.");
            return;
        }

        final int codigo;
        try {
            codigo = Integer.parseInt(codigoParam);
        } catch (NumberFormatException nfe) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'codigo' no es válido.");
            return;
        }

        // 2) Consultar BD
        try {
            final Optional<Producto> productoOpt = daoP.findById(codigo);
            if (productoOpt.isEmpty()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado (código " + codigo + ").");
                return;
            }

            final Producto producto = productoOpt.get();
            final List<Fabricante> fabricantes = daoF.findAll();

            // 3) Preparar datos y forward a la vista
            request.setAttribute("producto", producto);
            request.setAttribute("fabricantes", fabricantes);
            request.getRequestDispatcher("/formularioProducto.jsp").forward(request, response);

        } catch (SQLException e){
            log.severe(e.getMessage());
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

    }
}

