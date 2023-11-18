package com.senac.filmeUC14.repository;

import com.senac.filmeUC14.model.Analise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Integer> {
    
}
