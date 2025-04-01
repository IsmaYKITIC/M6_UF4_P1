package com.itic.webapp.Repositories;

import com.itic.webapp.Model.Llibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface LlibreRepository extends JpaRepository<Llibre, Integer> {
    Optional<Llibre> findByIsbn(String isbn);

    Set<Llibre> findByTitol(String titol);

    Set<Llibre> findByTitolAndEditorial(String titol, String editorial);

    Optional<Llibre> findById(int id);
}