package org.lessons.java.final_project.backend.controller;

import java.util.List;

import org.lessons.java.final_project.backend.model.Videogioco;
import org.lessons.java.final_project.backend.service.VideogiocoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/videogiochi")
public class VideogiocoController {
    
    @Autowired
    private VideogiocoService videogiocoService;

    @GetMapping
    public String getVideogiocoList(@RequestParam(name = "titolo", required = false) String titolo, Authentication authentication, Model model) {
        List<Videogioco> videogiochi;

        if (titolo != null && !titolo.isEmpty()) {
            videogiochi = videogiocoService.findByName(titolo);
        } else {
            videogiochi = videogiocoService.findAll();
        }

        model.addAttribute("videogiochi", videogiochi);
        /* username */

        return "videogiochi/index";
    }

}
