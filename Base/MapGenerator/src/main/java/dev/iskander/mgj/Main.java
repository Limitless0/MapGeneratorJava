package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;

public class Main {
	public static void createMap(final GraphicsContext gc) {
		Thread thread = new Thread(() -> func(gc));
		thread.start();
	}

	private static void func(GraphicsContext gc) {
		BiomePlacementManager.initialise(gc);
		BiomePlacementManager.ellaMethod();
	}
}
