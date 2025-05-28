package com.example.controllers;

import com.example.projeto2.models.TipoProjeto;
import com.example.projeto2.services.TipoProjetoService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RemoverTipoProjetoController {

    @FXML
    private ComboBox<TipoProjeto> tipoProjetoComboBox;

    @FXML
    private Button removerTipoProjetoButton;

    @Autowired
    private TipoProjetoService tipoProjetoService;

    @FXML
    public void initialize() {
        var tiposProjetos = tipoProjetoService.listarTodos();
        tiposProjetos.sort((t1, t2) -> t1.getTipoprojeto().compareToIgnoreCase(t2.getTipoprojeto()));

        tipoProjetoComboBox.getItems().setAll(tiposProjetos);

        tipoProjetoComboBox.setCellFactory(param -> new javafx.scene.control.ListCell<TipoProjeto>() {
            @Override
            protected void updateItem(TipoProjeto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getTipoprojeto());
            }
        });

        tipoProjetoComboBox.setButtonCell(tipoProjetoComboBox.getCellFactory().call(null));
    }


    @FXML
    private void handleRemoverTipoProjeto() {
        TipoProjeto tipoProjetoSelecionado = tipoProjetoComboBox.getValue();

        if (tipoProjetoSelecionado == null) {
            showAlert("Erro", "Por favor, selecione um tipo de projeto para remover.", AlertType.WARNING);
        } else {
            try {
                tipoProjetoService.excluirTipoProjeto(tipoProjetoSelecionado.getId());
                showAlert("Sucesso", "Tipo de projeto removido com sucesso!", AlertType.INFORMATION);

                Stage stage = (Stage) tipoProjetoComboBox.getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                showAlert("Erro", "Não foi possível remover o tipo de projeto: " + e.getMessage(), AlertType.ERROR);
            }
        }
    }


    @FXML
    private void handleFechar() {
        Stage stage = (Stage) tipoProjetoComboBox.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
