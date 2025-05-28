package com.example.projeto2.repositories;

import com.example.projeto2.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface MaterialRepository extends JpaRepository<Material, Integer> {
}
