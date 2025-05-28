package com.example.projeto2.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "\"PagamentoFaturaFornecedor\"")
public class PagamentoFaturaFornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpagamento", nullable = false)
    private Integer id;

    @Column(name = "dtpagamento", nullable = false)
    private LocalDate dtpagamento;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "dtfatura", nullable = false)
    private LocalDate dtfatura;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idforn", nullable = false)
    private Fornecedor idforn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDtpagamento() {
        return dtpagamento;
    }

    public void setDtpagamento(LocalDate dtpagamento) {
        this.dtpagamento = dtpagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDtfatura() {
        return dtfatura;
    }

    public void setDtfatura(LocalDate dtfatura) {
        this.dtfatura = dtfatura;
    }

    public Fornecedor getIdforn() {
        return idforn;
    }

    public void setIdforn(Fornecedor idforn) {
        this.idforn = idforn;
    }

}