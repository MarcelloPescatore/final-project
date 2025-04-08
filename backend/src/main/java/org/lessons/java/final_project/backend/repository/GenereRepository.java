package org.lessons.java.final_project.backend.repository;

import java.util.List;

import org.lessons.java.final_project.backend.model.Genere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenereRepository extends JpaRepository<Genere, Integer> {
    public List<Genere> findByNomeContaining(String nome);
}
