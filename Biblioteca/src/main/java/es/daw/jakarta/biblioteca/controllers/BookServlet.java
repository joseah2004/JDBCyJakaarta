package es.daw.jakarta.biblioteca.controllers;

import es.daw.jakarta.biblioteca.model.Book;
import es.daw.jakarta.biblioteca.model.Author;
import es.daw.jakarta.biblioteca.repository.BookDAO;
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
import java.time.LocalDate;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private final BookDAO bookDAO = new BookDAO();
    private final AuthorDAO authorDAO = new AuthorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list":
                    List<Book> books = bookDAO.findAll();
                    request.setAttribute("books", books);
                    request.getRequestDispatcher("/books.jsp").forward(request, response);
                    break;

                case "new":
                    List<Author> authors = authorDAO.findAll();
                    request.setAttribute("authors", authors);
                    request.setAttribute("book", new Book());
                    request.getRequestDispatcher("/book-form.jsp").forward(request, response);
                    break;

                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Book book = bookDAO.findById(id).orElseThrow();
                    List<Author> allAuthors = authorDAO.findAll();
                    request.setAttribute("book", book);
                    request.setAttribute("authors", allAuthors);
                    request.getRequestDispatcher("/book-form.jsp").forward(request, response);
                    break;

                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    bookDAO.delete(deleteId);
                    response.sendRedirect("books?action=list");
                    break;

                default:
                    response.sendRedirect("books?action=list");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/plain");
            response.getWriter().println("Error al cargar libros: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            String title = request.getParameter("title");
            int authorId = Integer.parseInt(request.getParameter("authorId"));
            LocalDate publicationDate = LocalDate.parse(request.getParameter("publicationDate"));

            Book book = new Book(title, authorId, publicationDate);

            if (idParam == null || idParam.isEmpty() || Integer.parseInt(idParam) == 0) {
                bookDAO.save(book);
            } else {
                int id = Integer.parseInt(idParam);
                book.setId(id);
                bookDAO.update(book);

                String encodedTitle = URLEncoder.encode(book.getTitle(), StandardCharsets.UTF_8);
                Cookie lastBookCookie = new Cookie("lastBook", encodedTitle);
                lastBookCookie.setMaxAge(7 * 24 * 60 * 60); // 7 días
                lastBookCookie.setPath("/");
                response.addCookie(lastBookCookie);

            }

            response.sendRedirect("books?action=list");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/plain");
            response.getWriter().println("Error al guardar el libro: " + e.getMessage());
        }
    }
}