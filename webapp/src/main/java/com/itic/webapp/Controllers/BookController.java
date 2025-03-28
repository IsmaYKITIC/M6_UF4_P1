package com.itic.webapp.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.itic.webapp.Model.Llibre;
import com.itic.webapp.Model.Usuaris;
import com.itic.webapp.Repositories.RepoLlibre;

@Controller
@SessionAttributes("users")
public class BookController {

    @Autowired
    RepoLlibre repoll = new RepoLlibre();

    @GetMapping("/")
    public String iniciar(Model model) {
        return "login";
    }

    @PostMapping("/index")
    public String login(@ModelAttribute("users") Usuaris users, Model model) {

        model.addAttribute("users", users);

        if (users.getUsuari().equals("toni")
                && users.getPassword().equals("h3ll0!!")) {
            return "index";
        } else {
            return "login";
        }
    }

    @GetMapping("/index")
    public String index(@ModelAttribute("users") Usuaris users, Model model) {

        return "index";

    }

    @GetMapping("/consulta")
    public String consulta(@ModelAttribute("users") Usuaris users, Model model) {

        ArrayList<Llibre> llibres = repoll.getAllLlibres();

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

}
