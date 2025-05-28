package com.example.projeto2.repositories;

import com.example.projeto2.models.TipoFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoFuncionarioRepository extends JpaRepository<TipoFuncionario, Integer> {
}
