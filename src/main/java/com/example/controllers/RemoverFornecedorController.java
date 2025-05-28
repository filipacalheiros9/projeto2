package com.example.controllers;

import com.example.projeto2.models.Fornecedor;
import com.example.projeto2.services.FornecedorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RemoverFornecedorController {

    @FXML private TableView<Fornecedor> fornecedoresTable;
    @FXML private TableColumn<Fornecedor, Integer> idCol;
    @FXML private TableColumn<Fornecedor, String> nomeCol;
    @FXML private TableColumn<Fornecedor, String> nifCol;
    @FXML private TableColumn<Fornecedor, String> telefoneCol;
    @FXML private TableColumn<Fornecedor, String> ruaCol;
    @FXML private TableColumn<Fornecedor, Integer> portaCol;
    @FXML private TableColumn<Fornecedor, String> codPostalCol;

    @Autowired
    private FornecedorService fornecedorService;

    private ObservableList<Fornecedor> fornecedoresData;

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nomeCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNomefornecedor()));
        nifCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNiffornecedor()));
        telefoneCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTelefonefornecedor()));
        ruaCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getRuafornecedor()));
        portaCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getNportafornecedor()).asObject());
        codPostalCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCodpostalforn().getCodpostalfornecedor()));

        carregarFornecedores();
    }

    private void carregarFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.listarTodos();

        fornecedores.sort((f1, f2) -> f1.getNomefornecedor().compareToIgnoreCase(f2.getNomefornecedor()));

        fornecedoresData = FXCollections.observableArrayList(fornecedores);
        fornecedoresTable.setItems(fornecedoresData);
    }


    @FXML
    public void handleRemover() {
        Fornecedor selectedFornecedor = fornecedoresTable.getSelectionModel().getSelectedItem();

        if (selectedFornecedor != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Remoção");
            alert.setHeaderText("Deseja realmente remover o fornecedor?");
            alert.setContentText("Fornecedor: " + selectedFornecedor.getNomefornecedor());

            if (alert.showAndWait().get() == ButtonType.OK) {
                fornecedorService.excluir(selectedFornecedor.getId());
                fornecedoresData.remove(selectedFornecedor);

                new Alert(Alert.AlertType.INFORMATION, "Fornecedor removido com sucesso!").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Por favor, selecione um fornecedor para remover.").showAndWait();
        }
    }

    @FXML
    public void handleFechar() {
        Stage stage = (Stage) fornecedoresTable.getScene().getWindow();
        stage.close();
    }
}
