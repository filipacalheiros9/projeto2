package com.example.controllers;

import com.example.projeto2.models.Material;
import com.example.projeto2.repositories.MaterialRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StockPopupController {

    @Autowired
    private MaterialRepository materialRepo;

    @FXML private TableView<Material> tableMaterials;
    @FXML private TableColumn<Material, Integer> colId;
    @FXML private TableColumn<Material, String> colNome;
    @FXML private TableColumn<Material, Integer> colStock;
    @FXML private TableColumn<Material, Double> colPreco;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nomematerial"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("qtdstockmaterial"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("precomaterial"));

        List<Material> materiais = materialRepo.findAll();
        materiais.sort((m1, m2) -> m1.getNomematerial().compareToIgnoreCase(m2.getNomematerial()));

        tableMaterials.getItems().setAll(materiais);
    }


    @FXML
    public void closePopup(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
