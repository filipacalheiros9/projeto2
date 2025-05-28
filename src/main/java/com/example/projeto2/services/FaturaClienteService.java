package com.example.projeto2.services;

import com.example.projeto2.models.FaturaCliente;
import com.example.projeto2.repositories.FaturaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaturaClienteService {

    @Autowired
    private FaturaClienteRepository faturaClienteRepository;

    public List<FaturaCliente> listarTodas() {
        return faturaClienteRepository.findAll();
    }

    public Optional<FaturaCliente> procurarPorId(Integer id) {
        return faturaClienteRepository.findById(id);
    }

    public FaturaCliente salvar(FaturaCliente faturaCliente) {
        return faturaClienteRepository.save(faturaCliente);
    }

    public FaturaCliente atualizar(Integer id, FaturaCliente faturaAtualizada) {
        return faturaClienteRepository.findById(id).map(fatura -> {
            fatura.setDatafatura(faturaAtualizada.getDatafatura());
            fatura.setValorfatura(faturaAtualizada.getValorfatura());
            return faturaClienteRepository.save(fatura);
        }).orElseThrow(() -> new RuntimeException("Fatura não encontrada!"));
    }

    public void excluir(Integer id) {
        faturaClienteRepository.deleteById(id);
    }
}
