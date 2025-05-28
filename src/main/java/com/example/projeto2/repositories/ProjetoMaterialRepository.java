package com.example.projeto2.repositories;

import com.example.projeto2.models.ProjetoMaterial;
import com.example.projeto2.models.ProjetoMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoMaterialRepository extends JpaRepository<ProjetoMaterial, ProjetoMaterialId> {
}
