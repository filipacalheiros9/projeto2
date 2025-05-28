package com.example.projeto2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Fornecedor\"")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfornecedor", nullable = false)
    private Integer id;

    @Column(name = "nomefornecedor", nullable = false, length = Integer.MAX_VALUE)
    private String nomefornecedor;

    @Column(name = "niffornecedor", nullable = false, length = Integer.MAX_VALUE)
    private String niffornecedor;

    @Column(name = "telefonefornecedor", nullable = false, length = Integer.MAX_VALUE)
    private String telefonefornecedor;

    @Column(name = "ruafornecedor", nullable = false, length = Integer.MAX_VALUE)
    private String ruafornecedor;

    @Column(name = "nportafornecedor", nullable = false)
    private Integer nportafornecedor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codpostalforn", nullable = false)
    private CodPostalFornecedor codpostalforn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomefornecedor() {
        return nomefornecedor;
    }

    public void setNomefornecedor(String nomefornecedor) {
        this.nomefornecedor = nomefornecedor;
    }

    public String getNiffornecedor() {
        return niffornecedor;
    }

    public void setNiffornecedor(String niffornecedor) {
        this.niffornecedor = niffornecedor;
    }

    public String getTelefonefornecedor() {
        return telefonefornecedor;
    }

    public void setTelefonefornecedor(String telefonefornecedor) {
        this.telefonefornecedor = telefonefornecedor;
    }

    public String getRuafornecedor() {
        return ruafornecedor;
    }

    public void setRuafornecedor(String ruafornecedor) {
        this.ruafornecedor = ruafornecedor;
    }

    public Integer getNportafornecedor() {
        return nportafornecedor;
    }

    public void setNportafornecedor(Integer nportafornecedor) {
        this.nportafornecedor = nportafornecedor;
    }

    public CodPostalFornecedor getCodpostalforn() {
        return codpostalforn;
    }

    public void setCodpostalforn(CodPostalFornecedor codpostalforn) {
        this.codpostalforn = codpostalforn;
    }

}