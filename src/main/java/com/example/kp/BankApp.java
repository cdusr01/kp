package com.example.kp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class BankApp extends Application {
        public static Stage primaryStage;
        public static Scene clients;
        public static Scene credits;
        public static Scene kindCredits;

        @Override
        public void start(Stage stage) throws IOException {
            primaryStage = stage;
            clients = createScene("client-view.fxml");
            credits = createScene("credit-view.fxml");
            kindCredits = createScene("kind-credit-view.fxml");
            primaryStage.setMinWidth(1200);
            primaryStage.setMinHeight(675);
            primaryStage.setTitle("Bank manager");
            primaryStage.setScene(credits);
            primaryStage.show();
        }

        private Scene createScene(String name) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(BankApp.class.getResource(name));
            return new Scene(fxmlLoader.load());
        }

        public static void main(String[] args) {
            launch();
        }
}