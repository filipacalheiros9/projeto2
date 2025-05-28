package com.example.projeto2.services;

import com.example.projeto2.models.Material;
import com.example.projeto2.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> listarTodos() {
        return materialRepository.findAll();
    }

    public Optional<Material> procurarPorId(Integer id) {
        return materialRepository.findById(id);
    }

    public Material salvarMaterial(Material material) {
        return materialRepository.save(material);
    }

    public void excluirMaterial(Integer id) {
        materialRepository.deleteById(id);
    }

    public Material atualizarMaterial(Material material) {
        if (material.getId() == null) {
            throw new IllegalArgumentException("ID do material não pode ser nulo para atualização.");
        }

        Optional<Material> existente = materialRepository.findById(material.getId());
        if (existente.isPresent()) {
            return materialRepository.save(material);
        } else {
            throw new RuntimeException("Material com ID " + material.getId() + " não encontrado.");
        }
    }

}
