package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.Random;


public final class CanvasDrawifier {

	private static final Random random = new Random();
	private static double width;
	private static double height;
	public static Path path;

	private CanvasDrawifier() {} //this class is a static utility class, instances should not be permitted



	public static void drawFlatLayer(GraphicsContext gc, Biomes biome) {
		setUpPath(biome.COLOUR);
		//gc.fillRect(0,0, width, height);
		path.getElements().addAll(new MoveTo(0, 0),
				new VLineTo(height), new HLineTo(width), new VLineTo(0), new HLineTo(0));
	}

	public static void drawDeliberateLandLayer(GraphicsContext gc, Color colour,
											   double startX, double startY, double maxSize) {
		setUpPath(colour);
		drawLandBiome(startX, startY, maxSize);
	}

	public static void drawLineLandLayer(GraphicsContext gc, Biomes biome, double x, double y, double maxLength) {
		setUpPath(biome.COLOUR);
		double length = 0;
		boolean flipX = (x > width/2);
		boolean flipY = (y > height/2);

		while (length < maxLength) {
			drawLandBiome(biome, x, y);
			double[] array = lineSegmentCreatorLine(x, y, length, biome.MAX, flipX, flipY);
			x = array[0];
			y = array[1];
			length = array[2];
		}
	}

	private static void drawLandBiome(Biomes biomes, double startX, double startY) {
		drawLandBiome(startX, startY, biomes.MAX);
	}

	private static void drawLandBiome(double startX, double startY, double maxSize) {
		//gc.moveTo(startX, startY);
		path.getElements().add(new MoveTo(startX, startY));
		for (int ii = 0; ii < 5_000; ii++) {
			drawWonkyLine(startX, startY, maxSize);
		}
	}

	private static void drawWonkyLine(double startX, double startY,
									  double maxSize) {
		double length = 0;
		while (length < maxSize) {
			double[] array = lineSegmentCreatorRandom(startX, startY, length, maxSize);
			startX = array[0];
			startY = array[1];
			length = array[2];
			path.getElements().add(new LineTo(startX, startY));
			//gc.lineTo(startX, startY);
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

		endX = moveXCoordinateIntoBounds(transformCoordinate(endX, random.nextBoolean()) + startX);
		endY = moveYCoordinateIntoBounds(transformCoordinate(endY, random.nextBoolean()) + startY);

		if (validateEndpoints(startX, startY, endX, endY, maxSize)) {
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
			inCoord = width;
		}
		if (inCoord < 0) {
			inCoord = 0;
		}
		return inCoord;
	}

	private static double moveYCoordinateIntoBounds(double inCoord) {
		if (inCoord > height) {
			inCoord = height;
		}
		if (inCoord < 0) {
			inCoord = 0;
		}
		return inCoord;
	}

	private static boolean validateEndpoints(double startX, double startY,
											 double endX, double endY, double maxSize) {

		if (Math.sqrt(Math.pow((endX - startX), 2) + Math.pow((endY - startY), 2)) >= (maxSize)) { // length
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

	private static void setUpPath(Color colour) {
		path = new Path();
		path.minHeight(height);
		path.minWidth(width);
		path.maxHeight(height);
		path.maxWidth(width);

		path.setStroke(colour);
		path.setFill(colour);
	}

	public static void initialise(GraphicsContext gc) {
		width = gc.getCanvas().getWidth();
		height = gc.getCanvas().getHeight();
	}
}
