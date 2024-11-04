module io.github.damianlebiedz.financemanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires javafx.graphics;

    opens io.github.damianlebiedz.financemanager to javafx.fxml;
    exports io.github.damianlebiedz.financemanager;
}