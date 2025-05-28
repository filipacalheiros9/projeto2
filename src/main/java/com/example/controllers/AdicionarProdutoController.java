package com.example.controllers;

import com.example.projeto2.models.Material;
import com.example.projeto2.repositories.MaterialRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdicionarProdutoController {

    @Autowired
    private MaterialRepository materialRepo;

    @FXML private TextField txtNome;
    @FXML private TextField txtPreco;
    @FXML private TextField txtQuantidade;

    @FXML
    public void guardarProduto() {
        try {
            String nome = txtNome.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            Material material = new Material();
            material.setNomematerial(nome);
            material.setPrecomaterial(preco);
            material.setQtdstockmaterial(quantidade);

            materialRepo.save(material);

            new Alert(Alert.AlertType.INFORMATION, "Produto adicionado com sucesso!").showAndWait();
            ((Stage) txtNome.getScene().getWindow()).close();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao adicionar produto: " + e.getMessage()).showAndWait();
        }
    }

}
