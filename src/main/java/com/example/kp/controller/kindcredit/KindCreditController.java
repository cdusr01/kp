package com.example.kp.controller.kindcredit;

import com.example.kp.BankApp;
import com.example.kp.controller.client.AddClientDialog;
import com.example.kp.controller.client.ClientTableItem;
import com.example.kp.controller.client.EditClientDialog;
import com.example.kp.model.KindCredit;
import com.example.kp.service.KindCreditService;
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

public class KindCreditController {

    private List<KindCredit> kindCredits;

    @FXML
    private Button clientsButton;

    @FXML
    private TableColumn<?, ?> conditionColumn;

    @FXML
    private Button creditsButton;

    @FXML
    private TableView<KindCreditTableItem> kindCreditsTable;

    @FXML
    private Button kindKreditButton;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> rateColumn;

    @FXML
    private TableColumn<?, ?> termColumn;
    private ObservableList<KindCreditTableItem> creditsObservable;

    @FXML
    void addKindCredit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(BankApp.class.getResource("add-kind-credit-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(BankApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить кредит");
            AddKindCreditDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            System.out.println("Ошибка открытия окна: " + e.getMessage());
        }
    }

    @FXML
    void deleteKindCredit(ActionEvent event) {
        KindCreditTableItem currentItem = kindCreditsTable.getSelectionModel().getSelectedItem();
        int currentItemId = kindCreditsTable.getSelectionModel().getSelectedIndex();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение удаления");
        alert.setHeaderText("Удаление записи");
        alert.setContentText("Вы действительно хотите удалить \"" + currentItem.getName() + "\"?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            new KindCreditService().delete(currentItem.getKindCredit());
            kindCreditsTable.getItems().remove(currentItemId);
        }
    }

    @FXML
    void editKindCredit(ActionEvent event) {
        KindCreditTableItem currentItem = kindCreditsTable.getSelectionModel().getSelectedItem();
        int currentItemId = kindCreditsTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            try {
                FXMLLoader loader = new FXMLLoader(BankApp.class.getResource("edit-kind-credit-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(BankApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать кредит");
                EditKindCreditDialog controller = loader.getController();
                controller.setDialogStage(dialogStage, currentItem.getKindCredit());
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
    void onClientsButtonClick(ActionEvent event) {
        BankApp.primaryStage.setScene(BankApp.clients);
    }

    @FXML
    void onCreditButtonClick(ActionEvent event) {
        BankApp.primaryStage.setScene(BankApp.credits);
    }

    @FXML
    void powerOff(ActionEvent event) {
        BankApp.primaryStage.close();
    }

    @FXML
    void updateKindCredits(ActionEvent event) {
        updateList();
    }

    public void updateList() {
        kindCredits = new KindCreditService().findAll();
        creditsObservable = FXCollections.observableArrayList();

        for (KindCredit kindCredit : kindCredits) {
            creditsObservable.add(new KindCreditTableItem(kindCredit));
        }
        kindCreditsTable.setItems(creditsObservable);
    }

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
        termColumn.setCellValueFactory(new PropertyValueFactory<>("term"));

        updateList();
    }

}
