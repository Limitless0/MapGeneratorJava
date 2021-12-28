package dev.iskander.mgj;

import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Path;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AppController {

	private static ExecutorService executorService = Executors.newSingleThreadExecutor();
	private static Future<?> future;
    @FXML
	Canvas canvas;

	@FXML
	ProgressBar progressBar;

	@FXML
	void doTheThing() {
		MapGenerator.canvasDrawifier.initialise(canvas.getGraphicsContext2D());
		BiomePlacementManager.initialise(canvas.getGraphicsContext2D());
		MapGenerator.canvasDrawifier.initialise(canvas.getGraphicsContext2D());
		MapCreator mapCreator = new MapCreator();
		future = executorService.submit(mapCreator);
		progressBar.progressProperty().bind(mapCreator.progressProperty());

		try {
			future.get();
			executorService.shutdown();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		checkOnTheThing();
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
			System.out.println((ii + 1) + "/" + MapGenerator.canvasDrawifier.pathList.size());
		}
	}
}