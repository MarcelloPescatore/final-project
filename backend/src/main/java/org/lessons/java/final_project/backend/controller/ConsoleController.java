package org.lessons.java.final_project.backend.controller;

import java.util.List;

import org.lessons.java.final_project.backend.model.Console;
import org.lessons.java.final_project.backend.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    private ConsoleService consoleService;

    @GetMapping
    public String getConsoleList(Authentication authentication, Model model) {
        List<Console> console = consoleService.findAll();

        model.addAttribute("console", console);
        model.addAttribute("username", authentication.getName());

        return "console/index";
    }

    /* create and store */
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("console", new Console());

        return "console/create-or-edit";
    }

    @PostMapping("/create")
    public String postMethodName(@Valid @ModelAttribute("console") Console console,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "console/create-or-edit";
        }

        consoleService.create(console);
        return "redirect:/console";
    }

    /* edit and update */
    @GetMapping("/update/{id}")
    public String store(@PathVariable("id") Integer id,Model model) {
        model.addAttribute("console", consoleService.getById(id));
        model.addAttribute("edit", true);

        return "console/create-or-edit";
    }

    @PostMapping("/update/{id}")
    public String edit(@Valid @ModelAttribute("console") Console console,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "console/create-or-edit";
        }

        consoleService.update(console);
        return "redirect:/console";
    }

    // delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        consoleService.deleteById(id);

        return "redirect:/console";
    }


}
