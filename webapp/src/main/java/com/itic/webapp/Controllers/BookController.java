package com.itic.webapp.Controllers;

import com.itic.webapp.Model.Llibre;
import com.itic.webapp.Model.Usuaris;
import com.itic.webapp.Services.LlibreService;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("users")
public class BookController {

    @Autowired
    private LlibreService LService;

    @GetMapping("/")
    public String iniciar(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("users") Usuaris users, Model model) {

        model.addAttribute("users", users);

        if (users.getUsuari().equals("IsmaM6")
                && users.getPassword().equals("password")) {
            return "index";
        } else {
            model.addAttribute("errorMessage", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/index")
    public String index(@ModelAttribute("users") Usuaris users, Model model) {

        return "index";

    }

    @GetMapping("/consulta")
    public String consulta(@ModelAttribute("users") Usuaris users, Model model) {

        List<Llibre> llibres = LService.getAllLlibres();

        model.addAttribute("llibres", llibres);

        return "consulta";
    }

    @GetMapping("/inserir")
    public String inputInserir(@ModelAttribute("users") Usuaris users, Model model) {
        return "inserir";
    }

    @GetMapping("/cercaid")
    public String inputCerca(@ModelAttribute("users") Usuaris users, Model model) {

        Llibre llibre = new Llibre();
        llibre.setIdLlibre(0);
        model.addAttribute("llibreErr", true);
        model.addAttribute("message", "");
        model.addAttribute("llibre", llibre);

        return "cercaid";

    }

    @PostMapping("/inserir")
    public String inserir(
            @ModelAttribute("Llibre") Llibre llibre,
            @RequestParam(name = "titol") String titol,
            @RequestParam(name = "autor") String autor,
            @RequestParam(name = "editorial") String editorial,
            @RequestParam(name = "datapublicacio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate datapublicacio,
            @RequestParam(name = "tematica") String tematica,
            @RequestParam(name = "isbn") String isbn,
            Model model) {
        try {
            Llibre nouLlibre = new Llibre();
            nouLlibre.setTitol(titol);
            nouLlibre.setAutor(autor);
            nouLlibre.setEditorial(editorial);
            nouLlibre.setDataPublicacio(datapublicacio);
            nouLlibre.setTematica(tematica);
            nouLlibre.setIsbn(isbn);

            LService.saveLlibre(nouLlibre);
            return "redirect:/consulta";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "inserir";
        }
    }

    @PostMapping("/cercaid")
    public String cercaId(
            @ModelAttribute("Llibre") Llibre llibre,
            @RequestParam(name = "idLlibre") String idLlibre,
            Model model) {
        try {
            int idLlib = Integer.parseInt(idLlibre);
            Optional<Llibre> llibres = LService.findByIdLlibre(idLlib);

            if (llibres.isPresent()) {
                model.addAttribute("llibre", llibres.get());
                model.addAttribute("llibreErr", false);
            } else {
                model.addAttribute("message", "No hi ha cap llibre amb aquesta id");
                model.addAttribute("llibreErr", true);
            }
        } catch (NumberFormatException e) {
            model.addAttribute("message", "La id de llibre ha de ser un nombre enter");
            model.addAttribute("llibreErr", true);
        }
        return "cercaid";
    }

    @PostMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDuplicateISBN(DataIntegrityViolationException e, Model model) {
        model.addAttribute("errorMessage", "L'ISBN ja existeix a la base de dades");
        return "inserir";
    }

    @ModelAttribute("users")
    public Usuaris getDefaultUser() {
        return new Usuaris();
    }
}