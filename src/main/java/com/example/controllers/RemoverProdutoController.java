package com.example.controllers;

import com.example.projeto2.models.Material;
import com.example.projeto2.services.MaterialService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javafx.scene.control.ListCell;

import java.util.List;

@Controller
public class RemoverProdutoController {

    @Autowired
    private MaterialService materialService;

    @FXML
    private ListView<Material> listViewProdutos;

    private List<Material> materials;

    @FXML
    public void setMaterials(List<Material> materials) {
        if (listViewProdutos == null) {
            new Alert(Alert.AlertType.ERROR, "Erro: ListView não foi inicializado corretamente.").showAndWait();
            return;
        }

        System.out.println("Número de materiais recebidos: " + materials.size());

        materials.sort((m1, m2) -> m1.getNomematerial().compareToIgnoreCase(m2.getNomematerial()));

        this.materials = materials;
        listViewProdutos.getItems().setAll(materials);

        listViewProdutos.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Material item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNomematerial());
            }
        });
    }





    @FXML
    public void removerProduto() {
        if (listViewProdutos == null) {
            new Alert(Alert.AlertType.ERROR, "Erro: ListView não foi inicializado corretamente.").showAndWait();
            return;
        }

        Material selectedMaterial = listViewProdutos.getSelectionModel().getSelectedItem();

        if (selectedMaterial == null) {
            new Alert(Alert.AlertType.WARNING, "Por favor, selecione um produto para remover.").showAndWait();
            return;
        }

        System.out.println("Produto selecionado para remoção: " + selectedMaterial.getNomematerial());

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Tem certeza de que deseja remover o produto: " + selectedMaterial.getNomematerial() + "?");
        confirmation.showAndWait().filter(response -> response.getButtonData().isDefaultButton()).ifPresent(response -> {
            try {
                materialService.excluirMaterial(selectedMaterial.getId());
                materials.remove(selectedMaterial);
                listViewProdutos.getItems().remove(selectedMaterial);
                listViewProdutos.refresh();
                new Alert(Alert.AlertType.INFORMATION, "Produto removido com sucesso!").showAndWait();
                fecharJanela();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Erro ao remover o produto: " + e.getMessage()).showAndWait();
            }
        });
    }




    @FXML
    public void fecharJanela() {
        Stage stage = (Stage) listViewProdutos.getScene().getWindow();
        stage.close();
    }
}
