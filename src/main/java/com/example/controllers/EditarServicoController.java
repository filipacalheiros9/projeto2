package com.example.controllers;

import com.example.projeto2.models.Servico;
import com.example.projeto2.repositories.ServicoRepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EditarServicoController {

    @FXML
    private ComboBox<Servico> comboServico;

    @FXML
    private TextField txtEstado;

    @FXML
    private TextField txtPreco;

    @FXML
    private Label lblMensagem;

    @Autowired
    private ServicoRepository servicoRepo;

    @FXML
    public void initialize() {
        List<Servico> servicos = servicoRepo.findAll();
        comboServico.getItems().setAll(servicos);

        comboServico.setConverter(new StringConverter<>() {
            @Override
            public String toString(Servico servico) {
                return (servico != null) ? String.valueOf(servico.getId()) : "";
            }

            @Override
            public Servico fromString(String string) {
                return comboServico.getItems().stream()
                        .filter(s -> String.valueOf(s.getId()).equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });
    }

    @FXML
    public void handleAtualizar() {
        Servico servico = comboServico.getValue();
        if (servico == null) {
            lblMensagem.setText("Selecione um serviço.");
            return;
        }

        try {
            String novoEstado = txtEstado.getText().trim();
            if (!novoEstado.isEmpty()) {
                servico.setEstadoservico(novoEstado);
            }

            String precoInput = txtPreco.getText().trim();
            if (!precoInput.isEmpty()) {
                servico.setPrecoservico(Double.parseDouble(precoInput));
            }

            servicoRepo.save(servico);
            lblMensagem.setText("Serviço atualizado com sucesso!");
        } catch (Exception e) {
            lblMensagem.setText("Erro ao atualizar serviço: " + e.getMessage());
        }
    }

    @FXML
    public void handleFechar() {
        Stage stage = (Stage) comboServico.getScene().getWindow();
        stage.close();
    }
}
