package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;


public class CanvasDrawifier {

	private static final Random random = new Random();

	public static void drawBiomes(GraphicsContext gc) {
		for (Biomes biome : Biomes.values()) {
			gc.beginPath();
			final double startX = random.nextDouble(gc.getCanvas().getWidth());
			final double startY = random.nextDouble(gc.getCanvas().getHeight());
			gc.moveTo(startX, startY);
			System.out.println(startX + " " + startY);
			drawBiome(gc, biome, startX, startY);
			gc.stroke();
			gc.closePath();
		}
	}

	public static void drawBiome(GraphicsContext gc, Biomes biomes, double startX, double startY) {
		drawBiome(gc, biomes.COLOUR, startX, startY, biomes.MAX);
	}

	public static void drawBiome(GraphicsContext gc, Color colour, double startX, double startY, double maxSize) {
		gc.setStroke(colour);
		gc.setFill(colour);
		for (int ii = 0; ii < 1_000; ii++) {
			drawWonkyLine(gc, startX, startY, maxSize);
		}
	}

	static void drawWonkyLine(GraphicsContext gc, double startX, double startY, double maxSize) {
		double length = 0;
		while (length < maxSize) {
			double[] array = lineSegmentCreator(gc, startX, startY, length, maxSize);
			startX = array[0];
			startY = array[1];
			length = array[2];
			gc.lineTo(startX, startY);
		}
	}



	static double[] lineSegmentCreator(GraphicsContext gc, double startX, double startY, double length, double maxSize) {
		double[] endpoints = findEndpoint(gc, startX, startY, maxSize);
		double outLen = length + (Math.sqrt(Math.pow(endpoints[0] - startX, 2) + Math.pow(endpoints[1] - startY, 2)));
		return new double[] {endpoints[0], endpoints[1], outLen};
	}

	private static double[] findEndpoint(GraphicsContext gc, double startX, double startY, double maxSize) {

		double endX = random.nextDouble(Math.sqrt(maxSize));
		System.out.println("X: " + (startX - endX));
		double endY = random.nextDouble(Math.sqrt(maxSize));
		System.out.println("Y: " + (startY - endY));

		if (random.nextBoolean()) {
			endX = -endX;
		}
		if (random.nextBoolean()) {
			endY = -endY;
		}

		endX += startX;
		endY += startY;

		if (validateEndpoints(gc, startX, startY, endX, endY, maxSize)) {
			return new double[]{endX, endY};
		} else {
			return findEndpoint(gc, startX, startY, maxSize);
		}

	}

	private static boolean validateEndpoints(GraphicsContext gc, double startX, double startY,
											 double endX, double endY, double maxSize) {

		if (Math.sqrt(Math.pow((endX - startX), 2) + Math.pow((endY - startY), 2)) > (maxSize * maxSize)) { // length
			System.out.println("wrongLen");
			return false;
		}
		if (endX < 0 || endX > gc.getCanvas().getWidth()) { // in bounds
			System.out.println("out of bounds x");
			return false;
		}
		if (endY < 0 || endY > gc.getCanvas().getHeight()) { //not simplified for readability
			System.out.println("out of bounds y");
			return false;
		}

		return true;
	}
}
