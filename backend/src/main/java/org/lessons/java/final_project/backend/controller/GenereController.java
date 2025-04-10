package org.lessons.java.final_project.backend.controller;

import java.util.List;

import org.lessons.java.final_project.backend.model.Genere;
import org.lessons.java.final_project.backend.service.GenereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/generi")
public class GenereController {

    @Autowired
    private GenereService genereService;

    @GetMapping
    public String getGenereList(Model model) {
        List<Genere> generi = genereService.findAll();
    
        model.addAttribute("generi", generi);
        /* username */

        return "genere/index";
    }

     /* create and store */
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("genere", new Genere());

        return "genere/create-or-edit";
    }

    @PostMapping("/create")
    public String postMethodName(@Valid @ModelAttribute("genere") Genere genere,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "genere/create-or-edit";
        }

        genereService.create(genere);
        return "redirect:/generi";
    }

    /* edit and update */
    @GetMapping("/update/{id}")
    public String store(@PathVariable("id") Integer id,Model model) {
        model.addAttribute("genere", genereService.getById(id));
        model.addAttribute("edit", true);

        return "genere/create-or-edit";
    }

    @PostMapping("/update/{id}")
    public String edit(@Valid @ModelAttribute("genere") Genere genere,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "genere/create-or-edit";
        }

        genereService.update(genere);
        return "redirect:/generi";
    }

    // delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        genereService.deleteById(id);

        return "redirect:/generi";
    }

}
