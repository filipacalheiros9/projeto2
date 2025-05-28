package com.example.projeto2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"TipoImpressao\"")
public class TipoImpressao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipoimpressao", nullable = false)
    private Integer id;

    @Column(name = "tipoimpressao", nullable = false, length = Integer.MAX_VALUE)
    private String tipoimpressao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoimpressao() {
        return tipoimpressao;
    }

    public void setTipoimpressao(String tipoimpressao) {
        this.tipoimpressao = tipoimpressao;
    }

    @Override
    public String toString() {
        return tipoimpressao;
    }

}