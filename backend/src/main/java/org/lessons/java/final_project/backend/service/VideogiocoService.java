package org.lessons.java.final_project.backend.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.final_project.backend.model.Videogioco;
import org.lessons.java.final_project.backend.repository.VideogiocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VideogiocoService {
    
    @Autowired
    private VideogiocoRepository videogiocoRepository;

    /* recupera videogiochi */
    public List<Videogioco> findAll() {
        return videogiocoRepository.findAll();
    }

    /* ordinati per titolo  */
    public List<Videogioco> findAllSortedByName() {
        return videogiocoRepository.findAll(Sort.by("titolo"));
    }

    /* ordinati per data decrescente */
    public List<Videogioco> findAllSortedByDataDesc() {
        return videogiocoRepository.findAll(Sort.by(Sort.Order.desc("anno_uscita")));
    }

    /* optional */
    public Optional<Videogioco> findById(Integer id) {
        return videogiocoRepository.findById(id);
    }

    public Videogioco getById(Integer id) {
        return videogiocoRepository.findById(id).get();
    }

    public List<Videogioco> findByName(String titolo) {
        return videogiocoRepository.findByTitoloContaining(titolo);
    }

    public Videogioco create(Videogioco videogioco) {
        return videogiocoRepository.save(videogioco);
    }

    public Videogioco update(Videogioco videogioco) {
        return videogiocoRepository.save(videogioco);
    }


    public void deleteById(Integer id) {
        Videogioco videogioco = getById(id);

        videogiocoRepository.delete(videogioco);
    }

}
