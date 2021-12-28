package dev.iskander.canvasDrawifier;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

import java.util.ArrayList;
import java.util.List;

public interface CanvasDrawifier {

	List<Path> pathList = new ArrayList<>();
	List<SnapshotParameters> sps = new ArrayList<>();
	Path path = new Path();

	void initialise(GraphicsContext gc);
	void drawFlatLayer(Color color);

	void drawDeliberateLandLayer(Color colour, double startX, double startY, double maxSize);

	void drawLineLandLayer(Biomes biome, double x, double y, double maxLength);
}