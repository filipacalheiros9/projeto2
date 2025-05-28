package com.example.projeto2.repositories;

import com.example.projeto2.models.TipoProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProjetoRepository extends JpaRepository<TipoProjeto, Integer> {
}
