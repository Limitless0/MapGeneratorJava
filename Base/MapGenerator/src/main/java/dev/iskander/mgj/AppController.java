package dev.iskander.mgj;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.Random;

public class AppController {

    @FXML
	Canvas canvas;

	@FXML
	void doTheThing() {
		Random random = new Random();
		CanvasDrawifier.drawBiome(canvas.getGraphicsContext2D(), new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 0.5), random.nextDouble(100));

	}

}