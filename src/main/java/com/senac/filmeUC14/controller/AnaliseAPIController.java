package com.senac.filmeUC14.controller;

import com.senac.filmeUC14.model.Analise;
import com.senac.filmeUC14.service.AnaliseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analise")
public class AnaliseAPIController {
    @Autowired
    AnaliseService analiseService;
    
    //cadastrar
    @PostMapping("/registrar")
    public ResponseEntity<Analise> addComentario(@RequestBody Analise analise) {
        var novaAnalise = analiseService.criar(analise);
        return new ResponseEntity<>(novaAnalise, HttpStatus.CREATED);
    }
    
    //Buscar análises por filme
    @GetMapping("/buscar/{idAnalise}")
    public ResponseEntity<List> listarAnalises(@PathVariable Integer idAnalise) {
        List<Analise> lista = analiseService.listar(idAnalise);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    //Atualizar 
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarAnalise(@PathVariable Integer id, @RequestBody Analise analise) {
        var analiseAtualizada = analiseService.atualizar(id, analise);
        return new ResponseEntity<>(analiseAtualizada, HttpStatus.OK);
    }
    
    //Excluir
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        analiseService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
        
}
