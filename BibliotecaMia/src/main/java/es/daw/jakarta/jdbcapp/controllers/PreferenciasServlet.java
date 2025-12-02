package es.daw.jakarta.jdbcapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/preferencias")
public class PreferenciasServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Alguien ha implementado un Filter????
        String mostrar = req.getParameter("mostrar") == null?"si":req.getParameter("mostrar"); // "si" o "no"

        Cookie cookie = new Cookie("mostrarProductos", mostrar);
        //cookie.setMaxAge(7 * 24 * 60 * 60); // 1 semana
        resp.addCookie(cookie);

        // Redirigir al listado de fabricantes
        resp.sendRedirect(req.getContextPath() + "/fabricantes/ver");
    }
}

