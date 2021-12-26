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

	public static void drawRandomLandLayer(GraphicsContext gc, Biomes biome, int count) {
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

		boolean flipX = (x > width/2);
		boolean flipY = (y > height/2);

		while (length < maxLength) {
			drawLandBiome(gc, biome, x, y);
			double[] array = lineSegmentCreatorLine(x, y, length, biome.MAX, flipX, flipY);
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
			drawWonkyLine(gc, startX, startY, maxSize);
		}
	}

	private static void drawWonkyLine(GraphicsContext gc, double startX, double startY,
									  double maxSize) {
		double length = 0;
		while (length < maxSize) {
			double[] array = lineSegmentCreatorRandom(startX, startY, length, maxSize);
			startX = array[0];
			startY = array[1];
			length = array[2];
			gc.lineTo(startX, startY);
		}
	}

	private static double[] lineSegmentCreatorRandom(double startX, double startY,
													 double length, double maxSize) {
		double[] endpoints = findEndpointsRandom(startX, startY, maxSize);
		double outLen = length + (Math.sqrt(Math.pow(endpoints[0] - startX, 2) + Math.pow(endpoints[1] - startY, 2)));
		return new double[] {endpoints[0], endpoints[1], outLen};
	}

	private static double[] lineSegmentCreatorLine(double startX, double startY, double length,
												   double maxSize, boolean flipX, boolean flipY) {
		double[] endpoints = findEndpointsLine(startX, startY, maxSize, flipX, flipY);
		double outLen = length + (Math.sqrt(Math.pow(endpoints[0] - startX, 2) + Math.pow(endpoints[1] - startY, 2)));
		return new double[] {endpoints[0], endpoints[1], outLen};
	}

	private static double[] findEndpointsRandom(double startX, double startY, double maxSize) {
		double endX = random.nextDouble(Math.sqrt(maxSize));
		double endY = random.nextDouble(Math.sqrt(maxSize));

		endX = transformCoordinate(endX, random.nextBoolean()) + startX;
		endY = transformCoordinate(endY, random.nextBoolean()) + startY;

		if (validateEndpoints(startX, startY, endX, endY, maxSize)) {
			endX = moveXCoordinateIntoBounds(endX);
			endY = moveYCoordinateIntoBounds(endY);
			return new double[]{endX, endY};
		} else {
			return findEndpointsRandom(startX, startY, maxSize);
		}
	}

	private static double[] findEndpointsLine(double startX, double startY, double maxSize,
												boolean flipX, boolean flipY) {
		double endX = random.nextDouble(Math.sqrt(maxSize));
		double endY = random.nextDouble(Math.sqrt(maxSize));

		endX = moveXCoordinateIntoBounds(transformCoordinate(endX, flipX) + startX);
		endY = moveYCoordinateIntoBounds(transformCoordinate(endY, flipY) + startY);

		if (validateEndpoints(startX, startY, endX, endY, maxSize)) {
			return new double[]{endX, endY};
		} else {
			return findEndpointsRandom(startX, startY, maxSize);
		}
	}

	private static double transformCoordinate(double inCoord, boolean isFlipping) {
		if (isFlipping) {
			return -inCoord;
		}
		return inCoord;
	}

	private static double moveXCoordinateIntoBounds(double inCoord) {
		if (inCoord > width) {
			inCoord = width - 1;
		}
		return inCoord;
	}

	private static double moveYCoordinateIntoBounds(double inCoord) {
		if (inCoord > height) {
			inCoord = height - 1;
		}
		return inCoord;
	}

	private static boolean validateEndpoints(double startX, double startY,
											 double endX, double endY, double maxSize) {

		if (Math.sqrt(Math.pow((endX - startX), 2) + Math.pow((endY - startY), 2)) > (maxSize * maxSize)) { // length
			System.out.println("WrongLen");
			return false;
		}
		if (endX < 0 || endX > width) { // in bounds
			System.out.println("X OOB");
			return false;
		}
		if (endY < 0 || endY > height) { //not simplified for readability
			System.out.println("Y OOB");
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
