package com.example.projeto2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Funcionario\"")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfuncionario", nullable = false)
    private Integer id;

    @Column(name = "nomefuncionario", nullable = false, length = Integer.MAX_VALUE)
    private String nomefuncionario;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idtipofunc", nullable = false)
    private TipoFuncionario idtipofunc;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomefuncionario() { return nomefuncionario; }
    public void setNomefuncionario(String nomefuncionario) { this.nomefuncionario = nomefuncionario; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public TipoFuncionario getIdtipofunc() { return idtipofunc; }
    public void setIdtipofunc(TipoFuncionario idtipofunc) { this.idtipofunc = idtipofunc; }

    @Override
    public String toString() {
        return nomefuncionario;
    }


}
