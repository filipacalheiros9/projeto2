package com.example.projeto2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Material\"")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmaterial", nullable = false)
    private Integer id;

    @Column(name = "nomematerial", nullable = false, length = Integer.MAX_VALUE)
    private String nomematerial;

    @Column(name = "qtdstockmaterial", nullable = false)
    private Integer qtdstockmaterial;

    @Column(name = "precomaterial", nullable = false)
    private Double precomaterial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomematerial() {
        return nomematerial;
    }

    public void setNomematerial(String nomematerial) {
        this.nomematerial = nomematerial;
    }

    public Integer getQtdstockmaterial() {
        return qtdstockmaterial;
    }

    public void setQtdstockmaterial(Integer qtdstockmaterial) {
        this.qtdstockmaterial = qtdstockmaterial;
    }

    public Double getPrecomaterial() {
        return precomaterial;
    }

    public void setPrecomaterial(Double precomaterial) {
        this.precomaterial = precomaterial;
    }

}