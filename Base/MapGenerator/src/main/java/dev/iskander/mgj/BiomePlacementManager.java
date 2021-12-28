package dev.iskander.mgj;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class BiomePlacementManager {

	private static GraphicsContext gc;
	private static final Random random = new Random();
	private static ArrayList<Double> mountainX = new ArrayList<>();
	private static ArrayList<Double> mountainY = new ArrayList<>();
	private static double width;
	private static double height;
	public static WritableImage writableImage;

	public static int getLoops() {
		return loops;
	}

	private static int loops = 0;

	public static void initialise(GraphicsContext graphicsContext) {
		gc = graphicsContext;
		width = gc.getCanvas().getWidth();
		height = gc.getCanvas().getHeight();
		writableImage = new WritableImage((int) width, (int) height);
	}

	public static void placeBackgroundLayer(Biomes biome) {
		System.out.println("background");
		CanvasDrawifier.drawFlatLayer(gc, biome);
		SnapshotParameters sp = new SnapshotParameters();
		sp.setFill(biome.COLOUR);
		savePath(sp);
	}

	public static void placeLineLayer(Biomes biome, double maxLength, double... coordsOrLoopNumber) {
		int loopNumber = validateVarargs(coordsOrLoopNumber);
		System.out.println("Line Layer");
		drawLineLayer(biome, loopNumber, maxLength);
		SnapshotParameters sp = new SnapshotParameters();
		sp.setFill(Color.TRANSPARENT);
		savePath(sp);
	}


	public static void placeBlobLayer(Biomes biome, double maxSize, double... coordsOrLoopNumber) {
		int loopNumber = validateVarargs(coordsOrLoopNumber);
		System.out.println("Blob Layer");
		drawRegularLayer(biome, loopNumber, maxSize);
		SnapshotParameters sp = new SnapshotParameters();
		sp.setFill(Color.TRANSPARENT);
		savePath(sp);
	}

	private static void savePath(SnapshotParameters sp) {
		sp.setViewport(new Rectangle2D(0, 0, width, height));
		MapCreator.pathList.add(CanvasDrawifier.path);
		MapCreator.sps.add(sp);
		//writableImage = CanvasDrawifier.path.snapshot(sp, new WritableImage((int) width, (int) height));
		//MapCreator.imageList.add(writableImage);
		//gc.drawImage(BiomePlacementManager.writableImage, 0, 0);
	}

	private static int validateVarargs(double... doubles) {
		int loopNumber;
		try {
			loopNumber = setMountainLocation(doubles[0], doubles[1]);
		} catch (Exception ignored) { //easy vararg validation lmao
			try {
				loopNumber = (int) Math.floor(doubles[0]);
				setMountainLocation(mountainX.get(loopNumber), mountainY.get(loopNumber));
			} catch (Exception alsoIgnored) {
				loopNumber = setMountainLocation();
			}
		}
		return loopNumber;
	}

	private static int setMountainLocation(double x, double y) {
		mountainX.add(x);
		mountainY.add(y);
		loops++;
		return loops - 1;
	}

	private static int setMountainLocation() {
		mountainX.add(random.nextDouble(width));
		mountainY.add(random.nextDouble(height));
		loops++;
		return loops - 1;
	}

	private static void drawLineLayer(Biomes biome, int loopNumber, double maxLength) {
		CanvasDrawifier.drawLineLandLayer(gc, biome, mountainX.get(loopNumber), mountainY.get(loopNumber), maxLength);
	}

	private static void drawRegularLayer(Biomes biome, int loopNumber, double maxSize) {
		CanvasDrawifier.drawDeliberateLandLayer(gc, biome.COLOUR, mountainX.get(loopNumber), mountainY.get(loopNumber), maxSize);
	}
}
