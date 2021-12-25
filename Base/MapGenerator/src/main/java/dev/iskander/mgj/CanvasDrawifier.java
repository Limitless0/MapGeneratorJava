package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;


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

	public static void drawBiome(GraphicsContext gc, Color colour, double maxSize) {

		Random random = new Random();

		gc.setStroke(colour);
		gc.setFill(colour);

		final double startX = random.nextDouble(gc.getCanvas().getWidth());
		final double startY = random.nextDouble(gc.getCanvas().getHeight());

		for (int ii = 0; ii < 1_000; ii++) {
			drawFromPoint(gc, startX, startY, maxSize);
		}

	}

	static double[] drawFromPoint(GraphicsContext gc, double startX, double startY, double maxSize) {

		gc.beginPath();
		gc.moveTo(startX, startY);
		double[] endpoints = findEndpoint(gc, startX, startY, maxSize);
		System.out.println(endpoints[0] + " " + endpoints[1]);
		gc.lineTo(endpoints[0], endpoints[1]);
		gc.stroke();
		gc.closePath();
		return endpoints;
	}

	private static double[] findEndpoint(GraphicsContext gc, double startX, double startY, double maxSize) {
		Random random = new Random();
		return validateEndpoints(gc, random, startX, startY, maxSize);

	}

	private static double[] validateEndpoints(GraphicsContext gc, Random random, double startX, double startY, double maxSize) {
		double endX = random.nextDouble(Math.sqrt(maxSize));
		double endY = random.nextDouble(Math.sqrt(maxSize));
		System.out.println(endX + " " + endY);
		if ((endX * endX) + (endY * endY) > maxSize) {
			return validateEndpoints(gc, random, startX, startY, maxSize); // max length
		}
		if (endX < 0 || endX > gc.getCanvas().getWidth()) {
			return validateEndpoints(gc, random, startX, startY, maxSize); // stay in bounds
		}
		if (endY < 0 || endY > gc.getCanvas().getHeight()) {
			return validateEndpoints(gc, random, startX, startY, maxSize);
		}

		if (random.nextBoolean()) {
			endX = -endX;
		}
		if (random.nextBoolean()) {
			endY = -endY;
		}
		endX += startX;
		endY += startY;
		System.out.println(endX + "_________________________" + endY);
		return new double[]{endX, endY};
	}

	private static Color parseColour(String colourString) {
		Color color;
		try {
			color = Color.valueOf(colourString);
		} catch (RuntimeException runtimeException) {
			color = Color.RED;
		}

		return color;
	}
}
