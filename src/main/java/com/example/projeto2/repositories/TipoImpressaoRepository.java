package com.example.projeto2.repositories;

import com.example.projeto2.models.TipoImpressao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoImpressaoRepository extends JpaRepository<TipoImpressao, Integer> {
}
