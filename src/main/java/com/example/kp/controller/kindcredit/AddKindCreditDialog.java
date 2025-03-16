package com.example.kp.controller.kindcredit;

import com.example.kp.controller.client.ClientTableItem;
import com.example.kp.model.Client;
import com.example.kp.model.KindCredit;
import com.example.kp.service.ClientService;
import com.example.kp.service.KindCreditService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class AddKindCreditDialog {

    @FXML
    private TextField conditionField;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Button okButton;

    @FXML
    private TextField rateField;

    @FXML
    private TextField termField;
    private Stage dialogStage;

    @FXML
    void handleOk(ActionEvent event) {
        try {
            KindCredit kindCredit = new KindCredit();
            kindCredit.setName(nameField.getText());
            kindCredit.setConditions(conditionField.getText());
            kindCredit.setRate(rateField.getText());
            kindCredit.setTerm(termField.getText());
            KindCreditTableItem kindCreditTableItem = new KindCreditTableItem(kindCredit);

            new KindCreditService().save(kindCredit);

            dialogStage.close();
        }catch (IllegalArgumentException e){
            errorLabel.setText(e.getMessage());
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
