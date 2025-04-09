package org.lessons.java.final_project.backend.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.final_project.backend.model.Console;
import org.lessons.java.final_project.backend.model.Videogioco;
import org.lessons.java.final_project.backend.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsoleService {
    @Autowired
    private ConsoleRepository consoleRepository;

    /* recupera console */
    public List<Console> findAll() {
        return consoleRepository.findAll();
    }

    /* optional */
    public Optional<Console> findById(Integer id) {
        return consoleRepository.findById(id);
    }

    public Console getById(Integer id) {
        return consoleRepository.findById(id).get();
    }

    public Console create(Console console) {
        return consoleRepository.save(console);
    }

    public Console update(Console console) {
        return consoleRepository.save(console);
    }

    public void deleteById(Integer id) {
        Console consoleToDelete = getById(id);

        for (Videogioco linkedVideogioco : consoleToDelete.getVideogiochi()) {
            linkedVideogioco.getConsole().remove(consoleToDelete);
        }

        consoleRepository.delete(consoleToDelete);
    }
}
