package com.example.controllers;

import com.example.projeto2.models.Funcionario;
import com.example.projeto2.services.FuncionarioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class RemoverFuncionarioController {

    private final FuncionarioService funcionarioService;

    private Stage dialogStage;

    @FXML
    private ComboBox<Funcionario> funcionarioComboBox;

    private ObservableList<Funcionario> funcionarios;

    public RemoverFuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @FXML
    public void initialize() {
        funcionarios = FXCollections.observableArrayList(funcionarioService.listarTodos());
        funcionarioComboBox.setItems(funcionarios);

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
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void removerSelecionado() {
        Funcionario selecionado = funcionarioComboBox.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
            alerta.setTitle("Aviso");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, selecione um funcionário para remover.");
            alerta.showAndWait();
            return;
        }

        try {
            funcionarioService.excluir(selecionado.getId());
            funcionarios.remove(selecionado);
            funcionarioComboBox.getSelectionModel().clearSelection();

            javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alerta.setTitle("Sucesso");
            alerta.setHeaderText(null);
            alerta.setContentText("Funcionário removido com sucesso!");
            alerta.showAndWait();

            fechar();

        } catch (Exception e) {
            javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText(null);
            alerta.setContentText("Erro ao remover funcionário: " + e.getMessage());
            alerta.showAndWait();
        }
    }


    @FXML
    private void fechar() {
        if (dialogStage != null) {
            dialogStage.close();
        }
    }
}
