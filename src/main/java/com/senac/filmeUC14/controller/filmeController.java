package com.senac.filmeUC14.controller;

import com.senac.filmeUC14.model.Filme;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilmeController {
    private List<Filme> listaFilme = new ArrayList();
    
    @GetMapping("/pagina-inicial")
    public String ExibirPagInicial() {
        return "index";
    }
    
    @GetMapping("/pagina-cadastro")
    public String cadastroForm(Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastro";
    }
    
    @GetMapping("/pagina-listagem")
    public String listagem(Model model) {
        model.addAttribute("lista", listaFilme);
        return "lista";
    }
    
    @PostMapping("/registrar")
    public String recebeCadastro(@ModelAttribute Filme filme, Model model) {
        filme.setId(listaFilme.size() + 1);
        listaFilme.add(filme);
        return "redirect:/pagina-listagem";
    }
}
