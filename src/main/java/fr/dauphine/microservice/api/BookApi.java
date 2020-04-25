package fr.dauphine.microservice.api;

import fr.dauphine.microservice.model.Book;
import fr.dauphine.microservice.service.BookServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookApi {
/*
    void update(final Book book);
    void delete(final Book book);
*/
    @Autowired
    private BookServiceProvider bookServiceProvider;

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book created = bookServiceProvider.create(book);
        return ResponseEntity.created(URI.create(String.format("/api/books/%s", created.getIsbn()))).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Book>> findBy(@RequestParam(value = "author", required = false) String author,
                                             @RequestParam(value = "editor", required = false) String editor,
                                             @RequestParam(value = "title", required = false) String title,
                                             @RequestParam(value = "edition", required = false) Integer edition) {
        List<Book> books;
        if(author != null) books = bookServiceProvider.findByAuthor(author);
        else if(editor != null) books = bookServiceProvider.findByEditor(editor);
        else if(title != null) books = bookServiceProvider.findByTitle(title);
        else if(edition != null) books = bookServiceProvider.findByEdition(edition);
        else books = bookServiceProvider.getAll();
        return ResponseEntity.ok(books);
    }
    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody Book book) {
        bookServiceProvider.update(book);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{isbn}")
    public ResponseEntity<Void> update(@PathVariable String isbn) {
        bookServiceProvider.delete(new Book().setIsbn(isbn));
        return ResponseEntity.ok().build();
    }

}
