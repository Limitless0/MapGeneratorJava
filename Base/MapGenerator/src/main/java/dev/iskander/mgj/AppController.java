package dev.iskander.mgj;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;

public class AppController {

    @FXML
	Canvas canvas;

	@FXML
	ColorPicker colourPicker;

	@FXML
	void doTheThing() {

		CanvasDrawifier.drawPath(canvas.getGraphicsContext2D(), colourPicker.getValue());

	}

}