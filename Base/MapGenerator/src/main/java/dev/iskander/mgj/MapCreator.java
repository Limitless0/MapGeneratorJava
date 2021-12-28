package dev.iskander.mgj;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MapCreator extends Task<Void> {

	static int blobLocations = 2;
	static int lineLocations = 1;
	static int totalStartLocations = blobLocations + lineLocations;
	static double size = 300;
	static double length = 100;

	@Override
	public Void call() {
		BiomePlacementManager.placeBackgroundLayer(Biomes.WATER);
		ExecutorService executorService = Executors.newFixedThreadPool(totalStartLocations);
		List<Future<Void>> futures = new ArrayList<>();
		for (int ii = 0; ii < blobLocations; ii++) {
			int jj = ii;
			futures.add(executorService.submit(() -> {
				placeGrasslandBlob(size, jj);
				updateProgress(BiomePlacementManager.getLoops(),
					(long) (Biomes.values().length - 1) * totalStartLocations);
				return null;
			}));
		}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			int jj = ii;
			futures.add(executorService.submit(() -> {
				placeGrasslandLine(length, jj);
				updateProgress(BiomePlacementManager.getLoops(),
					(long) (Biomes.values().length - 1) * totalStartLocations);
				return null;
			}));
		}
		waitForCompletion(futures);
		System.out.println(MapGenerator.canvasDrawifier.pathList.size());
		futures.clear();

		for (int ii = 0; ii < blobLocations; ii++) {
			int jj = ii; // effectively final
			futures.add(executorService.submit(() -> {
				placeDenseWoodBlob(size * 0.7, jj);
				updateProgress(BiomePlacementManager.getLoops(),
						(long) (Biomes.values().length - 1) * totalStartLocations);
				return null;
			}));}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			int jj = ii; // effectively final
			futures.add(executorService.submit(() -> {
				placeDenseWoodLine(length, jj);
				updateProgress(BiomePlacementManager.getLoops(),
						(long) (Biomes.values().length - 1) * totalStartLocations);
				return null;
			}));}

		waitForCompletion(futures);
		System.out.println(MapGenerator.canvasDrawifier.pathList.size());
		futures.clear();

		for (int ii = 0; ii < blobLocations; ii++) {
			int jj = ii; // effectively final
			futures.add(executorService.submit(() -> {
				placeSparseWoodBlob(size * 0.4, jj);
				updateProgress(BiomePlacementManager.getLoops(),
						(long) (Biomes.values().length - 1) * totalStartLocations);
				return null;
			}));}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			int jj = ii; // effectively final
			futures.add(executorService.submit(() -> {
				placeSparseWoodLine(length, jj);
				updateProgress(BiomePlacementManager.getLoops(),
						(long) (Biomes.values().length - 1) * totalStartLocations);
				return null;
			}));}

		waitForCompletion(futures);
		System.out.println(MapGenerator.canvasDrawifier.pathList.size());
		futures.clear();

		for (int ii = 0; ii < blobLocations; ii++) {
			int jj = ii; // effectively final
			futures.add(executorService.submit(() -> {
				placeMountainBlob(size * 0.1, jj);
				updateProgress(BiomePlacementManager.getLoops(),
						(long) (Biomes.values().length - 1) * totalStartLocations);
				return null;
			}));}
		for (int ii = blobLocations; ii < totalStartLocations; ii++) {
			int jj = ii; // effectively final
			futures.add(executorService.submit(() -> {
				placeMountainLine(length, jj);
				return null;
			}));}

		waitForCompletion(futures);
		System.out.println(MapGenerator.canvasDrawifier.pathList.size());

		updateProgress(BiomePlacementManager.getLoops(),
					(long) (Biomes.values().length - 1) * totalStartLocations);
		System.out.println("Done!");
		executorService.shutdown();
		return null;
	}

	private void waitForCompletion(List<Future<Void>> futures) {
		for (Future<Void> future : futures) {
			try {
				future.get();
			} catch (InterruptedException | ExecutionException ignored) {
			}
		}
	}

	private void placeGrasslandBlob(double size, double locationNumber) {
		BiomePlacementManager.placeBlobLayer(Biomes.GRASSLAND, size, locationNumber);
	}

	private void placeGrasslandLine(double length, double locationNumber) {
		BiomePlacementManager.placeLineLayer(Biomes.GRASSLAND, length, locationNumber);
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