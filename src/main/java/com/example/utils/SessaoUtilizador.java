package com.example.utils;

import com.example.projeto2.models.Funcionario;

public class SessaoUtilizador {
    private static Funcionario funcionario;

    public static void setFuncionario(Funcionario f) {
        funcionario = f;
    }

    public static Funcionario getFuncionario() {
        return funcionario;
    }

    public static void limpar() {
        funcionario = null;
    }
}
