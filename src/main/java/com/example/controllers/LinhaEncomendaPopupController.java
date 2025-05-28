package com.example.controllers;

import com.example.projeto2.services.LinhaEncomendaService;
import com.example.projeto2.models.LinhaEncomenda;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LinhaEncomendaPopupController {

    @Autowired
    private LinhaEncomendaService linhaService;

    @FXML private TableView<LinhaEncomenda> tableEncomendas;
    @FXML private TableColumn<LinhaEncomenda, String>  colFornecedor;
    @FXML private TableColumn<LinhaEncomenda, String>  colMaterial;
    @FXML private TableColumn<LinhaEncomenda, Integer> colQuantidade;
    @FXML private TableColumn<LinhaEncomenda, Double>  colValor;

    @FXML
    public void initialize() {
        colFornecedor .setCellValueFactory(new PropertyValueFactory<>("fornecedorName"));
        colMaterial   .setCellValueFactory(new PropertyValueFactory<>("materialName"));
        colQuantidade .setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colValor      .setCellValueFactory(new PropertyValueFactory<>("valor"));

        tableEncomendas.getItems().setAll(linhaService.listarTodos());
    }

    @FXML
    public void closePopup() {
        Stage stage = (Stage) tableEncomendas.getScene().getWindow();
        stage.close();
    }
}
