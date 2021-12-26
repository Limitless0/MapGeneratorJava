package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;

public class Main {
	public static void createMap(final GraphicsContext gc) {
		BiomePlacementManager.initialise(gc);
		BiomePlacementManager.lineMethod();
	}
}
