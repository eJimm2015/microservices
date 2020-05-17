package fr.dauphine.microservice.service;

import fr.dauphine.microservice.model.Loan;
import fr.dauphine.microservice.model.Reader;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface LoanServiceProvider {
    Loan create(final Loan loan);
    Loan returnBook(final Loan loan);
    List<Loan> findByBorrowingDate(final Date date);
    List<Loan> getAllBorrowedBooks();
    List<Loan> getHistoryByReader(final Reader reader);
    Optional<Loan> getById(final Integer id);
}
