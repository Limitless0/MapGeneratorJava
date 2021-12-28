package dev.iskander.mgj;

import dev.iskander.canvasDrawifier.CanvasDrawifier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.ServiceLoader;

public class MapGenerator extends Application {

    private static List<ServiceLoader.Provider<CanvasDrawifier>> canvasDrawifiers = ServiceLoader.load(CanvasDrawifier.class).stream().toList();
    public static CanvasDrawifier canvasDrawifier = canvasDrawifiers.get(0).get();
    public static FXMLLoader fxmlLoader = new FXMLLoader(MapGenerator.class.getResource("app.fxml"));
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Map Generator");
        stage.setScene(scene);
        stage.show();
        System.out.println(canvasDrawifiers.size());
    }

    public static void main(String[] args) {
        launch();
    }
}