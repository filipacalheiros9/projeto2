package com.example.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.Node;
import javafx.stage.Stage;
import com.example.projeto2.models.Fornecedor;
import com.example.projeto2.models.LinhaEncomenda;
import com.example.projeto2.models.LinhaEncomendaId;
import com.example.projeto2.models.Material;
import com.example.projeto2.repositories.FornecedorRepository;
import com.example.projeto2.repositories.LinhaEncomendaRepository;
import com.example.projeto2.repositories.MaterialRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderMaterialDialogController {

    @Autowired private MaterialRepository materialRepo;
    @Autowired private FornecedorRepository fornecedorRepo;
    @Autowired private LinhaEncomendaRepository linhaRepo;

    @FXML private ComboBox<Material> comboMaterial;
    @FXML private ComboBox<Fornecedor> comboFornecedor;
    @FXML private TextField quantidadeField;
    @FXML private Label totalPriceLabel;

    @FXML
    public void initialize() {
        List<Material> mats = materialRepo.findAll();
        mats.sort((m1, m2) -> m1.getNomematerial().compareToIgnoreCase(m2.getNomematerial()));
        comboMaterial.getItems().setAll(mats);
        comboMaterial.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override protected void updateItem(Material item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNomematerial());
            }
        });
        comboMaterial.setButtonCell(comboMaterial.getCellFactory().call(null));

        List<Fornecedor> fors = fornecedorRepo.findAll();
        fors.sort((f1, f2) -> f1.getNomefornecedor().compareToIgnoreCase(f2.getNomefornecedor()));
        comboFornecedor.getItems().setAll(fors);
        comboFornecedor.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override protected void updateItem(Fornecedor item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNomefornecedor());
            }
        });
        comboFornecedor.setButtonCell(comboFornecedor.getCellFactory().call(null));

        ChangeListener<Object> listener = (ObservableValue<?> o, Object old, Object nw) -> updateTotal();
        comboMaterial.valueProperty().addListener(listener);
        comboFornecedor.valueProperty().addListener(listener);
        quantidadeField.textProperty().addListener(listener);
    }

    private void updateTotal() {
        Material m = comboMaterial.getValue();
        String qtText = quantidadeField.getText();
        if (m != null && qtText != null && qtText.matches("\\d+")) {
            int qt = Integer.parseInt(qtText);
            double tot = qt * m.getPrecomaterial();
            totalPriceLabel.setText(String.format("%.2f", tot));
        } else {
            totalPriceLabel.setText("0.00");
        }
    }

    @FXML
    public void handleCancelar(ActionEvent ev) {
        Stage st = (Stage)((Node)ev.getSource()).getScene().getWindow();
        st.close();
    }

    @FXML
    public void handleConfirmar(ActionEvent ev) {
        Material m = comboMaterial.getValue();
        Fornecedor f = comboFornecedor.getValue();
        String qtText = quantidadeField.getText();
        if (m != null && f != null && qtText.matches("\\d+")) {
            int qt = Integer.parseInt(qtText);
            double tot = qt * m.getPrecomaterial();

            LinhaEncomendaId linhaId = new LinhaEncomendaId(m.getId(), f.getId());
            LinhaEncomenda le = new LinhaEncomenda();
            le.setId(linhaId);
            le.setIdmaterial(m);
            le.setIdfornecedor(f);
            le.setQtdencomendada(qt);
            le.setValorencomendado(tot);

            linhaRepo.save(le);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Encomenda");
            alert.setHeaderText(null);
            alert.setContentText("Encomenda concluída com sucesso!");
            alert.showAndWait();

            Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Encomenda");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, escolha material, fornecedor e quantidade válidos.");
            alert.showAndWait();
        }
    }
}
