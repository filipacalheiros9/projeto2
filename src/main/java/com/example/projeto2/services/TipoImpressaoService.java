package com.example.projeto2.services;

import com.example.projeto2.models.TipoImpressao;
import com.example.projeto2.repositories.TipoImpressaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoImpressaoService {

    private final TipoImpressaoRepository tipoImpressaoRepository;

    @Autowired
    public TipoImpressaoService(TipoImpressaoRepository tipoImpressaoRepository) {
        this.tipoImpressaoRepository = tipoImpressaoRepository;
    }

    public List<TipoImpressao> listarTodos() {
        return tipoImpressaoRepository.findAll();
    }

    public Optional<TipoImpressao> procurarPorId(Integer id) {
        return tipoImpressaoRepository.findById(id);
    }

    public TipoImpressao salvarTipoImpressao(TipoImpressao tipoImpressao) {
        return tipoImpressaoRepository.save(tipoImpressao);
    }

    public void excluirTipoImpressao(Integer id) {
        tipoImpressaoRepository.deleteById(id);
    }
}
