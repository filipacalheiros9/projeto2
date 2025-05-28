package com.example.projeto2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"TipoProjeto\"")
public class TipoProjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipoprojeto", nullable = false)
    private Integer id;

    @Column(name = "tipoprojeto", nullable = false, length = Integer.MAX_VALUE)
    private String tipoprojeto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoprojeto() {
        return tipoprojeto;
    }

    public void setTipoprojeto(String tipoprojeto) {
        this.tipoprojeto = tipoprojeto;
    }

}