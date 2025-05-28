package com.example.projeto2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"LinhaEncomenda\"")
public class LinhaEncomenda {
    @EmbeddedId
    private LinhaEncomendaId id;

    @MapsId("idmaterial")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)  // mudou para EAGER
    @JoinColumn(name = "idmaterial", nullable = false)
    private Material idmaterial;

    @MapsId("idfornecedor")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)  // mudou para EAGER
    @JoinColumn(name = "idfornecedor", nullable = false)
    private Fornecedor idfornecedor;

    @Column(name = "qtdencomendada", nullable = false)
    private Integer qtdencomendada;

    @Column(name = "valorencomendado", nullable = false)
    private Double valorencomendado;

    public LinhaEncomendaId getId() {
        return id;
    }

    public void setId(LinhaEncomendaId id) {
        this.id = id;
    }

    public Material getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Material idmaterial) {
        this.idmaterial = idmaterial;
    }

    public Fornecedor getIdfornecedor() {
        return idfornecedor;
    }

    public void    setIdfornecedor(Fornecedor idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public Integer getQtdencomendada() {
        return qtdencomendada;
    }

    public void setQtdencomendada(Integer qtdencomendada) {
        this.qtdencomendada = qtdencomendada;
    }

    public Double getValorencomendado() {
        return valorencomendado;
    }

    public void setValorencomendado(Double valorencomendado) {
        this.valorencomendado = valorencomendado;
    }

    public String getFornecedorName() {
        return idfornecedor.getNomefornecedor();
    }

    public String getMaterialName() {
        return idmaterial.getNomematerial();
    }

    public Integer getQuantidade() {
        return qtdencomendada;
    }

    public Double getValor() {
        return valorencomendado;
    }

    @Override
    public String toString() {
        return idmaterial.getNomematerial() + " (Qtd: " + qtdencomendada + ")";
    }


}