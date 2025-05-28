package com.example.projeto2.services;

import com.example.projeto2.models.LinhaEncomenda;
import com.example.projeto2.models.LinhaEncomendaId;
import com.example.projeto2.repositories.LinhaEncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LinhaEncomendaService {

    @Autowired
    private LinhaEncomendaRepository linhaEncomendaRepository;

    @Transactional(readOnly = true)
    public List<LinhaEncomenda> listarTodos() {
        return linhaEncomendaRepository.findAllWithAssociations();
    }

    public Optional<LinhaEncomenda> procurarPorId(LinhaEncomendaId id) {
        return linhaEncomendaRepository.findById(id);
    }

    public LinhaEncomenda salvar(LinhaEncomenda linhaEncomenda) {
        return linhaEncomendaRepository.save(linhaEncomenda);
    }

    public LinhaEncomenda atualizar(LinhaEncomendaId id, LinhaEncomenda linhaAtualizada) {
        return linhaEncomendaRepository.findById(id).map(le -> {
            le.setIdmaterial(linhaAtualizada.getIdmaterial());
            le.setIdfornecedor(linhaAtualizada.getIdfornecedor());
            le.setQtdencomendada(linhaAtualizada.getQtdencomendada());
            le.setValorencomendado(linhaAtualizada.getValorencomendado());
            return linhaEncomendaRepository.save(le);
        }).orElseThrow(() -> new RuntimeException("Linha de Encomenda não encontrada!"));
    }

    public void excluir(LinhaEncomendaId id) {
        linhaEncomendaRepository.deleteById(id);
    }
}
