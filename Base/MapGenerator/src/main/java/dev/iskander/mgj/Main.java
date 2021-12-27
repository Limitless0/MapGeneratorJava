package dev.iskander.mgj;

import javafx.scene.canvas.GraphicsContext;

public class Main {

	static int totalStartLocations = 4;
	static int blobLocations = 3;
	static int lineLocations = 1;
	static double size = 300;
	static double length = 100;

	public static void createMap(GraphicsContext gc) {
		BiomePlacementManager.initialise(gc);
		BiomePlacementManager.placeBackgroundLayer(Biomes.WATER);
		placeGrasslandBlobs(blobLocations, size);
		placeGrasslandLines(lineLocations, length);
		for (int ii = 0; ii < blobLocations; ii++) {
			placeDenseWoodBlobs(size * 0.75, ii);
		}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			placeDenseWoodLines(length, ii);
		}
		for (int ii = 0; ii < blobLocations; ii++) {
			placeSparseWoodBlobs(size * 0.20, ii);
		}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			placeSparseWoodLines(length, ii);
		}
		for (int ii = 0; ii < blobLocations; ii++) {
			placeMountainBlobs(size * 0.05, ii);
		}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			placeMountainLines(length, ii);
		}
	}

	private static void placeGrasslandBlobs(int count, double size) {
		for (int ii = 0; ii < count; ii++) {
			BiomePlacementManager.placeBlobLayer(Biomes.GRASSLAND, size);
		}
	}

	private static void placeGrasslandLines(int count, double length) {
		for (int ii = 0; ii < count; ii++) {
			BiomePlacementManager.placeLineLayer(Biomes.GRASSLAND, length);
		}
	}

	private static void placeDenseWoodBlobs(double size, int locationNumber) {
		BiomePlacementManager.placeBlobLayer(Biomes.DENSE_WOODLAND, size, locationNumber);
	}

	private static void placeDenseWoodLines(double length, int locationNumber) {
		BiomePlacementManager.placeLineLayer(Biomes.DENSE_WOODLAND, length, locationNumber);
	}

	private static void placeSparseWoodBlobs(double size, int locationNumber) {
		BiomePlacementManager.placeBlobLayer(Biomes.SPARSE_WOODLAND, size, locationNumber);
	}

	private static void placeSparseWoodLines(double length, int locationNumber) {
		BiomePlacementManager.placeLineLayer(Biomes.SPARSE_WOODLAND, length, locationNumber);
	}

	private static void placeMountainBlobs(double size, int locationNumber) {
		BiomePlacementManager.placeBlobLayer(Biomes.MOUNTAIN, size, locationNumber);
	}

	private static void placeMountainLines(double length, int locationNumber) {
		BiomePlacementManager.placeLineLayer(Biomes.MOUNTAIN, length, locationNumber);
	}
}