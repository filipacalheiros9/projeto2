package com.example.projeto2.services;

import com.example.projeto2.models.Funcionario;
import com.example.projeto2.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> procurarPorId(Integer id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario salvar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Integer id, Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionario.setNomefuncionario(funcionarioAtualizado.getNomefuncionario());
            funcionario.setUsername(funcionarioAtualizado.getUsername());
            funcionario.setPassword(funcionarioAtualizado.getPassword()); // <-- Adicione isso
            funcionario.setIdtipofunc(funcionarioAtualizado.getIdtipofunc());
            return funcionarioRepository.save(funcionario);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));
    }


    public void excluir(Integer id) {
        funcionarioRepository.deleteById(id);
    }

    public Optional<Funcionario> login(String username, String password) {
        return funcionarioRepository.findByUsernameAndPassword(username, password);
    }



}
