package com.example.controllers;

import com.example.AppContextProvider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class ProjetosController {

    @FXML
    public void handleAdicionarTipoProjeto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adicionar_tipo_projeto.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Adicionar Tipo de Projeto");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de adicionar tipo de projeto: " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRemoverTipoProjeto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/remover_tipo_projeto.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Remover Tipo de Projeto");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de remover tipo de projeto: " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void handleListarTiposProjetos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/listar_tipos_projetos.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Listar Tipos de Projetos");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de listar tipos de projeto: " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAdicionarTipoImpressao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adicionar_tipo_impressao.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Adicionar Tipo de Impressão");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de adicionar tipo de impressão: " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRemoverTipoImpressao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/remover_tipo_impressao.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Remover Tipo de Impressão");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de remover tipo de impressão: " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void handleListarTiposImpressoes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/listar_tipos_impressoes.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Listar Tipos de Impressão");
            popup.setScene(new Scene(root));
            popup.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir janela de listar tipos de impressão: " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message, AlertType type) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }
}
