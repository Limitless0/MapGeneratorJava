package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;


public class CanvasDrawifier {

	private static final Random random = new Random();
	private static double width;
	private static double height;

	public static void drawFlatLayer(GraphicsContext gc, Biomes biome) {
		setUpGC(gc, biome.COLOUR);
		gc.fillRect(0,0, width, height);
		tearDownGC(gc);
	}

	public static void drawDynamicLandLayer(GraphicsContext gc, Biomes biome, int count) {
		for (int ii = 0; ii < count; ii++) {
			setUpGC(gc, biome.COLOUR);
			drawLandBiome(gc, biome,
					random.nextDouble(width), random.nextDouble(height));

			tearDownGC(gc);
		}
	}

	public static void drawLineLandLayer(GraphicsContext gc, Biomes biome) {
		setUpGC(gc, biome.COLOUR);
		double x = random.nextDouble(width);
		double y = random.nextDouble(height);
		double maxLength = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		double length = 0;
		while (length < maxLength) {
			drawLandBiome(gc, biome, x, y);
			double[] array = lineSegmentCreator(gc, x, y, length, biome.MAX, false);
			x = array[0];
			y = array[1];
			length = array[2];
		}
		tearDownGC(gc);
	}

	public static void drawBiomes(GraphicsContext gc) {
		for (Biomes biome : Biomes.values()) {
			final double startX = random.nextDouble(gc.getCanvas().getWidth());
			final double startY = random.nextDouble(gc.getCanvas().getHeight());
			System.out.println(startX + " " + startY);
			drawLandBiome(gc, biome, startX, startY);
		}
	}

	public static void drawLandBiome(GraphicsContext gc, Biomes biomes, double startX, double startY) {
		drawLandBiome(gc, startX, startY, biomes.MAX);
	}

	public static void drawLandBiome(GraphicsContext gc, double startX, double startY, double maxSize) {
		gc.moveTo(startX, startY);
		for (int ii = 0; ii < 1_000; ii++) {
			drawWonkyLine(gc, startX, startY, maxSize, true);
		}
	}

	private static void drawWonkyLine(GraphicsContext gc, double startX, double startY,
									  double maxSize, boolean circle) {
		double length = 0;
		while (length < maxSize) {
			double[] array = lineSegmentCreator(gc, startX, startY, length, maxSize, circle);
			startX = array[0];
			startY = array[1];
			length = array[2];
			gc.lineTo(startX, startY);
		}
	}



	private static double[] lineSegmentCreator(GraphicsContext gc, double startX, double startY,
											   double length, double maxSize, boolean circle) {
		double[] endpoints = findEndpoint(gc, startX, startY, maxSize, circle);
		double outLen = length + (Math.sqrt(Math.pow(endpoints[0] - startX, 2) + Math.pow(endpoints[1] - startY, 2)));
		return new double[] {endpoints[0], endpoints[1], outLen};
	}

	private static double[] findEndpoint(GraphicsContext gc, double startX, double startY,
										 double maxSize, boolean circle) {

		double endX = random.nextDouble(Math.sqrt(maxSize));
		double endY = random.nextDouble(Math.sqrt(maxSize));

		if (circle) {
			if (random.nextBoolean()) {
				endX = -endX;
			}
			if (random.nextBoolean()) {
				endY = -endY;
			}
		}

		endX += startX;
		endY += startY;

		if (validateEndpoints(gc, startX, startY, endX, endY, maxSize)) {
			return new double[]{endX, endY};
		} else {
			return findEndpoint(gc, startX, startY, maxSize, circle);
		}
	}

	private static boolean validateEndpoints(GraphicsContext gc, double startX, double startY,
											 double endX, double endY, double maxSize) {

		if (Math.sqrt(Math.pow((endX - startX), 2) + Math.pow((endY - startY), 2)) > (maxSize * maxSize)) { // length
			return false;
		}
		if (endX < 0 || endX > width) { // in bounds
			return false;
		}
		if (endY < 0 || endY > height) { //not simplified for readability
			return false;
		}
		return true;
	}

	private static void setUpGC(GraphicsContext gc, Color color) {
		width = gc.getCanvas().getWidth();
		height = gc.getCanvas().getHeight();
		gc.setFill(color);
		gc.setStroke(color);
		gc.beginPath();
	}

	private static void tearDownGC(GraphicsContext gc) {
		gc.stroke();
		gc.closePath();
	}
}
