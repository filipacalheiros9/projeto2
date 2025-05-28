package com.example.projeto2.services;

import com.example.projeto2.models.Horario;
import com.example.projeto2.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> listarTodos() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> procurarPorId(Integer id) {
        return horarioRepository.findById(id);
    }

    public Horario salvar(Horario horario) {
        return horarioRepository.save(horario);
    }

    public Horario atualizar(Integer id, Horario horarioAtualizado) {
        return horarioRepository.findById(id).map(horario -> {
            horario.setData(horarioAtualizado.getData());
            horario.setHoraini(horarioAtualizado.getHoraini());
            horario.setHorafim(horarioAtualizado.getHorafim());
            horario.setIdfunc(horarioAtualizado.getIdfunc());
            return horarioRepository.save(horario);
        }).orElseThrow(() -> new RuntimeException("Horário não encontrado!"));
    }

    public void excluir(Integer id) {
        horarioRepository.deleteById(id);
    }
}
