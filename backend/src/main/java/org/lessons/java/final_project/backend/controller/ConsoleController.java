package org.lessons.java.final_project.backend.controller;

import java.util.List;

import org.lessons.java.final_project.backend.model.Console;
import org.lessons.java.final_project.backend.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    private ConsoleService consoleService;

    @GetMapping
    public String getConsoleList(Authentication authentication, Model model) {
        List<Console> console = consoleService.findAll();

        model.addAttribute("console", console);
        /* username */

        return "console/index";
    }

}
