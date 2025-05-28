package com.example.controllers;

import com.example.projeto2.models.FaturaCliente;
import com.example.projeto2.models.Servico;
import com.example.projeto2.repositories.FaturaClienteRepository;
import com.example.projeto2.repositories.ServicoRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RegistarPagamentoController {

    @FXML
    private TextField valorFaturaField;

    @FXML
    private DatePicker dataFaturaPicker;

    @FXML
    private TextField servicoField;

    @Autowired
    private FaturaClienteRepository faturaClienteRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @FXML
    private ComboBox<Servico> servicoComboBox;

    @FXML
    public void initialize() {
        try {
            servicoComboBox.getItems().addAll(servicoRepository.findAll());
            servicoComboBox.setCellFactory(lv -> new ListCell<>() {
                @Override
                protected void updateItem(Servico item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : "Serviço #" + item.getId());
                }
            });
            servicoComboBox.setButtonCell(new ListCell<>() {
                @Override
                protected void updateItem(Servico item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : "Serviço #" + item.getId());
                }
            });
        } catch (Exception e) {
            showErrorMessage("Erro ao carregar serviços: " + e.getMessage());
        }
    }

    public void handleRegistarPagamento() {
        try {
            Double valorFatura = Double.parseDouble(valorFaturaField.getText());
            LocalDate dataFatura = dataFaturaPicker.getValue();
            Servico servico = servicoComboBox.getValue();

            if (servico == null) {
                showErrorMessage("Por favor, selecione um serviço.");
                return;
            }

            FaturaCliente fatura = new FaturaCliente();
            fatura.setValorfatura(valorFatura);
            fatura.setDatafatura(dataFatura);
            fatura.setServico(servico);

            faturaClienteRepository.save(fatura);
            showSuccessMessage();
        } catch (Exception e) {
            showErrorMessage("Erro ao registrar pagamento: " + e.getMessage());
        }
    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Pagamento Registrado com Sucesso");
        alert.setContentText("O pagamento foi registrado com sucesso!");
        alert.showAndWait();
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Erro ao Registrar Pagamento");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleFecharPopup() {
        Stage stage = (Stage) valorFaturaField.getScene().getWindow();
        stage.close();
    }
}
