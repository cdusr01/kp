package com.example.kp.controller.client;

import com.example.kp.BankApp;
import com.example.kp.model.Client;
import com.example.kp.service.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ClientController {

    private List<Client> clients;
    private ObservableList<ClientTableItem> creditsObservable;
    @FXML
    private TableColumn<?, ?> addressColumn;

    @FXML
    private Button clientsButton;

    @FXML
    private TableView<ClientTableItem> clientsTable;

    @FXML
    private Button creditsButton;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private Button kindKreditButton;

    @FXML
    private TableColumn<?, ?> kindPropertyColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> phoneColumn;

    @FXML
    void addClient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(BankApp.class.getResource("add-client-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(BankApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить клиента");
            AddClientDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            System.out.println("Ошибка открытия окна: " + e.getMessage());
        }
    }

    @FXML
    void deleteClient(ActionEvent event) {
        ClientTableItem currentItem = clientsTable.getSelectionModel().getSelectedItem();
        int currentItemId = clientsTable.getSelectionModel().getSelectedIndex();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение удаления");
        alert.setHeaderText("Удаление записи");
        alert.setContentText("Вы действительно хотите удалить \"" + currentItem.getName() + "\"?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            new ClientService().delete(currentItem.getClient());
            clientsTable.getItems().remove(currentItemId);
        }
    }

    @FXML
    void editClient(ActionEvent event) {
        ClientTableItem currentItem = clientsTable.getSelectionModel().getSelectedItem();
        int currentItemId = clientsTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            try {
                FXMLLoader loader = new FXMLLoader(BankApp.class.getResource("edit-client-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(BankApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать клиента");
                EditClientDialog controller = loader.getController();
                controller.setDialogStage(dialogStage, currentItem.getClient());
                dialogStage.showAndWait();
                updateList();
            } catch (IOException e) {
                System.out.println("Ошибка открытия окна: " + e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для редактирования");
            alert.showAndWait();
        }
    }

    @FXML
    void onClickCredit(ActionEvent event) throws IOException {
        BankApp.primaryStage.setScene(BankApp.credits);
    }

    @FXML
    void onKindKreditClick(ActionEvent event) {
        BankApp.primaryStage.setScene(BankApp.kindCredits);
    }

    @FXML
    void powerOff(ActionEvent event) {
        BankApp.primaryStage.close();
    }

    @FXML
    void updateClients(ActionEvent event) {
        updateList();
    }

    private void updateList() {
        clients = new ClientService().findAll();
        creditsObservable = FXCollections.observableArrayList();

        for (Client client : clients) {
            creditsObservable.add(new ClientTableItem(client));
        }
        clientsTable.setItems(creditsObservable);
    }

    public void initialize() {
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        kindPropertyColumn.setCellValueFactory(new PropertyValueFactory<>("kindProperty"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        updateList();
    }

}
