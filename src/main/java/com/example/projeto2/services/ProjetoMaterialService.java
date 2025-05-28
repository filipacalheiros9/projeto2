package com.example.projeto2.services;

import com.example.projeto2.models.ProjetoMaterial;
import com.example.projeto2.models.ProjetoMaterialId;
import com.example.projeto2.repositories.ProjetoMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoMaterialService {

    private final ProjetoMaterialRepository projetoMaterialRepository;

    @Autowired
    public ProjetoMaterialService(ProjetoMaterialRepository projetoMaterialRepository) {
        this.projetoMaterialRepository = projetoMaterialRepository;
    }

    public List<ProjetoMaterial> listarTodos() {
        return projetoMaterialRepository.findAll();
    }

    public Optional<ProjetoMaterial> procurarPorId(ProjetoMaterialId id) {
        return projetoMaterialRepository.findById(id);
    }

    public ProjetoMaterial salvarProjetoMaterial(ProjetoMaterial projetoMaterial) {
        ProjetoMaterialId id = projetoMaterial.getId();
        Optional<ProjetoMaterial> existingProjetoMaterial = projetoMaterialRepository.findById(id);
        if (existingProjetoMaterial.isEmpty()) {
            return projetoMaterialRepository.save(projetoMaterial);
        } else {
            System.out.println("ProjetoMaterial com IDs " + id.getIdmaterial() + " e " + id.getIdprojeto() + " já existe.");
            return existingProjetoMaterial.get();
        }
    }

    public void excluirProjetoMaterial(ProjetoMaterialId id) {
        projetoMaterialRepository.deleteById(id);
    }
}