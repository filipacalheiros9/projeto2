package com.example.controllers;

import com.example.AppContextProvider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class FornecedorController {

    @FXML
    private TableView<?> fornecedorTable;

    @FXML
    public void initialize() {
    }

    @FXML
    public void handleAdicionarFornecedor() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adicionar_fornecedor.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Adicionar Fornecedor");
            popup.setScene(new Scene(root));
            popup.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de adicionar fornecedor: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void handleRemoverFornecedores() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/remover_fornecedor.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();
            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Remover Fornecedor");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de remover fornecedor: " + e.getMessage()).showAndWait();
        }
    }


    @FXML
    public void handleListarFornecedores() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/listar_fornecedores.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Listar Fornecedores");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de listar fornecedores: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void handlePagamentoFornecedor() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pagamento_fornecedor.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();
            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Pagamento Fornecedor");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de pagamento fornecedor: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void handleListarPagamentosFornecedores() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/listar_pagamentos_fornecedores.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            ListarPagamentosFornecedoresController controller = loader.getController();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Pagamentos do Fornecedor");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de listar pagamentos fornecedores: " + e.getMessage()).showAndWait();
        }
    }
}
