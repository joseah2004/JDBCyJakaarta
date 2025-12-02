package es.daw.jakarta.cabecerasapp.controllers;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

import es.daw.jakarta.cabecerasapp.model.Producto;
import es.daw.jakarta.cabecerasapp.service.ProductoService;
import es.daw.jakarta.cabecerasapp.service.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/productos.xls","/productos.html"})
public class ProductoXlsServlet extends HttpServlet {
    private static  final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ProductoXlsServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Obtener un objeto del servicio
        ProductoService service = new ProductoServiceImpl();

        // Obtener la lista de productos
        List<Producto> productos = service.findAll();

        log.info("* Request URI: " + request.getRequestURI());
        log.info("* Servlet path: " + request.getServletPath());



        // pendiente controlar si exporto a XLS...
        if (request.getServletPath().endsWith(".xls")) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=productos.xls");

        }





        request.setAttribute("productos", productos);
        getServletContext().getRequestDispatcher("/productos.jsp").forward(request,response);


    }


}