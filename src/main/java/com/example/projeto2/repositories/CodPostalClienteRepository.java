package com.example.projeto2.repositories;

import com.example.projeto2.models.CodPostalCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodPostalClienteRepository extends JpaRepository<CodPostalCliente, String> {
}
