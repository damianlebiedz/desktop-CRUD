module com.damianlebiedz.financemanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires javafx.graphics;

    opens com.damianlebiedz.financemanager to javafx.fxml;
    exports com.damianlebiedz.financemanager;
    exports com.damianlebiedz.financemanager.controller;
    opens com.damianlebiedz.financemanager.controller to javafx.fxml;
    exports com.damianlebiedz.financemanager.model;
    opens com.damianlebiedz.financemanager.model to javafx.fxml;
    exports com.damianlebiedz.financemanager.db;
    opens com.damianlebiedz.financemanager.db to javafx.fxml;
}