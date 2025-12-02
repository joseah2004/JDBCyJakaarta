-- Eliminar tablas si ya existen (solo en desarrollo)
DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS Author;

-- Crear la tabla de autores
CREATE TABLE Author (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);

-- Crear la tabla de libros con campo de fecha de publicación
CREATE TABLE Book (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      authorId BIGINT NOT NULL,
                      publicationDate DATE NOT NULL,
                      FOREIGN KEY (authorId) REFERENCES Author(id) ON DELETE CASCADE
);

-- Insertar autores de ejemplo
INSERT INTO Author (name) VALUES ('Gabriel García Márquez');
INSERT INTO Author (name) VALUES ('Julio Cortázar');
INSERT INTO Author (name) VALUES ('Isabel Allende');
INSERT INTO Author (name) VALUES ('Mario Vargas Llosa');
INSERT INTO Author (name) VALUES ('Laura Esquivel');

-- Insertar libros de ejemplo
INSERT INTO Book (title, authorId, publicationDate) VALUES ('Cien años de soledad', 1, '1967-06-05');
INSERT INTO Book (title, authorId, publicationDate) VALUES ('El amor en los tiempos del cólera', 1, '1985-04-05');
INSERT INTO Book (title, authorId, publicationDate) VALUES ('Rayuela', 2, '1963-06-28');
INSERT INTO Book (title, authorId, publicationDate) VALUES ('La vuelta al día en ochenta mundos', 2, '1970-10-01');
INSERT INTO Book (title, authorId, publicationDate) VALUES ('La casa de los espíritus', 3, '1982-01-01');
INSERT INTO Book (title, authorId, publicationDate) VALUES ('Paula', 3, '1994-05-01');
INSERT INTO Book (title, authorId, publicationDate) VALUES ('La ciudad y los perros', 4, '1963-10-01');
INSERT INTO Book (title, authorId, publicationDate) VALUES ('Como agua para chocolate', 5, '1989-09-15');