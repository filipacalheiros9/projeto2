package com.example.controllers;

import com.example.projeto2.models.Servico;
import com.example.projeto2.services.ServicoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ListarServicosController {

    @FXML private TableView<Servico> servicosTable;
    @FXML private TableColumn<Servico, Integer> idCol;
    @FXML private TableColumn<Servico, String> dataCol;
    @FXML private TableColumn<Servico, Double> precoCol;
    @FXML private TableColumn<Servico, String> estadoCol;
    @FXML private TableColumn<Servico, String> nomeProjetoCol;
    @FXML private TableColumn<Servico, String> nomeFuncionarioCol;

    @Autowired
    private ServicoService servicoService;

    private ObservableList<Servico> servicosData;

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        dataCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDataservico().toString()));
        precoCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPrecoservico()).asObject());
        estadoCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEstadoservico()));
        nomeProjetoCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getIdproj().getTemaprojeto()));
        nomeFuncionarioCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getIdfuncio().getNomefuncionario()));

        carregarServicos();
    }

    private void carregarServicos() {
        List<Servico> servicos = servicoService.listarTodos();
        servicosData = FXCollections.observableArrayList(servicos);
        servicosTable.setItems(servicosData);
    }

    @FXML
    public void handleFechar() {
        Stage stage = (Stage) servicosTable.getScene().getWindow();
        stage.close();
    }
}
