package com.example.projeto2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjetoMaterialId implements Serializable {
    private static final long serialVersionUID = 8719819160992480219L;
    @Column(name = "idmaterial", nullable = false)
    private Integer idmaterial;

    @Column(name = "idprojeto", nullable = false)
    private Integer idprojeto;

    public Integer getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Integer idmaterial) {
        this.idmaterial = idmaterial;
    }

    public Integer getIdprojeto() {
        return idprojeto;
    }

    public void setIdprojeto(Integer idprojeto) {
        this.idprojeto = idprojeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProjetoMaterialId entity = (ProjetoMaterialId) o;
        return Objects.equals(this.idmaterial, entity.idmaterial) &&
                Objects.equals(this.idprojeto, entity.idprojeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmaterial, idprojeto);
    }

}