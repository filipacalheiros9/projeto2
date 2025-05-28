package com.example.projeto2.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "\"Servico\"")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idservico", nullable = false)
    private Integer id;

    @Column(name = "dataservico", nullable = false)
    private LocalDate dataservico;

    @Column(name = "precoservico", nullable = false)
    private Double precoservico;

    @Column(name = "estadoservico", nullable = false, length = Integer.MAX_VALUE)
    private String estadoservico;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idproj", nullable = false)
    private Projeto idproj;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idfuncio", nullable = false)
    private Funcionario idfuncio;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idtipoimpre", nullable = false)
    private TipoImpressao idtipoimpre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataservico() {
        return dataservico;
    }

    public void setDataservico(LocalDate dataservico) {
        this.dataservico = dataservico;
    }

    public Double getPrecoservico() {
        return precoservico;
    }

    public void setPrecoservico(Double precoservico) {
        this.precoservico = precoservico;
    }

    public String getEstadoservico() {
        return estadoservico;
    }

    public void setEstadoservico(String estadoservico) {
        this.estadoservico = estadoservico;
    }

    public Projeto getIdproj() {
        return idproj;
    }

    public void setIdproj(Projeto idproj) {
        this.idproj = idproj;
    }

    public Funcionario getIdfuncio() {
        return idfuncio;
    }

    public void setIdfuncio(Funcionario idfuncio) {
        this.idfuncio = idfuncio;
    }

    public TipoImpressao getIdtipoimpre() {
        return idtipoimpre;
    }

    public void setIdtipoimpre(TipoImpressao idtipoimpre) {
        this.idtipoimpre = idtipoimpre;
    }

}