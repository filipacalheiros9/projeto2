package com.example.projeto2.services;

import com.example.projeto2.models.Projeto;
import com.example.projeto2.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> procurarPorId(Integer id) {
        return projetoRepository.findById(id);
    }

    public Projeto salvarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public void excluirProjeto(Integer id) {
        projetoRepository.deleteById(id);
    }
}
