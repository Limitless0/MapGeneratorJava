package dev.iskander.mgj;

import javafx.scene.paint.Color;

public enum Biomes {
	GRASSLAND(Color.LIGHTGREEN, 100),
	DENSE_WOODLAND(Color.DARKGREEN, 10),
	MOUNTAIN(Color.LIGHTGRAY, 10),
	SPARSE_WOODLAND(Color.YELLOWGREEN, 10)
	;

	final Color COLOUR;
	final double MAX;

	Biomes(Color color, double maxSize) {
		this.COLOUR = color;
		this.MAX = maxSize;
	}

}
