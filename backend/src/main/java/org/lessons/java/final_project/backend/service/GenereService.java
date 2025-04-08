package org.lessons.java.final_project.backend.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.final_project.backend.model.Genere;
import org.lessons.java.final_project.backend.repository.GenereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenereService {
    
    @Autowired
    private GenereRepository genereRepository;

    /* recupera generi */
    public List<Genere> findAll() {
        return genereRepository.findAll();
    }

    /* optional */
    public Optional<Genere> findById(Integer id) {
        return genereRepository.findById(id);
    }

    public Genere getById(Integer id) {
        return genereRepository.findById(id).get();
    }

    public Genere create(Genere genere) {
        return genereRepository.save(genere);
    }

    public Genere update(Genere genere) {
        return genereRepository.save(genere);
    }

    public void deleteById(Integer id) {
        Genere genere = getById(id);

        genereRepository.delete(genere);
    }

}
