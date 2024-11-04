package io.github.damianlebiedz.financemanager;

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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));

            int width = 1080;
            int height = 720;
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm());

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
    
    public static void main(String[] args) {
        launch();
    }
}
