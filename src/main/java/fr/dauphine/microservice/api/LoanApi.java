package fr.dauphine.microservice.api;

import fr.dauphine.microservice.model.Loan;
import fr.dauphine.microservice.model.Reader;
import fr.dauphine.microservice.service.LoanServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanApi {

    @Autowired
    private LoanServiceProvider loanServiceProvider;

    @PostMapping
    public ResponseEntity<Loan> create(@RequestBody Loan loan) {
        Loan created = loanServiceProvider.create(loan);
        return ResponseEntity.created(URI.create(String.format("/api/books/%s", created.getId()))).body(created);
    }

    @PutMapping
    public ResponseEntity<Loan> returnBook(@RequestBody Loan loan) {
        Loan returned = loanServiceProvider.returnBook(loan);
        return ResponseEntity.ok(returned);
    }
    @GetMapping
    public ResponseEntity<List<Loan>> findBy(@RequestParam(value = "date", required = false) String date,
                                             @RequestParam(value = "reader", required = false) String id) {
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
        return ResponseEntity.ok(loans);
    }

}
