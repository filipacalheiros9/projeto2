package com.example.projeto2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"CodPostalFornecedor\"")
public class CodPostalFornecedor {
    @Id
    @Column(name = "codpostalfornecedor", nullable = false, length = Integer.MAX_VALUE)
    private String codpostalfornecedor;

    @Column(name = "localidade", nullable = false, length = Integer.MAX_VALUE)
    private String localidade;

    public String getCodpostalfornecedor() {
        return codpostalfornecedor;
    }

    public void setCodpostalfornecedor(String codpostalfornecedor) {
        this.codpostalfornecedor = codpostalfornecedor;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

}