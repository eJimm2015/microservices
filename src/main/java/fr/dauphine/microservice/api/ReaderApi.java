package fr.dauphine.microservice.api;

import fr.dauphine.microservice.model.Reader;
import fr.dauphine.microservice.service.ReaderServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/readers")
public class ReaderApi {

    @Autowired
    private ReaderServiceProvider readerServiceProvider;

    @PostMapping
    public ResponseEntity<Reader> create(@RequestBody Reader reader) {
        Reader created = readerServiceProvider.create(reader);
        return ResponseEntity
                .created(URI.create(String.format("/api/readers/%s", created.getId())))
                .body(created);
    }
}
