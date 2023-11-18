package com.senac.filmeUC14.controller;

import com.senac.filmeUC14.model.Filme;
import com.senac.filmeUC14.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filme")
public class FilmeAPIController {
    @Autowired
    FilmeService filmeService;
    
    //Cadastrar(POST)
    @PostMapping("/cadastrar")
    public ResponseEntity<Filme> addFilme(@RequestBody Filme filme) {
        var novoFilme = filmeService.criar(filme);
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }
}
