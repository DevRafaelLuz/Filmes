package com.senac.filmeUC14.service;

import com.senac.filmeUC14.model.Analise;
import com.senac.filmeUC14.repository.AnaliseRepository;
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
}
