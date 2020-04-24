package fr.dauphine.microservice.service;

import fr.dauphine.microservice.model.Loan;
import fr.dauphine.microservice.model.Reader;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface LoanServiceProvider {
    Loan create(final Loan loan);
    Loan returnBook(final Loan loan);
    List<Loan> findByBorrowingDate(final Date date);
    List<Loan> getAllBorrowedBooks();
    List<Loan> getHistoryByReader(final Reader reader);
}
