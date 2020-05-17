package fr.dauphine.microservice.api;

import fr.dauphine.microservice.model.Reader;
import fr.dauphine.microservice.service.ReaderServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/readers")
public class ReaderApi {

    @Autowired
    private ReaderServiceProvider readerServiceProvider;

    @PostMapping
    public ResponseEntity<EntityModel<Reader>> create(@RequestBody Reader reader) {
        Reader created = readerServiceProvider.create(reader);
        Link link = getLink(created.getId());
        return new ResponseEntity<>(new EntityModel<>(created, link), CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<Reader>> getById(@PathVariable("id") Integer id) {
        Optional<Reader> readerOptional = readerServiceProvider.getById(id);
        Reader reader = readerOptional.orElse(new Reader());
        Link link = getLink(id);
        return new ResponseEntity<>(new EntityModel<>(reader, link), CREATED);
    }

    private Link getLink(Integer id) {
        return linkTo(methodOn(ReaderApi.class)
                .getById(id)).withSelfRel();
    }
}
