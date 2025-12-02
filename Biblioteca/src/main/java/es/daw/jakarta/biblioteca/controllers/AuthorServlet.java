package es.daw.jakarta.biblioteca.controllers;

import es.daw.jakarta.biblioteca.model.Author;
import es.daw.jakarta.biblioteca.repository.AuthorDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {

    private AuthorDAO authorDAO = new AuthorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String action = request.getParameter("action");
            if (action == null) {
                action = "list";
            }

            switch (action) {
                case "list":

                    List<Author> authors = authorDAO.findAll();
                    request.setAttribute("authors", authors);
                    request.getRequestDispatcher("/authors.jsp").forward(request, response);
                    break;

                case "new":
                    request.setAttribute("author", new Author());
                    request.getRequestDispatcher("/author-form.jsp").forward(request, response);
                    break;

                case "edit":
                    int editId = Integer.parseInt(request.getParameter("id"));
                    Optional<Author> editAuthor = authorDAO.findById(editId);
                    if (editAuthor.isPresent()) {
                        request.setAttribute("author", editAuthor.get());
                        request.getRequestDispatcher("/author-form.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("authors?action=list");
                    }
                    break;

                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    authorDAO.delete(deleteId);
                    response.sendRedirect("authors?action=list");
                    break;

                default:
                    response.sendRedirect("authors?action=list");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ha ocurrido un error: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idParam = request.getParameter("id");
            String name = request.getParameter("name");

            if (name == null || name.trim().isEmpty()) {
                Author author = new Author();
                if (idParam != null && !idParam.isEmpty()) {
                    author.setId(Integer.parseInt(idParam));
                }
                request.setAttribute("author", author);
                request.setAttribute("error", "El nombre no puede estar vacío");
                request.getRequestDispatcher("/author-form.jsp").forward(request, response);
                return;
            }

            if (idParam == null || idParam.isEmpty()) {
                Author newAuthor = new Author(name);
                authorDAO.update(newAuthor);
                authorDAO.save(newAuthor);
            } else {
                int id = Integer.parseInt(idParam);
                Author updatedAuthor = new Author(id, name);
                authorDAO.update(updatedAuthor);

                String encodedName = URLEncoder.encode(updatedAuthor.getName(), StandardCharsets.UTF_8);
                Cookie lastAuthorCookie = new Cookie("lastAuthor", encodedName);
                lastAuthorCookie.setMaxAge(7 * 24 * 60 * 60); // 7 días
                lastAuthorCookie.setPath("/");
                response.addCookie(lastAuthorCookie);
            }

            response.sendRedirect("authors?action=list");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ha ocurrido un error al guardar el autor: " + e.getMessage());
            request.getRequestDispatcher("/author-form.jsp").forward(request, response);
        }
    }
}