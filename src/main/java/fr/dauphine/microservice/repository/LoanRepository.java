package fr.dauphine.microservice.repository;

import fr.dauphine.microservice.model.Loan;
import fr.dauphine.microservice.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByBorrowDate(final Date borrowDate);
    List<Loan> findByReader(final Reader reader);
    List<Loan> findByReturnDateNull();
}
