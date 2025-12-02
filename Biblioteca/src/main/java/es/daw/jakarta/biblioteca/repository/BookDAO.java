package es.daw.jakarta.biblioteca.repository;

import es.daw.jakarta.biblioteca.model.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDAO implements GenericDAO<Book, Integer> {

    @Override
    public void save(Book book) throws SQLException {
        String sql = "INSERT INTO Book (title, authorId, publicationDate) VALUES (?, ?, ?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, book.getTitle());
        stmt.setInt(2, book.getAuthorId());
        stmt.setDate(3, Date.valueOf(book.getPublicationDate()));
        stmt.executeUpdate();
    }

    @Override
    public Optional<Book> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Book WHERE id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Book book = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("authorId"),
                    rs.getDate("publicationDate").toLocalDate()
            );
            return Optional.of(book);
        }

        return Optional.empty();
    }

    @Override
    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Book ORDER BY id ASC";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Book book = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("authorId"),
                    rs.getDate("publicationDate").toLocalDate()
            );
            books.add(book);
        }

        return books;
    }

    @Override
    public void update(Book book) throws SQLException {
        String sql = "UPDATE Book SET title = ?, authorId = ?, publicationDate = ? WHERE id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, book.getTitle());
        stmt.setInt(2, book.getAuthorId());
        stmt.setDate(3, Date.valueOf(book.getPublicationDate()));
        stmt.setInt(4, book.getId());
        stmt.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Book WHERE id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}