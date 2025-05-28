package com.example.projeto2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"ProjetoMaterial\"")
public class ProjetoMaterial {
    @EmbeddedId
    private ProjetoMaterialId id;

    @MapsId("idmaterial")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idmaterial", nullable = false)
    private Material idmaterial;

    @MapsId("idprojeto")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idprojeto", nullable = false)
    private Projeto idprojeto;

    public ProjetoMaterialId getId() {
        return id;
    }

    public void setId(ProjetoMaterialId id) {
        this.id = id;
    }

    public Material getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Material idmaterial) {
        this.idmaterial = idmaterial;
    }

    public Projeto getIdprojeto() {
        return idprojeto;
    }

    public void setIdprojeto(Projeto idprojeto) {
        this.idprojeto = idprojeto;
    }

}