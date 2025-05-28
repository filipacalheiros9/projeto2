package com.example.controllers;

import com.example.projeto2.models.Funcionario;
import com.example.projeto2.models.TipoFuncionario;
import com.example.projeto2.services.FuncionarioService;
import com.example.projeto2.services.TipoFuncionarioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class EditarFuncionarioController {

    private final FuncionarioService funcionarioService;
    private final TipoFuncionarioService tipoFuncionarioService;
    private Stage dialogStage;

    @FXML
    private ComboBox<Funcionario> funcionarioComboBox;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private ComboBox<TipoFuncionario> cargoComboBox;

    private ObservableList<Funcionario> funcionarios;
    private ObservableList<TipoFuncionario> cargos;

    public EditarFuncionarioController(FuncionarioService funcionarioService, TipoFuncionarioService tipoFuncionarioService) {
        this.funcionarioService = funcionarioService;
        this.tipoFuncionarioService = tipoFuncionarioService;
    }

    @FXML
    public void initialize() {
        funcionarios = FXCollections.observableArrayList(funcionarioService.listarTodos());
        funcionarioComboBox.setItems(funcionarios);

        cargos = FXCollections.observableArrayList(tipoFuncionarioService.listarTodos());
        cargoComboBox.setItems(cargos);

        funcionarioComboBox.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Funcionario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNomefuncionario());
            }
        });
        funcionarioComboBox.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Funcionario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNomefuncionario());
            }
        });

        cargoComboBox.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(TipoFuncionario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCargo());
            }
        });
        cargoComboBox.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(TipoFuncionario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCargo());
            }
        });

        funcionarioComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                nomeField.setText(newVal.getNomefuncionario());
                usernameField.setText(newVal.getUsername());
                passwordField.setText(newVal.getPassword());
                cargos.stream()
                        .filter(c -> c.getId().equals(newVal.getIdtipofunc().getId()))
                        .findFirst()
                        .ifPresent(cargoComboBox::setValue);
            } else {
                nomeField.clear();
                usernameField.clear();
                passwordField.clear();
                cargoComboBox.getSelectionModel().clearSelection();
            }
        });
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void salvarEdicao() {
        Funcionario selecionado = funcionarioComboBox.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um funcionário para editar.");
            return;
        }
        if (nomeField.getText().isEmpty() || usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || cargoComboBox.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Preencha todos os campos.");
            return;
        }
        try {
            selecionado.setNomefuncionario(nomeField.getText());
            selecionado.setUsername(usernameField.getText());
            selecionado.setPassword(passwordField.getText());
            selecionado.setIdtipofunc(cargoComboBox.getValue());
            funcionarioService.atualizar(selecionado.getId(), selecionado);
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Funcionário atualizado com sucesso!");
            fechar();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    @FXML
    private void fechar() {
        if (dialogStage != null) {
            dialogStage.close();
        }
    }

    private void showAlert(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
