package com.example.controllers;

import com.example.projeto2.models.PagamentoFaturaFornecedor;
import com.example.projeto2.services.PagamentoFaturaFornecedorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ListarPagamentosFornecedoresController {

    @FXML private TableView<PagamentoFaturaFornecedor> pagamentosTable;
    @FXML private TableColumn<PagamentoFaturaFornecedor, Integer> idCol;
    @FXML private TableColumn<PagamentoFaturaFornecedor, String> fornecedorCol;
    @FXML private TableColumn<PagamentoFaturaFornecedor, Double> valorCol;
    @FXML private TableColumn<PagamentoFaturaFornecedor, String> dataFaturaCol;
    @FXML private TableColumn<PagamentoFaturaFornecedor, String> dataPagamentoCol;

    @Autowired
    private PagamentoFaturaFornecedorService pagamentoFaturaFornecedorService;

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        fornecedorCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getIdforn().getNomefornecedor()));
        valorCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getValor()).asObject());
        dataFaturaCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDtfatura().toString()));
        dataPagamentoCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDtpagamento().toString()));

        carregarPagamentos();
    }

    private void carregarPagamentos() {
        List<PagamentoFaturaFornecedor> pagamentos = pagamentoFaturaFornecedorService.listarTodos();

        pagamentos.sort((p1, p2) -> p1.getIdforn().getNomefornecedor().compareToIgnoreCase(p2.getIdforn().getNomefornecedor()));

        ObservableList<PagamentoFaturaFornecedor> data = FXCollections.observableArrayList(pagamentos);
        pagamentosTable.setItems(data);
    }


    @FXML
    public void handleFechar() {
        // Fecha a janela
        Stage stage = (Stage) pagamentosTable.getScene().getWindow();
        stage.close();
    }
}
