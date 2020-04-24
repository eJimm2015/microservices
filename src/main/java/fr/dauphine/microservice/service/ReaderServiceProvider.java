package fr.dauphine.microservice.service;

import fr.dauphine.microservice.model.Reader;
import org.springframework.stereotype.Service;

@Service
public interface ReaderServiceProvider {
    Reader create(final Reader reader);

}
