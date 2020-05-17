package fr.dauphine.microservice.service;

import fr.dauphine.microservice.model.Reader;

import java.util.Optional;


public interface ReaderServiceProvider {
    Reader create(final Reader reader);
    Optional<Reader> getById(final Integer id);
}
