package fr.dauphine.microservice.api;

import fr.dauphine.microservice.model.Loan;
import fr.dauphine.microservice.model.Reader;
import fr.dauphine.microservice.service.LoanServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/loans")
public class LoanApi {

    @Autowired
    private LoanServiceProvider loanServiceProvider;

    @PostMapping
    public ResponseEntity<EntityModel<Loan>> create(@RequestBody Loan loan) {
        Loan created = loanServiceProvider.create(loan);
        Link link = getLink(created.getId());
        return new ResponseEntity<>(new EntityModel<>(created, link), CREATED);
    }

    @PutMapping
    public ResponseEntity<EntityModel<Loan>> returnBook(@RequestBody Loan loan) {
        Loan returned = loanServiceProvider.returnBook(loan);
        return ResponseEntity.ok(new EntityModel<>(returned, getLink(returned.getId())));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<Loan>> getById(@PathVariable("id") Integer id) {
        Optional<Loan> loanOptional = loanServiceProvider.getById(id);
        Loan loan = loanOptional.orElse(new Loan());
        Link link = getLink(id);
        return new ResponseEntity<>(new EntityModel<>(loan, link), CREATED);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Loan>> findBy(@RequestParam(value = "date", required = false) String date,
                                                        @RequestParam(value = "reader", required = false) Integer id) {
        List<Loan> loans = Collections.emptyList();
        if(date != null) {
            try {
                loans = loanServiceProvider
                        .findByBorrowingDate(new SimpleDateFormat("dd/mm/yyyy").parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if(id != null) loans = loanServiceProvider.getHistoryByReader(new Reader().setId(id));
        else loans = loanServiceProvider.getAllBorrowedBooks();

        List<Loan> collect = loans.stream()
                .map(e -> e.add(getLink(e.getId())))
                .collect(Collectors.toList());
        Link link = linkTo(methodOn(LoanApi.class)
                .findBy(date, id)).withSelfRel();
        return ResponseEntity.ok(new CollectionModel<>(collect, link));
    }

    private Link getLink(Integer id) {
        return linkTo(methodOn(LoanApi.class)
                .getById(id)).withSelfRel();
    }

}
