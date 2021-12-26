package dev.iskander.mgj;

import javafx.scene.paint.Color;

public enum Biomes {
	SPARSE_WOODLAND(Color.YELLOWGREEN, 10),
	GRASSLAND(Color.LIGHTGREEN, 100),
	DENSE_WOODLAND(Color.DARKGREEN, 100),
	MOUNTAIN(Color.LIGHTGRAY, 10),
	WATER(Color.BLUE, 1)
	;

	final Color COLOUR;
	final double MAX;

	Biomes(Color color, double maxSize) {
		this.COLOUR = color;
		this.MAX = maxSize;
	}

}
