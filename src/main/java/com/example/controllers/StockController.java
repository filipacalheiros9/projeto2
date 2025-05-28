package com.example.controllers;

import com.example.AppContextProvider;
import com.example.projeto2.models.Material;
import com.example.projeto2.services.MaterialService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Controller
public class StockController {

    @Autowired
    private MaterialService materialService;

    @FXML
    private Button btnAdicionarProduto;

    @FXML
    public void handleOrderMaterial() {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
        try (InputStream fxmlStream = getClass().getResourceAsStream("/fxml/order-material-dialog.fxml")) {
            if (fxmlStream == null) {
                new Alert(Alert.AlertType.ERROR, "Formulário de encomenda não encontrado.").showAndWait();
                return;
            }
            Parent root = loader.load(fxmlStream);
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Encomendar Material");
            dialog.setScene(new Scene(root));
            dialog.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Não foi possível abrir o formulário: " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancelarEncomenda() {
        try {
            URL url = getClass().getResource("/fxml/cancelar-encomenda-dialog.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Cancelar Encomenda");
            dialog.setScene(new Scene(root));
            dialog.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void handleListarEncomenda() {
        try {
            URL url = getClass().getResource("/fxml/encomenda-popup.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent popupRoot = loader.load();
            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Listagem de Encomendas");
            popup.setScene(new Scene(popupRoot));
            popup.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de encomendas: " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void handleListarStock() {
        try {
            URL url = getClass().getResource("/fxml/stock-popup.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent popupRoot = loader.load();
            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Listagem de Stock");
            popup.setScene(new Scene(popupRoot));
            popup.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de stock: " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAdicionarProduto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adicionar-produto-dialog.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Adicionar Produto ao Stock");
            popup.setScene(new Scene(root));
            popup.showAndWait();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de adicionar produto: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void handleRemoverProduto() {
        try {
            List<Material> materials = materialService.listarTodos();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/remover-produto-dialog.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            RemoverProdutoController removerProdutoController = loader.getController();
            System.out.println("Controller RemoverProdutoController injetado corretamente.");

            removerProdutoController.setMaterials(materials);

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Remover Produto do Stock");
            popup.setScene(new Scene(root));
            popup.showAndWait();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de remover produto: " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEditarProduto() {
        try {
            List<Material> materials = materialService.listarTodos();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editar-produto-dialog.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            EditarProdutoController editarProdutoController = loader.getController();
            editarProdutoController.setMaterials(materials);

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Editar Produto");
            popup.setScene(new Scene(root));
            popup.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de editar produto: " + e.getMessage()).showAndWait();
        }


    }
}
