package com.example.kp.controller.client;

import com.example.kp.model.Client;
import com.example.kp.service.ClientService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddClientDialog implements Initializable {

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField kindPropertyField;

    @FXML
    private TextField nameField;

    @FXML
    private Button okButton;

    @FXML
    private TextField phoneField;

    private Stage dialogStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void handleOk() {
        try {
            Client client = new Client();
            client.setName(nameField.getText());
            client.setPhone(phoneField.getText());
            client.setAddress(addressField.getText());
            client.setContact(emailField.getText());
            client.setKindProperty(kindPropertyField.getText());
            ClientTableItem clientTableItem = new ClientTableItem(client);

            new ClientService().save(client);

            dialogStage.close();
        }catch (IllegalArgumentException e){
            errorLabel.setText(e.getMessage());
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
