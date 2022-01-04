package dev.iskander.mgj;

import dev.iskander.canvasDrawifier.CanvasDrawifier;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Path;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AppController {

	@FXML
	Canvas canvas;

	@FXML
	ProgressBar progressBar;

	@FXML
	ComboBox<CanvasDrawifier> drawifierPicker;

	void initialise() {
		for (int ii = 0; ii < MapGenerator.canvasDrawifiers.size(); ii++) {
			drawifierPicker.getItems().add(MapGenerator.canvasDrawifiers.get(ii).get());
		}
	}

	@FXML
	private void setDrawifier() {
		MapGenerator.canvasDrawifier = drawifierPicker.getValue();
	}


	@FXML
	void doTheThing() {
		System.out.println(LocalDateTime.now() + " Start");
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		MapGenerator.canvasDrawifier.initialise(canvas.getGraphicsContext2D());
		BiomePlacementManager.initialise(canvas.getGraphicsContext2D());
		MapCreator mapCreator = new MapCreator();
		Future<?> future = executorService.submit(mapCreator);
		progressBar.progressProperty().bind(mapCreator.progressProperty());

		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
		System.out.println(LocalDateTime.now() + " Generation completed, rendering...");
		checkOnTheThing();
		System.out.println(LocalDateTime.now() + " Completely done");
	}

	@FXML
	void checkOnTheThing() {
		for (int ii = 0; ii < MapGenerator.canvasDrawifier.pathList.size(); ii++) {
			Path path = MapGenerator.canvasDrawifier.pathList.get(ii);
			SnapshotParameters sp = MapGenerator.canvasDrawifier.sps.get(ii);
			canvas.getGraphicsContext2D().drawImage(path.snapshot(sp,
							new WritableImage(
									(int) canvas.getWidth(), (int) canvas.getHeight())),
					0, 0);
		}
	}
}