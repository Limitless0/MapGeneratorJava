package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;

public class Main {
	public static void createMap(GraphicsContext gc) {
		CanvasDrawifier.drawFlatLayer(gc, Biomes.SPARSE_WOODLAND);
		CanvasDrawifier.drawLineLandLayerRandom(gc, Biomes.DENSE_WOODLAND);
		CanvasDrawifier.drawLineLandLayerRandom(gc, Biomes.DENSE_WOODLAND);
		CanvasDrawifier.drawLineLandLayerRandom(gc, Biomes.DENSE_WOODLAND);
		CanvasDrawifier.drawRandomLandLayer(gc, Biomes.GRASSLAND, 3);
		CanvasDrawifier.drawLineLandLayerRandom(gc, Biomes.MOUNTAIN);
	}
}
