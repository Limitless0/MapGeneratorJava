package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Random;

public class BiomePlacementManager {

	private static GraphicsContext gc;
	private static final Random random = new Random();
	private static ArrayList<Double> mountainX = new ArrayList<>();
	private static ArrayList<Double> mountainY = new ArrayList<>();
	private static double width;
	private static double height;

	public static int getLoops() {
		return loops;
	}

	private static int loops = 0;

	public static void initialise(GraphicsContext graphicsContext) {
		gc = graphicsContext;
		width = gc.getCanvas().getWidth();
		height = gc.getCanvas().getHeight();
	}

	public static void placeBackgroundLayer(Biomes biome) {
		CanvasDrawifier.drawFlatLayer(gc, biome);
		System.out.println("background");
	}

	public static void placeLineLayer(Biomes biome, double maxLength, double... coordsOrLoopNumber) {
		int loopNumber = validateVarargs(coordsOrLoopNumber);
		drawLineLayer(biome, loopNumber, maxLength);
		System.out.println("Line Layer");
	}


	public static void placeBlobLayer(Biomes biome, double maxSize, double... coordsOrLoopNumber) {
		int loopNumber = validateVarargs(coordsOrLoopNumber);
		drawRegularLayer(biome, loopNumber, maxSize);
		System.out.println("Blob Layer");
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
