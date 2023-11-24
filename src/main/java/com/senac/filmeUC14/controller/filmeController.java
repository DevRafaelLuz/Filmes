package com.senac.filmeUC14.controller;

import com.senac.filmeUC14.model.Analise;
import com.senac.filmeUC14.model.Filme;
import com.senac.filmeUC14.service.AnaliseService;
import com.senac.filmeUC14.service.FilmeService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmeController {
    @Autowired
    FilmeService filmeService;
    
    @Autowired
    AnaliseService analiseService;
    
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
        model.addAttribute("lista", filmeService.listarTodos());
        return "lista";
    }
    
    @PostMapping("/registrar")
    public String recebeCadastro(@ModelAttribute Filme filme, Model model) {  
        if (filme.getId() != null) {
            filmeService.atualizar(filme.getId(), filme);
        } else {
            filmeService.criar(filme);
        }
        return "redirect:/pagina-listagem";
    }
    
    @PostMapping("/registrar-analise")
    public String recebeAnalise(@ModelAttribute Filme filme, @ModelAttribute Analise analise, Model model) {
        analise.setFilme(filme);
        analiseService.criar(analise);
        return "redirect:/pagina-listagem";
    }
    
    @GetMapping("/detalhes")
    public String exibiDetalhesFilme(Model model, @RequestParam String id) {
        Integer idFilme = Integer.parseInt(id);
        
        Filme filmeEncontrado = new Filme();
        filmeEncontrado = filmeService.buscarPorId(idFilme);
        
        List<Analise> analisesEncontradas = new ArrayList<>();
        analisesEncontradas = analiseService.listar(idFilme);
        
        
        model.addAttribute("filme", filmeEncontrado);
        model.addAttribute("analise", new Analise());
        model.addAttribute("analises", analisesEncontradas);
        return "detalhes";
    }
    
    @GetMapping("/alterar")
    public String alterarFilme(@RequestParam String id, Model model) {
        Integer idFilme = Integer.parseInt(id);
        Filme filmeEncontrado = filmeService.buscarPorId(idFilme);
        model.addAttribute("filme", filmeEncontrado);
        return "alterar";
    }
    
    @GetMapping("/excluir")
    public String excluirFilme(@RequestParam String id) {
        Integer idFilme = Integer.parseInt(id);
        analiseService.excluirTodasAnalisePorFilme(idFilme);
        filmeService.excluir(idFilme);
        return "redirect:/pagina-listagem";
    }
    
    @GetMapping("/excluirAnalise")
    public String deletarAnalise(@RequestParam String id) {
        Integer idAnalise = Integer.parseInt(id);
        analiseService.excluir(idAnalise);
        return "redirect:/pagina-listagem";
    }   
}
