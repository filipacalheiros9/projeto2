package com.example.controllers;

import com.example.projeto2.models.LinhaEncomenda;
import com.example.projeto2.repositories.LinhaEncomendaRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CancelarEncomendaController {

    @FXML private ComboBox<LinhaEncomenda> comboEncomendas;

    @Autowired
    private LinhaEncomendaRepository linhaRepo;

    @FXML
    public void initialize() {
        List<LinhaEncomenda> encomendas = linhaRepo.findAll();
        encomendas.sort((e1, e2) -> e1.getIdmaterial().getNomematerial()
                .compareToIgnoreCase(e2.getIdmaterial().getNomematerial()));
        comboEncomendas.getItems().setAll(encomendas);

        comboEncomendas.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(LinhaEncomenda item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getIdmaterial().getNomematerial());
            }
        });
        comboEncomendas.setButtonCell(comboEncomendas.getCellFactory().call(null));
    }


    @FXML
    public void handleConfirmarCancelamento() {
        LinhaEncomenda selecionada = comboEncomendas.getValue();
        if (selecionada != null) {
            linhaRepo.delete(selecionada);
            new Alert(Alert.AlertType.INFORMATION, "Encomenda cancelada com sucesso!").showAndWait();
            Stage stage = (Stage) comboEncomendas.getScene().getWindow();
            stage.close();
        } else {
            new Alert(Alert.AlertType.WARNING, "Por favor, selecione uma encomenda.").showAndWait();
        }
    }


    @FXML
    private void handleCancelar() {
        Stage stage = (Stage) comboEncomendas.getScene().getWindow();
        stage.close();
    }
}

