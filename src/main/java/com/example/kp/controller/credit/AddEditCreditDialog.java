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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class AddEditCreditDialog {

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
    private Stage dialogStage;
    private Credit credit;

    void add() {
        try {
            if (userField.getSelectionModel().getSelectedIndex() == -1){
                throw new IllegalArgumentException("Нужно заполнить поле \"Клиент\"");
            }
            if (kindCreditField.getSelectionModel().getSelectedIndex() == -1){
                throw new IllegalArgumentException("Нужно заполнить поле \"Вид кредита\"");
            }
            Credit credit = new Credit();
            credit.setClient(userField.getSelectionModel().getSelectedItem());
            credit.setKindCredit(kindCreditField.getSelectionModel().getSelectedItem());
            credit.setSumma(sumField.getText());
            credit.setDate((Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
            CreditTableItem clientTableItem = new CreditTableItem(credit);

            new CreditService().save(credit);

            dialogStage.close();
        }catch (IllegalArgumentException e){
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        List<Client> clients = new ClientService().findAll();
        userField.getItems().addAll(FXCollections.observableList(clients));
        List<KindCredit> kindCredits = new KindCreditService().findAll();
        kindCreditField.getItems().addAll(FXCollections.observableList(kindCredits));
        dateField.setValue(LocalDate.now());

        okButton.setOnAction((www) -> add());
    }

    void edit() {
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

    public void setEditDialogStage(Stage dialogStage, Credit credit) {
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

        okButton.setOnAction((www) -> edit());
    }
}
