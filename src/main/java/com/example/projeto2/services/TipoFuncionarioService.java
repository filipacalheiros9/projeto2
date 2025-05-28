package com.example.projeto2.services;

import com.example.projeto2.models.TipoFuncionario;
import com.example.projeto2.repositories.TipoFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoFuncionarioService {

    private final TipoFuncionarioRepository tipoFuncionarioRepository;

    @Autowired
    public TipoFuncionarioService(TipoFuncionarioRepository tipoFuncionarioRepository) {
        this.tipoFuncionarioRepository = tipoFuncionarioRepository;
    }

    public List<TipoFuncionario> listarTodos() {
        return tipoFuncionarioRepository.findAll();
    }

    public Optional<TipoFuncionario> procurarPorId(Integer id) {
        return tipoFuncionarioRepository.findById(id);
    }

    public TipoFuncionario salvarTipoFuncionario(TipoFuncionario tipoFuncionario) {
        return tipoFuncionarioRepository.save(tipoFuncionario);
    }

    public void excluirTipoFuncionario(Integer id) {
        tipoFuncionarioRepository.deleteById(id);
    }

    public TipoFuncionario getTipoFuncionarioById(Integer id) {
        return tipoFuncionarioRepository.findById(id).orElse(null);
    }
}
