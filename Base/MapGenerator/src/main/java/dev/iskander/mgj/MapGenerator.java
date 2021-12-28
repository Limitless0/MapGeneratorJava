package dev.iskander.mgj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MapGenerator extends Application {

    public static FXMLLoader fxmlLoader = new FXMLLoader(MapGenerator.class.getResource("app.fxml"));
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Map Generator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}