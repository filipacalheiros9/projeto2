package com.example.controllers;

import com.example.projeto2.models.*;
import com.example.projeto2.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdicionarServicoController {

    @FXML private DatePicker dataServicoField;
    @FXML private TextField precoServicoField;
    @FXML private TextField estadoServicoField;
    @FXML private ComboBox<Projeto> projetoComboBox;
    @FXML private ComboBox<Funcionario> funcionarioComboBox;
    @FXML private ComboBox<TipoImpressao> tipoImpressaoComboBox;

    @FXML private ComboBox<Material> materialComboBox;
    @FXML private TextField quantidadeMaterialField;
    @FXML private Button adicionarMaterialButton;
    @FXML private ListView<String> materiaisListView;

    private final ServicoService servicoService;
    private final ProjetoService projetoService;
    private final FuncionarioService funcionarioService;
    private final TipoImpressaoService tipoImpressaoService;
    private final MaterialService materialService;

    private final Map<Material, Integer> materiaisSelecionados = new HashMap<>();
    private final ObservableList<String> materiaisListItems = FXCollections.observableArrayList();

    public AdicionarServicoController(ServicoService servicoService,
                                      ProjetoService projetoService,
                                      FuncionarioService funcionarioService,
                                      TipoImpressaoService tipoImpressaoService,
                                      MaterialService materialService) {
        this.servicoService = servicoService;
        this.projetoService = projetoService;
        this.funcionarioService = funcionarioService;
        this.tipoImpressaoService = tipoImpressaoService;
        this.materialService = materialService;
    }

    @FXML
    public void initialize() {
        projetoComboBox.getItems().addAll(projetoService.listarTodos());
        funcionarioComboBox.getItems().addAll(funcionarioService.listarTodos());
        tipoImpressaoComboBox.getItems().addAll(tipoImpressaoService.listarTodos());

        List<Material> materiais = materialService.listarTodos();
        materiais.sort((m1, m2) -> m1.getNomematerial().compareToIgnoreCase(m2.getNomematerial()));
        materialComboBox.getItems().setAll(materiais);

        materialComboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Material item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNomematerial());
            }
        });
        materialComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Material item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNomematerial());
            }
        });

        materiaisListView.setItems(materiaisListItems);

        adicionarMaterialButton.setOnAction(e -> {
            Material mat = materialComboBox.getValue();
            String qtStr = quantidadeMaterialField.getText();
            if (mat != null && qtStr.matches("\\d+")) {
                int qt = Integer.parseInt(qtStr);
                if (qt > 0 && qt <= mat.getQtdstockmaterial()) {
                    materiaisSelecionados.put(mat, materiaisSelecionados.getOrDefault(mat, 0) + qt);
                    atualizarListaMateriais();
                    quantidadeMaterialField.clear();
                } else {
                    showAlert("Erro", "Quantidade inválida ou insuficiente no stock", Alert.AlertType.WARNING);
                }
            } else {
                showAlert("Erro", "Selecione um material e insira uma quantidade válida", Alert.AlertType.WARNING);
            }
        });
    }


    private void atualizarListaMateriais() {
        materiaisListItems.clear();
        for (Map.Entry<Material, Integer> entry : materiaisSelecionados.entrySet()) {
            materiaisListItems.add(entry.getKey().getNomematerial() + " x" + entry.getValue());
        }
    }

    @FXML
    public void handleAdicionarServico() {
        try {
            LocalDate dataServico = dataServicoField.getValue();
            Double precoServico = Double.parseDouble(precoServicoField.getText());
            String estadoServico = estadoServicoField.getText();
            Projeto projeto = projetoComboBox.getValue();
            Funcionario funcionario = funcionarioComboBox.getValue();
            TipoImpressao tipoImpressao = tipoImpressaoComboBox.getValue();

            if (projeto == null || funcionario == null || tipoImpressao == null) {
                showAlert("Erro", "Por favor, selecione todos os campos obrigatórios.", Alert.AlertType.ERROR);
                return;
            }

            for (Map.Entry<Material, Integer> entry : materiaisSelecionados.entrySet()) {
                Material mat = entry.getKey();
                int quantidadeUsada = entry.getValue();
                mat.setQtdstockmaterial(mat.getQtdstockmaterial() - quantidadeUsada);
                materialService.salvarMaterial(mat);
            }

            Servico servico = new Servico();
            servico.setDataservico(dataServico);
            servico.setPrecoservico(precoServico);
            servico.setEstadoservico(estadoServico);
            servico.setIdproj(projeto);
            servico.setIdfuncio(funcionario);
            servico.setIdtipoimpre(tipoImpressao);

            servicoService.salvarServico(servico);

            showAlert("Sucesso", "Serviço adicionado com sucesso!", Alert.AlertType.INFORMATION);
            fecharJanela();

        } catch (Exception e) {
            showAlert("Erro", "Erro ao adicionar o serviço: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @FXML
    public void handleCancelar() {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) dataServicoField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
