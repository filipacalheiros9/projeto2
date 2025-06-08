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
public class ClienteController {

    @FXML
    public void initialize() {
    }

    @FXML
    public void handleListarProjetos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/listar_projetos.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Listar Projetos");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de listar projetos: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void handleAdicionarServico() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adicionar_servico.fxml"));

            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Adicionar Serviço");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRemoverServico() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/remover_servico.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Remover Serviço");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao abrir a janela de remoção de serviço: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleEditarServico() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editar-servico.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Editar Serviço");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao abrir a janela de edição de serviço: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    public void handleListarServicos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/listar_servicos.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Listar Serviços");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao abrir a janela de listar serviços: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void handleRegistarPagamento() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/registar_pagamento.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Registar Pagamento");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao abrir a janela de registar pagamento: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void handleListarPagamentos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/listar_pagamentos.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Listar Pagamentos");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao abrir a janela de listar pagamentos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
