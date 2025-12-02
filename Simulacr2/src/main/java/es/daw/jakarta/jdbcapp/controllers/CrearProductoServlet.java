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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/productos/crear")
public class CrearProductoServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(EditarProductoServlet.class.getName());

    private GenericDAO daoF;
    private GenericDAO daoP;

    @Override
    public void init() throws ServletException {
        try {
            daoF = new FabricanteDAO();
            daoP = new ProductoDAO();
        } catch (Exception e) {
            throw new ServletException("Error inicializando DAOs", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Fabricante> fabricantes = daoF.findAll();
            request.setAttribute("fabricantes", fabricantes);
            request.getRequestDispatcher("/formularioProducto.jsp").forward(request, response);
        } catch (SQLException e){
            log.severe(e.getMessage());
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
        int codigoFabricante = Integer.parseInt(request.getParameter("codigo_fabricante"));

        Producto nuevo = new Producto(codigo, nombre, precio, codigoFabricante);
        try {
            daoP.save(nuevo);
        } catch (SQLException e){
            log.severe(e.getMessage());
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

        response.sendRedirect(request.getContextPath() + "/productos/ver");
    }
}
