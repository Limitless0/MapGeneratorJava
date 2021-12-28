package dev.iskander.mgj;

import dev.iskander.canvasDrawifier.Biomes;
import javafx.concurrent.Task;

public class MapCreator extends Task<Void> {

	static int blobLocations = 3;
	static int lineLocations = 1;
	static int totalStartLocations = blobLocations + lineLocations;
	static double size = 300;
	static double length = 100;

	@Override
	public Void call() {
		BiomePlacementManager.placeBackgroundLayer(Biomes.WATER);
		for (int ii = 0; ii < blobLocations; ii++) {
			placeGrasslandBlob(size);
			updateProgress(BiomePlacementManager.getLoops(),
					(long) (Biomes.values().length - 1) * totalStartLocations);
		}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			placeGrasslandLine(length);
			updateProgress(BiomePlacementManager.getLoops(),
					(long) (Biomes.values().length - 1) * totalStartLocations);
		}
		for (int ii = 0; ii < blobLocations; ii++) {
			placeDenseWoodBlob(size * 0.75, ii);
			updateProgress(BiomePlacementManager.getLoops(),
					(long) (Biomes.values().length - 1) * totalStartLocations);
		}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			placeDenseWoodLine(length, ii);
			updateProgress(BiomePlacementManager.getLoops(),
					(long) (Biomes.values().length - 1) * totalStartLocations);
		}
		for (int ii = 0; ii < blobLocations; ii++) {
			placeSparseWoodBlob(size * 0.20, ii);
			updateProgress(BiomePlacementManager.getLoops(),
					(long) (Biomes.values().length - 1) * totalStartLocations);
		}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			placeSparseWoodLine(length, ii);
			updateProgress(BiomePlacementManager.getLoops(),
					(long) (Biomes.values().length - 1) * totalStartLocations);
		}
		for (int ii = 0; ii < blobLocations; ii++) {
			placeMountainBlob(size * 0.05, ii);
			updateProgress(BiomePlacementManager.getLoops(),
					(long) (Biomes.values().length - 1) * totalStartLocations);
		}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			placeMountainLine(length, ii);
			updateProgress(BiomePlacementManager.getLoops(),
					(long) (Biomes.values().length - 1) * totalStartLocations);
		}
		System.out.println("Done!");
		return null;
	}

	private void placeGrasslandBlob(double size) {
		BiomePlacementManager.placeBlobLayer(Biomes.GRASSLAND, size);
	}

	private void placeGrasslandLine(double length) {
		BiomePlacementManager.placeLineLayer(Biomes.GRASSLAND, length);
	}

	private void placeDenseWoodBlob(double size, int locationNumber) {
		BiomePlacementManager.placeBlobLayer(Biomes.DENSE_WOODLAND, size, locationNumber);
	}

	private void placeDenseWoodLine(double length, int locationNumber) {
		BiomePlacementManager.placeLineLayer(Biomes.DENSE_WOODLAND, length, locationNumber);
	}

	private void placeSparseWoodBlob(double size, int locationNumber) {
		BiomePlacementManager.placeBlobLayer(Biomes.SPARSE_WOODLAND, size, locationNumber);
	}

	private void placeSparseWoodLine(double length, int locationNumber) {
		BiomePlacementManager.placeLineLayer(Biomes.SPARSE_WOODLAND, length, locationNumber);
	}

	private void placeMountainBlob(double size, int locationNumber) {
		BiomePlacementManager.placeBlobLayer(Biomes.MOUNTAIN, size, locationNumber);
	}

	private void placeMountainLine(double length, int locationNumber) {
		BiomePlacementManager.placeLineLayer(Biomes.MOUNTAIN, length, locationNumber);
	}
}