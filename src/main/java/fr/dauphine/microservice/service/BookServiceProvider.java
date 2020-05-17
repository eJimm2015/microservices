package fr.dauphine.microservice.service;

import fr.dauphine.microservice.model.Book;

import java.util.List;
import java.util.Optional;


public interface BookServiceProvider {
    Book create(final Book book);
    List<Book> getAll();
    Optional<Book> findByIsbn(final String isbn);
    List<Book> findByAuthor(final String authorName);
    List<Book> findByEditor(final String editor);
    List<Book> findByEdition(final int year);
    List<Book> findByTitle(final String title);
    Book update(final Book book);
    void delete(final Book book);
}
