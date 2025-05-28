package com.example.controllers;

import com.example.AppContextProvider;
import com.example.projeto2.models.TipoFuncionario;
import com.example.projeto2.services.FuncionarioService;
import com.example.projeto2.services.TipoFuncionarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class GerirFuncionariosController {

    private final TipoFuncionarioService tipoFuncionarioService;
    private final FuncionarioService funcionarioService;

    public GerirFuncionariosController(TipoFuncionarioService tipoFuncionarioService, FuncionarioService funcionarioService) {
        this.tipoFuncionarioService = tipoFuncionarioService;
        this.funcionarioService = funcionarioService;
    }

    @FXML
    private void abrirAdicionarFuncionarioPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adicionar_funcionario.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            AdicionarFuncionarioController controller = loader.getController();
            List<TipoFuncionario> cargos = tipoFuncionarioService.listarTodos();
            controller.setCargos(cargos);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Adicionar Funcionário");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void RemoverFuncionario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/remover_funcionario.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            RemoverFuncionarioController controller = loader.getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Remover Funcionário");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirEditarFuncionarioPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editar_funcionario.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            EditarFuncionarioController controller = loader.getController();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Funcionário");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
