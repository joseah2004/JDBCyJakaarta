package es.daw.jakarta.biblioteca.model;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private int authorId;
    private LocalDate publicationDate;

    public Book() {}

    public Book(int id, String title, int authorId, LocalDate publicationDate) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.publicationDate = publicationDate;
    }

    public Book(String title, int authorId, LocalDate publicationDate) {
        this.title = title;
        this.authorId = authorId;
        this.publicationDate = publicationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', authorId=" + authorId +
                ", publicationDate=" + publicationDate + "}";
    }
}