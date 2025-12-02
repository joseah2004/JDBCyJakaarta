package es.daw.jakarta.biblioteca.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica para operaciones CRUD sobre entidades.
 *
 * @param <T>  Tipo de entidad (Book, Author, etc.)
 * @param <ID> Tipo de clave primaria (Integer, Long, etc.)
 */
public interface GenericDAO<T, ID> {

    /**
     * Inserta una nueva entidad en la base de datos.
     *
     * @param entity Entidad a guardar
     * @throws SQLException Si ocurre un error de acceso a datos
     */
    void save(T entity) throws SQLException;

    /**
     * Busca una entidad por su ID.
     *
     * @param id Clave primaria
     * @return Optional con la entidad encontrada o vacío si no existe
     * @throws SQLException Si ocurre un error de acceso a datos
     */
    Optional<T> findById(ID id) throws SQLException;

    /**
     * Devuelve todas las entidades de la tabla.
     *
     * @return Lista de entidades
     * @throws SQLException Si ocurre un error de acceso a datos
     */
    List<T> findAll() throws SQLException;

    /**
     * Actualiza una entidad existente.
     *
     * @param entity Entidad con los nuevos datos
     * @throws SQLException Si ocurre un error de acceso a datos
     */
    void update(T entity) throws SQLException;

    /**
     * Elimina una entidad por su ID.
     *
     * @param id Clave primaria
     * @throws SQLException Si ocurre un error de acceso a datos
     */
    void delete(ID id) throws SQLException;

    // Puedes añadir métodos personalizados como este si lo necesitas:
    // List<T> findByNombre(String nombre) throws SQLException;
}