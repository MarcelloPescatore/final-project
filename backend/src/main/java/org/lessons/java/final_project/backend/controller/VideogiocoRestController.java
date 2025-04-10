package org.lessons.java.final_project.backend.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.final_project.backend.model.Videogioco;
import org.lessons.java.final_project.backend.service.VideogiocoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videogiochi")
public class VideogiocoRestController {
    
    @Autowired
    private VideogiocoService videogiocoService;

    /* read */
    @GetMapping
    public ResponseEntity<List<Videogioco>> index(){
        List<Videogioco> videogiochi = videogiocoService.findAll();
        return new ResponseEntity<>(videogiochi, HttpStatus.OK);
    }

    /* read specifico */
    @GetMapping("/{id}")
    public ResponseEntity<Videogioco> show(@PathVariable("id") Integer id){
        Optional<Videogioco> videogiocoTentativo = videogiocoService.findById(id);

        if (videogiocoTentativo.isEmpty()) {
            return new ResponseEntity<Videogioco>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<Videogioco>(videogiocoTentativo.get(), HttpStatus.OK);
    }

    /* create */
    @PostMapping
    public ResponseEntity<Videogioco> store(@RequestBody Videogioco videogioco){

        Videogioco videogiocoCreato = videogiocoService.create(videogioco);
        
        return new ResponseEntity<Videogioco>(videogiocoCreato, HttpStatus.CREATED);
    }

    /* update */
    @PutMapping("/{id}")
    public ResponseEntity<Videogioco> update(@RequestBody Videogioco videogioco, @PathVariable Integer id){
        Optional<Videogioco> videogiocoTentativo = videogiocoService.findById(id);

        if (videogiocoTentativo.isEmpty()) {
            return new ResponseEntity<Videogioco>(HttpStatus.NOT_FOUND);
        }
        videogioco.setId(id);
        Videogioco videogiocoAggiornato = videogiocoService.update(videogioco);

        return new ResponseEntity<Videogioco>(videogiocoAggiornato, HttpStatus.OK);
    }

    /* delete */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Videogioco> videogiocoDaEliminare = videogiocoService.findById(id);
        
        if (videogiocoDaEliminare.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        videogiocoService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
