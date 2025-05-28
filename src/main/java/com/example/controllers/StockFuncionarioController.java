package com.example.controllers;

import com.example.AppContextProvider;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;


@Controller
public class StockFuncionarioController {

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
}
