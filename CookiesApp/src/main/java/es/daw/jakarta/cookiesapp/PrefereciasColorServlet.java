package es.daw.jakarta.cookiesapp;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "preferenciasServlet", value = "/preferencias")
public class PrefereciasColorServlet extends HttpServlet {
    private static final String COOKIE_NAME = "colorFondo";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        if ("borrar".equalsIgnoreCase(accion)) {
            Cookie cookie = new Cookie(COOKIE_NAME, "");
            cookie.setMaxAge(0);   // eliminar
            cookie.setPath("/");   // mismo path que al crear
            response.addCookie(cookie);
        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String color = request.getParameter("color"); // ejemplo: "#673AB7"

        if (color != null && !color.isBlank()) {
            // Guardar sin el "#"
            if (color.startsWith("#")) {
                color = color.substring(1);
            }

            Cookie cookie = new Cookie(COOKIE_NAME, color);
            cookie.setMaxAge(7 * 24 * 60 * 60); // 7 días
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
