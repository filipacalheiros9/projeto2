package com.example.projeto2.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "\"FaturaCliente\"")
public class FaturaCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfaturacliente", nullable = false)
    private Integer id;

    @Column(name = "datafatura", nullable = false)
    private LocalDate datafatura;

    @Column(name = "valorfatura", nullable = false)
    private Double valorfatura;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idservic", nullable = false)
    private Servico servic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDatafatura() {
        return datafatura;
    }

    public void setDatafatura(LocalDate datafatura) {
        this.datafatura = datafatura;
    }

    public Double getValorfatura() {
        return valorfatura;
    }

    public void setValorfatura(Double valorfatura) {
        this.valorfatura = valorfatura;
    }

    public Servico getServico() {
        return servic;
    }

    public void setServico(Servico servic) {
        this.servic = servic;
    }
}
