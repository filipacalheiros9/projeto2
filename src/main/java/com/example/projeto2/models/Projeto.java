package com.example.projeto2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Projeto\"")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprojeto", nullable = false)
    private Integer id;

    @Column(name = "temaprojeto", nullable = false, length = Integer.MAX_VALUE)
    private String temaprojeto;

    @Column(name = "tipoletra", nullable = false, length = Integer.MAX_VALUE)
    private String tipoletra;

    @Column(name = "tamanho", nullable = false, length = Integer.MAX_VALUE)
    private String tamanho;

    @Column(name = "cores", nullable = false, length = Integer.MAX_VALUE)
    private String cores;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idclien", nullable = false)
    private Cliente idclien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtipoproj", nullable = false)
    private TipoProjeto idtipoproj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemaprojeto() {
        return temaprojeto;
    }

    public void setTemaprojeto(String temaprojeto) {
        this.temaprojeto = temaprojeto;
    }

    public String getTipoletra() {
        return tipoletra;
    }

    public void setTipoletra(String tipoletra) {
        this.tipoletra = tipoletra;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Cliente getIdclien() {
        return idclien;
    }

    public void setIdclien(Cliente idclien) {
        this.idclien = idclien;
    }

    public TipoProjeto getIdtipoproj() {
        return idtipoproj;
    }

    public void setIdtipoproj(TipoProjeto idtipoproj) {
        this.idtipoproj = idtipoproj;
    }

    public String getCores() {
        return cores;
    }
    public void setCores(String cores) {
        this.cores = cores;
    }

    @Override
    public String toString() {
        return "Projeto #" + id + " - " + temaprojeto;
    }

}