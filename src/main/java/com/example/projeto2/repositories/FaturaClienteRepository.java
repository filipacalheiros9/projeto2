package com.example.projeto2.repositories;

import com.example.projeto2.models.FaturaCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaturaClienteRepository extends JpaRepository<FaturaCliente, Integer> {
}
