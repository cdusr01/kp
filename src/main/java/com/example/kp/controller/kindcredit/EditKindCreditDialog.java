package com.example.kp.controller.kindcredit;

import com.example.kp.model.Client;
import com.example.kp.model.KindCredit;
import com.example.kp.service.KindCreditService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditKindCreditDialog {

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

    private KindCredit kindCredit;
    private Stage dialogStage;

    @FXML
    void handleOk(ActionEvent event) {
        try {
            kindCredit.setName(nameField.getText());
            kindCredit.setConditions(conditionField.getText());
            kindCredit.setRate(rateField.getText());
            kindCredit.setTerm(termField.getText());

            new KindCreditService().update(kindCredit);

            dialogStage.close();
        }catch (IllegalArgumentException e){
            errorLabel.setText(e.getMessage());
        }
    }
    public void setDialogStage(Stage dialogStage, KindCredit kindCredit) {
        this.kindCredit = kindCredit;
        this.dialogStage = dialogStage;

        nameField.setText(kindCredit.getName());
        conditionField.setText(kindCredit.getConditions());
        rateField.setText(String.valueOf(kindCredit.getRate()));
        termField.setText(String.valueOf(kindCredit.getTerm()));
    }
}
