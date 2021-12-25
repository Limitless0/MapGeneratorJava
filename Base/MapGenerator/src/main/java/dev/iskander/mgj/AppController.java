package dev.iskander.mgj;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class AppController {

    @FXML
	Canvas canvas;

	@FXML
	void doTheThing() {
		CanvasDrawifier.drawBiomes(canvas.getGraphicsContext2D());
	}

}