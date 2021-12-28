package dev.iskander.mgj;

import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Path;

public class AppController {

    @FXML
	Canvas canvas;

	@FXML
	ProgressBar progressBar;

	@FXML
	void doTheThing() {
		CanvasDrawifier.initialise(canvas.getGraphicsContext2D());
		BiomePlacementManager.initialise(canvas.getGraphicsContext2D());
		Thread thread = new Thread(new MapCreator());
		thread.start();
	}
	@FXML
	 void checkOnTheThing() {
		for (int ii = 0; ii < MapCreator.pathList.size(); ii++) {
			Path path = MapCreator.pathList.get(ii);
			SnapshotParameters sp = MapCreator.sps.get(ii);

			canvas.getGraphicsContext2D().drawImage(path.snapshot(sp,
					new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight())),
					0, 0);
		}
	}
}