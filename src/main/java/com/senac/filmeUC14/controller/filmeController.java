package com.senac.filmeUC14.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class filmeController {
    @GetMapping("/pagina-inicial")
    public String ExibirPagInicial() {
        return "index";
    }
}
