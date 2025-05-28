package com.example.projeto2.services;

import com.example.projeto2.models.CodPostalCliente;
import com.example.projeto2.repositories.CodPostalClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodPostalClienteService {

    @Autowired
    private CodPostalClienteRepository codPostalClienteRepository;

    public List<CodPostalCliente> listarTodos() {
        return codPostalClienteRepository.findAll();
    }

    public Optional<CodPostalCliente> procurarPorCodigo(String codigo) {
        return codPostalClienteRepository.findById(codigo);
    }

    public CodPostalCliente salvar(CodPostalCliente codPostalCliente) {
        return codPostalClienteRepository.save(codPostalCliente);
    }

    public CodPostalCliente atualizar(String codigo, CodPostalCliente atualizado) {
        return codPostalClienteRepository.findById(codigo).map(codPostal -> {
            codPostal.setLocalidade(atualizado.getLocalidade());
            return codPostalClienteRepository.save(codPostal);
        }).orElseThrow(() -> new RuntimeException("Código Postal não encontrado!"));
    }

    public void executar(String codigo) {
        codPostalClienteRepository.deleteById(codigo);
    }
}
