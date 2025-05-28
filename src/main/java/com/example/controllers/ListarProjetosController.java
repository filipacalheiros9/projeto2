package com.example.controllers;

import com.example.projeto2.models.Projeto;
import com.example.projeto2.repositories.ProjetoRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ListarProjetosController {

    @FXML private TableView<Projeto> projetosTable;
    @FXML private TableColumn<Projeto, Integer> colId;
    @FXML private TableColumn<Projeto, String> colTema;
    @FXML private TableColumn<Projeto, String> colTipoLetra;
    @FXML private TableColumn<Projeto, String> colTamanho;
    @FXML private TableColumn<Projeto, String> colCores;

    @Autowired
    private ProjetoRepository projetoRepository;

    @FXML private TableColumn<Projeto, String> colCliente;
    @FXML private TableColumn<Projeto, String> colTipoProjeto;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTema.setCellValueFactory(new PropertyValueFactory<>("temaprojeto"));
        colTipoLetra.setCellValueFactory(new PropertyValueFactory<>("tipoletra"));
        colTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        colCores.setCellValueFactory(new PropertyValueFactory<>("cores"));

        colCliente.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getIdclien().getNomecliente()
                )
        );

        colTipoProjeto.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getIdtipoproj().getTipoprojeto()
                )
        );

        List<Projeto> projetos = projetoRepository.findAll();
        projetosTable.getItems().setAll(projetos);
    }


    @FXML
    public void fecharJanela() {
        Stage stage = (Stage) projetosTable.getScene().getWindow();
        stage.close();
    }
}
