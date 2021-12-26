package dev.iskander.mgj;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class AppController {

    @FXML
	Canvas canvas;

	@FXML
	void doTheThing() {
		Thread thread = new Thread(() -> Main.createMap(canvas.getGraphicsContext2D()));
		thread.start();
	}
}