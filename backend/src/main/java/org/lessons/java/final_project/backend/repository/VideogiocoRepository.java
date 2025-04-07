package org.lessons.java.final_project.backend.repository;

import java.util.List;

import org.lessons.java.final_project.backend.model.Videogioco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideogiocoRepository extends JpaRepository<Videogioco,Integer> {

    public List<Videogioco> findByTitoloContaining(String titolo);
} 