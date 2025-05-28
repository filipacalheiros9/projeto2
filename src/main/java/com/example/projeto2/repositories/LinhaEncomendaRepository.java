package com.example.projeto2.repositories;

import com.example.projeto2.models.LinhaEncomenda;
import com.example.projeto2.models.LinhaEncomendaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinhaEncomendaRepository extends JpaRepository<LinhaEncomenda, LinhaEncomendaId> {

    @Query("SELECT le FROM LinhaEncomenda le " +
            "JOIN FETCH le.idmaterial m " +
            "JOIN FETCH le.idfornecedor f")
    List<LinhaEncomenda> findAllWithAssociations();
}
