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
		mountainX = random.nextDouble(width);
		mountainY = random.nextDouble(height);
	}

	public static void placeBackgroundLayer(Biomes biome) {
		CanvasDrawifier.drawFlatLayer(gc, biome);
	}

	public static void ellaMethod() {
		placeBackgroundLayer(Biomes.WATER);
		CanvasDrawifier.drawDeliberateLandLayer(gc, Biomes.GRASSLAND.COLOUR, mountainX, mountainY, 1000);
		CanvasDrawifier.drawDeliberateLandLayer(gc, Biomes.DENSE_WOODLAND.COLOUR, mountainX, mountainY, 500);
		CanvasDrawifier.drawDeliberateLandLayer(gc, Biomes.SPARSE_WOODLAND.COLOUR, mountainX, mountainY, 200);
		CanvasDrawifier.drawDeliberateLandLayer(gc, Biomes.MOUNTAIN.COLOUR, mountainX, mountainY, 40);
	}

	public static void lineMethod() {
		placeBackgroundLayer(Biomes.WATER);
		CanvasDrawifier.drawLineLandLayer(gc, Biomes.GRASSLAND, mountainX, mountainY, 100);
		CanvasDrawifier.drawLineLandLayer(gc, Biomes.DENSE_WOODLAND, mountainX, mountainY, 100);
		CanvasDrawifier.drawLineLandLayer(gc, Biomes.SPARSE_WOODLAND, mountainX, mountainY, 75);
		CanvasDrawifier.drawLineLandLayer(gc, Biomes.MOUNTAIN, mountainX, mountainY, 50);
		CanvasDrawifier.drawLineLandLayer(gc, Biomes.WATER, mountainX, mountainY, 110);
	}


}
