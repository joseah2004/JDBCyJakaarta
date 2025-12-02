package es.daw.jakarta.formpost.contollers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/registro2")
public class ParamsFormServlet2 extends HttpServlet {

    /*
    SLF4J:
    Simple Logging Facade for Java es una librería que actúa como interface o abstracción para el registro de logs en aplicaciones Java.
    En lugar de atarte a una implementación específica de logs (como Log4j, java.util.logging, o Logback), SLF4J te permite escribir código que es independiente de la implementación de registro y luego elegir qué framework usar en tiempo de ejecución.
    Logback: Es el backend de logging que implementa los logs en Spring Framework. Spring Boot ya lo configura por defecto.
     */
    // Logger de util
    // https://keepcoding.io/blog/que-es-java-util-logging-logger-como-funciona/
    private static final Logger logger = Logger.getLogger(ParamsFormServlet2.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> PARAMS FORM SERVLET VÍA GET</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        System.out.println("* username: " + username);
        logger.info("username: " + username);
        String password = req.getParameter("password");
        logger.info("password: " + password);
        String email = req.getParameter("email");
        logger.info("email: " + email);
        String pais = req.getParameter("pais");
        logger.info("pais: " + pais);

        String[] lenguajes = req.getParameterValues("lenguajes");
        //logger.info("lenguajes: " + lenguajes); // sale churro
        logger.info("lenguajes: " + Arrays.toString(lenguajes));

        String[] roles = req.getParameterValues("roles");
        // PENDIENTE GENERAR MÁS TRAZAS CON LOGGER... SI QUIERES...
        String idioma = req.getParameter("idioma");

        String habilitar = req.getParameter("habilitar");

        String secreto = req.getParameter("secreto");
        logger.config("secreto: " + secreto);

        Map<String,String> errores = new HashMap<>(); // PENDIENTE REVISAR TIPOS DE MAP!!!

        // VALIDACIONES
       /*
        el username es requerido.
        el password no puede ser vacío.
        el email es requerido y debe tener un formato de correo.
        debe seleccionar un idioma.
         */
        // PENDIENTE!!! diferencia entre isBlank y isEmpty (controla nulos?)

        if (username.isBlank()) {
            errores.put("username","El username es obligatorio");
        }
        if (password.isBlank()) {
            errores.put("password","El password es obligatorio");
        }

        if (email.isBlank() || !email.contains("@")) {
            errores.put("email","El email es obligatorio y debe tener un formato correcto");
        }

        if (idioma == null) errores.put("idioma","El idioma es obligatorio");

        // 3. GENERAR HTML DE RESPUESTA
        req.setAttribute("errores", errores);
        // Redirigir a JSP
        getServletContext().getRequestDispatcher("/index2.jsp").forward(req, resp);




    }
}