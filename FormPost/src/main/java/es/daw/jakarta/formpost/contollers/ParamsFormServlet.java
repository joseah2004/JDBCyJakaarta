package es.daw.jakarta.formpost.contollers;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import jakarta.data.repository.Param;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/registro")
public class ParamsFormServlet extends HttpServlet {

    /*
    SLF4J:
    Simple Logging Facade for Java es una librería que actúa como interface o abstracción para el registro de logs en aplicaciones Java.
    En lugar de atarte a una implementación específica de logs (como Log4j, java.util.logging, o Logback), SLF4J te permite escribir código que es independiente de la implementación de registro y luego elegir qué framework usar en tiempo de ejecución.
    Logback: Es el backend de logging que implementa los logs en Spring Framework. Spring Boot ya lo configura por defecto.
     */
    // Logger de util
    // https://keepcoding.io/blog/que-es-java-util-logging-logger-como-funciona/
    private static final Logger logger = Logger.getLogger(ParamsFormServlet.class.getName());

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



        /*
         * Generalmente una mejor práctica. Principios de diseño orientado a interfaces
         * Tu código dependerá de la interfaz (List) en lugar de la implementación concreta (ArrayList).
         * Esto facilita cambiar la implementación a otra clase que implemente la interfaz List
         * (como LinkedList, CopyOnWriteArrayList, etc.) sin necesidad de modificar mucho el código.
         * Solo puedes utilizar los métodos definidos en la interfaz List.
         * Si necesitas acceder a métodos específicos de ArrayList, tendrías que hacer un casting.
         */
        /*
         * ArrayList si necesitas acceso rápido por índice o si tienes pocas modificaciones en la lista.
         * LinkedList si haces muchas inserciones o eliminaciones en el medio de la lista o en los extremos, o si tu lista cambia de tamaño con mucha frecuencia.
         */
        ArrayList<String> errores = new ArrayList<String>();
        //List<String> errores2 = new ArrayList<String>();

        // VALIDACIONES
       /*
        el username es requerido.
        el password no puede ser vacío.
        el email es requerido y debe tener un formato de correo.
        debe seleccionar un idioma.
         */
        // PENDIENTE!!! diferencia entre isBlank y isEmpty (controla nulos?)

        if (username.isBlank()) {
            errores.add("El username es obligatorio");
        }
        if (password.isBlank()) {
            errores.add("El password es obligatorio");
        }

        if (email.isBlank() || !email.contains("@")) {
            errores.add("El email es obligatorio y debe tener un formato correcto");
        }

        if (idioma == null) errores.add("El idioma es obligatorio");

        // 3. GENERAR HTML DE RESPUESTA
        try (PrintWriter out = resp.getWriter()) {
//            out.println("<html><body>");
//            out.println("<h1> PARAMS FORM SERVLET VÍA GET</h1>");
//            out.println("</body></html>");
            out.println("""
                    <html>
                    <head>
                        <title>ParamsFormServlet</title>
                    </head>
                    <body>
                        <h1>Informe de datos recibidos del formulario</h1>
                        <ul>
                    """);

            if (!errores.isEmpty()) {
                    // SI HAY ERRORES PINTO LA LISTA DE ERRORES
                                    // un li por cada mensaje de error
                    //           for (String error: errores){
                    //               //out.println("<li>"+error+"</li>");
                    //               out.printf("<li>%s</li>\n", error);
                    //           }


                errores.forEach(err -> out.printf("<li>%s</li>\n", err));
            }else{
                String lenguajesHtml = "";
                for (String lenguaje : lenguajes) {
                    lenguajesHtml += "<li>" + lenguaje + "</li>";
                }

                String rolesHtml = "";
                for (String role : roles) {
                    rolesHtml = rolesHtml.concat("<li>" + role + "</li>");
                }

                String htmlBody = """
                        <li>Username: %s</li>
                        <li>Password: %s</li>
                        <li>Email: %s</li>
                        <li>Lenguajes: <ul>%s</ul></li>
                        <li>Roles:
                            <ul>%s</ul>
                        </li>
                        <li>Idioma: %s</li>
                        <li>Habilitar: %s</li>
                        <li>Secreto: %s</li>
                        """.formatted(username,password,email,lenguajesHtml,rolesHtml,idioma,habilitar,secreto);

                out.println(htmlBody);

            }

            out.println("""
                            </ul>
                            <p><a href="index.jsp">volver</a></p>
                        </body>
                    </html>
                    """);

        }


    }
}