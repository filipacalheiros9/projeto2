package com.example.projeto2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"TipoFuncionario\"")
public class TipoFuncionario {
    @Id
    @Column(name = "idtipofuncionario", nullable = false)
    private Integer id;

    @Column(name = "cargo", nullable = false, length = Integer.MAX_VALUE)
    private String cargo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return cargo;
    }

}
