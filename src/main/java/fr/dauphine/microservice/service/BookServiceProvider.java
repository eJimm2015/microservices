package fr.dauphine.microservice.service;

import fr.dauphine.microservice.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookServiceProvider {
    Book create(final Book book);
    List<Book> getAll();
    Optional<Book> findByIsbn(final String isbn);
    List<Book> findByAuthor(final String authorName);
    List<Book> findByEditor(final String editor);
    List<Book> findByEdition(final int year);
    List<Book> findByTitle(final String title);
    void update(final Book book);
    void delete(final Book book);
}
