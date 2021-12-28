package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomePlacementManager {

	private static GraphicsContext gc;
	private static final Random random = new Random();
	private static List<Double> mountainX = new ArrayList<>();
	private static List<Double> mountainY = new ArrayList<>();
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
		MapGenerator.canvasDrawifier.drawFlatLayer(biome.COLOUR);
	}

	public static void placeLineLayer(Biomes biome, double maxLength, double... coordsOrLoopNumber) {
		int loopNumber = validateVarargs(coordsOrLoopNumber);
		System.out.println("Line Layer");
		drawLineLayer(biome, loopNumber, maxLength);
	}


	public static void placeBlobLayer(Biomes biome, double maxSize, double... coordsOrLoopNumber) {
		int loopNumber = validateVarargs(coordsOrLoopNumber);
		System.out.println("Blob Layer");
		drawRegularLayer(biome, loopNumber, maxSize);
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
		MapGenerator.canvasDrawifier.drawLineLandLayer(biome.COLOUR, biome.MAX, mountainX.get(loopNumber), mountainY.get(loopNumber), maxLength);
	}

	private static void drawRegularLayer(Biomes biome, int loopNumber, double maxSize) {
		MapGenerator.canvasDrawifier.drawDeliberateLandLayer(biome.COLOUR, mountainX.get(loopNumber), mountainY.get(loopNumber), maxSize);
	}
}
