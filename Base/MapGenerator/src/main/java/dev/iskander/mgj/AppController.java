package dev.iskander.mgj;

import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Path;

public class AppController {

	public static Thread thread;

    @FXML
	Canvas canvas;

	@FXML
	ProgressBar progressBar;

	@FXML
	void doTheThing() {
		MapGenerator.canvasDrawifier.initialise(canvas.getGraphicsContext2D());
		BiomePlacementManager.initialise(canvas.getGraphicsContext2D());
		MapGenerator.canvasDrawifier.initialise(canvas.getGraphicsContext2D());
		if (thread == null || !thread.isAlive()) {
			var mapCreator = new MapCreator();
			progressBar.progressProperty().bind(mapCreator.progressProperty());
			System.out.println("new thread");
			thread = new Thread(mapCreator);
			thread.start();
		} else {
			System.out.println("Old thread still running");
		}
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