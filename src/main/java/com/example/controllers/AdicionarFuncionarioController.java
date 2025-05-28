package com.example.controllers;

import com.example.projeto2.models.Funcionario;
import com.example.projeto2.models.TipoFuncionario;
import com.example.projeto2.services.FuncionarioService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdicionarFuncionarioController {

    @FXML private TextField nomeField;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private ComboBox<TipoFuncionario> cargoComboBox;

    private Stage dialogStage;

    private final FuncionarioService funcionarioService;

    @Autowired
    public AdicionarFuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public void setCargos(List<TipoFuncionario> cargos) {
        cargoComboBox.setItems(FXCollections.observableArrayList(cargos));
    }

    @FXML
    private void cancelar() {
        dialogStage.close();
    }

    @FXML
    private void adicionarFuncionario() {
        String nome = nomeField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        TipoFuncionario cargoSelecionado = cargoComboBox.getSelectionModel().getSelectedItem();

        if (nome.isEmpty() || username.isEmpty() || password.isEmpty() || cargoSelecionado == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos.");
            alert.showAndWait();
            return;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNomefuncionario(nome);
        funcionario.setUsername(username);
        funcionario.setPassword(password);
        funcionario.setIdtipofunc(cargoSelecionado);

        try {
            funcionarioService.salvar(funcionario);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Funcionário adicionado com sucesso!");
            alert.showAndWait();

            dialogStage.close();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Falha ao adicionar funcionário: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
