package com.example.projeto2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LinhaEncomendaId implements Serializable {

    @Column(name = "idmaterial", nullable = false)
    private Integer idmaterial;

    @Column(name = "idfornecedor", nullable = false)
    private Integer idfornecedor;

    public LinhaEncomendaId() {
    }

    public LinhaEncomendaId(Integer idmaterial, Integer idfornecedor) {
        this.idmaterial = idmaterial;
        this.idfornecedor = idfornecedor;
    }

    public Integer getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Integer idmaterial) {
        this.idmaterial = idmaterial;
    }

    public Integer getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(Integer idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinhaEncomendaId that = (LinhaEncomendaId) o;
        return idmaterial.equals(that.idmaterial) && idfornecedor.equals(that.idfornecedor);
    }

    @Override
    public int hashCode() {
        return 31 * idmaterial.hashCode() + idfornecedor.hashCode();
    }
}
