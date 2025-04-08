package org.lessons.java.final_project.backend.controller;

import java.util.List;

import org.lessons.java.final_project.backend.model.Genere;
import org.lessons.java.final_project.backend.service.GenereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/generi")
public class GenereController {

    @Autowired
    private GenereService genereService;

    @GetMapping
    public String getGenereList(Authentication authentication, Model model) {
        List<Genere> generi = genereService.findAll();
    
        model.addAttribute("generi", generi);
        /* username */

        return "genere/index";
    }
}
