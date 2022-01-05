package dev.iskander.pixelWriterDrawifier;

import dev.iskander.canvasDrawifier.CanvasDrawifier;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import java.util.Random;

public class PixelWriterDrawifier implements CanvasDrawifier {
	private final Random random = new Random();
	private double width;
	private double height;
	private GraphicsContext gc;

	public void initialise(GraphicsContext gc) {
		this.gc = gc;
		this.width = gc.getCanvas().getWidth();
		this.height = gc.getCanvas().getHeight();
	}

	public void drawFlatLayer(Color colour) {
		synchronized (gc) {
			gc.setFill(colour);
			gc.fillRect(0, 0, width, height);
		}
	}

	public void drawDeliberateLandLayer(Color colour, double startX, double startY, double maxSize) {
		synchronized (gc) {
			PixelWriter pixelWriter = gc.getPixelWriter();

			double minX = startX - maxSize;
			double maxX = startX + maxSize;
			double minY = startY - maxSize;
			double maxY = startY + maxSize;

			for (double ii = (minX); ii < (maxX); ii++) {
				for (double jj = (minY); jj < (maxY); jj++) {
					if (checkLineLength(startX, startY, ii, jj, maxSize)) {
						pixelWriter.setColor((int) ii, (int) jj, colour);
					}
				}
			}
		}
	}

	public void drawLineLandLayer(Color colour, double size, double x, double y, double maxLength) {
		double length = 0;
		boolean flipX = (x > width/2);
		boolean flipY = (y > height/2);
		double distance = random.nextDouble(size / 1.5);

		while (length < maxLength) {
			drawDeliberateLandLayer(colour, x, y, size);
			if (flipX) {
				x -= distance;
			} else {
				x += distance;
			}
			if (flipY) {
				y -= distance;
			} else {
				y += distance;
			}
			length += Math.sqrt(2 * (distance * distance));
		}
	}

	private boolean checkLineLength(double startX, double startY, double endX, double endY, double maxLength) {
		return ((Math.pow((endX - startX), 2) + Math.pow((endY - startY), 2)) < maxLength * maxLength);
	}

	public void render() {

	}

	public String toString() {
		return "Pixel Writer Drawifier";
	}
}
