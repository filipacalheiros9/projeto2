package com.example.controllers;

import com.example.projeto2.models.FaturaCliente;
import com.example.projeto2.repositories.FaturaClienteRepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.format.DateTimeFormatter;

@Controller
public class ListarPagamentosController {

    @FXML
    private TableView<FaturaCliente> pagamentosTable;

    @FXML
    private TableColumn<FaturaCliente, Integer> colId;

    @FXML
    private TableColumn<FaturaCliente, String> colData;

    @FXML
    private TableColumn<FaturaCliente, Double> colValor;

    @FXML
    private TableColumn<FaturaCliente, String> colServico;

    @Autowired
    private FaturaClienteRepository faturaClienteRepository;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getId()));
        colData.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(
                cell.getValue().getDatafatura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        colValor.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getValorfatura()));
        colServico.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(
                "Serviço #" + cell.getValue().getServico().getId()));

        pagamentosTable.setItems(FXCollections.observableArrayList(faturaClienteRepository.findAll()));
    }

    public void handleFecharPopup() {
        Stage stage = (Stage) pagamentosTable.getScene().getWindow();
        stage.close();
    }
}
