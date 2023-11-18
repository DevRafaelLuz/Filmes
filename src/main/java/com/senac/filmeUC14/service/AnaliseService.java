package com.senac.filmeUC14.service;

import com.senac.filmeUC14.model.Analise;
import com.senac.filmeUC14.repository.AnaliseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnaliseService {
    @Autowired
    AnaliseRepository analiseRepository;
    
    public Analise criar(Analise analise) {
        analise.setId(null);
        analiseRepository.save(analise);
        return analise;
    }
    
    public List<Analise> listar(Integer id) {
        return analiseRepository.findByFilmeId(id);
    }
    
    public Analise buscarAnalisePorId(Integer id) {
        return analiseRepository.findById(id).orElseThrow();
    }
    
    public Analise atualizar(Integer id, Analise analiseEnviada) {
        Analise analiseEncontrada = buscarAnalisePorId(id);
        analiseEncontrada.setComentario(analiseEnviada.getComentario());
        analiseEncontrada.setNota(analiseEnviada.getNota());
        analiseRepository.save(analiseEncontrada);
        return analiseEncontrada;
    }
}
