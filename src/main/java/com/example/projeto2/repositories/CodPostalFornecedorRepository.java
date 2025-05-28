package com.example.projeto2.repositories;

import com.example.projeto2.models.CodPostalFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodPostalFornecedorRepository extends JpaRepository<CodPostalFornecedor, String> {
}
