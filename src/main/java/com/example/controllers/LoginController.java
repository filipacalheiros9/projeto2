package com.example.controllers;

import com.example.AppContextProvider;
import com.example.projeto2.services.FuncionarioService;
import com.example.projeto2.models.Funcionario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.utils.SessaoUtilizador;


import java.io.IOException;
import java.net.URL;

@Controller
public class LoginController {

    @Autowired
    private FuncionarioService funcionarioService;

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    public void handleLogin() {
        Funcionario f = funcionarioService
                .login(usernameField.getText(), passwordField.getText())
                .orElse(null);

        if (f != null) {
            SessaoUtilizador.setFuncionario(f);

            String fxmlDestino;
            String tituloJanela;

            if (f.getIdtipofunc().getId() == 1) {
                fxmlDestino = "fxml/gerente_dashboard.fxml";
                tituloJanela = "Painel do Gerente";
            } else {
                fxmlDestino = "fxml/funcionario_dashboard.fxml";
                tituloJanela = "Painel do Funcionário";
            }

            URL url = Thread.currentThread().getContextClassLoader().getResource(fxmlDestino);
            if (url == null) {
                showAlert("Erro", "FXML não encontrado: " + fxmlDestino, Alert.AlertType.ERROR);
                return;
            }

            try {
                FXMLLoader loader = new FXMLLoader(url);
                loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
                Parent root = loader.load();

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root, 900, 600));
                stage.setTitle(tituloJanela);
                stage.show();
            } catch (IOException e) {
                showAlert("Erro", "Não foi possível abrir o painel: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }

        } else {
            showAlert("Erro", "Credenciais inválidas ou sem permissão", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }
}
