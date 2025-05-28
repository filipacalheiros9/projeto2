package com.example.projeto2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Cliente\"")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente", nullable = false)
    private Integer id;

    @Column(name = "nomecliente", nullable = false, length = Integer.MAX_VALUE)
    private String nomecliente;

    @Column(name = "telefonecliente", nullable = false, length = Integer.MAX_VALUE)
    private String telefonecliente;

    @Column(name = "ruacliente", nullable = false, length = Integer.MAX_VALUE)
    private String ruacliente;

    @Column(name = "nportacliente", nullable = false)
    private Integer nportacliente;

    @Column(name = "nifcliente", nullable = false, length = Integer.MAX_VALUE)
    private String nifcliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codpostalcli", nullable = false)
    private CodPostalCliente codpostalcli;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "pass", nullable = false)
    private String pass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public String getTelefonecliente() {
        return telefonecliente;
    }

    public void setTelefonecliente(String telefonecliente) {
        this.telefonecliente = telefonecliente;
    }

    public String getRuacliente() {
        return ruacliente;
    }

    public void setRuacliente(String ruacliente) {
        this.ruacliente = ruacliente;
    }

    public Integer getNportacliente() {
        return nportacliente;
    }

    public void setNportacliente(Integer nportacliente) {
        this.nportacliente = nportacliente;
    }

    public String getNifcliente() {
        return nifcliente;
    }

    public void setNifcliente(String nifcliente) {
        this.nifcliente = nifcliente;
    }

    public CodPostalCliente getCodpostalcli() {
        return codpostalcli;
    }

    public void setCodpostalcli(CodPostalCliente codpostalcli) {
        this.codpostalcli = codpostalcli;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
