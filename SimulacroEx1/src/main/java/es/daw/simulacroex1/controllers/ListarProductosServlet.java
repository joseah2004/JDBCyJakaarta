package es.daw.simulacroex1.controllers;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import es.daw.simulacroex1.model.Fabricante;
import es.daw.simulacroex1.model.Producto;
import es.daw.simulacroex1.repository.DBConnection;
import es.daw.simulacroex1.repository.FabricanteDAO;
import es.daw.simulacroex1.repository.GenericDAO;
import es.daw.simulacroex1.repository.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ListarProductosServlet", value = "/productos/ver")
public class ListarProductosServlet extends HttpServlet {

    private static final Logger log =  Logger.getLogger(ListarProductosServlet.class.getName());

    // listar productos
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Producto> productos = new ArrayList<>(); // no es nula, está vacía
        List<Fabricante> fabricantes = new ArrayList<>(); // no es nula, está vacía
        try {
            GenericDAO<Producto,Integer> daoP = new ProductoDAO();
            GenericDAO<Fabricante,Integer> daoF = new FabricanteDAO();

            productos = daoP.findAll();
            fabricantes = daoF.findAll();

            //productos.forEach(System.out::println);
            productos.forEach(p -> log.info(p.toString()));
            fabricantes.forEach(f -> log.info(f.toString()));

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            log.severe(e.getMessage());
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

        // SALIDA HTML
        request.setAttribute("productos", productos);
        request.setAttribute("fabricantes", fabricantes);
        //getServletContext().getRequestDispatcher("/informe.jsp").forward(request, response);
        getServletContext().getRequestDispatcher("/productos.jsp").forward(request, response);

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }

    @Override
    public void destroy() {
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e); // el server devolvería 500
            // OBSERVACIÓN....
            // QUIERO REUTILIZAR ERROR.JSP PARA ENVIAR e.getMesage()
        }
    }
}