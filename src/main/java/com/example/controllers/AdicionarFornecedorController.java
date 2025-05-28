package com.example.controllers;

import com.example.projeto2.models.CodPostalFornecedor;
import com.example.projeto2.models.Fornecedor;
import com.example.projeto2.services.CodPostalFornecedorService;
import com.example.projeto2.services.FornecedorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdicionarFornecedorController {

    @FXML private TextField nomeField;
    @FXML private TextField nifField;
    @FXML private TextField telefoneField;
    @FXML private TextField ruaField;
    @FXML private TextField portaField;
    @FXML private ComboBox<CodPostalFornecedor> codPostalComboBox;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private CodPostalFornecedorService codPostalFornecedorService;

    @FXML
    public void initialize() {
        List<CodPostalFornecedor> codigos = codPostalFornecedorService.listarTodos();
        codigos.sort((c1, c2) -> c1.getCodpostalfornecedor().compareToIgnoreCase(c2.getCodpostalfornecedor()));
        ObservableList<CodPostalFornecedor> listaOrdenada = FXCollections.observableArrayList(codigos);
        codPostalComboBox.setItems(listaOrdenada);

        codPostalComboBox.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(CodPostalFornecedor item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCodpostalfornecedor());
            }
        });
        codPostalComboBox.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(CodPostalFornecedor item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCodpostalfornecedor());
            }
        });
    }

    @FXML
    public void handleSalvar() {
        try {
            if (nomeField.getText().isEmpty() || nifField.getText().isEmpty() || telefoneField.getText().isEmpty() ||
                    ruaField.getText().isEmpty() || portaField.getText().isEmpty() || codPostalComboBox.getValue() == null) {
                mostrarErro("Todos os campos devem ser preenchidos!");
                return;
            }

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNomefornecedor(nomeField.getText());
            fornecedor.setNiffornecedor(nifField.getText());
            fornecedor.setTelefonefornecedor(telefoneField.getText());
            fornecedor.setRuafornecedor(ruaField.getText());
            fornecedor.setNportafornecedor(Integer.parseInt(portaField.getText()));
            fornecedor.setCodpostalforn(codPostalComboBox.getValue());

            fornecedorService.salvar(fornecedor);
            mostrarSucesso("Fornecedor adicionado com sucesso!");
            fecharJanela();
        } catch (Exception e) {
            mostrarErro("Erro ao salvar fornecedor: " + e.getMessage());
        }
    }

    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    public void handleCancelar() {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) nomeField.getScene().getWindow();
        stage.close();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
