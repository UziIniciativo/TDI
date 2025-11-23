package edu.unam.tdi.tarea02.web;

import edu.unam.tdi.tarea02.model.Credito;
import edu.unam.tdi.tarea02.repo.CreditoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CreditoController {

    private final CreditoRepository repo;

    public CreditoController(CreditoRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String formulario(Model model) {
        model.addAttribute("credito", new Credito());
        return "index"; // templates/index.html
    }

    @PostMapping("/credito")
    public String capturar(@ModelAttribute("credito") Credito credito, Model model) {
        Credito guardado = repo.save(credito);
        model.addAttribute("credito", guardado);
        return "resultado"; // templates/resultado.html
    }

    // listar todo lo guardado
    @GetMapping("/creditos")
    public String listar(Model model) {
        model.addAttribute("lista", repo.findAll());
        return "lista";
    }
}
