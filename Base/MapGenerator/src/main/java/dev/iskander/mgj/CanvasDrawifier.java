package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;


public class CanvasDrawifier {

	public static void drawBiomes(GraphicsContext gc) {
		Random random = new Random();
		for (Biomes biome : Biomes.values()) {
			double startX = random.nextDouble(gc.getCanvas().getWidth());
			double startY = random.nextDouble(gc.getCanvas().getHeight());
			drawBiome(gc, biome, startX, startY);
		}
	}

	public static void drawBiome(GraphicsContext gc, Biomes biomes, double startX, double startY) {
		drawBiome(gc, biomes.COLOUR, biomes.MAX, startX, startY);
	}

	public static void drawBiome(GraphicsContext gc, Color colour, double maxSize, double startX, double startY) {
		gc.setStroke(colour);
		gc.setFill(colour);
		for (int ii = 0; ii < 1_000; ii++) {
			drawWonkyLine(gc, startX, startY, maxSize);
		}

	}

	static void drawWonkyLine(GraphicsContext gc, double startX, double startY, double maxSize) {
		double length = 0;
		gc.beginPath();
		gc.moveTo(startX, startY);
		while (length < maxSize) {
			double[] array = lineSegmentCreator(gc, startX, startY, maxSize, length);
			startX = array[0];
			startY = array[1];
			length = array[2];
			gc.lineTo(startX, startY);
			gc.stroke();
		}
		gc.closePath();
	}



	static double[] lineSegmentCreator(GraphicsContext gc, double startX, double startY, double length, double maxSize) {


		double[] endpoints = findEndpoint(gc, startX, startY, maxSize);

		double outLen = length + (Math.sqrt(Math.pow(endpoints[0] - startX, 2) + Math.pow(endpoints[1] - startY, 2)));
		return new double[] {endpoints[0], endpoints[1], outLen};

	}

	private static double[] findEndpoint(GraphicsContext gc, double startX, double startY, double maxSize) {
		Random random = new Random();
		double endX = random.nextDouble(Math.sqrt(maxSize));
		double endY = random.nextDouble(Math.sqrt(maxSize));

		while(!validateEndpoints(gc, endX + startX, endY + startY, maxSize)) {
			endX = random.nextDouble(Math.sqrt(maxSize));
			endY = random.nextDouble(Math.sqrt(maxSize));
		}

		if (random.nextBoolean()) {
			endX = -endX;
		}
		if (random.nextBoolean()) {
			endY = -endY;
		}
		endX += startX;
		endY += startY;
		return new double[]{endX, endY};

	}

	private static boolean validateEndpoints(GraphicsContext gc, double endX, double endY, double maxSize) {

		if ((endX * endX) + (endY * endY) > maxSize) {
			return false;
		}
		if (endX < 0 || endX > gc.getCanvas().getWidth()) {
			return false;
		}
		if (endY < 0 || endY > gc.getCanvas().getHeight()) { //not simplified for readability
			return false;
		}

		return true;
	}
}
