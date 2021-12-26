package dev.iskander.mgj;

import javafx.scene.paint.Color;

public enum Biomes {
	GRASSLAND(Color.LIGHTGREEN, 200),
	DENSE_WOODLAND(Color.DARKGREEN, 100),
	SPARSE_WOODLAND(Color.YELLOWGREEN, 40),
	MOUNTAIN(Color.LIGHTGRAY, 8),
	WATER(Color.BLUE, 1)
	;

	final Color COLOUR;
	final double MAX;

	Biomes(Color color, double maxSize) {
		this.COLOUR = color;
		this.MAX = maxSize;
	}

}
