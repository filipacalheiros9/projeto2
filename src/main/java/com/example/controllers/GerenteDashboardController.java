package com.example.controllers;

import com.example.AppContextProvider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;

@Controller
public class GerenteDashboardController {

    @FXML private Button logoutButton;
    @FXML private Button inicioButton;
    @FXML private Button perfilButton;
    @FXML private Button funcionariosButton;
    @FXML private Button stockButton;
    @FXML private Button fornecedorButton;
    @FXML private Button projetosButton;

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

    @FXML
    public void handleInicio() {
        setActiveButton(inicioButton);

        Label welcomeMessage = new Label("Bem-vindo, gerente!");
        welcomeMessage.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #000000;");

        contentArea.getChildren().setAll(welcomeMessage);
    }

    @FXML
    public void handlePerfil() {
        setActiveButton(perfilButton);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/perfil.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            VBox perfilView = loader.load();
            contentArea.getChildren().setAll(perfilView);
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a vista de perfil: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleFuncionarios() {
        setActiveButton(funcionariosButton);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gerir_funcionarios.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            VBox funcionariosView = loader.load();
            contentArea.getChildren().setAll(funcionariosView);
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a vista de funcionários: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void handleStock() {
        setActiveButton(stockButton);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/stock.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            VBox stockView = loader.load();
            contentArea.getChildren().setAll(stockView);
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a vista de stock: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleFornecedor() {
        setActiveButton(fornecedorButton);
        try {
            URL fxmlLocation = getClass().getResource("/fxml/fornecedor.fxml");
            System.out.println("Tentando carregar FXML de: " + fxmlLocation);

            if (fxmlLocation == null) {
                System.out.println("O arquivo FXML não foi encontrado!");
            } else {
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
                VBox fornecedorView = loader.load();
                contentArea.getChildren().setAll(fornecedorView);
            }
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a vista de fornecedor: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void handleProjetos() {
        setActiveButton(projetosButton);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/projetos.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();
            contentArea.getChildren().setAll(root);
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a vista de projetos: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


    private void setActiveButton(Button activeBtn) {
        Button[] buttons = {inicioButton, perfilButton, funcionariosButton, stockButton, fornecedorButton, projetosButton};
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
