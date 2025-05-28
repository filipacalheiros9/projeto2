package com.example.projeto2.services;

import com.example.projeto2.models.Fornecedor;
import com.example.projeto2.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> procurarPorId(Integer id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizar(Integer id, Fornecedor fornecedorAtualizado) {
        return fornecedorRepository.findById(id).map(fornecedor -> {
            fornecedor.setNomefornecedor(fornecedorAtualizado.getNomefornecedor());
            fornecedor.setNiffornecedor(fornecedorAtualizado.getNiffornecedor());
            fornecedor.setTelefonefornecedor(fornecedorAtualizado.getTelefonefornecedor());
            fornecedor.setRuafornecedor(fornecedorAtualizado.getRuafornecedor());
            fornecedor.setNportafornecedor(fornecedorAtualizado.getNportafornecedor());
            fornecedor.setCodpostalforn(fornecedorAtualizado.getCodpostalforn());
            return fornecedorRepository.save(fornecedor);
        }).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado!"));
    }

    public void excluir(Integer id) {
        fornecedorRepository.deleteById(id);
    }
}
