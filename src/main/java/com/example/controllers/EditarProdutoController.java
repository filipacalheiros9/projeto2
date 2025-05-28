package com.example.controllers;

import com.example.projeto2.services.MaterialService;
import javafx.scene.control.ListCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.projeto2.models.Material;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

@Controller
public class EditarProdutoController {

    @FXML
    private ListView<Material> listViewProdutos;

    @FXML
    private TextField nomeProdutoTextField;

    @FXML
    private TextField precoProdutoTextField;

    @FXML
    private TextField quantidadeProdutoTextField;

    private Material selectedMaterial;

    @Autowired
    private MaterialService materialService;

    private List<Material> materials;

    public void setMaterials(List<Material> materials) {
        materials.sort((m1, m2) -> m1.getNomematerial().compareToIgnoreCase(m2.getNomematerial()));
        this.materials = materials;

        listViewProdutos.getItems().setAll(materials);

        listViewProdutos.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Material> call(ListView<Material> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Material item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty || item == null ? null : item.getNomematerial());
                    }
                };
            }
        });
    }


    @FXML
    public void selecionarProduto() {
        selectedMaterial = listViewProdutos.getSelectionModel().getSelectedItem();
        if (selectedMaterial != null) {
            setMaterial(selectedMaterial);
        }
    }

    public void setMaterial(Material material) {
        nomeProdutoTextField.setText(material.getNomematerial());
        precoProdutoTextField.setText(String.valueOf(material.getPrecomaterial()));
        quantidadeProdutoTextField.setText(String.valueOf(material.getQtdstockmaterial()));
    }

    @FXML
    public void handleSalvarEdicao() {
        try {
            String nome = nomeProdutoTextField.getText();
            String precoString = precoProdutoTextField.getText().replace(',', '.');
            String quantidadeString = quantidadeProdutoTextField.getText();

            Double preco = null;
            Integer quantidade = null;

            if (nome.isEmpty() || precoString.isEmpty() || quantidadeString.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Por favor, preencha todos os campos corretamente!").showAndWait();
                return;
            }

            try {
                preco = Double.parseDouble(precoString);
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.WARNING, "Preço inválido!").showAndWait();
                return;
            }

            try {
                quantidade = Integer.parseInt(quantidadeString);
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.WARNING, "Quantidade inválida!").showAndWait();
                return;
            }

            if (preco <= 0 || quantidade < 0) {
                new Alert(Alert.AlertType.WARNING, "Valores inválidos!").showAndWait();
                return;
            }

            selectedMaterial.setNomematerial(nome);
            selectedMaterial.setPrecomaterial(preco);
            selectedMaterial.setQtdstockmaterial(quantidade);

            materialService.salvarMaterial(selectedMaterial);

            ((Stage) nomeProdutoTextField.getScene().getWindow()).close();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao salvar edição: " + e.getMessage()).showAndWait();
        }
    }


    @FXML
    public void handleCancelarEdicao() {
        ((Stage) nomeProdutoTextField.getScene().getWindow()).close();
    }
}
