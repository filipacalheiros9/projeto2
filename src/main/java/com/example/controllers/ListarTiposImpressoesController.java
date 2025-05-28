package com.example.controllers;

import com.example.projeto2.models.TipoImpressao;
import com.example.projeto2.services.TipoImpressaoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.List;

@Controller
public class ListarTiposImpressoesController {

    @FXML
    private ListView<String> tiposImpressaoListView;

    private final TipoImpressaoService tipoImpressaoService;

    @Autowired
    public ListarTiposImpressoesController(TipoImpressaoService tipoImpressaoService) {
        this.tipoImpressaoService = tipoImpressaoService;
    }

    @FXML
    public void initialize() {
        List<TipoImpressao> tiposImpressao = tipoImpressaoService.listarTodos();

        tiposImpressao.sort(Comparator.comparing(TipoImpressao::getTipoimpressao));

        ObservableList<String> items = FXCollections.observableArrayList();
        for (TipoImpressao tipoImpressao : tiposImpressao) {
            items.add(tipoImpressao.getTipoimpressao());
        }
        tiposImpressaoListView.setItems(items);
    }

    @FXML
    public void handleFecharJanela() {
        Stage stage = (Stage) tiposImpressaoListView.getScene().getWindow();
        stage.close();
    }
}
