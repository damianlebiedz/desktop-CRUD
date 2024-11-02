package com.damianlebiedz.financemanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            //Dodawanie FXML i ustalenie wielkości okna aplikacji
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
            int width = 1080;
            int height = 720;
            Scene scene = new Scene(fxmlLoader.load(), width, height);

            //Dodawanie CSS
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm());

            //Dodawanie ikony i tytułu aplikacji
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icon.png")));
            stage.getIcons().add(icon);
            stage.setTitle("Finance Manager");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // Główna metoda aplikacji
    public static void main(String[] args) {
        launch();
    }
}
