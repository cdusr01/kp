package com.example.kp.controller.credit;

import com.example.kp.BankApp;
import com.example.kp.model.Client;
import com.example.kp.model.Credit;
import com.example.kp.service.CreditService;
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

public class CreditController {

    private List<Credit> credits;
    private ObservableList<CreditTableItem> creditsObservable;

    @FXML
    private TableColumn<?, ?> addressColumn;

    @FXML
    private Button clientsButton;

    @FXML
    private Button creditsButton;

    @FXML
    private TableView<CreditTableItem> creditsTable;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private Button kindKreditButton;

    @FXML
    private TableColumn<?, ?> kindKreditColumn;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<?, ?> phoneColumn;

    @FXML
    private TableColumn<?, ?> summaColumn;

    @FXML
    void addCredit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(BankApp.class.getResource("add-edit-credit-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(BankApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить кредит");
            AddEditCreditDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            System.out.println("Ошибка открытия окна: " + e.getMessage());
        }
    }

    @FXML
    void deleteCredit(ActionEvent event) {
        CreditTableItem currentItem = creditsTable.getSelectionModel().getSelectedItem();
        int currentItemId = creditsTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить \"" + currentItem.getName() + "\"?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                new CreditService().delete(currentItem.getCredit());
                creditsTable.getItems().remove(currentItemId);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void editCredit(ActionEvent event) {
        CreditTableItem currentItem = creditsTable.getSelectionModel().getSelectedItem();
        int currentItemId = creditsTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            try {
                FXMLLoader loader = new FXMLLoader(BankApp.class.getResource("add-edit-credit-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(BankApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать кредит");
                AddEditCreditDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getCredit());
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
    void onClientsButtonClick(ActionEvent event) throws IOException {
        BankApp.primaryStage.setScene(BankApp.clients);
    }

    @FXML
    void onKindKreditClick(ActionEvent event) throws IOException {
        BankApp.primaryStage.setScene(BankApp.kindCredits);
    }

    @FXML
    void powerOff(ActionEvent event) {
        BankApp.primaryStage.close();
    }

    @FXML
    void updateCredits(ActionEvent event) {
        updateList();
    }


    public void updateList() {
        credits = new CreditService().findAll();
        creditsObservable = FXCollections.observableArrayList();

        for (Credit credit : credits) {
            creditsObservable.add(new CreditTableItem(credit));
        }
        creditsTable.setItems(creditsObservable);
    }

    public void initialize() {
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        kindKreditColumn.setCellValueFactory(new PropertyValueFactory<>("kindCredit"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        summaColumn.setCellValueFactory(new PropertyValueFactory<>("summa"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        updateList();
    }
}

