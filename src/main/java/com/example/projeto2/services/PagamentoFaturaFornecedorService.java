package com.example.projeto2.services;

import com.example.projeto2.models.Fornecedor;
import com.example.projeto2.models.PagamentoFaturaFornecedor;
import com.example.projeto2.repositories.PagamentoFaturaFornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoFaturaFornecedorService {

    private final PagamentoFaturaFornecedorRepository pagamentoFaturaFornecedorRepository;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    public PagamentoFaturaFornecedorService(PagamentoFaturaFornecedorRepository pagamentoFaturaFornecedorRepository) {
        this.pagamentoFaturaFornecedorRepository = pagamentoFaturaFornecedorRepository;
    }

    public List<PagamentoFaturaFornecedor> listarTodos() {
        return pagamentoFaturaFornecedorRepository.findAll();
    }

    public Optional<PagamentoFaturaFornecedor> procurarPorId(Integer id) {
        return pagamentoFaturaFornecedorRepository.findById(id);
    }

    public PagamentoFaturaFornecedor salvarPagamento(PagamentoFaturaFornecedor pagamentoFaturaFornecedor) {
        return pagamentoFaturaFornecedorRepository.save(pagamentoFaturaFornecedor);
    }

    public void excluirPagamento(Integer id) {
        pagamentoFaturaFornecedorRepository.deleteById(id);
    }

    public void realizarPagamento(Fornecedor fornecedor, double valor, LocalDate dtfatura) {
        PagamentoFaturaFornecedor pagamento = new PagamentoFaturaFornecedor();
        pagamento.setDtpagamento(LocalDate.now());
        pagamento.setValor(valor);
        pagamento.setDtfatura(dtfatura);
        pagamento.setIdforn(fornecedor);

        pagamentoFaturaFornecedorRepository.save(pagamento);
    }

    public Fornecedor buscarFornecedorPorId(Integer id) {
        Optional<Fornecedor> fornecedor = fornecedorService.procurarPorId(id);
        return fornecedor.orElse(null);
    }
}
