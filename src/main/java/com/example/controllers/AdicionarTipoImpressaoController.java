package com.example.controllers;

import com.example.projeto2.models.TipoImpressao;
import com.example.projeto2.services.TipoImpressaoService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdicionarTipoImpressaoController {

    @FXML
    private TextField tipoImpressaoTextField;

    @Autowired
    private TipoImpressaoService tipoImpressaoService;

    @FXML
    private void handleAdicionarTipoImpressao() {
        String tipoImpressao = tipoImpressaoTextField.getText().trim();
        if (tipoImpressao.isEmpty()) {
            showAlert("Erro", "O campo 'Tipo de Impressão' não pode ser vazio!", AlertType.ERROR);
            return;
        }

        TipoImpressao novoTipoImpressao = new TipoImpressao();
        novoTipoImpressao.setTipoimpressao(tipoImpressao);

        try {
            tipoImpressaoService.salvarTipoImpressao(novoTipoImpressao);
            showAlert("Sucesso", "Tipo de Impressão adicionado com sucesso!", AlertType.INFORMATION);

            Stage stage = (Stage) tipoImpressaoTextField.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            showAlert("Erro", "Erro ao adicionar tipo de impressão: " + e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }


    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleCancelar() {
        Stage stage = (Stage) tipoImpressaoTextField.getScene().getWindow();
        stage.close();
    }
}
