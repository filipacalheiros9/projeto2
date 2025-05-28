package com.example.controllers;

import com.example.projeto2.models.TipoImpressao;
import com.example.projeto2.services.TipoImpressaoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RemoverTipoImpressaoController {

    @FXML
    private ComboBox<TipoImpressao> tipoImpressaoComboBox;

    @Autowired
    private TipoImpressaoService tipoImpressaoService;

    public void initialize() {
        List<TipoImpressao> tiposImpressao = tipoImpressaoService.listarTodos();

        tiposImpressao.sort((t1, t2) -> t1.getTipoimpressao().compareToIgnoreCase(t2.getTipoimpressao()));

        tipoImpressaoComboBox.setItems(FXCollections.observableArrayList(tiposImpressao));
    }

    @FXML
    private void handleRemoverTipoImpressao() {
        TipoImpressao tipoImpressaoSelecionado = tipoImpressaoComboBox.getSelectionModel().getSelectedItem();

        if (tipoImpressaoSelecionado == null) {
            showAlert("Erro", "Por favor, selecione um tipo de impressão para remover.", AlertType.ERROR);
            return;
        }

        try {
            tipoImpressaoService.excluirTipoImpressao(tipoImpressaoSelecionado.getId());
            showAlert("Sucesso", "Tipo de Impressão removido com sucesso!", AlertType.INFORMATION);

            Stage stage = (Stage) tipoImpressaoComboBox.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            showAlert("Erro", "Erro ao remover tipo de impressão: " + e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }


    @FXML
    private void handleFechar() {
        Stage stage = (Stage) tipoImpressaoComboBox.getScene().getWindow();
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
