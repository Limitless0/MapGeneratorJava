package dev.iskander.mgj;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;

public class AppController {

    @FXML
	Canvas canvas;

	@FXML
	TextField colourPicker;

	@FXML
	void doTheThing() {

		CanvasDrawifier.drawPath(canvas.getGraphicsContext2D(), colourPicker.getText());

	}

}