package com.senac.filmeUC14.controller;

import com.senac.filmeUC14.model.Filme;
import com.senac.filmeUC14.service.FilmeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filme")
public class FilmeAPIController {
    @Autowired
    FilmeService filmeService;
    
    //Cadastrar
    @PostMapping("/cadastrar")
    public ResponseEntity<Filme> addFilme(@RequestBody Filme filme) {
        var novoFilme = filmeService.criar(filme);
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }
    
    //Listar
    @GetMapping("/listar")
    public ResponseEntity<List> listar() {
        List<Filme> livros = filmeService.listarTodos();
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }
    
    //Buscar por id
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Filme> procurar(@PathVariable Integer id) {
        Filme FilmeEncontrado = filmeService.buscarPorId(id);
        return new ResponseEntity<>(FilmeEncontrado, HttpStatus.OK);
    }
}
