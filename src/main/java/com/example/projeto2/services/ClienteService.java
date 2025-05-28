package com.example.projeto2.services;

import com.example.projeto2.models.Cliente;
import com.example.projeto2.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> procurarPorId(Integer id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        if (clienteSalvo.getId() != null) {
            System.out.println("Cliente salvo com sucesso: " + clienteSalvo);
        } else {
            System.out.println("Erro ao salvar cliente.");
        }
        return clienteSalvo;
    }

    @Transactional
    public Cliente atualizar(Integer id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNomecliente(clienteAtualizado.getNomecliente());
            cliente.setTelefonecliente(clienteAtualizado.getTelefonecliente());
            cliente.setRuacliente(clienteAtualizado.getRuacliente());
            cliente.setNportacliente(clienteAtualizado.getNportacliente());
            cliente.setNifcliente(clienteAtualizado.getNifcliente());
            cliente.setCodpostalcli(clienteAtualizado.getCodpostalcli());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }

    @Transactional
    public void excluir(Integer id) {
        clienteRepository.deleteById(id);
    }
}
