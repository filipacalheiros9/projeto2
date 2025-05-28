package com.example.controllers;

import com.example.projeto2.models.TipoProjeto;
import com.example.projeto2.services.TipoProjetoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.List;

@Controller
public class ListarTiposProjetosController {

    @FXML
    private ListView<String> tiposProjetoListView;

    @Autowired
    private TipoProjetoService tipoProjetoService;

    @FXML
    public void initialize() {
        List<TipoProjeto> tiposProjetos = tipoProjetoService.listarTodos();

        tiposProjetos.sort(Comparator.comparing(TipoProjeto::getTipoprojeto));

        ObservableList<String> items = FXCollections.observableArrayList();
        for (TipoProjeto tipoProjeto : tiposProjetos) {
            items.add(tipoProjeto.getTipoprojeto());
        }
        tiposProjetoListView.setItems(items);
    }

    @FXML
    private void handleFechar() {
        Stage stage = (Stage) tiposProjetoListView.getScene().getWindow();
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
