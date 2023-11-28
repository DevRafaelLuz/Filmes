package com.senac.filmeUC14.controller;

import com.senac.filmeUC14.model.Analise;
import com.senac.filmeUC14.model.Filme;
import com.senac.filmeUC14.model.Tema;
import com.senac.filmeUC14.service.AnaliseService;
import com.senac.filmeUC14.service.FilmeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FilmeController {
    @Autowired
    FilmeService filmeService;
    
    @Autowired
    AnaliseService analiseService;
    
    //Página inicial
    @PostMapping("/tema-inicial")
    public ModelAndView gravaTema(@ModelAttribute Tema tema, HttpServletResponse response) {
        Cookie cookieTema = new Cookie("tema-pagina", tema.getTema());
        cookieTema.setDomain("localhost"); 
        cookieTema.setHttpOnly(true); 
        cookieTema.setMaxAge(86400);
        response.addCookie(cookieTema);
        return new ModelAndView("redirect:/pagina-inicial");
    }
    
    @GetMapping("/pagina-inicial")
    public String ExibirPagInicial(@CookieValue(name="tema-pagina", defaultValue="claro")String tema, Model model) {
        model.addAttribute("css", tema);
        return "index";
    }
    
    //Página cadastro
    @PostMapping("/tema-cadastro")
    public ModelAndView gravaTemaCad(@ModelAttribute Tema tema, HttpServletResponse response) {
        Cookie cookieTema = new Cookie("tema-pagina", tema.getTema());
        cookieTema.setDomain("localhost"); 
        cookieTema.setHttpOnly(true); 
        cookieTema.setMaxAge(86400);
        response.addCookie(cookieTema);
        return new ModelAndView("redirect:/pagina-cadastro");
    }
    
    @GetMapping("/pagina-cadastro")
    public String cadastroForm(@CookieValue(name="tema-pagina", defaultValue="claro")String tema, Model model) {
        model.addAttribute("css", tema);
        model.addAttribute("filme", new Filme());
        return "cadastro";
    }
    
    //Página listagem
    @PostMapping("/tema-listagem")
    public ModelAndView gravaTemaList(@ModelAttribute Tema tema, HttpServletResponse response) {
        Cookie cookieTema = new Cookie("tema-pagina", tema.getTema());
        cookieTema.setDomain("localhost"); 
        cookieTema.setHttpOnly(true); 
        cookieTema.setMaxAge(86400);
        response.addCookie(cookieTema);
        return new ModelAndView("redirect:/pagina-listagem");
    }
    
    @GetMapping("/pagina-listagem")
    public String listagem(@CookieValue(name="tema-pagina", defaultValue="claro")String tema, Model model) {
        model.addAttribute("css", tema);
        model.addAttribute("lista", filmeService.listarTodos());
        return "lista";
    }
    
    //Registrar
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
    
    
    //Página detalhes
    @PostMapping("/tema-detalhes")
    public ModelAndView gravaTemaDetalhes(@ModelAttribute Tema tema, HttpServletResponse response) {
        Cookie cookieTema = new Cookie("tema-pagina", tema.getTema());
        cookieTema.setDomain("localhost"); 
        cookieTema.setHttpOnly(true); 
        cookieTema.setMaxAge(86400);
        response.addCookie(cookieTema);
        return new ModelAndView("redirect:/detalhes");
    }
    
    @GetMapping("/detalhes")
    public String exibiDetalhesFilme(@CookieValue(name="tema-pagina", defaultValue="claro")String tema, Model model, @RequestParam String id) {
        Integer idFilme = Integer.parseInt(id);
        
        Filme filmeEncontrado = new Filme();
        filmeEncontrado = filmeService.buscarPorId(idFilme);
        
        List<Analise> analisesEncontradas = new ArrayList<>();
        analisesEncontradas = analiseService.listar(idFilme);
        
        model.addAttribute("css", tema);
        model.addAttribute("filme", filmeEncontrado);
        model.addAttribute("analise", new Analise());
        model.addAttribute("analises", analisesEncontradas);
        return "detalhes";
    }
    
    //Página alterar
    @PostMapping("/tema-alterar")
    public ModelAndView gravaTemaAlterar(@ModelAttribute Tema tema, HttpServletResponse response) {
        Cookie cookieTema = new Cookie("tema-pagina", tema.getTema());
        cookieTema.setDomain("localhost"); 
        cookieTema.setHttpOnly(true); 
        cookieTema.setMaxAge(86400);
        response.addCookie(cookieTema);
        return new ModelAndView("redirect:/alterar");
    }
    
    @GetMapping("/alterar")
    public String alterarFilme(@RequestParam String id, @CookieValue(name="tema-pagina", defaultValue="claro")String tema, Model model) {
        Integer idFilme = Integer.parseInt(id);
        Filme filmeEncontrado = filmeService.buscarPorId(idFilme);
        model.addAttribute("css", tema);
        model.addAttribute("filme", filmeEncontrado);
        return "alterar";
    }
    
    //Excluir
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