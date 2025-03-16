module com.example.kp {
        requires javafx.controls;
        requires javafx.fxml;
        requires jakarta.persistence;
        requires org.hibernate.orm.core;
        requires java.naming;
        requires java.desktop;
        requires javafx.swing;
        opens com.example.kp to javafx.fxml;
        opens com.example.kp.model to org.hibernate.orm.core;
        exports com.example.kp;
        exports com.example.kp.controller.client;
        opens com.example.kp.controller.client to javafx.fxml;
        exports com.example.kp.controller.credit;
        opens com.example.kp.controller.credit to javafx.fxml;
        exports com.example.kp.controller.kindcredit;
        opens com.example.kp.controller.kindcredit to javafx.fxml;
}