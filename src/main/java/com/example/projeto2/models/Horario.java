package com.example.projeto2.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "\"Horario\"")
public class Horario {
    @Id
    @Column(name = "idhorario", nullable = false)
    private Integer id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "horaini", nullable = false, length = Integer.MAX_VALUE)
    private String horaini;

    @Column(name = "horafim", nullable = false, length = Integer.MAX_VALUE)
    private String horafim;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idfunc", nullable = false)
    private Funcionario idfunc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHoraini() {
        return horaini;
    }

    public void setHoraini(String horaini) {
        this.horaini = horaini;
    }

    public String getHorafim() {
        return horafim;
    }

    public void setHorafim(String horafim) {
        this.horafim = horafim;
    }

    public Funcionario getIdfunc() {
        return idfunc;
    }

    public void setIdfunc(Funcionario idfunc) {
        this.idfunc = idfunc;
    }

}