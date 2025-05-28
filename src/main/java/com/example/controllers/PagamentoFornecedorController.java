package com.example.controllers;

import com.example.projeto2.models.Fornecedor;
import com.example.projeto2.services.PagamentoFaturaFornecedorService;
import com.example.projeto2.services.FornecedorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PagamentoFornecedorController {

    @FXML private ComboBox<Fornecedor> fornecedoresComboBox;
    @FXML private TextField valorPagamentoField;
    @FXML private DatePicker dataFaturaField;

    @Autowired
    private PagamentoFaturaFornecedorService pagamentoService;

    @Autowired
    private FornecedorService fornecedorService;

    @FXML
    public void initialize() {
        carregarFornecedores();
    }

    private void carregarFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.listarTodos();
        fornecedores.sort((f1, f2) -> f1.getNomefornecedor().compareToIgnoreCase(f2.getNomefornecedor()));
        ObservableList<Fornecedor> fornecedoresData = FXCollections.observableArrayList(fornecedores);

        fornecedoresComboBox.setItems(fornecedoresData);

        fornecedoresComboBox.setCellFactory(param -> new ListCell<Fornecedor>() {
            @Override
            protected void updateItem(Fornecedor item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getNomefornecedor());
                }
            }
        });

        fornecedoresComboBox.setButtonCell(new ListCell<Fornecedor>() {
            @Override
            protected void updateItem(Fornecedor item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getNomefornecedor());
                }
            }
        });
    }



    @FXML
    public void handleConfirmarPagamento() {
        Fornecedor selectedFornecedor = fornecedoresComboBox.getSelectionModel().getSelectedItem();
        String valorPagamento = valorPagamentoField.getText();
        LocalDate dtfatura = dataFaturaField.getValue();

        if (selectedFornecedor != null && !valorPagamento.isEmpty() && dtfatura != null) {
            try {
                double valor = Double.parseDouble(valorPagamento);

                pagamentoService.realizarPagamento(selectedFornecedor, valor, dtfatura);

                new Alert(Alert.AlertType.INFORMATION, "Pagamento realizado com sucesso!").showAndWait();

                Stage stage = (Stage) fornecedoresComboBox.getScene().getWindow();
                stage.close();
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Por favor, insira um valor válido para o pagamento.").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Por favor, preencha todos os campos corretamente.").showAndWait();
        }
    }

    @FXML
    public void handleFechar() {
        Stage stage = (Stage) fornecedoresComboBox.getScene().getWindow();
        stage.close();
    }
}
