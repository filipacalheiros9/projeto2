package com.example.projeto2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"CodPostalCliente\"")
public class CodPostalCliente {
    @Id
    @Column(name = "codpostalcliente", nullable = false, length = Integer.MAX_VALUE)
    private String codpostalcliente;

    @Column(name = "localidade", nullable = false, length = Integer.MAX_VALUE)
    private String localidade;

    public String getCodpostalcliente() {
        return codpostalcliente;
    }

    public void setCodpostalcliente(String codpostalcliente) {
        this.codpostalcliente = codpostalcliente;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }


}