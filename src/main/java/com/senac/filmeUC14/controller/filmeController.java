package com.senac.filmeUC14.controller;

import com.senac.filmeUC14.model.Analise;
import com.senac.filmeUC14.model.Filme;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmeController {
    private List<Filme> listaFilme = new ArrayList();
    private List<Analise> listaAnalise = new ArrayList();
    
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
    
    @PostMapping("/registrar-analise")
    public String recebeAnalise(@ModelAttribute Filme filme, @ModelAttribute Analise analise, Model model) {
        analise.setId(listaAnalise.size() + 1);
        analise.setFilme(filme);
        listaAnalise.add(analise);
        return "redirect:/pagina-listagem";
    }
    
    @GetMapping("/detalhes")
    public String exibiDetalhesFilme(Model model, @RequestParam String id) {
        Integer idFilme = Integer.parseInt(id);
        
        Filme filmeEncontrado = new Filme();
        for(Filme f: listaFilme) {
            if(f.getId() == idFilme) {
                filmeEncontrado = f;
                break;
            }
        }
        
        Analise analiseEncontrada = new Analise();
        for (Analise a: listaAnalise) {
            if (a.getId() == idFilme) {
                analiseEncontrada = a;
                break;
            }
        }
        
        model.addAttribute("filme", filmeEncontrado);
        model.addAttribute("analise", analiseEncontrada);
        return "detalhes";
    }
}
