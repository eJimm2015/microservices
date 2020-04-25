package fr.dauphine.microservice.service;

import fr.dauphine.microservice.model.Reader;


public interface ReaderServiceProvider {
    Reader create(final Reader reader);

}
