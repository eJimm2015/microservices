package fr.dauphine.microservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.Objects;

@Entity
public class Loan {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public Loan setId(String id) {
        this.id = id;
        return this;
    }

    @OneToOne
    private Book book;

    @OneToOne
    private Reader reader;

    private Date borrowDate;

    private Date returnDate;

    public Book getBook() {
        return book;
    }

    public Loan setBook(Book book) {
        this.book = book;
        return this;
    }

    public Reader getReader() {
        return reader;
    }

    public Loan setReader(Reader reader) {
        this.reader = reader;
        return this;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Loan setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
        return this;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id.equals(loan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
