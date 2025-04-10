package org.lessons.java.final_project.backend.controller;

import java.util.List;

import org.lessons.java.final_project.backend.model.Videogioco;
import org.lessons.java.final_project.backend.service.ConsoleService;
import org.lessons.java.final_project.backend.service.GenereService;
import org.lessons.java.final_project.backend.service.VideogiocoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/videogiochi")
public class VideogiocoController {

    @Autowired
    private VideogiocoService videogiocoService;

    @Autowired
    private GenereService genereService;

    @Autowired
    private ConsoleService consoleService;

    /* read all */
    @GetMapping
    public String getVideogiocoList(@RequestParam(name = "titolo", required = false) String titolo,
            Model model) {
        List<Videogioco> videogiochi;

        if (titolo != null && !titolo.isEmpty()) {
            videogiochi = videogiocoService.findByName(titolo);
        } else {
            videogiochi = videogiocoService.findAll();
        }

        model.addAttribute("videogiochi", videogiochi);
        model.addAttribute("generi", genereService.findAll());
        /* username */

        return "videogiochi/index";
    }

    /* read specific */
    @GetMapping("/{id}")
    public String getVideogiocoDetails(@PathVariable("id") Integer id, Model model) {
        Videogioco videogioco = videogiocoService.getById(id);

        model.addAttribute("videogioco", videogioco);
        return "videogiochi/show";
    }

    /* create and store */
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("videogioco", new Videogioco());
        model.addAttribute("generi", genereService.findAll());
        model.addAttribute("console", consoleService.findAll());

        return "videogiochi/create-or-edit";
    }

    @PostMapping("/create")
    public String postMethodName(@Valid @ModelAttribute("videogioco") Videogioco videogioco,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("generi", genereService.findAll());
            model.addAttribute("console", consoleService.findAll());
            return "videogiochi/create-or-edit";
        }

        videogiocoService.create(videogioco);
        return "redirect:/videogiochi";
    }

    /* edit and update */
    @GetMapping("/update/{id}")
    public String store(@PathVariable("id") Integer id,Model model) {
        model.addAttribute("videogioco", videogiocoService.getById(id));
        model.addAttribute("generi", genereService.findAll());
        model.addAttribute("console", consoleService.findAll());
        model.addAttribute("edit", true);

        return "videogiochi/create-or-edit";
    }

    @PostMapping("/update/{id}")
    public String edit(@Valid @ModelAttribute("videogioco") Videogioco videogioco,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("generi", genereService.findAll());
            model.addAttribute("console", consoleService.findAll());
            model.addAttribute("edit", true);
            return "videogiochi/create-or-edit";
        }

        videogiocoService.update(videogioco);
        return "redirect:/videogiochi";
    }


    // delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        videogiocoService.deleteById(id);

        return "redirect:/videogiochi";
    }


}
