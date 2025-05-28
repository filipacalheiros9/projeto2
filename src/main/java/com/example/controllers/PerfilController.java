package com.example.controllers;

import com.example.projeto2.models.Funcionario;
import com.example.projeto2.services.FuncionarioService;
import com.example.utils.SessaoUtilizador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PerfilController {

    @FXML private TextField nomeField;
    @FXML private TextField usernameField;

    @Autowired private FuncionarioService funcionarioService;

    private Funcionario funcionarioAtual;

    @FXML private ComboBox<String> cargoBox;


    @FXML
    public void initialize() {
        funcionarioAtual = SessaoUtilizador.getFuncionario();

        cargoBox.getItems().addAll("Gerente", "Funcionário");

        if (funcionarioAtual != null) {
            nomeField.setText(funcionarioAtual.getNomefuncionario());
            usernameField.setText(funcionarioAtual.getUsername());

            String cargoAtual = funcionarioAtual.getIdtipofunc().getId() == 1 ? "Gerente" : "Funcionário";
            cargoBox.setValue(cargoAtual);
        }
    }


    @FXML
    public void salvarAlteracoes() {
        String novoNome = nomeField.getText();
        String novoUsername = usernameField.getText();
        String novoCargo = cargoBox.getValue();

        if (novoNome.isEmpty() || novoUsername.isEmpty() || novoCargo == null) {
            showAlert("Erro", "Todos os campos devem ser preenchidos", Alert.AlertType.WARNING);
            return;
        }

        funcionarioAtual.setNomefuncionario(novoNome);
        funcionarioAtual.setUsername(novoUsername);

        int novoTipoId = novoCargo.equals("Gerente") ? 1 : 2;
        funcionarioAtual.getIdtipofunc().setId(novoTipoId);

        try {
            funcionarioService.atualizar(funcionarioAtual.getId(), funcionarioAtual);
            showAlert("Sucesso", "Dados atualizados com sucesso!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Erro", "Falha ao atualizar: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    private void showAlert(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
