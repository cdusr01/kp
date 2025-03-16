package com.example.kp.controller.credit;

import com.example.kp.model.Client;
import com.example.kp.model.Credit;
import com.example.kp.model.KindCredit;
import com.example.kp.service.ClientService;
import com.example.kp.service.CreditService;
import com.example.kp.service.KindCreditService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class EditCreditDialog {

    @FXML
    private DatePicker dateField;

    @FXML
    private Label errorLabel;

    @FXML
    private ComboBox<KindCredit> kindCreditField;

    @FXML
    private Button okButton;

    @FXML
    private TextField sumField;

    @FXML
    private ComboBox<Client> userField;
    private Credit credit;
    private Stage dialogStage;

    @FXML
    void handleOk(ActionEvent event) {
        try {
            credit.setClient(userField.getSelectionModel().getSelectedItem());
            credit.setKindCredit(kindCreditField.getSelectionModel().getSelectedItem());
            credit.setSumma(sumField.getText());
            credit.setDate((Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));

            new CreditService().update(credit);

            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setDialogStage(Stage dialogStage, Credit credit) {
        this.dialogStage = dialogStage;
        this.credit = credit;

        List<Client> clients = new ClientService().findAll();
        userField.getItems().addAll(FXCollections.observableList(clients));
        List<KindCredit> kindCredits = new KindCreditService().findAll();
        kindCreditField.getItems().addAll(FXCollections.observableList(kindCredits));
        dateField.setValue(LocalDate.now());

        userField.setValue(credit.getClient());
        kindCreditField.setValue(credit.getKindCredit());
        sumField.setText(credit.getSumma().toString());
        dateField.setValue(Instant.ofEpochMilli(credit.getDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
    }
}
