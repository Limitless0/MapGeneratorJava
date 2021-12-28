package dev.iskander.pathToImageDrawifier;

import dev.iskander.canvasDrawifier.Biomes;
import dev.iskander.canvasDrawifier.CanvasDrawifier;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.Random;

public class PathToImageDrawifier implements CanvasDrawifier {

	private final Random random = new Random();
	private double width;
	private double height;
	private GraphicsContext gc;


	public PathToImageDrawifier() {}

	@Override
	public void initialise(GraphicsContext gc) {
		this.gc = gc;
		width = gc.getCanvas().getWidth();
		height = gc.getCanvas().getHeight();

	}
	@Override
	public void drawFlatLayer(Color colour) {
		Path path = new Path();
		setUpPath(path, colour);
		SnapshotParameters sp = new SnapshotParameters();
		sp.setFill(colour);
		path.getElements().addAll(new MoveTo(0, 0),
				new VLineTo(height), new HLineTo(width), new VLineTo(0), new HLineTo(0));
		storePathAndSnapshot(path, sp);
	}

	@Override
	public void drawDeliberateLandLayer(Color colour, double startX, double startY, double maxSize) {
		Path path = new Path();
		setUpPath(path, colour);
		path.getElements().add(new MoveTo(startX, startY));
		drawLandBiome(startX, startY, maxSize, path);
		SnapshotParameters sp = new SnapshotParameters();
		sp.setFill(Color.TRANSPARENT);
		storePathAndSnapshot(path, sp);
	}

	@Override
	public void drawLineLandLayer(Biomes biome, double x, double y, double maxLength) {
		Path path = new Path();
		setUpPath(path, biome.COLOUR);
		path.getElements().add(new MoveTo(x, y));
		double length = 0;
		boolean flipX = (x > width/2);
		boolean flipY = (y > height/2);

		while (length < maxLength) {
			drawLandBiome(biome, x, y, path);
			double[] array = lineSegmentCreatorLine(x, y, length, biome.MAX, flipX, flipY);
			x = array[0];
			y = array[1];
			length = array[2];
		}
		SnapshotParameters sp = new SnapshotParameters();
		sp.setFill(Color.TRANSPARENT);
		storePathAndSnapshot(path, sp);
	}

	private void drawLandBiome(Biomes biomes, double startX, double startY, Path path) {
		drawLandBiome(startX, startY, biomes.MAX, path);
	}

	private void drawLandBiome(double startX, double startY, double maxSize, Path path) {
		//gc.moveTo(startX, startY);
		path.getElements().add(new MoveTo(startX, startY));
		for (int ii = 0; ii < 5_000; ii++) {
			drawWonkyLine(startX, startY, maxSize, path);
		}
	}

	private void drawWonkyLine(double startX, double startY,
									  double maxSize, Path path) {
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

	private double[] lineSegmentCreatorRandom(double startX, double startY,
													 double length, double maxSize) {
		double[] endpoints = findEndpointsRandom(startX, startY, maxSize);
		double outLen = length + (Math.sqrt(Math.pow(endpoints[0] - startX, 2) + Math.pow(endpoints[1] - startY, 2)));
		return new double[] {endpoints[0], endpoints[1], outLen};
	}

	private double[] lineSegmentCreatorLine(double startX, double startY, double length,
												   double maxSize, boolean flipX, boolean flipY) {
		double[] endpoints = findEndpointsLine(startX, startY, maxSize, flipX, flipY);
		double outLen = length + (Math.sqrt(Math.pow(endpoints[0] - startX, 2) + Math.pow(endpoints[1] - startY, 2)));
		return new double[] {endpoints[0], endpoints[1], outLen};
	}

	private double[] findEndpointsRandom(double startX, double startY, double maxSize) {
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

	private double[] findEndpointsLine(double startX, double startY, double maxSize,
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

	private double transformCoordinate(double inCoord, boolean isFlipping) {
		if (isFlipping) {
			return -inCoord;
		}
		return inCoord;
	}

	private double moveXCoordinateIntoBounds(double inCoord) {
		if (inCoord > width) {
			inCoord = width;
		}
		if (inCoord < 0) {
			inCoord = 0;
		}
		return inCoord;
	}

	private double moveYCoordinateIntoBounds(double inCoord) {
		if (inCoord > height) {
			inCoord = height;
		}
		if (inCoord < 0) {
			inCoord = 0;
		}
		return inCoord;
	}

	private boolean validateEndpoints(double startX, double startY,
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

	private void setUpPath(Path path, Color colour) {
		path.getElements().removeAll(path.getElements());
		path.minHeight(height);
		path.minWidth(width);
		path.maxHeight(height);
		path.maxWidth(width);
		path.setStroke(colour);
		path.setFill(colour);
	}

	private void storePathAndSnapshot(Path path, SnapshotParameters sp) {
		sp.setViewport(new Rectangle2D(0, 0, width, height));
		pathList.add(path);
		sps.add(sp);

	}
}
