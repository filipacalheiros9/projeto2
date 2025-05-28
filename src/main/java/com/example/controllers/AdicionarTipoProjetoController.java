package com.example.controllers;

import com.example.projeto2.models.TipoProjeto;
import com.example.projeto2.repositories.TipoProjetoRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdicionarTipoProjetoController {

    @FXML private TextField tipoprojetoField;

    @Autowired
    private TipoProjetoRepository tipoProjetoRepository;

    @FXML
    public void handleSalvarTipoProjeto() {
        String tipoProjeto = tipoprojetoField.getText();

        if (tipoProjeto.isEmpty()) {
            showAlert("Erro", "O nome do tipo de projeto não pode ser vazio.", AlertType.ERROR);
            return;
        }

        TipoProjeto novoTipoProjeto = new TipoProjeto();
        novoTipoProjeto.setTipoprojeto(tipoProjeto);

        try {
            tipoProjetoRepository.save(novoTipoProjeto);
            showAlert("Sucesso", "Tipo de projeto adicionado com sucesso!", AlertType.INFORMATION);
            closeWindow();
        } catch (Exception e) {
            showAlert("Erro", "Erro ao salvar tipo de projeto: " + e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    public void handleCancelar() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) tipoprojetoField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message, AlertType type) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }
}
