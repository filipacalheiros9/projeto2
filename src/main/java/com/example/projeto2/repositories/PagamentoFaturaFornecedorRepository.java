package com.example.projeto2.repositories;

import com.example.projeto2.models.PagamentoFaturaFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoFaturaFornecedorRepository extends JpaRepository<PagamentoFaturaFornecedor, Integer> {
}
