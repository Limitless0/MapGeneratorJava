package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;

public class Main {
	public static void createMap(GraphicsContext gc) {
		CanvasDrawifier.drawFlatLayer(gc, Biomes.SPARSE_WOODLAND);
		CanvasDrawifier.drawDynamicLandLayer(gc, Biomes.GRASSLAND, 10);
		CanvasDrawifier.drawDynamicLandLayer(gc, Biomes.DENSE_WOODLAND, 10);
		CanvasDrawifier.drawLineLandLayer(gc, Biomes.MOUNTAIN);
	}
}
