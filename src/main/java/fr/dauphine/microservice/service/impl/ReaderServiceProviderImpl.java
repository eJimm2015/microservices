package fr.dauphine.microservice.service.impl;

import fr.dauphine.microservice.model.Reader;
import fr.dauphine.microservice.repository.ReaderRepository;
import fr.dauphine.microservice.service.ReaderServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReaderServiceProviderImpl implements ReaderServiceProvider {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public Reader create(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public Optional<Reader> getById(Integer id) {
        return readerRepository.findById(id);
    }
}
