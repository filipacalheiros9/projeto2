package com.example.projeto2.repositories;

import com.example.projeto2.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    @Query("SELECT f FROM Funcionario f JOIN FETCH f.idtipofunc WHERE f.username = :username AND f.password = :password")
    Optional<Funcionario> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
