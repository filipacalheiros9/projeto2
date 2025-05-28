package com.example.controllers;

import com.example.AppContextProvider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;

@Controller
public class FuncionarioDashboardController {

    @FXML private Button logoutButton;
    @FXML private Button inicioButton;
    @FXML private Button stockButton;
    @FXML private Button clienteButton;

    @FXML private ImageView logoImage;
    @FXML private VBox contentArea;

    @FXML
    public void initialize() {
        URL imgUrl = getClass().getResource("/images/logo.png");
        if (imgUrl != null) {
            logoImage.setImage(new Image(imgUrl.toExternalForm()));
        }

        handleInicio();
    }

    @FXML
    public void handleInicio() {
        setActiveButton(inicioButton);

        Label welcomeMessage = new Label("Bem-vindo, funcionário!");
        welcomeMessage.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #000000;");

        contentArea.getChildren().setAll(welcomeMessage);
    }

    @FXML
    public void handleStock() {
        setActiveButton(stockButton);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/stock_funcionario.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            VBox stockView = loader.load();
            contentArea.getChildren().setAll(stockView);
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a vista de stock: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleCliente() {
        setActiveButton(clienteButton);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cliente.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            VBox stockView = loader.load();
            contentArea.getChildren().setAll(stockView);
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a vista de cliente: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void logout() {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Login");
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível fazer logout: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void setActiveButton(Button activeBtn) {
        Button[] buttons = {inicioButton, stockButton, clienteButton};
        for (Button btn : buttons) {
            btn.setStyle("-fx-font-size: 14px; -fx-background-color: transparent; -fx-text-fill: black;");
        }
        activeBtn.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: transparent; -fx-text-fill: black;");
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }
}
