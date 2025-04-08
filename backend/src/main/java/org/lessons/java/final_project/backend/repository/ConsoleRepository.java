package org.lessons.java.final_project.backend.repository;

import java.util.List;

import org.lessons.java.final_project.backend.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsoleRepository extends JpaRepository<Console, Integer>{
    public List<Console> findByNomeContaining(String nome);
}
