package com.example.projeto2.services;

import com.example.projeto2.models.Servico;
import com.example.projeto2.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    @Autowired
    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> procurarPorId(Integer id) {
        return servicoRepository.findById(id);
    }

    public Servico salvarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public void excluirServico(Integer id) {
        servicoRepository.deleteById(id);
    }
}
