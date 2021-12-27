package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class BiomePlacementManager {

	private static GraphicsContext gc;
	private static final Random random = new Random();
	private static double mountainX;
	private static double mountainY;
	private static double width;
	private static double height;

	public static void initialise(GraphicsContext graphicsContext) {
		gc = graphicsContext;
		width = gc.getCanvas().getWidth();
		height = gc.getCanvas().getHeight();
	}

	public static void placeBackgroundLayer(Biomes biome) {
		CanvasDrawifier.drawFlatLayer(gc, biome);
	}

	public static void ellaMethod() {
		setMountainLocation();
		CanvasDrawifier.drawDeliberateLandLayer(gc, Biomes.GRASSLAND.COLOUR, mountainX, mountainY, 200);
		CanvasDrawifier.drawDeliberateLandLayer(gc, Biomes.DENSE_WOODLAND.COLOUR, mountainX, mountainY, 100);
		CanvasDrawifier.drawDeliberateLandLayer(gc, Biomes.SPARSE_WOODLAND.COLOUR, mountainX, mountainY, 40);
		CanvasDrawifier.drawDeliberateLandLayer(gc, Biomes.MOUNTAIN.COLOUR, mountainX, mountainY, 8);
	}

	public static void lineMethod() {
		setMountainLocation();
		CanvasDrawifier.drawLineLandLayer(gc, Biomes.GRASSLAND, mountainX, mountainY, 100);
		CanvasDrawifier.drawLineLandLayer(gc, Biomes.DENSE_WOODLAND, mountainX, mountainY, 100);
		CanvasDrawifier.drawLineLandLayer(gc, Biomes.SPARSE_WOODLAND, mountainX, mountainY, 75);
		CanvasDrawifier.drawLineLandLayer(gc, Biomes.MOUNTAIN, mountainX, mountainY, 50);
	}

	private static void setMountainLocation () {
		mountainX = random.nextDouble(width);
		mountainY = random.nextDouble(height);
	}

}
