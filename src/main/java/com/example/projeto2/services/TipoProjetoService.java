package com.example.projeto2.services;

import com.example.projeto2.models.TipoProjeto;
import com.example.projeto2.repositories.TipoProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoProjetoService {

    private final TipoProjetoRepository tipoProjetoRepository;

    @Autowired
    public TipoProjetoService(TipoProjetoRepository tipoProjetoRepository) {
        this.tipoProjetoRepository = tipoProjetoRepository;
    }

    public List<TipoProjeto> listarTodos() {
        return tipoProjetoRepository.findAll();
    }

    public Optional<TipoProjeto> procurarPorId(Integer id) {
        return tipoProjetoRepository.findById(id);
    }

    public TipoProjeto salvarTipoProjeto(TipoProjeto tipoProjeto) {
        return tipoProjetoRepository.save(tipoProjeto);
    }

    public void excluirTipoProjeto(Integer id) {
        tipoProjetoRepository.deleteById(id);
    }
}
