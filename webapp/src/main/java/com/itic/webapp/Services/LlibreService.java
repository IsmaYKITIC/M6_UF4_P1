package com.itic.webapp.Services;

import com.itic.webapp.Model.Llibre;
import com.itic.webapp.Repositories.LlibreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LlibreService {

    @Autowired
    private LlibreRepository llibreRepository;

    public Llibre saveLlibre(Llibre llibre) {
        validateISBN(llibre.getIsbn());
        esISBNDuplicat(llibre.getIsbn());
        return llibreRepository.save(llibre);
    }

    private void esISBNDuplicat(String isbn) {
        if (llibreRepository.findByIsbn(isbn).isPresent()) {
            throw new IllegalArgumentException("L'ISBN ja existeix a la base de dades");
        }
    }

    public List<Llibre> getAllLlibres() {
        return llibreRepository.findAll();
    }

    public Optional<Llibre> findByIdLlibre(int id) {
        return llibreRepository.findById(id);
    }

    public Optional<Llibre> findByIsbn(String isbn) {
        return llibreRepository.findByIsbn(isbn);
    }

    public Set<Llibre> findByTitol(String titol) {
        return llibreRepository.findByTitol(titol);
    }

    public Set<Llibre> findByTitolAndEditorial(String titol, String editorial) {
        return llibreRepository.findByTitolAndEditorial(titol, editorial);
    }

    private void validateISBN(String isbn) {
        if (isbn == null || !isbn.matches("^\\d{10}(\\d{3})?$")) {
            throw new IllegalArgumentException("Format d'ISBN invàlid: ha de tenir 10 o 13 dígits numèrics");
        }
    }

    public boolean isValidISBN(String isbn) {
        try {
            validateISBN(isbn);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
