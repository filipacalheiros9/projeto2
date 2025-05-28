package com.example.projeto2.services;

import com.example.projeto2.models.CodPostalFornecedor;
import com.example.projeto2.repositories.CodPostalFornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodPostalFornecedorService {

    @Autowired
    private CodPostalFornecedorRepository codPostalFornecedorRepository;

    public List<CodPostalFornecedor> listarTodos() {
        return codPostalFornecedorRepository.findAll();
    }

    public Optional<CodPostalFornecedor> procurarPorCodigo(String codigo) {
        return codPostalFornecedorRepository.findById(codigo);
    }

    public CodPostalFornecedor salvar(CodPostalFornecedor codPostalFornecedor) {
        return codPostalFornecedorRepository.save(codPostalFornecedor);
    }

    public CodPostalFornecedor atualizar(String codigo, CodPostalFornecedor atualizado) {
        return codPostalFornecedorRepository.findById(codigo).map(codPostal -> {
            codPostal.setLocalidade(atualizado.getLocalidade());
            return codPostalFornecedorRepository.save(codPostal);
        }).orElseThrow(() -> new RuntimeException("Código Postal do Fornecedor não encontrado!"));
    }

    public void excluir(String codigo) {
        codPostalFornecedorRepository.deleteById(codigo);
    }
}
