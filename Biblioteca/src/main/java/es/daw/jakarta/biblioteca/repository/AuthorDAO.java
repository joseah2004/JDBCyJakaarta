package es.daw.jakarta.biblioteca.repository;

import es.daw.jakarta.biblioteca.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorDAO implements GenericDAO<Author, Integer> {

    @Override
    public void save(Author author) throws SQLException {
        String sql = "INSERT INTO Author (name) VALUES (?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, author.getName());
        stmt.executeUpdate();
    }

    @Override
    public Optional<Author> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Author WHERE id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Author a = new Author(rs.getInt("id"), rs.getString("name"));
            return Optional.of(a);
        }

        return Optional.empty();
    }

    @Override
    public List<Author> findAll() throws SQLException {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM Author ORDER BY id ASC";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Author a = new Author(rs.getInt("id"), rs.getString("name"));
            authors.add(a);
        }

        return authors;
    }

    @Override
    public void update(Author author) throws SQLException {
        String sql = "UPDATE Author SET name = ? WHERE id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, author.getName());
        stmt.setInt(2, author.getId());
        stmt.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Author WHERE id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    // Método adicional opcional
    public boolean existsByName(String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Author WHERE name = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0;
        }

        return false;
    }
}