package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class CanvasDrawifier {

	public static void drawShapes(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLUE);
		double height = gc.getCanvas().getHeight();
		double width = gc.getCanvas().getWidth();
		Image img = new Image("https://iskander.dev/chop.png", 100.0, 100, true, true, false);
		
		double imgWidth = img.getWidth();

		for (double ii = 0; ii < width; ii += imgWidth) {
			gc.drawImage(img, ii,0);
		}

	}

	public static void drawPath(GraphicsContext gc, String colourString) {
		Color color;

		if (colourString.isEmpty()) {
			color = Color.BLUE;
		} else {
			try {
				color = Color.valueOf(colourString);
			} catch (RuntimeException runtimeException) {
				color = Color.RED;
			}
		}

		System.out.println(color);
	}
}
