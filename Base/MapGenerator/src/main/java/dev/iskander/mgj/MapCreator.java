package dev.iskander.mgj;

import javafx.scene.SnapshotParameters;
import javafx.scene.shape.Path;

import java.util.ArrayList;
import java.util.List;

public class MapCreator implements Runnable {

	static int blobLocations = 3;
	static int lineLocations = 1;
	static int totalStartLocations = blobLocations + lineLocations;
	static double size = 300;
	static double length = 100;
	public static List<Path> pathList = new ArrayList<>();
	public static List<SnapshotParameters> sps = new ArrayList<>(); //each path has a corresponding SP

	@Override
	public void run() {
		BiomePlacementManager.placeBackgroundLayer(Biomes.WATER);
		for (int ii = 0; ii < blobLocations; ii++) {
			placeGrasslandBlob(size);
		}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			placeGrasslandLine(length);
		}
		for (int ii = 0; ii < blobLocations; ii++) {
			placeDenseWoodBlob(size * 0.75, ii);
		}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			placeDenseWoodLine(length, ii);
		}
		for (int ii = 0; ii < blobLocations; ii++) {
			placeSparseWoodBlob(size * 0.20, ii);
		}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			placeSparseWoodLine(length, ii);
		}
		for (int ii = 0; ii < blobLocations; ii++) {
			placeMountainBlob(size * 0.05, ii);
		}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			placeMountainLine(length, ii);
		}
		System.out.println("Done!");
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