package com.api.travelagency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/publico/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        if (error != null) {
            model.addAttribute("error", "Usuário ou senha inválidos!");
        }
        if (logout != null) {
            model.addAttribute("message", "Você saiu com sucesso!");
        }
        return "login"; // Nome do arquivo HTML da página de login (login.html)
    }

    @GetMapping("/home")
    public String homePage() {
        return "home"; // Nome do arquivo HTML da página inicial (home.html)
    }
}
